package MuseDashReskin.elfins;

import MuseDashReskin.elfins.data.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;

import static MuseDashReskin.ReMuseMod.getModID;
import static MuseDashReskin.ReMuseMod.settings;

public class ElfinInfo {

    public int index = 0;
    public ArrayList<ElfinData> elfinData = new ArrayList<>();

    public ElfinInfo() {
        this.addElfins();

        try {
            SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
            config.load();
            this.index = Math.min(Math.max(config.getInt("elfinIndex"), 0), this.elfinData.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
            config.setInt("elfinIndex", this.index);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addElfins() {
        this.addElfinData(new CatData());
        this.addElfinData(new AngelData());
        this.addElfinData(new ReaperData());
        this.addElfinData(new CarrotData());
        this.addElfinData(new NurseData());
        this.addElfinData(new MagicData());
        this.addElfinData(new DragonData());
        this.addElfinData(new DevilData());
        //this.addElfinData(new DoctorData());
    }

    protected void addElfinData(ElfinData data) {
        this.elfinData.add(data);
        System.out.println("Elfin Data Added!   Elfin: " + data.nameKey);
    }

    public ElfinData getElfin() {
        return this.elfinData.get(this.index);
    }
}
