//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MuseDashReskin.util;

import MuseDashReskin.ReMuseMod;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MuseSound {
    private static final Logger logger = LogManager.getLogger(MuseSound.class.getName());
    private HashMap<String, Sfx> map = new HashMap();
    private ArrayList<SoundInfo> fadeOutList = new ArrayList();
    private static final String SFX_DIR = "ReMuseResources/audios/";
    private boolean isFadeAll = false;

    public MuseSound() {
        long startTime = System.currentTimeMillis();

        this.map.put("DEFECT_HURT_1", this.load("defect/VoiceBuroHurt01"));
        this.map.put("DEFECT_HURT_2", this.load("defect/VoiceBuroHurt03"));
        this.map.put("DEFECT_HURT_3", this.load("defect/VoiceBuroHurt05"));
        this.map.put("DEFECT_HURT_4", this.load("defect/VoiceBuroHurt06"));
        this.map.put("DEFECT_HURT_5", this.load("defect/VoiceBuroHurt07"));
        this.map.put("DEFECT_HURT_6", this.load("defect/VoiceBuroHurt08"));
        this.map.put("DEFECT_HURT_7", this.load("defect/VoiceBuroHurt10"));
        this.map.put("SILENT_HURT_1", this.load("silent/VoiceMarijaHurt02"));
        this.map.put("SILENT_HURT_2", this.load("silent/VoiceMarijaHurt03"));
        this.map.put("SILENT_HURT_3", this.load("silent/VoiceMarijaHurt05"));
        this.map.put("SILENT_HURT_4", this.load("silent/VoiceMarijaHurt06"));
        this.map.put("SILENT_HURT_5", this.load("silent/VoiceMarijaHurt07"));
        this.map.put("SILENT_HURT_6", this.load("silent/VoiceMarijaHurt08"));
        this.map.put("SILENT_HURT_7", this.load("silent/VoiceMarijaHurt09"));
        this.map.put("IRONCLAD_HURT_1", this.load("ironclad/VoiceRinHurt01"));
        this.map.put("IRONCLAD_HURT_2", this.load("ironclad/VoiceRinHurt02"));
        this.map.put("IRONCLAD_HURT_3", this.load("ironclad/VoiceRinHurt03"));
        this.map.put("IRONCLAD_HURT_4", this.load("ironclad/VoiceRinHurt06"));
        this.map.put("IRONCLAD_HURT_5", this.load("ironclad/VoiceRinHurt09"));
        this.map.put("IRONCLAD_HURT_6", this.load("ironclad/VoiceRinHurt10"));
        this.map.put("IRONCLAD_HURT_7", this.load("ironclad/VoiceRinHurt11"));
        this.map.put("YUME_HURT_1", this.load("watcher/VoiceYumeHurt01"));
        this.map.put("YUME_HURT_2", this.load("watcher/VoiceYumeHurt02"));
        this.map.put("YUME_HURT_3", this.load("watcher/VoiceYumeHurt03"));
        this.map.put("YUME_HURT_4", this.load("watcher/VoiceYumeHurt04"));
        this.map.put("YUME_HURT_5", this.load("watcher/VoiceYumeHurt05"));
        this.map.put("YUME_HURT_6", this.load("watcher/VoiceYumeHurt06"));
        this.map.put("YUME_HURT_7", this.load("watcher/VoiceYumeHurt07"));
        this.map.put("NEKO_HURT_1", this.load("watcher/VoiceNekoHurt01"));
        this.map.put("NEKO_HURT_2", this.load("watcher/VoiceNekoHurt02"));
        this.map.put("NEKO_HURT_3", this.load("watcher/VoiceNekoHurt03"));
        this.map.put("NEKO_HURT_4", this.load("watcher/VoiceNekoHurt04"));
        this.map.put("NEKO_HURT_5", this.load("watcher/VoiceNekoHurt05"));
        this.map.put("REIMU_HURT_1", this.load("watcher/VoiceReimuHurt01"));
        this.map.put("REIMU_HURT_2", this.load("watcher/VoiceReimuHurt02"));
        this.map.put("REIMU_HURT_3", this.load("watcher/VoiceReimuHurt03"));
        this.map.put("REIMU_HURT_4", this.load("watcher/VoiceReimuHurt04"));
        this.map.put("REIMU_HURT_5", this.load("watcher/VoiceReimuHurt05"));
        this.map.put("SELECT_PILOT", this.load("select/VoicePilotApply"));
        this.map.put("SELECT_ROBOT", this.load("select/VoiceRobotApply"));
        this.map.put("SELECT_ZOMBIE", this.load("select/VoiceZombieApply"));
        this.map.put("SELECT_JOKER", this.load("select/VoiceJokerApply"));
        this.map.put("SELECT_JK", this.load("select/VoiceJKApply"));
        this.map.put("SELECT_VIOLIN", this.load("select/VoiceViolinApply"));
        this.map.put("SELECT_MAID", this.load("select/VoiceMaidApply"));
        this.map.put("SELECT_MAGIC", this.load("select/VoiceMagicApply"));
        this.map.put("SELECT_EVIL", this.load("select/VoiceEvilApply"));
        this.map.put("SELECT_BLACK", this.load("select/VoiceBlackApply"));
        this.map.put("SELECT_ROCK", this.load("select/VoiceRockApply"));
        this.map.put("SELECT_RAMPAGE", this.load("select/VoiceRampageApply"));
        this.map.put("SELECT_SLEEPY", this.load("select/VoiceSleepyApply"));
        this.map.put("SELECT_BUNNY", this.load("select/VoiceBunnyApply"));
        this.map.put("SELECT_SANTA", this.load("select/VoiceSantaApply"));
        this.map.put("SELECT_WORKER", this.load("select/VoiceWorkerApply"));
        this.map.put("SELECT_YUME", this.load("select/VoiceYumeApply"));
        this.map.put("SELECT_NEKO", this.load("select/VoiceNekoApply"));
        this.map.put("SELECT_REIMU", this.load("select/VoiceReimuApply"));

        logger.info("Sound Effect Volume: " + Settings.SOUND_VOLUME);
        logger.info("Loaded " + this.map.size() + " Sound Effects");
        logger.info("SFX load time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private Sfx load(String filename) {
        return this.load(filename, false);
    }

    private Sfx load(String filename, boolean preload) {
        return new Sfx(SFX_DIR + filename + ".ogg", preload);
    }

    public void update() {

        Iterator i = this.fadeOutList.iterator();

        while(i.hasNext()) {
            SoundInfo e = (SoundInfo)i.next();
            e.update();
            Sfx sfx = (Sfx)this.map.get(e.name);
            if (sfx != null) {
                if (e.isDone) {
                    sfx.stop(e.id);
                    i.remove();
                } else {
                    sfx.setVolume(e.id, Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * e.volumeMultiplier);
                }
            }
        }

    }

    public void preload(String key) {
        if (this.map.containsKey(key)) {
            logger.info("Preloading: " + key);
            long id = ((Sfx)this.map.get(key)).play(0.0F);
            ((Sfx)this.map.get(key)).stop(id);
        } else {
            logger.info("Missing: " + key);
        }

    }

    public long play(String key, boolean useBgmVolume) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return useBgmVolume ? ((Sfx)this.map.get(key)).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME) : ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public void fadeSelect() {
        this.fadeOut(ReMuseMod.selectKey, ReMuseMod.selectId);
    }

    public long stopAndPlay(String key) {
        if(this.map.containsKey(ReMuseMod.selectKey)) {
            this.map.get(ReMuseMod.selectKey).stop();
        }
        return this.play(key);
    }

    public long play(String key) {
        return CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded ? 0L : this.play(key, 0.0f);
    }

    public long play(String key, float pitchVariation) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * 0.6f, 1.0F + MathUtils.random(-pitchVariation, pitchVariation), 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playA(String key, float pitchAdjust) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + pitchAdjust, 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playV(String key, float volumeMod) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * volumeMod, 1.0F, 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playAV(String key, float pitchAdjust, float volumeMod) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * volumeMod, 1.0F + pitchAdjust, 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playAndLoop(String key) {
        if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).loop(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playAndLoop(String key, float volume) {
        if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).loop(volume);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }
    /*
    public long playIntroLoop(String key, float volume) {
        if (this.map.containsKey(key)) {
            logger.info("Preloading: " + key);
            long id = ((Sfx)this.map.get(key)).play(0.0F);
            ((Sfx)this.map.get(key)).stop(id);
        } else {
            logger.info("Missing: " + key);
        }
    }*/

    public void adjustVolume(String key, long id, float volume) {
        ((Sfx)this.map.get(key)).setVolume(id, volume);
    }

    public void adjustVolume(String key, long id) {
        ((Sfx)this.map.get(key)).setVolume(id, Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
    }

    public void fadeOut(String key, long id) {
        this.fadeOutList.add(new SoundInfo(key, id));
    }

    public void stop(String key, long id) {
        ((Sfx)this.map.get(key)).stop(id);
    }

    public void stop(String key) {
        if (this.map.get(key) != null) {
            ((Sfx)this.map.get(key)).stop();
        }

    }
}
