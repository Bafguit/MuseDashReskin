package MuseDashReskin.elfins.data;

import MuseDashReskin.ReMuseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class ElfinData {
    protected TextureAtlas atlas;
    protected Skeleton skeleton;
    public AnimationState state;
    protected AnimationStateData stateData;

    public float drawX;
    public float drawY;
    public String name;
    public String nameKey;
    public String stateKey;

    public ElfinData(String name, String key) {
        this.drawX = (float)Settings.WIDTH * 0.125F;
        this.drawY = AbstractDungeon.floorY;
        this.nameKey = name;
        this.stateKey = key;
        this.loadAnimation();
    }

    public void loadAnimation() {
        this.atlas = new TextureAtlas(Gdx.files.internal(ReMuseMod.mip("elfin/" + this.nameKey + "/elfin_" + this.nameKey + ".atlas")));
        SkeletonJson json = new SkeletonJson(this.atlas);
        json.setScale(Settings.renderScale / 1.5f);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(ReMuseMod.mip("elfin/" + this.nameKey + "/elfin_" + this.nameKey + ".json")));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);
        AnimationState.TrackEntry e = this.state.setAnimation(0, this.stateKey, true);
        e.setTimeScale(1.0F);
    }

    public void render(SpriteBatch sb, AbstractPlayer player) {
        if (this.atlas != null) {
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(player.drawX + (player.flipHorizontal ? this.drawX : -this.drawX),
                    player.drawY + (player.flipVertical ? -this.drawY : this.drawY));
            this.skeleton.setColor(Color.WHITE);
            this.skeleton.setFlip(player.flipHorizontal, player.flipVertical);
            sb.end();
            CardCrawlGame.psb.begin();
            AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
        }
    }

    public void render(SpriteBatch sb) {
        if (this.atlas != null) {
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(350 * Settings.scale, 900 * Settings.scale);
            this.skeleton.setColor(Color.WHITE);
            this.skeleton.setFlip(false, false);
            sb.end();
            CardCrawlGame.psb.begin();
            AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
        }
    }
}
