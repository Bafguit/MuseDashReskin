package MuseDashReskin.bg;

import MuseDashReskin.ReMuseMod;
import MuseDashReskin.spine.AnimationState;
import MuseDashReskin.spine.SkeletonData;
import MuseDashReskin.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import com.megacrit.cardcrawl.scenes.TitleCloud;

import java.util.Iterator;

import static MuseDashReskin.ReMuseMod.isAnimation;
import static MuseDashReskin.ReMuseMod.mip;

public abstract class BgData extends TitleBackground {

    public static MuseDashReskin.spine.SkeletonRenderer sr = new MuseDashReskin.spine.SkeletonRenderer();

    private Texture bg;
    private TextureAtlas atlas;
    private MuseDashReskin.spine.Skeleton skeleton;
    private MuseDashReskin.spine.AnimationState state;
    private MuseDashReskin.spine.AnimationStateData stateData;
    private String atlasUrl;
    private String jsonUrl;

    protected String nameKey;
    protected String animKey;
    protected float x;
    protected float y;

    private float timer = 1.0F;

    public BgData(String nameKey, String animKey, float x, float y) {
        this.x = x;
        this.y = y;
        this.nameKey = nameKey;
        this.animKey = animKey;
        String temp = "";
        if(this.nameKey == "welcome_11") {
            temp = "welcome11";
        } else {
            temp = this.nameKey;
        }
        System.out.println("Bg Loading###   nameKey: " + this.nameKey + "   animKey: " + temp);
        this.bg = TextureLoader.getTexture(mip("bg/" + this.nameKey + "/bg.png"));
        this.atlasUrl = mip("bg/" + this.nameKey + "/" + temp + ".atlas");
        this.jsonUrl = mip("bg/" + this.nameKey + "/" + temp + ".json");
        this.loadAnimation();
    }

    private void loadAnimation() {
        this.atlas = new TextureAtlas(Gdx.files.internal(this.atlasUrl));
        MuseDashReskin.spine.SkeletonJson json = new MuseDashReskin.spine.SkeletonJson(this.atlas);
        json.setScale(Settings.renderScale / 2.0f);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(this.jsonUrl));
        this.skeleton = new MuseDashReskin.spine.Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new MuseDashReskin.spine.AnimationStateData(skeletonData);
        this.state = new MuseDashReskin.spine.AnimationState(this.stateData);
        AnimationState.TrackEntry e = this.state.setAnimation(0, this.animKey, true);
        this.skeleton.setPosition(this.x * Settings.scale, this.y * Settings.scale);
        this.skeleton.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
        this.skeleton.setFlip(false, false);
        e.setTimeScale(ReMuseMod.isAnimation ? 0.0f : 1.0f);
    }

    public void update() {
        if (InputHelper.justClickedLeft && !this.activated) {
            this.activated = true;
            this.timer = 1.0F;
        }

        if (this.activated && this.timer != 0.0F) {
            this.timer -= Gdx.graphics.getDeltaTime();
            if (this.timer < 0.0F) {
                this.timer = 0.0F;
            }

            if (this.timer < 1.0F) {
                this.slider = Interpolation.pow4In.apply(0.0F, 1.0F, this.timer);
            }
        }
        if(isAnimation) {
            this.state.getCurrent(0).setTrackTime(0.0f);
        }
    }

    public void render(SpriteBatch sb) {
        sr.setPremultipliedAlpha(true);
        if(this.activated) {
            if (this.bg != null) {
                sb.draw(this.bg, 0 * Settings.scale, 0 * Settings.scale, 1920 * Settings.scale, 1200 * Settings.scale);
            }
            if (this.atlas != null) {
                this.state.update(Gdx.graphics.getDeltaTime());
                this.state.apply(this.skeleton);
                this.state.getCurrent(0).setTimeScale(ReMuseMod.isAnimation ? 0.0f : 1.0f);
                this.skeleton.updateWorldTransform();
                this.skeleton.setPosition(this.x * Settings.scale, this.y * Settings.scale);
                this.skeleton.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                this.skeleton.setFlip(false, false);
                sb.end();
                CardCrawlGame.psb.begin();
                sr.draw(CardCrawlGame.psb, this.skeleton);
                CardCrawlGame.psb.end();
                sb.begin();
            }
        }
    }
}
