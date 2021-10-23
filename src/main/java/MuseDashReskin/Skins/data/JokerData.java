package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class JokerData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.DEFECT;
    private static final int number = 2;
    private static final int voice = 7;
    private static final String name = "joker";
    private static final String campKey = "standby";
    private static final String mainKey = "Standby";

    public JokerData() {
        super(playerClass, number, name, campKey, mainKey, voice, getClass(playerClass));
    }
}
