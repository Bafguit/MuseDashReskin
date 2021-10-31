//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MuseDashReskin.patches;

import MuseDashReskin.ReMuseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbGreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SilentReSkin extends MusePlayer {
    private static final Logger logger = LogManager.getLogger(SilentReSkin.class.getName());
    private static final CharacterStrings characterStrings;
    public static final String[] NAMES;
    public static final String[] TEXT;
    private static final PlayerClass CLASS = PlayerClass.THE_SILENT;
    private EnergyOrbInterface energyOrb = new EnergyOrbGreen();
    private Prefs prefs;
    private CharStat charStat = new CharStat(this);

    SilentReSkin() {
        super(NAMES[0], CLASS);
        if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") || ModHelper.isModEnabled("Blue Cards"))) {
            this.masterMaxOrbs = 1;
        }

    }

    public String getPortraitImageName() {
        return "silentPortrait.jpg";
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Strike_G");
        retVal.add("Strike_G");
        retVal.add("Strike_G");
        retVal.add("Strike_G");
        retVal.add("Strike_G");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Defend_G");
        retVal.add("Survivor");
        retVal.add("Neutralize");
        return retVal;
    }

    public AbstractCard getStartCardForEvent() {
        return new Neutralize();
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Ring of the Snake");
        UnlockTracker.markRelicAsSeen("Ring of the Snake");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], this.skinData.charText, 70, 70, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    public String getTitle(PlayerClass plyrClass) {
        return NAMES[0];
    }

    public CardColor getCardColor() {
        return CardColor.GREEN;
    }

    public Color getCardRenderColor() {
        return Color.CHARTREUSE;
    }

    public String getAchievementKey() {
        return "EMERALD";
    }

    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        CardLibrary.addGreenCards(tmpPool);
        if (ModHelper.isModEnabled("Red Cards")) {
            CardLibrary.addRedCards(tmpPool);
        }

        if (ModHelper.isModEnabled("Blue Cards")) {
            CardLibrary.addBlueCards(tmpPool);
        }

        if (ModHelper.isModEnabled("Purple Cards")) {
            CardLibrary.addPurpleCards(tmpPool);
        }

        return tmpPool;
    }

    public Color getCardTrailColor() {
        return Color.CHARTREUSE.cpy();
    }

    public String getLeaderboardCharacterName() {
        return "SILENT";
    }

    public Texture getEnergyImage() {
        return ImageMaster.GREEN_ORB_FLASH_VFX;
    }

    public int getAscensionMaxHPLoss() {
        return 4;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
    }

    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    public Prefs getPrefs() {
        if (this.prefs == null) {
            logger.error("prefs need to be initialized first!");
        }

        return this.prefs;
    }

    public void loadPrefs() {
        this.prefs = SaveHelper.getPrefs("STSDataTheSilent");
    }

    public CharStat getCharStat() {
        return this.charStat;
    }

    public int getUnlockedCardCount() {
        return UnlockTracker.unlockedGreenCardCount;
    }

    public int getSeenCardCount() {
        return CardLibrary.seenGreenCards;
    }

    public int getCardCount() {
        return CardLibrary.greenCards;
    }

    public boolean saveFileExists() {
        return SaveAndContinue.saveExistsAndNotCorrupted(this);
    }

    public String getWinStreakKey() {
        return "win_streak_silent";
    }

    public String getLeaderboardWinStreakKey() {
        return "SILENT_CONSECUTIVE_WINS";
    }

    public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
        if (!UnlockTracker.isCharacterLocked("The Silent")) {
            if (CardCrawlGame.mainMenuScreen.statsScreen.silentHb == null) {
                CardCrawlGame.mainMenuScreen.statsScreen.silentHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
            }

            StatsScreen.renderHeader(sb, StatsScreen.NAMES[3], screenX, renderY);
            this.charStat.render(sb, screenX, renderY);
        }

    }

    public void doCharSelectScreenSelectEffect() {
        if(ReMuseMod.isVoice) {
            CardCrawlGame.sound.playA("ATTACK_DAGGER_2", MathUtils.random(-0.2F, 0.2F));
        } else {
            this.playSelectSound("SELECT_" + this.skinData.name.toUpperCase());
        }
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_2";
    }

    public Texture getCustomModeCharacterButtonImage() {
        return ImageMaster.FILTER_SILENT;
    }

    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    public void refreshCharStat() {
        this.charStat = new CharStat(this);
    }

    public AbstractPlayer newInstance() {
        return new SilentReSkin();
    }

    public AtlasRegion getOrb() {
        return AbstractCard.orb_green;
    }

    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[9];
    }

    public Color getSlashAttackColor() {
        return Color.GREEN;
    }

    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AttackEffect[]{AttackEffect.SLASH_HEAVY, AttackEffect.POISON, AttackEffect.SLASH_DIAGONAL, AttackEffect.SLASH_HEAVY, AttackEffect.POISON, AttackEffect.SLASH_DIAGONAL};
    }

    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString(ReMuseMod.makeID("Marija"));
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }
}
