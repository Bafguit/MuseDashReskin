package MuseDashReskin.Skins.data;

import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

public class YumeData extends SkinData{

    private static final PlayerClass playerClass = PlayerClass.WATCHER;
    private static final int number = 4;
    private static final int voice = 7;
    private static final String name = "yume";
    private static final String campKey = "BpmStandby";
    private static final String mainKey = "standby";

    public YumeData() {
        super(playerClass, number, name, campKey, mainKey, voice, name);
    }
}
