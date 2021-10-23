package MuseDashReskin.bg;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class BgInfo {

    public int index = 0;
    public ArrayList<BgData> bgData = new ArrayList<>();

    public BgInfo() {
        this.addBgs();
        Random random = new Random();
        this.index = random.nextInt(this.bgData.size());
    }

    protected void addBgs() {
        this.addBgData(new HalloweenData());
        this.addBgData(new FirstData());
        this.addBgData(new ThirdData());
        this.addBgData(new FourthData());
        //this.addBgData(new SecondData());
    }

    protected void addBgData(BgData data) {
        this.bgData.add(data);
    }

    public BgData getBg() {
        return this.bgData.get(this.index);
    }
}
