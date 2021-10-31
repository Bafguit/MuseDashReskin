package MuseDashReskin.patches;

import MuseDashReskin.ReMuseMod;
import MuseDashReskin.elfins.ElfinInfo;
import MuseDashReskin.elfins.data.ElfinData;
import MuseDashReskin.Skins.SkinInfo;
import MuseDashReskin.Skins.data.SkinData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Random;

import static MuseDashReskin.ReMuseMod.*;
import static MuseDashReskin.patches.MainScreenAnimation.*;
import static MuseDashReskin.spine.AnimationState.*;
import static com.megacrit.cardcrawl.cards.AbstractCard.*;
import static com.megacrit.cardcrawl.cards.DamageInfo.*;

public abstract class MusePlayer extends AbstractPlayer implements AnimationState.AnimationStateListener {

    public SkinData skinData;
    public ElfinData elfinData;
    public String charName;
    private boolean isGiveUp = true;

    public MusePlayer(String name, PlayerClass setClass) {
        super(CardCrawlGame.playerName, setClass);
        this.charName = name;
        this.skinData = skinInfo.getSkin(chosenClass);
        this.elfinData = elfinInfo.getElfin();
        this.drawX += 5.0F * Settings.scale;
        this.drawY += 7.0F * Settings.scale;
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 170.0F * Settings.scale;
        this.initializeClass((String)null, "images/characters/" + this.skinData.name + "/shoulder2.png",
                "images/characters/" + this.skinData.name + "/shoulder.png",
                "images/characters/" + this.skinData.name + "/corpse.png", this.getLoadout(),
                0.0F, -10.0F, 240.0F, 244.0F, new EnergyManager(3));
        this.reloadAnimations();
    }

    public void reloadAnimations() {
        this.loadAnimation(this.skinData.atlasUrl,
                this.skinData.jsonUrl, 1.5F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, this.skinData.name == "reimu" ? "Run" : "Standby", true);
        this.state.addListener(this);
        e.setTimeScale(1.0f);
        this.elfinData.loadAnimation();
    }

    public final CharacterStrings getCharacterString() {
        CharSelectInfo loadout = this.getLoadout();
        CharacterStrings characterStrings = new CharacterStrings();
        characterStrings.NAMES = new String[]{loadout.name};
        characterStrings.TEXT = new String[]{loadout.flavorText};
        return characterStrings;
    }

    public void renderPlayerImage(SpriteBatch sb) {
        if(isElfin) {
            //Empty
        } else {
            this.elfinData.render(sb, this);
        }
        super.renderPlayerImage(sb);
    }

    protected void playSelectSound(String key) {
        ReMuseMod.selectId = ReMuseMod.museSound.stopAndPlay(key);
        selectKey = key;
    }

    public void adjustIndex(int i) {
        int temp = skinInfo.index.get(chosenClass);
        skinInfo.index.replace(chosenClass, Math.min(Math.max(temp + i, 0), skinInfo.skinData.get(chosenClass).size() - 1));
        if(temp != skinInfo.index.get(chosenClass)) {
            this.skinData = skinInfo.getSkin(chosenClass);
            try {
                SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
                config.setInt(skinInfo.saveData.get(chosenClass), skinInfo.index.get(chosenClass));
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.playMainVoice();
        }
    }

    public void playMainVoice() {
        if(isVoice) {
            System.out.println("Nope");
        } else {
            this.playSelectSound("SELECT_" + this.skinData.name.toUpperCase());
        }
    }

    public void resetAnimation() {
        this.skeleton.setToSetupPose();
        this.state.apply(this.skeleton);
        System.out.println("Reset Animation : " + this.skeleton.getData().getName());
    }

    public void dispose() {
        if (this.atlas != null) {
            this.atlas.dispose();
        }

        if (this.img != null) {
            this.img.dispose();
        }
    }

    public void renderShoulderImg(SpriteBatch sb) { }

    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        if (c.type == CardType.ATTACK) {
            if(c.target == CardTarget.ALL_ENEMY || c.target == CardTarget.ALL) {
                ReMuseMod.setBigHitAnimation(this);
            } else {
                ReMuseMod.setHitAnimation(this);
            }
        }

        c.calculateCardDamage(monster);
        if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
            c.energyOnUse = EnergyPanel.totalCount;
        }

        if (c.cost == -1 && c.isInAutoplay) {
            c.freeToPlayOnce = true;
        }

        c.use(this, monster);
        AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
        if (!c.dontTriggerOnUseCard) {
            this.hand.triggerOnOtherCardPlayed(c);
        }

        this.hand.removeCard(c);
        this.cardInUse = c;
        c.target_x = (float)(Settings.WIDTH / 2);
        c.target_y = (float)(Settings.HEIGHT / 2);
        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (!this.hasPower("Corruption") || c.type != CardType.SKILL)) {
            this.energy.use(c.costForTurn);
        }

        if (!this.hand.canUseAnyCard() && !this.endTurnQueued) {
            AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
        }

    }

    public void damage(DamageInfo info) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && info.owner != null && info.type != DamageType.THORNS && info.output - this.currentBlock > 0) {
            ReMuseMod.setDmgAnimation(this);
            if(!ReMuseMod.isVoice) {
                Random random = new Random();
                int i = random.nextInt(this.skinData.voiceNumber) + 1;
                String key = this.skinData.voiceName + "_HURT_" + i;
                System.out.println("&&&&Play Hurt Sound : " + key);
                ReMuseMod.museSound.play(key);
            }
        }

        super.damage(info);
        if(this.isDead) {
            this.isGiveUp = false;
            ReMuseMod.setDieAnimation(this);
        }
    }

    public void playDeathAnimation() {
        if(this.isGiveUp) {
            ReMuseMod.setDieAnimation(this);
        }
    }

    @Override
    public final void event(int i, Event event) {

    }

    @Override
    public final void complete(int var1, int var2) {

    }

    @Override
    public final void start(int i) {
        if(this.state.getCurrent(i).getAnimation().getName() != "Die") {
            this.resetAnimation();
        }
    }

    @Override
    public final void end(int i) {
        if(this.state.getCurrent(i).getAnimation().getName() != "Die") {
            this.resetAnimation();
        }
    }
}
