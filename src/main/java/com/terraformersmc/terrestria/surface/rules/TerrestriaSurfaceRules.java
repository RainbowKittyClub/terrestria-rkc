package com.terraformersmc.terrestria.surface.rules;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import static net.minecraft.world.level.levelgen.material.MaterialRules.*;

public class TerrestriaSurfaceRules {
	private static final MaterialCondition ON_FLOOR = stoneDepthCheck(0, false, CaveSurface.FLOOR);
	private static final MaterialCondition UNDER_FLOOR = stoneDepthCheck(0, true, CaveSurface.FLOOR);

	public static MaterialRule bootstrap(RegistryAccess registryAccess) {
		HolderGetter<Biome> biomeGetter = registryAccess.lookupOrThrow(Registries.BIOME);

		// Sandy surface rules
		MaterialRule sandAndSandstone = sequence(ifTrue(UNDER_FLOOR,
			block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule redSandAndSandstone = sequence(ifTrue(UNDER_FLOOR,
			block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		// Special dirt surfaces
		MaterialRule oldGrowthSurface = ifTrue(ON_FLOOR, sequence(
			ifTrue(surfaceNoiseThreshold(1.75), block(Blocks.COARSE_DIRT)),
				ifTrue(surfaceNoiseThreshold(-0.95), block(Blocks.PODZOL))));

		// Biome-level rules
		MaterialRule canyon = ifTrue(isBiome(biomeGetter, TerrestriaBiomes.CANYON), sandAndSandstone);
		MaterialRule cypressSwamp = ifTrue(isBiome(biomeGetter, TerrestriaBiomes.CYPRESS_SWAMP),
			ifTrue(ON_FLOOR,
				ifTrue(yBlockCheck(VerticalAnchor.absolute(62), 0),
					ifTrue(not(yBlockCheck(VerticalAnchor.absolute(63), 0)),
						ifTrue(noiseCondition2d(Noises.SWAMP, 0.0D),
							block(Blocks.WATER))))));
		MaterialRule dunes = ifTrue(isBiome(biomeGetter, TerrestriaBiomes.DUNES), sandAndSandstone);
		MaterialRule lushDesert = ifTrue(isBiome(biomeGetter, TerrestriaBiomes.LUSH_DESERT),
			ifTrue(noiseCondition2d(Noises.SURFACE, -0.75D), sandAndSandstone));
		MaterialRule outback = ifTrue(isBiome(biomeGetter, TerrestriaBiomes.OUTBACK),
			ifTrue(noiseCondition2d(Noises.BADLANDS_SURFACE, -0.12D), redSandAndSandstone));
		MaterialRule redwoodForest = ifTrue(isBiome(biomeGetter, TerrestriaBiomes.REDWOOD_FOREST), oldGrowthSurface);

		// Return a surface-only sequence of our surface rules
		return ifTrue(abovePreliminarySurface(),
				sequence(canyon, cypressSwamp, dunes, lushDesert, outback, redwoodForest));
	}

	private static MaterialRule block(Block block) {
		return MaterialRules.state(block.defaultBlockState());
	}

	private static MaterialCondition surfaceNoiseThreshold(double min) {
		return MaterialRules.noiseCondition2d(Noises.SURFACE, min / 8.25, Double.MAX_VALUE);
	}

	public static void init() {
	}
}
