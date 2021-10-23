package MuseDashReskin;

import MuseDashReskin.patches.*;
import MuseDashReskin.util.MuseSound;
import MuseDashReskin.util.TextureLoader;
import basemod.*;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

@SpireInitializer
public class ReMuseMod implements EditStringsSubscriber, PostInitializeSubscriber{

    public static final Logger logger = LogManager.getLogger(ReMuseMod.class.getName());
    public static Properties settings = new Properties();
    public static boolean isAnimation = false;
    public static boolean isVoice = false;
    public static boolean isElfin = false;

    private static String modID;
    private static final String MODNAME = "Muse Dash Skin";
    private static final String AUTHOR = "FastCat";
    private static final String DESCRIPTION = "Mush Dash character skin mod.";

    public static MuseSound museSound = new MuseSound();
    public static String selectKey = "";
    public static long selectId = 0L;

    public static ArrayList<String> animations = new ArrayList<String>();
    public static ArrayList<String> animationsBig = new ArrayList<String>();
    public static Properties reSkin = new Properties();
    
    public static String mip(String resourcePath) {
        return getModID() + "Resources/images/" + resourcePath;
    }

    public static String mcp(String resourcePath) {
        return getModID() + "Resources/images/char/" + resourcePath;
    }
    private static void setModID(String id) {
        modID = id;
    }

    public static String getModID() {
        return modID;
    }

    public static String repID(String id) {
        return id.replace(getModID() + ":", "");
    }


    public ReMuseMod() {
        BaseMod.subscribe(this);
        setModID("ReMuse");
        animations.clear();
        animations.add("RoadHitGreat1");
        animations.add("RoadHitGreat2");
        animations.add("RoadHitGreat3");
        animations.add("RoadHitPerfect1");
        animations.add("RoadHitPerfect2");
        animations.add("RoadHitPerfect3");
        animations.add("RoadHitPerfect4");
        animationsBig.clear();
        animationsBig.add("DoubleHit1");
        animationsBig.add("DoubleHit2");

        try {
            SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
            config.load();
            isAnimation = config.getBool("isAnimation");
            isVoice = config.getBool("isVoice");
            isElfin = config.getBool("isElfin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        ReMuseMod ReMuse = new ReMuseMod();
    }

    @Override
    public void receivePostInitialize() {
        System.out.println("&&&&&&&&&&&&&&&& PostInitialize &&&&&&&&&&&&&&&&");
        Texture badgeTexture = TextureLoader.getTexture(mip("Badge.png"));

        ModPanel settingsPanel = new ModPanel();

        ModLabeledToggleButton animationButton = new ModLabeledToggleButton("Disable screen animation",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                isAnimation, settingsPanel, (label) -> {}, (button) -> {
            isAnimation = button.enabled;
            try {
                SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
                config.setBool("isAnimation", isAnimation);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ModLabeledToggleButton voiceButton = new ModLabeledToggleButton("Disable character voice",
                350.0f, 650.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                isVoice, settingsPanel, (label) -> {}, (button) -> {
            isVoice = button.enabled;
            try {
                SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
                config.setBool("isVoice", isVoice);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ModLabeledToggleButton elfinButton = new ModLabeledToggleButton("Disable Elfin",
                350.0f, 600.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                isElfin, settingsPanel, (label) -> {}, (button) -> {
            isElfin = button.enabled;
            try {
                SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
                config.setBool("isElfin", isElfin);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsPanel.addUIElement(animationButton);
        settingsPanel.addUIElement(voiceButton);
        settingsPanel.addUIElement(elfinButton);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    //언어팩 로드
    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/" + getLanguage() + "/char.json");
    }

    public static void setHitAnimation(MusePlayer p) {
        Random random = new Random();
        String anim = animations.get(random.nextInt(animations.size()));
        AnimationState.TrackEntry e = p.state.setAnimation(0, anim, false);
        p.state.addAnimation(0, p.skinData.name == "reimu" ? "Run" : "Standby", true, 0.0F);
        e.setTimeScale(1.0f);
    }


    public static void setBigHitAnimation(MusePlayer p) {
        Random random = new Random();
        String anim = animationsBig.get(random.nextInt(animationsBig.size()));
        AnimationState.TrackEntry e = p.state.setAnimation(0, anim, false);
        p.state.addAnimation(0, p.skinData.name == "reimu" ? "Run" : "Standby", true, 0.0F);
        e.setTimeScale(1.0f);
    }

    public static void setDmgAnimation(MusePlayer p) {
        AnimationState.TrackEntry e = p.state.setAnimation(0, "AirHitHurt", false);
        p.state.addAnimation(0, p.skinData.name == "reimu" ? "Run" : "Standby", true, 0.0F);

        e.setTimeScale(1.0f);
    }


    public static void setDieAnimation(MusePlayer p) {
        AnimationState.TrackEntry e = p.state.setAnimation(0, "Die", false);
        p.state.setTimeScale(0.5f);
        p.elfinData.state.setTimeScale(0.5f);
    }

    //언어 추가할때 사용. jpn, zhs, rus 등등...
    public String getLanguage() {
        switch (Settings.language.name()) {
            case "KOR":
                return "kor";
            case "JPN":
                return "jpn";
            case "ZHS":
                return "zhs";
            case "ZHT":
                return "zht";
            default:  //기본 언어는 영어로 해야함
                return "eng";
        }
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
