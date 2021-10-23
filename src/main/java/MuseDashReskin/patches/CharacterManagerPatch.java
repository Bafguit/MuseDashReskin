package MuseDashReskin.patches;

import MuseDashReskin.ReMuseMod;
import MuseDashReskin.bg.BgData;
import MuseDashReskin.bg.BgInfo;
import MuseDashReskin.util.TextureLoader;
import basemod.ReflectionHacks;
import basemod.helpers.SuperclassFinder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static MuseDashReskin.patches.MainScreenAnimation.updateAnimation;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass.*;

public class CharacterManagerPatch {
    @SpirePatch(
            clz = CharacterManager.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class MuseCharacterPatch {
        public MuseCharacterPatch() {
        }

        public static SpireReturn Postfix(CharacterManager __instance) throws NoSuchFieldException, IllegalAccessException {
            System.out.println("#####################Muse Dash Character Patch############################");
            Field masterCharacterList = SuperclassFinder.getSuperclassField(__instance.getClass(), "masterCharacterList");
            masterCharacterList.setAccessible(true);
            ArrayList<AbstractPlayer> list = (ArrayList) masterCharacterList.get(__instance);
            if (!list.isEmpty()) {
                for(int i = 0; i < list.size(); i++) {
                    AbstractPlayer temp = list.get(i);
                    if(temp instanceof Ironclad) {
                        list.set(i, new IroncladReSkin());
                    } else if (temp instanceof TheSilent) {
                        list.set(i, new SilentReSkin());
                    } else if(temp instanceof Defect) {
                        list.set(i, new DefectReSkin());
                    } else if(temp instanceof Watcher) {
                        list.set(i, new WatcherReSkin());
                    }
                }
                masterCharacterList.set(__instance, list);
            } else {
                Iterator var1 = ((ArrayList) masterCharacterList.get(__instance)).iterator();

                while (var1.hasNext()) {
                    AbstractPlayer c = (AbstractPlayer) var1.next();
                    c.loadPrefs();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = CharacterOption.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractPlayer.class}
    )
    public static class CharImgPatch {
        public CharImgPatch() {
        }

        public static SpireReturn Postfix(CharacterOption _instance) throws NoSuchFieldException, IllegalAccessException {
            Field portraitImg = SuperclassFinder.getSuperclassField(_instance.getClass(), "portraitImg");
            portraitImg.setAccessible(true);
            AbstractPlayer c = _instance.c;
            if(c instanceof MusePlayer) {
                if(c.chosenClass == WATCHER) {
                    _instance.name = ((MusePlayer) c).skinData.charName;
                } else {
                    _instance.name = ((MusePlayer) c).charName;
                }
                portraitImg.set(_instance, TextureLoader.getTexture("ReMuseResources/images/bg.png"));
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = CharacterOption.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {String.class, AbstractPlayer.class, String.class, String.class}
    )
    public static class CharImgPatch2 {
        public CharImgPatch2() {
        }

        public static SpireReturn Postfix(CharacterOption _instance) throws NoSuchFieldException, IllegalAccessException {
            Field portraitImg = SuperclassFinder.getSuperclassField(_instance.getClass(), "portraitImg");
            portraitImg.setAccessible(true);
            AbstractPlayer c = _instance.c;
            if(c instanceof MusePlayer) {
                if(c.chosenClass == WATCHER) {
                    _instance.name = ((MusePlayer) c).skinData.charName;
                } else {
                    _instance.name = ((MusePlayer) c).charName;
                }
                portraitImg.set(_instance, TextureLoader.getTexture("ReMuseResources/images/bg.png"));
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = CharacterOption.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {String.class, AbstractPlayer.class, Texture.class, Texture.class}
    )
    public static class CharImgPatch3 {
        public CharImgPatch3() {
        }

        public static SpireReturn Postfix(CharacterOption _instance) throws NoSuchFieldException, IllegalAccessException {
            Field portraitImg = SuperclassFinder.getSuperclassField(_instance.getClass(), "portraitImg");
            portraitImg.setAccessible(true);
            AbstractPlayer c = _instance.c;
            if(c instanceof MusePlayer) {
                if(c.chosenClass == WATCHER) {
                    _instance.name = ((MusePlayer) c).skinData.charName;
                } else {
                    _instance.name = ((MusePlayer) c).charName;
                }
                portraitImg.set(_instance, TextureLoader.getTexture("ReMuseResources/images/bg.png"));
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MenuCancelButton.class,
            method = "render"
    )
    public static class RenderMapPatch {
        public RenderMapPatch() {
        }

        public static SpireReturn Prefix(MenuCancelButton _instance, SpriteBatch sb) {
            if(_instance == CardCrawlGame.mainMenuScreen.charSelectScreen.cancelButton) {
                boolean selected = (boolean) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "anySelected");
                if (selected) {
                    Iterator var1 = CardCrawlGame.mainMenuScreen.charSelectScreen.options.iterator();
                    CharacterOption o;

                    while (var1.hasNext()) {
                        o = (CharacterOption) var1.next();
                        if (o.selected) {
                            if (o.c instanceof MusePlayer) {
                                MainScreenAnimation.renderAnimation(sb, (MusePlayer) o.c);
                                break;
                            }
                        }
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = CharacterOption.class,
            method = "update"
    )
    public static class CharAnimPatch {
        public CharAnimPatch() {
        }

        public static SpireReturn Postfix(CharacterOption _instance) {
            if(_instance.selected) {
                updateAnimation(_instance.c);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = RestRoom.class,
            method = "render"
    )
    public static class RestShoulderPatch {
        public RestShoulderPatch() {
        }

        public static SpireReturn Prefix(RestRoom _instance, SpriteBatch sb) {
            if(AbstractDungeon.player instanceof MusePlayer) {
                ((MusePlayer) AbstractDungeon.player).skinData.renderCampAnimation(sb);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = SoundMaster.class,
            method = "update"
    )
    public static class FadeUpdatePatch {
        public FadeUpdatePatch() {
        }

        public static SpireReturn Prefix(SoundMaster _instance) {
            ReMuseMod.museSound.update();
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MainMenuScreen.class,
            method = "fadeOutMusic"
    )
    public static class VoiceFadePatch {
        public VoiceFadePatch() {
        }

        public static SpireReturn Prefix(MainMenuScreen _instance) {
            System.out.println("FFFFFFFAAAAAAAADDDDDDDDEEEEEEE");
            ReMuseMod.museSound.fadeSelect();
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = CharacterSelectScreen.class,
            method = "update"
    )
    public static class CharSkinButtonUpdatePatch {
        public CharSkinButtonUpdatePatch() {
        }

        public static SpireReturn Prefix(CharacterSelectScreen _instance) {
            MainScreenAnimation.updateButton();
            MainScreenAnimation.updateElfinButton();
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MainMenuScreen.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {boolean.class}
    )
    public static class MainBgPatch {
        public MainBgPatch() {
        }

        public static SpireReturn Postfix(MainMenuScreen _instance) {
            BgInfo bgInfo = new BgInfo();
            _instance.bg = bgInfo.getBg();
            CardCrawlGame.sound.stop("WIND", _instance.windId);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MainMusic.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {String.class}
    )
    public static class TempMusicPatch {
        public TempMusicPatch() {
        }

        public static SpireReturn Postfix(MainMusic _instance, String key) {
            if(key.equals("MENU")) {
                ReflectionHacks.setPrivate(_instance, MainMusic.class, "music", MainMusic.newMusic("ReMuseResources/audios/other/welcome_bgm.ogg"));
                Music music = ReflectionHacks.getPrivate(_instance, MainMusic.class, "music");
                music.setLooping(true);
                music.play();
                music.setVolume(0.0F);
            }
            return SpireReturn.Continue();
        }
    }
}
