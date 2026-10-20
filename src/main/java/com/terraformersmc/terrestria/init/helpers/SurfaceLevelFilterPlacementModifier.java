package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;

public class SurfaceLevelFilterPlacementModifier implements PlacementFilter {
	public static final MapCodec<SurfaceLevelFilterPlacementModifier> MODIFIER_CODEC =
			RecordCodecBuilder.mapCodec(instance -> instance.group(
					Heightmap.Types.CODEC.fieldOf("heightmap").forGetter(arg -> arg.heightmap),
					Codec.INT.optionalFieldOf("min_inclusive", Integer.MIN_VALUE).forGetter(arg -> arg.min),
					Codec.INT.optionalFieldOf("max_inclusive", Integer.MAX_VALUE).forGetter(arg -> arg.max))
				.apply(instance, SurfaceLevelFilterPlacementModifier::new));
	private final Heightmap.Types heightmap;
	private final int min;
	private final int max;

	private SurfaceLevelFilterPlacementModifier(Heightmap.Types heightmap, int min, int max) {
		this.heightmap = heightmap;
		this.min = min;
		this.max = max;
	}

	public static SurfaceLevelFilterPlacementModifier of(Heightmap.Types heightmap, int min, int max) {
		return new SurfaceLevelFilterPlacementModifier(heightmap, min, max);
	}

	@Override
	public boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
		long l = context.getHeight(this.heightmap, pos.getX(), pos.getZ());
		return this.min <= l && this.max >= l;
	}

	@Override
	public MapCodec<SurfaceLevelFilterPlacementModifier> codec() {
		return TerrestriaPlacementModifierType.SURFACE_LEVEL_FILTER;
	}
}
