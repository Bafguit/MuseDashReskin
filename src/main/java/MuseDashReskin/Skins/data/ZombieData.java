package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class ZombieData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.DEFECT;
    private static final int number = 2;
    private static final int voice = 7;
    private static final String name = "zombie";
    private static final String campKey = "standby";
    private static final String mainKey = "15";

    public ZombieData() {
        super(playerClass, number, name, campKey, mainKey, 2.2f, voice, getClass(playerClass));
    }
}
