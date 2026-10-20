package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Products.P5;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.BiConsumer;

@NullMarked
public class MegaTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<MegaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
		megaTrunkPlacerParts(instance).apply(instance, MegaTrunkPlacer::new)
	);

	protected final Holder<BlockStateProvider> quarteredTrunkProvider;
	protected final Holder<BlockStateProvider> rootsProvider;

	protected static <P extends MegaTrunkPlacer> P5<RecordCodecBuilder.Mu<P>, Integer, Integer, Integer, Holder<BlockStateProvider>, Holder<BlockStateProvider>> megaTrunkPlacerParts(RecordCodecBuilder.Instance<P> instance) {
		return trunkPlacerParts(instance).and(instance.group(
			BlockStateProvider.CODEC.fieldOf("quartered_trunk_provider").forGetter(placer -> placer.quarteredTrunkProvider),
			BlockStateProvider.CODEC.fieldOf("roots_provider").forGetter(placer -> placer.rootsProvider)
		));
	}

	public MegaTrunkPlacer(int height, int randomHeight, int extraRandomHeight, Holder<BlockStateProvider> quarteredTrunkProvider, Holder<BlockStateProvider> rootsProvider) {
		super(height, randomHeight, extraRandomHeight);

		this.quarteredTrunkProvider = quarteredTrunkProvider;
		this.rootsProvider = rootsProvider;
	}

	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.MEGA;
	}

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeFeature tree) {
		// Set the blocks below the trunk to dirt
		BlockPos down = pos.below();
		placeBelowTrunkBlock(level, replacer, random, down, tree);
		placeBelowTrunkBlock(level, replacer, random, down.east(), tree);
		placeBelowTrunkBlock(level, replacer, random, down.south(), tree);
		placeBelowTrunkBlock(level, replacer, random, down.south().east(), tree);

		// Place the trunk
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(level, mutable, replacer, getState(level, random, mutable, tree, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, i, 0);
			setLog(level, mutable, replacer, getState(level, random, mutable, tree, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, i, 0);
			setLog(level, mutable, replacer, getState(level, random, mutable, tree, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, i, 1);
			setLog(level, mutable, replacer, getState(level, random, mutable, tree, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, i, 1);
		}

		growRoots(replacer, level, pos.mutable(), random, this.rootsProvider.value());

		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(trunkHeight), 0, true));
	}

	private BlockState getState(WorldGenLevel level, RandomSource random, BlockPos pos, TreeFeature tree, QuarterLogBlock.BarkSide side) {
		if (Terrestria.getConfigManager().getGeneralConfig().areQuarterLogsEnabled()) {
			return this.quarteredTrunkProvider.value().getState(level, random, pos).setValue(QuarterLogBlock.BARK_SIDE, side);
		} else {
			return tree.trunkProvider().value().getState(level, random, pos);
		}
	}

	private static void setLog(LevelSimulatedReader level, BlockPos.MutableBlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state, BlockPos blockPos, int i, int j, int k) {
		mutable.setWithOffset(blockPos, i, j, k);

		setLog(level, mutable, replacer, state);
	}

	private static void setLog(LevelSimulatedReader level, BlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state) {
		if (TreeFeature.validTreePos(level, mutable)) {
			replacer.accept(mutable.immutable(), state);
		}
	}

	public void growRoots(BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel level, BlockPos.MutableBlockPos pos, RandomSource random, BlockStateProvider wood) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(replacer, level, pos.set(x - 1, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + 2, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + random.nextInt(2), y, z - 1), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + random.nextInt(2), y, z + 2), random, wood);
	}

	public void tryGrowRoot(BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel level, BlockPos.MutableBlockPos bottom, RandomSource random, BlockStateProvider wood) {
		// Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		// Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		// Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.validTreePos(level, bottom) || TreeFeature.validTreePos(level, bottom) || level.isStateAtPosition(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				replacer.accept(bottom.immutable(), wood.getState(level, random, bottom));
			}

			bottom.move(Direction.UP);
		}
	}
}
