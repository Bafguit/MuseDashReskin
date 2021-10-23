package MuseDashReskin.Skins.data;

import MuseDashReskin.ReMuseMod;
import MuseDashReskin.patches.MainScreenAnimation;
import MuseDashReskin.spine.AnimationState;
import MuseDashReskin.spine.SkeletonData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

import static MuseDashReskin.ReMuseMod.*;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.*;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass.IRONCLAD;
import static com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass.THE_SILENT;

public abstract class SkinData {
    protected CharacterStrings characterStrings;

    public TextureAtlas campAtlas;
    public MuseDashReskin.spine.Skeleton campSkeleton;
    public MuseDashReskin.spine.AnimationState campState;
    public MuseDashReskin.spine.AnimationStateData campStateData;

    public TextureAtlas mainAtlas;
    public MuseDashReskin.spine.Skeleton mainSkeleton;
    public MuseDashReskin.spine.AnimationState mainState;
    public MuseDashReskin.spine.AnimationStateData mainStateData;

    public String atlasUrl;
    public String jsonUrl;

    public String campAtlasUrl;
    public String campJsonUrl;

    public String mainAtlasUrl;
    public String mainJsonUrl;

    public String charName;
    public String charText;
    public String campKey;
    public String mainKey;
    public String name;
    public String voiceName;
    public String cls;
    public float scale;
    public int infoNumber;
    public int voiceNumber;

    public enum UrlType {
        SD, CAMP, MAIN
    }

    public enum FileType {
        ATLAS, JSON
    }

    public SkinData(PlayerClass c, int number, String name, String campKey, String mainKey, int voiceNumber, String voiceName) {
        this(c, number, name, campKey, mainKey, 2.0f, voiceNumber, voiceName);
    }

    public SkinData(PlayerClass c, int number, String name, String campKey, String mainKey, float scale, int voiceNumber, String voiceName) {
        this.cls = getClass(c);
        this.name = name;
        this.campKey = campKey;
        this.mainKey = mainKey;
        this.scale = scale;
        this.infoNumber = number;
        this.voiceNumber = voiceNumber;
        this.voiceName = voiceName.toUpperCase();
        this.atlasUrl = generateUrl(UrlType.SD, FileType.ATLAS);
        this.jsonUrl = generateUrl(UrlType.SD, FileType.JSON);
        this.campAtlasUrl = generateUrl(UrlType.CAMP, FileType.ATLAS);
        this.campJsonUrl = generateUrl(UrlType.CAMP, FileType.JSON);
        this.mainAtlasUrl = generateUrl(UrlType.MAIN, FileType.ATLAS);
        this.mainJsonUrl = generateUrl(UrlType.MAIN, FileType.JSON);

        this.characterStrings = CardCrawlGame.languagePack.getCharacterString(ReMuseMod.makeID(this.name));
        this.charName = this.characterStrings.NAMES[0];
        this.charText = this.characterStrings.TEXT[0];
    }

    protected String generateUrl(UrlType type, FileType ft) {
        String s;
        if(type == UrlType.SD) {
            s = mcp(this.cls + "/" + this.name + "/char_" + this.infoNumber + "_" + this.name);
        } else if (type == UrlType.CAMP) {
            s = mcp(this.cls + "/" + this.name + "/" + this.infoNumber + "_" + this.name + "_main_show");
        } else {
            s = mcp(this.cls + "/" + this.name + "/" + this.infoNumber + "_" + this.name + "_victory");
        }

        if(ft == FileType.ATLAS) {
            return s + ".atlas";
        } else {
            return s + ".json";
        }
    }

    public void loadAnimation() {
        loadCampAnimation();
        loadMainAnimation();
        System.out.println("Skin Animation Loaded!   Class: " + this.cls + "   Skin: " + this.name);
    }

    private void loadCampAnimation() {
        this.campAtlas = new TextureAtlas(Gdx.files.internal(this.campAtlasUrl));
        MuseDashReskin.spine.SkeletonJson json = new MuseDashReskin.spine.SkeletonJson(this.campAtlas);
        json.setScale(Settings.renderScale / 2.0f);
        MuseDashReskin.spine.SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(this.campJsonUrl));
        this.campSkeleton = new MuseDashReskin.spine.Skeleton(skeletonData);
        this.campSkeleton.setColor(Color.WHITE);
        this.campStateData = new MuseDashReskin.spine.AnimationStateData(skeletonData);
        this.campState = new MuseDashReskin.spine.AnimationState(this.campStateData);
        AnimationState.TrackEntry e = this.campState.setAnimation(0, this.campKey, true);
        e.setTimeScale(ReMuseMod.isAnimation ? 0.0f : 1.0f);
    }

    private void loadMainAnimation() {
        this.mainAtlas = new TextureAtlas(Gdx.files.internal(this.mainAtlasUrl));
        MuseDashReskin.spine.SkeletonJson json = new MuseDashReskin.spine.SkeletonJson(this.mainAtlas);
        json.setScale(Settings.renderScale / this.scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(this.mainJsonUrl));
        this.mainSkeleton = new MuseDashReskin.spine.Skeleton(skeletonData);
        this.mainSkeleton.setColor(Color.WHITE);
        this.mainStateData = new MuseDashReskin.spine.AnimationStateData(skeletonData);
        this.mainState = new MuseDashReskin.spine.AnimationState(this.mainStateData);
        AnimationState.TrackEntry e = this.mainState.setAnimation(0, this.mainKey, true);
        this.mainSkeleton.setPosition(1200 * Settings.scale, -120 * Settings.scale);
        this.mainSkeleton.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
        this.mainSkeleton.setFlip(false, false);
        e.setTimeScale(ReMuseMod.isAnimation ? 0.0f : 1.0f);
    }

    public void renderCampAnimation(SpriteBatch sb) {
        if (this.campAtlas != null) {
            this.campState.update(Gdx.graphics.getDeltaTime());
            this.campState.apply(this.campSkeleton);
            this.campState.getCurrent(0).setTimeScale(ReMuseMod.isAnimation ? 0.0f : 1.0f);
            this.campSkeleton.updateWorldTransform();
            this.campSkeleton.setPosition(390 * Settings.scale, -90 * Settings.scale);
            this.campSkeleton.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
            this.campSkeleton.setFlip(false, false);
            sb.end();
            CardCrawlGame.psb.begin();
            MainScreenAnimation.sr.draw(CardCrawlGame.psb, this.campSkeleton);
            CardCrawlGame.psb.end();
            sb.begin();
        }
    }

    public void renderMainAnimation(SpriteBatch sb) {
        if (this.mainAtlas != null) {
            this.mainState.update(Gdx.graphics.getDeltaTime());
            this.mainState.apply(this.mainSkeleton);
            this.mainState.getCurrent(0).setTimeScale(ReMuseMod.isAnimation ? 0.0f : 1.0f);
            this.mainSkeleton.updateWorldTransform();
            this.mainSkeleton.setPosition(1200 * Settings.scale, -120 * Settings.scale);
            this.mainSkeleton.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
            this.mainSkeleton.setFlip(false, false);
            sb.end();
            CardCrawlGame.psb.begin();
            MainScreenAnimation.sr.draw(CardCrawlGame.psb, this.mainSkeleton);
            CardCrawlGame.psb.end();
            sb.begin();
        }
    }

    protected static String getClass(PlayerClass c) {
        if(c == PlayerClass.IRONCLAD) return "ironclad";
        else if (c == THE_SILENT) return "silent";
        else if (c == PlayerClass.DEFECT) return "defect";
        else if (c == PlayerClass.WATCHER) return "watcher";
        else return "defect";
    }
}
