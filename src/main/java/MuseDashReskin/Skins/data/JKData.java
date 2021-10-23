package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class JKData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.DEFECT;
    private static final int number = 2;
    private static final int voice = 7;
    private static final String name = "jk";
    private static final String campKey = "BgmStandby";
    private static final String mainKey = "standby";

    public JKData() {
        super(playerClass, number, name, campKey, mainKey, voice, getClass(playerClass));
    }
}
