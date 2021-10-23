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
import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbPurple;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WatcherReSkin extends MusePlayer {
    private static final Logger logger = LogManager.getLogger(WatcherReSkin.class.getName());
    private static final CharacterStrings characterStrings;
    public static final String[] NAMES;
    public static final String[] TEXT;
    private static final PlayerClass CLASS = PlayerClass.WATCHER;
    private EnergyOrbInterface energyOrb = new EnergyOrbPurple();
    private Prefs prefs;
    private CharStat charStat = new CharStat(this);

    WatcherReSkin() {
        super(NAMES[0], CLASS);
        if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") || ModHelper.isModEnabled("Blue Cards"))) {
            this.masterMaxOrbs = 1;
        }

    }

    public String getPortraitImageName() {
        return "watcherPortrait.jpg";
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("PureWater");
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add("Strike_P");
        retVal.add("Strike_P");
        retVal.add("Strike_P");
        retVal.add("Strike_P");
        retVal.add("Defend_P");
        retVal.add("Defend_P");
        retVal.add("Defend_P");
        retVal.add("Defend_P");
        retVal.add("Eruption");
        retVal.add("Vigilance");
        return retVal;
    }

    public AbstractCard getStartCardForEvent() {
        return new Eruption();
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(this.skinData.charName, this.skinData.charText, 72, 72, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    public String getTitle(PlayerClass plyrClass) {
        return this.skinData!= null ? this.skinData.charName : NAMES[0];
    }

    public CardColor getCardColor() {
        return CardColor.PURPLE;
    }

    public Color getCardRenderColor() {
        return Settings.PURPLE_COLOR;
    }

    public CharacterOption getCharacterSelectOption() {
        return new CharacterOption(CharacterSelectScreen.TEXT[14], this, ImageMaster.CHAR_SELECT_WATCHER, ImageMaster.CHAR_SELECT_BG_WATCHER);
    }

    public String getAchievementKey() {
        return "AMETHYST";
    }

    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        CardLibrary.addPurpleCards(tmpPool);
        if (ModHelper.isModEnabled("Red Cards")) {
            CardLibrary.addRedCards(tmpPool);
        }

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
        return Color.PURPLE.cpy();
    }

    public String getLeaderboardCharacterName() {
        return "WATCHER";
    }

    public Texture getEnergyImage() {
        return ImageMaster.PURPLE_ORB_FLASH_VFX;
    }

    public int getAscensionMaxHPLoss() {
        return 4;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontPurple;
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
        this.prefs = SaveHelper.getPrefs("STSDataWatcher");
    }

    public CharStat getCharStat() {
        return this.charStat;
    }

    public int getUnlockedCardCount() {
        return UnlockTracker.unlockedPurpleCardCount;
    }

    public int getSeenCardCount() {
        return CardLibrary.seenPurpleCards;
    }

    public int getCardCount() {
        return CardLibrary.purpleCards;
    }

    public boolean saveFileExists() {
        return SaveAndContinue.saveExistsAndNotCorrupted(this);
    }

    public String getWinStreakKey() {
        return "win_streak_watcher";
    }

    public String getLeaderboardWinStreakKey() {
        return "WATCHER_CONSECUTIVE_WINS";
    }

    public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
        if (!UnlockTracker.isCharacterLocked("Watcher")) {
            if (CardCrawlGame.mainMenuScreen.statsScreen.watcherHb == null) {
                CardCrawlGame.mainMenuScreen.statsScreen.watcherHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
            }

            StatsScreen.renderHeader(sb, StatsScreen.NAMES[5], screenX, renderY);
            this.charStat.render(sb, screenX, renderY);
        }

    }

    public void doCharSelectScreenSelectEffect() {
        if(ReMuseMod.isVoice) {
            CardCrawlGame.sound.playA("SELECT_WATCHER", MathUtils.random(-0.15F, 0.15F));
        } else {
            this.playSelectSound("SELECT_" + this.skinData.name.toUpperCase());
        }
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "SELECT_WATCHER";
    }

    public Texture getCustomModeCharacterButtonImage() {
        return ImageMaster.FILTER_WATCHER;
    }

    public String getLocalizedCharacterName() {
        return this.skinData.charName;
    }

    public void refreshCharStat() {
        this.charStat = new CharStat(this);
    }

    public AbstractPlayer newInstance() {
        return new WatcherReSkin();
    }

    public AtlasRegion getOrb() {
        return AbstractCard.orb_purple;
    }

    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[15];
    }

    public Color getSlashAttackColor() {
        return Color.MAGENTA;
    }

    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AttackEffect[]{AttackEffect.BLUNT_LIGHT, AttackEffect.BLUNT_HEAVY, AttackEffect.BLUNT_LIGHT, AttackEffect.BLUNT_HEAVY, AttackEffect.BLUNT_HEAVY, AttackEffect.BLUNT_LIGHT};
    }

    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    @Override
    public void event(int i, com.esotericsoftware.spine.Event event) {

    }

    @Override
    public void complete(int i, int i1) {

    }

    @Override
    public void start(int i) {
        if(this.state.getCurrent(i).getAnimation().getName() != "Die") {
            System.out.println("AnimationListener Started");
            this.resetAnimation();
        }
    }

    @Override
    public void end(int i) {
        if(this.state.getCurrent(i).getAnimation().getName() != "Die") {
            System.out.println("AnimationListener Ended");
            this.resetAnimation();
        }
    }

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString(ReMuseMod.makeID("Collab"));
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }
}
