package com.terraformersmc.terrestria.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record CattailFeature(float probability, Block normal, Block tall) implements Feature {
	public static final MapCodec<CattailFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(CattailFeature::probability),
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("normal").forGetter(CattailFeature::normal),
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("tall").forGetter(CattailFeature::tall)
	).apply(instance, CattailFeature::new));

	@Override
	public MapCodec<CattailFeature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
		int x = random.nextInt(8) - random.nextInt(8);
		int z = random.nextInt(8) - random.nextInt(8);
		int y = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, new BlockPos(origin.getX() + x, 0, origin.getZ() + z)).getY();

		BlockPos candidate = new BlockPos(origin.getX() + x, y, origin.getZ() + z);

		if (level.getBlockState(candidate).getBlock() == Blocks.WATER) {
			boolean tall = random.nextDouble() < this.probability;
			BlockState grass = tall ? this.tall.defaultBlockState() : this.normal.defaultBlockState();

			if (grass.canSurvive(level, candidate)) {
				if (tall) {
					BlockState grassTop = grass.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
					BlockPos upper = candidate.above();

					if (level.getBlockState(upper).getBlock() == Blocks.AIR) {
						level.setBlock(candidate, grass, 2);
						level.setBlock(upper, grassTop, 2);
					}
				} else {
					level.setBlock(candidate, grass, 2);
				}
				return true;
			}
		}

		return false;
	}
}
