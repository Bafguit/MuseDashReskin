package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class SantaData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.IRONCLAD;
    private static final int number = 1;
    private static final int voice = 7;
    private static final String name = "santa";
    private static final String campKey = "Standby";
    private static final String mainKey = "standby";

    public SantaData() {
        super(playerClass, number, name, campKey, mainKey, voice, getClass(playerClass));
    }
}
