package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaPlacementModifierType {
	public static final MapCodec<SurfaceLevelFilterPlacementModifier> SURFACE_LEVEL_FILTER = register("surface_level_filter", SurfaceLevelFilterPlacementModifier.MODIFIER_CODEC);

	public static void init() {
	}

	private static <P extends PlacementModifier> MapCodec<P> register(String id, MapCodec<P> codec) {
		return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, id, codec);
	}
}
