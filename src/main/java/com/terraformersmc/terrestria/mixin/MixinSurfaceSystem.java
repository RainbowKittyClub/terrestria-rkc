package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.surface.SurfaceRandomState;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.material.MaterialSystem;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(MaterialSystem.class)
public class MixinSurfaceSystem {
	// Biolith calls our surface builders from inside buildSurface, so they see the RandomState of
	// the level being generated instead of needing a server (which does not exist yet at spawn).
	@Inject(method = "buildSurface", at = @At("HEAD"))
	@SuppressWarnings("unused")
	private void terrestria$setSurfaceRandomState(RandomState randomState, BiomeManager biomeManager, WorldGenerationContext generationContext, ChunkAccess protoChunk, NoiseChunk noiseChunk, MaterialRule ruleSource, Set<Holder<Biome>> possibleBiomes, CallbackInfo ci) {
		SurfaceRandomState.set(randomState);
	}

	@Inject(method = "buildSurface", at = @At("RETURN"))
	@SuppressWarnings("unused")
	private void terrestria$clearSurfaceRandomState(CallbackInfo ci) {
		SurfaceRandomState.set(null);
	}
}
