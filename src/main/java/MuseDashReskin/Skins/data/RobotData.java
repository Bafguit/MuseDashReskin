package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class RobotData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.DEFECT;
    private static final int number = 2;
    private static final int voice = 7;
    private static final String name = "robot";
    private static final String campKey = "standby";
    private static final String mainKey = "16";

    public RobotData() {
        super(playerClass, number, name, campKey, mainKey, 2.2f, voice, getClass(playerClass));
    }
}
