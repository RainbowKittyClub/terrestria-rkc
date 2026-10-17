package com.terraformersmc.terrestria.surface;

import net.minecraft.world.level.levelgen.RandomState;
import org.jspecify.annotations.Nullable;

// The RandomState of the chunk whose surface this thread is building; set by MixinSurfaceSystem.
public final class SurfaceRandomState {
	private static final ThreadLocal<RandomState> CURRENT = new ThreadLocal<>();

	private SurfaceRandomState() {
	}

	public static @Nullable RandomState current() {
		return CURRENT.get();
	}

	public static void set(@Nullable RandomState randomState) {
		if (randomState == null) {
			CURRENT.remove();
		} else {
			CURRENT.set(randomState);
		}
	}
}
