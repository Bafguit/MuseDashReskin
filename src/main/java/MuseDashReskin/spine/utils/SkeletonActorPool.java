/******************************************************************************
 * Spine Runtimes Software License v2.5
 *
 * Copyright (c) 2013-2016, Esoteric Software
 * All rights reserved.
 *
 * You are granted a perpetual, non-exclusive, non-sublicensable, and
 * non-transferable license to use, install, execute, and perform the Spine
 * Runtimes software and derivative works solely for personal or internal
 * use. Without the written permission of Esoteric Software (see Section 2 of
 * the Spine Software License Agreement), you may not (a) modify, translate,
 * adapt, or develop new applications using the Spine Runtimes or otherwise
 * create derivative works or improvements of the Spine Runtimes or (b) remove,
 * delete, alter, or obscure any trademarks or any copyright, trademark, patent,
 * or other intellectual property or proprietary rights notices on or in the
 * Software, including any copy thereof. Redistributions in binary or source
 * form must include this license and terms.
 *
 * THIS SOFTWARE IS PROVIDED BY ESOTERIC SOFTWARE "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL ESOTERIC SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES, BUSINESS INTERRUPTION, OR LOSS OF
 * USE, DATA, OR PROFITS) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *****************************************************************************/

package MuseDashReskin.spine.utils;

import MuseDashReskin.spine.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class SkeletonActorPool extends Pool<MuseDashReskin.spine.utils.SkeletonActor> {
	private MuseDashReskin.spine.SkeletonRenderer renderer;
	MuseDashReskin.spine.SkeletonData skeletonData;
	MuseDashReskin.spine.AnimationStateData stateData;
	private final Pool<MuseDashReskin.spine.Skeleton> skeletonPool;
	private final Pool<MuseDashReskin.spine.AnimationState> statePool;
	private final Array<MuseDashReskin.spine.utils.SkeletonActor> obtained;

	public SkeletonActorPool (MuseDashReskin.spine.SkeletonRenderer renderer, MuseDashReskin.spine.SkeletonData skeletonData, MuseDashReskin.spine.AnimationStateData stateData) {
		this(renderer, skeletonData, stateData, 16, Integer.MAX_VALUE);
	}

	public SkeletonActorPool (SkeletonRenderer renderer, SkeletonData skeletonData, AnimationStateData stateData,
                              int initialCapacity, int max) {
		super(initialCapacity, max);

		this.renderer = renderer;
		this.skeletonData = skeletonData;
		this.stateData = stateData;

		obtained = new Array(false, initialCapacity);

		skeletonPool = new Pool<MuseDashReskin.spine.Skeleton>(initialCapacity, max) {
			protected MuseDashReskin.spine.Skeleton newObject () {
				return new MuseDashReskin.spine.Skeleton(SkeletonActorPool.this.skeletonData);
			}

			protected void reset (Skeleton skeleton) {
				skeleton.setColor(Color.WHITE);
				skeleton.setFlip(false, false);
				skeleton.setSkin((Skin)null);
				skeleton.setSkin(SkeletonActorPool.this.skeletonData.getDefaultSkin());
				skeleton.setToSetupPose();
			}
		};

		statePool = new Pool<MuseDashReskin.spine.AnimationState>(initialCapacity, max) {
			protected MuseDashReskin.spine.AnimationState newObject () {
				return new MuseDashReskin.spine.AnimationState(SkeletonActorPool.this.stateData);
			}

			protected void reset (MuseDashReskin.spine.AnimationState state) {
				state.clearTracks();
				state.clearListeners();
			}
		};
	}

	/** Each obtained skeleton actor that is no longer playing an animation is removed from the stage and returned to the pool. */
	public void freeComplete () {
		Array<MuseDashReskin.spine.utils.SkeletonActor> obtained = this.obtained;
		outer:
		for (int i = obtained.size - 1; i >= 0; i--) {
			MuseDashReskin.spine.utils.SkeletonActor actor = obtained.get(i);
			Array<MuseDashReskin.spine.AnimationState.TrackEntry> tracks = actor.state.getTracks();
			for (int ii = 0, nn = tracks.size; ii < nn; ii++)
				if (tracks.get(ii) != null) continue outer;
			free(actor);
		}
	}

	protected MuseDashReskin.spine.utils.SkeletonActor newObject () {
		MuseDashReskin.spine.utils.SkeletonActor actor = new MuseDashReskin.spine.utils.SkeletonActor();
		actor.setRenderer(renderer);
		return actor;
	}

	/** This pool keeps a reference to the obtained instance, so it should be returned to the pool via {@link #free(MuseDashReskin.spine.utils.SkeletonActor)}
	 * , {@link #freeAll(Array)} or {@link #freeComplete()} to avoid leaking memory. */
	public MuseDashReskin.spine.utils.SkeletonActor obtain () {
		MuseDashReskin.spine.utils.SkeletonActor actor = super.obtain();
		actor.setSkeleton(skeletonPool.obtain());
		actor.setAnimationState(statePool.obtain());
		obtained.add(actor);
		return actor;
	}

	protected void reset (MuseDashReskin.spine.utils.SkeletonActor actor) {
		actor.remove();
		obtained.removeValue(actor, true);
		skeletonPool.free(actor.getSkeleton());
		statePool.free(actor.getAnimationState());
	}

	public Array<SkeletonActor> getObtained () {
		return obtained;
	}
}
