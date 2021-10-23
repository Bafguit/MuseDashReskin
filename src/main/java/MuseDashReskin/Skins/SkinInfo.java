package MuseDashReskin.Skins;

import MuseDashReskin.Skins.data.*;
import MuseDashReskin.patches.MainScreenAnimation;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;
import java.util.HashMap;

import static MuseDashReskin.ReMuseMod.*;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.*;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass.*;

public class SkinInfo {

    public HashMap<PlayerClass, ArrayList<SkinData>> skinData = new HashMap<>();
    public HashMap<PlayerClass, String> saveData = new HashMap<>();
    public HashMap<PlayerClass, Integer> index = new HashMap<>();

    public SkinInfo() {
        this.skinData.clear();
        this.skinData.put(IRONCLAD, new ArrayList<SkinData>());
        this.skinData.put(THE_SILENT, new ArrayList<SkinData>());
        this.skinData.put(DEFECT, new ArrayList<SkinData>());
        this.skinData.put(WATCHER, new ArrayList<SkinData>());
        this.addSkins();

        this.saveData.clear();
        this.saveData.put(IRONCLAD, "ironcladSkin");
        this.saveData.put(THE_SILENT, "silentSkin");
        this.saveData.put(DEFECT, "defectSkin");
        this.saveData.put(WATCHER, "watcherSkin");

        this.index.clear();
        this.index.put(IRONCLAD, 0);
        this.index.put(THE_SILENT, 0);
        this.index.put(DEFECT, 0);
        this.index.put(WATCHER, 0);

        try {
            SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
            config.load();
            this.index.replace(IRONCLAD, Math.min(Math.max(config.getInt(this.saveData.get(IRONCLAD)), 0), this.skinData.get(IRONCLAD).size() - 1));
            this.index.replace(THE_SILENT, Math.min(Math.max(config.getInt(this.saveData.get(THE_SILENT)), 0), this.skinData.get(THE_SILENT).size() - 1));
            this.index.replace(DEFECT, Math.min(Math.max(config.getInt(this.saveData.get(DEFECT)), 0), this.skinData.get(DEFECT).size() - 1));
            this.index.replace(WATCHER, Math.min(Math.max(config.getInt(this.saveData.get(WATCHER)), 0), this.skinData.get(WATCHER).size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addSkins() {
        MainScreenAnimation.sr.setPremultipliedAlpha(true);

        this.addSkinData(IRONCLAD, new RockData());
        this.addSkinData(IRONCLAD, new RampageData());
        this.addSkinData(IRONCLAD, new SleepyData());
        this.addSkinData(IRONCLAD, new BunnyData());
        this.addSkinData(IRONCLAD, new SantaData());
        this.addSkinData(IRONCLAD, new WorkerData());

        this.addSkinData(THE_SILENT, new ViolinData());
        this.addSkinData(THE_SILENT, new MaidData());
        this.addSkinData(THE_SILENT, new MagicData());
        this.addSkinData(THE_SILENT, new EvilData());
        this.addSkinData(THE_SILENT, new BlackData());

        this.addSkinData(DEFECT, new PilotData());
        this.addSkinData(DEFECT, new RobotData());
        this.addSkinData(DEFECT, new ZombieData());
        this.addSkinData(DEFECT, new JokerData());
        //this.addSkinData(DEFECT, new JKData());

        this.addSkinData(WATCHER, new YumeData());
        this.addSkinData(WATCHER, new NekoData());
        //this.addSkinData(WATCHER, new ReimuData());

        loadAnimation();
    }

    private void loadAnimation() {
        //Git
        for(SkinData d : skinData.get(IRONCLAD)) {
            d.loadAnimation();
        }
        for(SkinData d : skinData.get(THE_SILENT)) {
            d.loadAnimation();
        }
        for(SkinData d : skinData.get(DEFECT)) {
            d.loadAnimation();
        }
        for(SkinData d : skinData.get(WATCHER)) {
            d.loadAnimation();
        }
    }

    protected void addSkinData(PlayerClass c, SkinData skinData) {
        this.skinData.get(c).add(skinData);
        System.out.println("Skin Data Added!   Class: " + c.name() + "   Skin: " + skinData.name);
    }

    public SkinData getSkin(PlayerClass c) {
        return this.skinData.get(c).get(this.index.get(c));
    }
}
