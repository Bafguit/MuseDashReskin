package MuseDashReskin.patches;

import MuseDashReskin.ReMuseMod;
import MuseDashReskin.Skins.SkinInfo;
import MuseDashReskin.elfins.ElfinInfo;
import MuseDashReskin.elfins.data.ElfinData;
import MuseDashReskin.spine.SkeletonData;
import basemod.ReflectionHacks;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.ui.panels.SeedPanel;

import java.util.Iterator;

import static MuseDashReskin.ReMuseMod.*;
import static MuseDashReskin.spine.AnimationState.*;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.*;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass.*;

public class MainScreenAnimation implements PostInitializeSubscriber {

    public static MuseDashReskin.spine.SkeletonRenderer sr = new MuseDashReskin.spine.SkeletonRenderer();

    public static SkinInfo skinInfo = new SkinInfo();
    public static ElfinInfo elfinInfo = new ElfinInfo();

    private static Hitbox skinLeftHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
    private static Hitbox skinRightHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);

    private static Hitbox elfinLeftHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
    private static Hitbox elfinRightHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);

    @Override
    public void receivePostInitialize() {
    }

    public MainScreenAnimation() {
    }

    public static void updateAnimation(AbstractPlayer c) {
        if(c instanceof MusePlayer && isAnimation) {
            ((MusePlayer) c).skinData.mainState.getCurrent(0).setTrackTime(0.0f);
        }
    }

    public static void updateButton() {
        skinLeftHb.move(190 * Settings.scale, 800 * Settings.scale);
        skinRightHb.move(510 * Settings.scale, 800 * Settings.scale);
        SeedPanel panel = ((SeedPanel) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "seedPanel"));
        if(!panel.shown) {
            boolean selected = (boolean) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "anySelected");
            if(selected) {
                skinLeftHb.update();
                skinRightHb.update();
            }
            if (InputHelper.justClickedLeft) {
                if (skinRightHb.hovered) {
                    skinRightHb.clickStarted = true;
                } else if (skinLeftHb.hovered) {
                    skinLeftHb.clickStarted = true;
                }
            }

            Iterator var1;
            CharacterOption o;
            if (skinLeftHb.clicked) {
                skinLeftHb.clicked = false;
                var1 = CardCrawlGame.mainMenuScreen.charSelectScreen.options.iterator();

                while(var1.hasNext()) {
                    o = (CharacterOption)var1.next();
                    if (o.selected) {
                        if(o.c instanceof MusePlayer) {
                            ((MusePlayer) o.c).adjustIndex(-1);
                            ReflectionHacks.setPrivate(o, CharacterOption.class, "name", o.c.getLoadout().name);
                            ReflectionHacks.setPrivate(o, CharacterOption.class, "flavorText", o.c.getLoadout().flavorText);
                        }
                        break;
                    }
                }
            }

            if (skinRightHb.clicked) {
                skinRightHb.clicked = false;
                var1 = CardCrawlGame.mainMenuScreen.charSelectScreen.options.iterator();

                while(var1.hasNext()) {
                    o = (CharacterOption)var1.next();
                    if (o.selected) {
                        if(o.c instanceof MusePlayer) {
                            ((MusePlayer) o.c).adjustIndex(1);
                            ReflectionHacks.setPrivate(o, CharacterOption.class, "name", o.c.getLoadout().name);
                            ReflectionHacks.setPrivate(o, CharacterOption.class, "flavorText", o.c.getLoadout().flavorText);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void updateElfinButton() {
        elfinLeftHb.move(190 * Settings.scale, 900 * Settings.scale);
        elfinRightHb.move(510 * Settings.scale, 900 * Settings.scale);
        SeedPanel panel = ((SeedPanel) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "seedPanel"));
        if(!panel.shown) {
            boolean selected = (boolean) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "anySelected");
            if(selected) {
                elfinLeftHb.update();
                elfinRightHb.update();
            }
            if (InputHelper.justClickedLeft) {
                if (elfinRightHb.hovered) {
                    elfinRightHb.clickStarted = true;
                } else if (elfinLeftHb.hovered) {
                    elfinLeftHb.clickStarted = true;
                }
            }

            Iterator var1;
            CharacterOption o;
            if (elfinLeftHb.clicked) {
                elfinLeftHb.clicked = false;
                var1 = CardCrawlGame.mainMenuScreen.charSelectScreen.options.iterator();

                while(var1.hasNext()) {
                    o = (CharacterOption)var1.next();
                    if (o.selected) {
                        if(o.c instanceof MusePlayer) {
                            adjustElfinIndex(-1, (MusePlayer) o.c);
                        }
                        break;
                    }
                }
            }

            if (elfinRightHb.clicked) {
                elfinRightHb.clicked = false;
                var1 = CardCrawlGame.mainMenuScreen.charSelectScreen.options.iterator();

                while(var1.hasNext()) {
                    o = (CharacterOption)var1.next();
                    if (o.selected) {
                        if(o.c instanceof MusePlayer) {
                            adjustElfinIndex(1, (MusePlayer) o.c);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void adjustElfinIndex(int i, MusePlayer p) {
        int temp = elfinInfo.index;
        elfinInfo.index = Math.min(Math.max(elfinInfo.index + i, 0), elfinInfo.elfinData.size() - 1);
        System.out.println("Elfin Index: " + elfinInfo.index);
        if(temp != elfinInfo.index) {
            p.elfinData = elfinInfo.getElfin();
            try {
                SpireConfig config = new SpireConfig(getModID(), getModID() + "Config", settings);
                config.setInt("elfinIndex", elfinInfo.index);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void renderAnimation(SpriteBatch sb, MusePlayer c) {
        c.skinData.renderMainAnimation(sb);
        renderElfinMenu(sb);
        renderSkinMenu(sb, c);
    }

    private static void renderSkinMenu(SpriteBatch sb, MusePlayer c) {
        String text = c.skinData.charName;
        FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, text, 350 * Settings.scale, 800 * Settings.scale, Settings.CREAM_COLOR);

        if (!skinLeftHb.hovered) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }
        sb.draw(ImageMaster.CF_LEFT_ARROW, skinLeftHb.cX - 24.0F, skinLeftHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);

        if (!skinRightHb.hovered) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }
        sb.draw(ImageMaster.CF_RIGHT_ARROW, skinRightHb.cX - 24.0F, skinRightHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);

        skinLeftHb.render(sb);
        skinRightHb.render(sb);
    }

    private static void renderElfinMenu(SpriteBatch sb) {
        if(isElfin) {
            //Elfin is null
        } else {
            elfinInfo.getElfin().render(sb);

            if (!elfinLeftHb.hovered) {
                sb.setColor(Color.LIGHT_GRAY);
            } else {
                sb.setColor(Color.WHITE);
            }
            sb.draw(ImageMaster.CF_LEFT_ARROW, elfinLeftHb.cX - 24.0F, elfinLeftHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);

            if (!elfinRightHb.hovered) {
                sb.setColor(Color.LIGHT_GRAY);
            } else {
                sb.setColor(Color.WHITE);
            }
            sb.draw(ImageMaster.CF_RIGHT_ARROW, elfinRightHb.cX - 24.0F, elfinRightHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);


            elfinLeftHb.render(sb);
            elfinRightHb.render(sb);
        }
    }
}
