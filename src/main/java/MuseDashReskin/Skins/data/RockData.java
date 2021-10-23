package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class RockData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.IRONCLAD;
    private static final int number = 1;
    private static final int voice = 7;
    private static final String name = "rock";
    private static final String campKey = "standby";
    private static final String mainKey = "standby";

    public RockData() {
        super(playerClass, number, name, campKey, mainKey, voice, getClass(playerClass));
    }
}
