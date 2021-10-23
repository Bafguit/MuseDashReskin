package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class NekoData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.WATCHER;
    private static final int number = 5;
    private static final int voice = 5;
    private static final String name = "neko";
    private static final String campKey = "BpmStandby";
    private static final String mainKey = "standby";

    public NekoData() {
        super(playerClass, number, name, campKey, mainKey, 1.9f, voice, name);
    }
}
