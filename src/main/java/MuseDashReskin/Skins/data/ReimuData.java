package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class ReimuData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.WATCHER;
    private static final int number = 6;
    private static final int voice = 5;
    private static final String name = "reimu";
    private static final String campKey = "BpmStandby";
    private static final String mainKey = "standby";

    public ReimuData() {
        super(playerClass, number, name, campKey, mainKey, voice, name);
    }
}
