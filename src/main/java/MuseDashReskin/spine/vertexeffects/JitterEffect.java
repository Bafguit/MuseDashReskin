
package MuseDashReskin.spine.vertexeffects;

import MuseDashReskin.spine.Skeleton;
import MuseDashReskin.spine.SkeletonRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class JitterEffect implements SkeletonRenderer.VertexEffect {
	private float x, y;

	public JitterEffect (float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void begin (Skeleton skeleton) {
	}

	public void transform (Vector2 position, Vector2 uv, Color light, Color dark) {
		position.x += MathUtils.randomTriangular(-x, y);
		position.y += MathUtils.randomTriangular(-x, y);
	}

	public void end () {
	}

	public void setJitter (float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setJitterX (float x) {
		this.x = x;
	}

	public void setJitterY (float y) {
		this.y = y;
	}
}
