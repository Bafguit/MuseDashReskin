package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class MaidData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.THE_SILENT;
    private static final int number = 3;
    private static final int voice = 7;
    private static final String name = "maid";
    private static final String campKey = "standby";
    private static final String mainKey = "typeB_8";

    public MaidData() {
        super(playerClass, number, name, campKey, mainKey, 2.2f, voice, getClass(playerClass));
    }
}
