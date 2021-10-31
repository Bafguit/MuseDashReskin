package MuseDashReskin.patches;

import MuseDashReskin.ReMuseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Defect;
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
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;
import static com.megacrit.cardcrawl.cards.AbstractCard.*;

public class DefectReSkin extends MusePlayer {
    private static final Logger logger = LogManager.getLogger(Defect.class.getName());
    private static final CharacterStrings characterStrings;
    public static final String[] NAMES;
    public static final String[] TEXT;
    private static final PlayerClass CLASS = PlayerClass.DEFECT;
    private EnergyOrbInterface energyOrb = new EnergyOrbBlue();
    private Prefs prefs;
    private CharStat charStat = new CharStat(this);

    DefectReSkin() {
        super(NAMES[0], CLASS);
    }

    public String getPortraitImageName() {
        return "defectPortrait.jpg";
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Cracked Core");
        UnlockTracker.markRelicAsSeen("Cracked Core");
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Strike_B");
        retVal.add("Strike_B");
        retVal.add("Strike_B");
        retVal.add("Strike_B");
        retVal.add("Defend_B");
        retVal.add("Defend_B");
        retVal.add("Defend_B");
        retVal.add("Defend_B");
        retVal.add("Zap");
        retVal.add("Dualcast");
        return retVal;
    }

    public AbstractCard getStartCardForEvent() {
        return new Zap();
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], this.skinData.charText, 75, 75, 3, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    public String getTitle(PlayerClass plyrClass) {
        return NAMES[0];
    }

    public CardColor getCardColor() {
        return CardColor.BLUE;
    }

    public Color getCardRenderColor() {
        return Color.SKY;
    }

    public String getAchievementKey() {
        return "SAPPHIRE";
    }

    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        CardLibrary.addBlueCards(tmpPool);
        if (ModHelper.isModEnabled("Red Cards")) {
            CardLibrary.addRedCards(tmpPool);
        }

        if (ModHelper.isModEnabled("Green Cards")) {
            CardLibrary.addGreenCards(tmpPool);
        }

        if (ModHelper.isModEnabled("Purple Cards")) {
            CardLibrary.addPurpleCards(tmpPool);
        }

        return tmpPool;
    }

    public Color getCardTrailColor() {
        return Color.SKY.cpy();
    }

    public String getLeaderboardCharacterName() {
        return "DEFECT";
    }

    public Texture getEnergyImage() {
        return ImageMaster.BLUE_ORB_FLASH_VFX;
    }

    public int getAscensionMaxHPLoss() {
        return 4;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
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
        this.prefs = SaveHelper.getPrefs("STSDataDefect");
    }

    public CharStat getCharStat() {
        return this.charStat;
    }

    public int getUnlockedCardCount() {
        return UnlockTracker.unlockedBlueCardCount;
    }

    public int getSeenCardCount() {
        return CardLibrary.seenBlueCards;
    }

    public int getCardCount() {
        return CardLibrary.blueCards;
    }

    public boolean saveFileExists() {
        return SaveAndContinue.saveExistsAndNotCorrupted(this);
    }

    public String getWinStreakKey() {
        return "win_streak_defect";
    }

    public String getLeaderboardWinStreakKey() {
        return "DEFECT_CONSECUTIVE_WINS";
    }

    public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
        if (!UnlockTracker.isCharacterLocked("Defect")) {
            if (CardCrawlGame.mainMenuScreen.statsScreen.defectHb == null) {
                CardCrawlGame.mainMenuScreen.statsScreen.defectHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
            }

            StatsScreen.renderHeader(sb, StatsScreen.NAMES[4], screenX, renderY);
            this.charStat.render(sb, screenX, renderY);
        }

    }

    public void doCharSelectScreenSelectEffect() {
        if(ReMuseMod.isVoice) {
            CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", MathUtils.random(-0.2F, 0.2F));
        } else {
            this.playSelectSound("SELECT_" + this.skinData.name.toUpperCase());
        }
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_MAGIC_BEAM_SHORT";
    }

    public Texture getCustomModeCharacterButtonImage() {
        return ImageMaster.FILTER_DEFECT;
    }

    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    public void refreshCharStat() {
        this.charStat = new CharStat(this);
    }

    public AbstractPlayer newInstance() {
        return new DefectReSkin();
    }

    public AtlasRegion getOrb() {
        return orb_blue;
    }

    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    public Color getSlashAttackColor() {
        return Color.SKY;
    }

    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AttackEffect[]{AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.SLASH_DIAGONAL, AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.SLASH_DIAGONAL};
    }

    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString(ReMuseMod.makeID("Buro"));
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }
}
