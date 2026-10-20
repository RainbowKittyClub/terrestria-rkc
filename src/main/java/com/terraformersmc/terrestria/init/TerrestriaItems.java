package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.item.LogTurnerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Objects;

// This class exports public item constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaItems {
	public static WoodItems REDWOOD;
	public static WoodItems HEMLOCK;
	public static WoodItems RUBBER;
	public static WoodItems CYPRESS;
	public static WoodItems WILLOW;
	public static WoodItems JAPANESE_MAPLE;
	public static WoodItems RAINBOW_EUCALYPTUS;
	public static WoodItems SAKURA;
	public static WoodItems YUCCA_PALM;

	public static BlockItem SMALL_OAK_LOG;
	public static BlockItem STRIPPED_SMALL_OAK_LOG;
	public static BlockItem SAGUARO_CACTUS;

	public static BlockItem STACKED_OAK_LOGS;
	public static BlockItem STACKED_SAKURA_LOGS;
	public static BlockItem STACKED_YUCCA_PALM_LOGS;
	public static BlockItem STACKED_STRIPPED_OAK_LOGS;
	public static BlockItem STACKED_STRIPPED_SAKURA_LOGS;
	public static BlockItem STACKED_STRIPPED_YUCCA_PALM_LOGS;

	public static BlockItem JAPANESE_MAPLE_SHRUB_LEAVES;
	public static BlockItem DARK_JAPANESE_MAPLE_LEAVES;
	public static BlockItem JUNGLE_PALM_LEAVES;

	public static BlockItem CATTAIL;

	public static BlockItem BRYCE_SAPLING;
	public static BlockItem REDWOOD_SAPLING;
	public static BlockItem HEMLOCK_SAPLING;
	public static BlockItem RUBBER_SAPLING;
	public static BlockItem CYPRESS_SAPLING;
	public static BlockItem WILLOW_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SHRUB_SAPLING;
	public static BlockItem DARK_JAPANESE_MAPLE_SAPLING;
	public static BlockItem RAINBOW_EUCALYPTUS_SAPLING;
	public static BlockItem SAKURA_SAPLING;
	public static BlockItem JUNGLE_PALM_SAPLING;
	public static BlockItem SAGUARO_CACTUS_SAPLING;
	public static BlockItem YUCCA_PALM_SAPLING;

	public static StoneItems VOLCANIC_ROCK;
	public static BlockItem VOLCANIC_SAND;
	public static BlockItem ANDISOL;
	public static BlockItem ANDISOL_GRASS_BLOCK;
	public static BlockItem ANDISOL_DIRT_PATH;
	public static BlockItem ANDISOL_PODZOL;
	public static BlockItem ANDISOL_FARMLAND;
	public static BlockItem INDIAN_PAINTBRUSH;
	public static BlockItem MONSTERAS;

	public static BlockItem TINY_CACTUS;
	public static BlockItem AGAVE;
	public static BlockItem ALOE_VERA;
	public static BlockItem DEAD_GRASS;

	public static LogTurnerItem LOG_TURNER;

	public static void init() {
		// Because DirtBlocks are nullable.
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.dirtBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.dirtPathBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.grassBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.podzolBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.farmBlock());

		REDWOOD = WoodItems.register(TerrestriaBlocks.REDWOOD);
		HEMLOCK = WoodItems.register(TerrestriaBlocks.HEMLOCK);
		RUBBER = WoodItems.register(TerrestriaBlocks.RUBBER);
		CYPRESS = WoodItems.register(TerrestriaBlocks.CYPRESS);
		WILLOW = WoodItems.register(TerrestriaBlocks.WILLOW);
		JAPANESE_MAPLE = WoodItems.register(TerrestriaBlocks.JAPANESE_MAPLE);
		RAINBOW_EUCALYPTUS = WoodItems.register(TerrestriaBlocks.RAINBOW_EUCALYPTUS);
		SAKURA = WoodItems.register(TerrestriaBlocks.SAKURA);
		YUCCA_PALM = WoodItems.register(TerrestriaBlocks.YUCCA_PALM);

		SMALL_OAK_LOG = TerrestriaRegistry.registerBlockItem("small_oak_log", TerrestriaBlocks.SMALL_OAK_LOG, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STRIPPED_SMALL_OAK_LOG = TerrestriaRegistry.registerBlockItem("stripped_small_oak_log", TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		SAGUARO_CACTUS = TerrestriaRegistry.registerBlockItem("saguaro_cactus", TerrestriaBlocks.SAGUARO_CACTUS, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM));

		STACKED_OAK_LOGS = TerrestriaRegistry.registerBlockItem("stacked_oak_logs", TerrestriaBlocks.STACKED_OAK_LOGS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_SAKURA_LOGS = TerrestriaRegistry.registerBlockItem("stacked_sakura_logs", TerrestriaBlocks.STACKED_SAKURA_LOGS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_YUCCA_PALM_LOGS = TerrestriaRegistry.registerBlockItem("stacked_yucca_palm_logs", TerrestriaBlocks.STACKED_YUCCA_PALM_LOGS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_STRIPPED_OAK_LOGS = TerrestriaRegistry.registerBlockItem("stacked_stripped_oak_logs", TerrestriaBlocks.STACKED_STRIPPED_OAK_LOGS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_STRIPPED_SAKURA_LOGS = TerrestriaRegistry.registerBlockItem("stacked_stripped_sakura_logs", TerrestriaBlocks.STACKED_STRIPPED_SAKURA_LOGS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_STRIPPED_YUCCA_PALM_LOGS = TerrestriaRegistry.registerBlockItem("stacked_stripped_yucca_palm_logs", TerrestriaBlocks.STACKED_STRIPPED_YUCCA_PALM_LOGS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.registerBlockItem("japanese_maple_shrub_leaves", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.registerBlockItem("dark_japanese_maple_leaves", TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		JUNGLE_PALM_LEAVES = TerrestriaRegistry.registerBlockItem("jungle_palm_leaves", TerrestriaBlocks.JUNGLE_PALM_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));

		CATTAIL = TerrestriaRegistry.registerBlockItem("cattail", TerrestriaBlocks.CATTAIL, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));

		BRYCE_SAPLING = TerrestriaRegistry.registerBlockItem("bryce_sapling", TerrestriaBlocks.BRYCE_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		REDWOOD_SAPLING = TerrestriaRegistry.registerBlockItem("redwood_sapling", TerrestriaBlocks.REDWOOD_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		HEMLOCK_SAPLING = TerrestriaRegistry.registerBlockItem("hemlock_sapling", TerrestriaBlocks.HEMLOCK_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		RUBBER_SAPLING = TerrestriaRegistry.registerBlockItem("rubber_sapling", TerrestriaBlocks.RUBBER_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		CYPRESS_SAPLING = TerrestriaRegistry.registerBlockItem("cypress_sapling", TerrestriaBlocks.CYPRESS_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		WILLOW_SAPLING = TerrestriaRegistry.registerBlockItem("willow_sapling", TerrestriaBlocks.WILLOW_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlockItem("japanese_maple_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.registerBlockItem("japanese_maple_shrub_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlockItem("dark_japanese_maple_sapling", TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.registerBlockItem("rainbow_eucalyptus_sapling", TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		SAKURA_SAPLING = TerrestriaRegistry.registerBlockItem("sakura_sapling", TerrestriaBlocks.SAKURA_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		JUNGLE_PALM_SAPLING = TerrestriaRegistry.registerBlockItem("jungle_palm_sapling", TerrestriaBlocks.JUNGLE_PALM_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.registerBlockItem("saguaro_cactus_sapling", TerrestriaBlocks.SAGUARO_CACTUS_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		YUCCA_PALM_SAPLING = TerrestriaRegistry.registerBlockItem("yucca_palm_sapling", TerrestriaBlocks.YUCCA_PALM_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));

		ANDISOL = TerrestriaRegistry.registerBlockItem("andisol", TerrestriaBlocks.ANDISOL.dirtBlock());
		ANDISOL_DIRT_PATH = TerrestriaRegistry.registerBlockItem("andisol_dirt_path", TerrestriaBlocks.ANDISOL.dirtPathBlock());
		ANDISOL_FARMLAND = TerrestriaRegistry.registerBlockItem("andisol_farmland", TerrestriaBlocks.ANDISOL.farmBlock());
		ANDISOL_GRASS_BLOCK = TerrestriaRegistry.registerBlockItem("andisol_grass_block", TerrestriaBlocks.ANDISOL.grassBlock());
		ANDISOL_PODZOL = TerrestriaRegistry.registerBlockItem("andisol_podzol", TerrestriaBlocks.ANDISOL.podzolBlock());

		VOLCANIC_ROCK = StoneItems.register("volcanic_rock", TerrestriaBlocks.VOLCANIC_ROCK);
		VOLCANIC_SAND = TerrestriaRegistry.registerBlockItem("volcanic_sand", TerrestriaBlocks.VOLCANIC_SAND);

		INDIAN_PAINTBRUSH = TerrestriaRegistry.registerBlockItem("indian_paintbrush", TerrestriaBlocks.INDIAN_PAINTBRUSH, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
		MONSTERAS = TerrestriaRegistry.registerBlockItem("monsteras", TerrestriaBlocks.MONSTERAS, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));

		TINY_CACTUS = TerrestriaRegistry.registerBlockItem("tiny_cactus", TerrestriaBlocks.TINY_CACTUS, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM));
		AGAVE = TerrestriaRegistry.registerBlockItem("agave", TerrestriaBlocks.AGAVE, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
		ALOE_VERA = TerrestriaRegistry.registerBlockItem("aloe_vera", TerrestriaBlocks.ALOE_VERA, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
		DEAD_GRASS = TerrestriaRegistry.registerBlockItem("dead_grass", TerrestriaBlocks.DEAD_GRASS, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));

		LOG_TURNER = TerrestriaRegistry.register("log_turner", LogTurnerItem::new, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL));
	}
}
