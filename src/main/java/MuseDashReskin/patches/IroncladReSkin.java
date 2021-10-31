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
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.helpers.ScreenShake.*;

public class IroncladReSkin extends MusePlayer {
    private static final Logger logger = LogManager.getLogger(IroncladReSkin.class.getName());
    private static final CharacterStrings characterStrings;
    public static final String[] NAMES;
    public static final String[] TEXT;
    private static final PlayerClass CLASS = PlayerClass.IRONCLAD;
    private EnergyOrbInterface energyOrb = new EnergyOrbRed();
    private Prefs prefs;
    private CharStat charStat = new CharStat(this);

    IroncladReSkin() {
        super(NAMES[0], CLASS);
        if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") || ModHelper.isModEnabled("Blue Cards"))) {
            this.masterMaxOrbs = 1;
        }
    }

    public String getPortraitImageName() {
        return "ironcladPortrait.jpg";
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Burning Blood");
        UnlockTracker.markRelicAsSeen("Burning Blood");
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Strike_R");
        retVal.add("Defend_R");
        retVal.add("Defend_R");
        retVal.add("Defend_R");
        retVal.add("Defend_R");
        retVal.add("Bash");
        return retVal;
    }

    public AbstractCard getStartCardForEvent() {
        return new Bash();
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], this.skinData.charText, 80, 80, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    public String getTitle(PlayerClass plyrClass) {
        return NAMES[0];
    }

    public CardColor getCardColor() {
        return CardColor.RED;
    }

    public Color getCardRenderColor() {
        return Color.SCARLET;
    }

    public String getAchievementKey() {
        return "RUBY";
    }

    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        CardLibrary.addRedCards(tmpPool);
        if (ModHelper.isModEnabled("Green Cards")) {
            CardLibrary.addGreenCards(tmpPool);
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
        return new Color(1.0F, 0.4F, 0.1F, 1.0F);
    }

    public String getLeaderboardCharacterName() {
        return "IRONCLAD";
    }

    public Texture getEnergyImage() {
        return ImageMaster.RED_ORB_FLASH_VFX;
    }

    public int getAscensionMaxHPLoss() {
        return 5;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
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
        this.prefs = SaveHelper.getPrefs("STSDataVagabond");
    }

    public CharStat getCharStat() {
        return this.charStat;
    }

    public int getUnlockedCardCount() {
        return UnlockTracker.unlockedRedCardCount;
    }

    public int getSeenCardCount() {
        return CardLibrary.seenRedCards;
    }

    public int getCardCount() {
        return CardLibrary.redCards;
    }

    public boolean saveFileExists() {
        return SaveAndContinue.saveExistsAndNotCorrupted(this);
    }

    public String getWinStreakKey() {
        return "win_streak_ironclad";
    }

    public String getLeaderboardWinStreakKey() {
        return "IRONCLAD_CONSECUTIVE_WINS";
    }

    public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
        StatsScreen.renderHeader(sb, StatsScreen.NAMES[2], screenX, renderY);
        this.charStat.render(sb, screenX, renderY);
    }

    public void doCharSelectScreenSelectEffect() {
        if(ReMuseMod.isVoice) {
            CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
        } else {
            this.playSelectSound("SELECT_" + this.skinData.name.toUpperCase());
        }
        CardCrawlGame.screenShake.shake(ShakeIntensity.MED, ShakeDur.SHORT, true);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    public Texture getCustomModeCharacterButtonImage() {
        return ImageMaster.FILTER_IRONCLAD;
    }

    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    public void refreshCharStat() {
        this.charStat = new CharStat(this);
    }

    public AbstractPlayer newInstance() {
        return new IroncladReSkin();
    }

    public AtlasRegion getOrb() {
        return AbstractCard.orb_red;
    }

    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    public Color getSlashAttackColor() {
        return Color.RED;
    }

    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AttackEffect[]{AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.BLUNT_HEAVY, AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.BLUNT_HEAVY};
    }

    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString(ReMuseMod.makeID("Rin"));
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }

}
