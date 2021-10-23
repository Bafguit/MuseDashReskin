package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class BlackData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.THE_SILENT;
    private static final int number = 3;
    private static final int voice = 7;
    private static final String name = "black";
    private static final String campKey = "Standby";
    private static final String mainKey = "standby";

    public BlackData() {
        super(playerClass, number, name, campKey, mainKey, 1.9f, voice, getClass(playerClass));
    }
}
