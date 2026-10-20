package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class WoodItems {
	private final String name;
	private final Identifier id;

	public final BlockItem log;
	public final BlockItem quarterLog;
	public final BlockItem wood;
	public final BlockItem leaves;
	public final BlockItem leafPile;
	public final BlockItem planks;
	public final BlockItem slab;
	public final BlockItem stairs;
	public final BlockItem fence;
	public final BlockItem fenceGate;
	public final BlockItem door;
	public final BlockItem button;
	public final BlockItem pressurePlate;
	public final StandingAndWallBlockItem sign;
	public final HangingSignItem hangingSign;
	public final BlockItem trapdoor;
	public final BlockItem shelf;
	public final BlockItem strippedLog;
	public final BlockItem strippedQuarterLog;
	public final BlockItem strippedWood;
	public final Item boat;
	public final Item chestBoat;

	private WoodItems(WoodBlocks blocks) {
		this.name = blocks.getName();
		this.id = blocks.getId();

		log = TerrestriaRegistry.registerBlockItem(name + "_log", blocks.log, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		leaves = TerrestriaRegistry.registerBlockItem(name + "_leaves", blocks.leaves, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		if (blocks.hasLeafPile()) {
			leafPile = TerrestriaRegistry.registerBlockItem(name + "_leaf_pile", blocks.leafPile, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		} else {
			leafPile = null;
		}
		planks = TerrestriaRegistry.registerBlockItem(name + "_planks", blocks.planks, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		slab = TerrestriaRegistry.registerBlockItem(name + "_slab", blocks.slab, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
		stairs = TerrestriaRegistry.registerBlockItem(name + "_stairs", blocks.stairs, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		fence = TerrestriaRegistry.registerBlockItem(name + "_fence", blocks.fence, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		fenceGate = TerrestriaRegistry.registerBlockItem(name + "_fence_gate", blocks.fenceGate, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		door = TerrestriaRegistry.registerBlockItem(name + "_door", blocks.door, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
		button = TerrestriaRegistry.registerBlockItem(name + "_button", blocks.button, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
		pressurePlate = TerrestriaRegistry.registerBlockItem(name + "_pressure_plate", blocks.pressurePlate, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		trapdoor = TerrestriaRegistry.registerBlockItem(name + "_trapdoor", blocks.trapdoor, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		shelf = TerrestriaRegistry.registerBlockItem(name + "_shelf", blocks.shelf, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		sign = TerrestriaRegistry.register(name + "_sign", settings -> new StandingAndWallBlockItem(blocks.sign, blocks.wallSign, Direction.DOWN, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText().useBlockDescriptionPrefix());
		hangingSign = TerrestriaRegistry.register(name + "_hanging_sign", settings -> new HangingSignItem(blocks.hangingSign, blocks.wallHangingSign, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText().useBlockDescriptionPrefix());
		strippedLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_log", blocks.strippedLog, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

		Identifier family = Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, name);
		boat = TerraformBoatItemHelper.registerBoatItem(family, boatProperties(), false, false);
		chestBoat = TerraformBoatItemHelper.registerBoatItem(family, boatProperties(), true, false);

		if (blocks.hasWood()) {
			wood = TerrestriaRegistry.registerBlockItem(name + "_wood", blocks.wood, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
			strippedWood = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_wood", blocks.strippedWood, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		} else {
			wood = null;
			strippedWood = null;
		}

		if (blocks.hasQuarterLog()) {
			quarterLog = TerrestriaRegistry.registerBlockItem(name + "_quarter_log", blocks.quarterLog, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
			strippedQuarterLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_quarter_log", blocks.strippedQuarterLog, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		} else {
			quarterLog = null;
			strippedQuarterLog = null;
		}
	}


	public static WoodItems register(WoodBlocks blocks) {
		return new WoodItems(blocks);
	}

	private static Item.Properties boatProperties() {
		return new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1);
	}

	public String getName() {
		return name;
	}

	public Identifier getId() {
		return id;
	}

	public boolean hasQuarterLog() {
		return (quarterLog != null && strippedQuarterLog != null);
	}

	public boolean hasLeafPile() {
		return (leafPile != null);
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public boolean hasBoat() {
		return (boat != null && chestBoat != null);
	}
}
