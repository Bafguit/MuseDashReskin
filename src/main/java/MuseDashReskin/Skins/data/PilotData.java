package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.*;

public class PilotData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.DEFECT;
    private static final int number = 2;
    private static final int voice = 7;
    private static final String name = "pilot";
    private static final String campKey = "standby";
    private static final String mainKey = "Standby";

    public PilotData() {
        super(playerClass, number, name, campKey, mainKey, 1.7f, voice, getClass(playerClass));
    }
}
