package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.feature.CattailFeature;
import com.terraformersmc.terrestria.feature.misc.DumDumHeadFeature;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfiguration;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaFeatures {
	public static final MapCodec<CattailFeature> CATTAIL = TerrestriaRegistry.register("cattail", CattailFeature.CODEC);
	public static final MapCodec<DumDumHeadFeature> DUM_DUM_HEAD = TerrestriaRegistry.register("dum_dum_head", DumDumHeadFeature.CODEC);
	public static final TreeFeature QUARTERED_MEGA_TREE = TerrestriaRegistry.register("quartered_mega_tree", new TreeFeature(QuarteredMegaTreeConfiguration.getCodec()));

	public static void init() {}
}
