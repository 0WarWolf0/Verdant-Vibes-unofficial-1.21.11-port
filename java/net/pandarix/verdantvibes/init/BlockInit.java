package net.pandarix.verdantvibes.init;

import java.util.List;
import java.util.function.Function;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.pandarix.verdantvibes.VerdantVibes;
import net.pandarix.verdantvibes.block.custom.TurnableFlowerPotBlock;
import net.pandarix.verdantvibes.block.custom.VerdantGrowablePlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantPetalPlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantPlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantTallPlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantVineBlock;
import org.jetbrains.annotations.Nullable;

public class BlockInit {
	private static final RegistryKey<ItemGroup> TAB = ItemGroupInit.VERDANTVIBES_ITEMGROUP;

	// ----------- Gardening table
	public static final Block GARDENING_TABLE = registerBlock("gardening_table",
			Block::new,
			AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE), TAB);

	// ----------- Plants
	public static final Block MONSTERA = registerBlock("monstera",
			settings -> new VerdantPlantBlock(settings, List.of(), Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 15.0, 13.0)),
			AbstractBlock.Settings.create()
					.mapColor(MapColor.GREEN)
					.noCollision()
					.breakInstantly()
					.sounds(BlockSoundGroup.CHERRY_SAPLING)
					.offset(AbstractBlock.OffsetType.XZ)
					.pistonBehavior(PistonBehavior.DESTROY),
			TAB);

	public static final Block SNAKE_PLANT = registerBlock("snake_plant",
			settings -> new VerdantPlantBlock(settings, List.of(Blocks.SAND, Blocks.RED_SAND), Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)),
			AbstractBlock.Settings.copy(MONSTERA), TAB);

	public static final Block PARLOUR_PALM = registerBlock("parlour_palm",
			settings -> new VerdantPlantBlock(settings, List.of(Blocks.SAND), Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 22.0, 14.0)),
			AbstractBlock.Settings.copy(MONSTERA), TAB);

	public static final Block MONEY_TREE = registerBlock("money_tree",
			settings -> new VerdantPlantBlock(settings, List.of(Blocks.MUD), Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 10.0, 13.0)),
			AbstractBlock.Settings.copy(MONSTERA), TAB);

	public static final Block LOBELIA = registerBlock("lobelia",
			settings -> new VerdantPlantBlock(settings, List.of(), Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 14.0, 13.0)),
			AbstractBlock.Settings.copy(MONSTERA), TAB);

	public static final VerdantTallPlantBlock TALL_DRAGON_TREE = registerBlock("tall_dragon_tree",
			settings -> new VerdantTallPlantBlock(settings, List.of(Blocks.SAND), Block.createCuboidShape(2.0, 0.0, 3.0, 13.0, 16.0, 13.0)),
			AbstractBlock.Settings.copy(MONSTERA).sounds(BlockSoundGroup.GRASS).strength(0.2F), null, false);

	public static final Block DRAGON_TREE = registerBlock("dragon_tree",
			settings -> new VerdantGrowablePlantBlock(settings, List.of(Blocks.SAND), Block.createCuboidShape(2.0, 0.0, 3.0, 13.0, 23.0, 13.0), TALL_DRAGON_TREE),
			AbstractBlock.Settings.copy(TALL_DRAGON_TREE), TAB);

	public static final Block CANDY_TUFT = registerBlock("candy_tuft",
			settings -> new VerdantPetalPlantBlock(settings, List.of(Blocks.GRAVEL, Blocks.COARSE_DIRT), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0)),
			AbstractBlock.Settings.copy(Blocks.PINK_PETALS), TAB);

	public static final Block PERIWINKLE = registerBlock("periwinkle",
			settings -> new VerdantPetalPlantBlock(settings, List.of(), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0)),
			AbstractBlock.Settings.copy(Blocks.PINK_PETALS), TAB);

	public static final Block IVY = registerBlock("ivy",
			VerdantVineBlock::new,
			AbstractBlock.Settings.copy(Blocks.VINE), TAB);

	// ----------- Potted (have an item, but no creative tab entry)
	public static final Block POTTED_MONSTERA = registerBlock("potted_monstera",
			settings -> new TurnableFlowerPotBlock(MONSTERA, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	public static final Block POTTED_SNAKE_PLANT = registerBlock("potted_snake_plant",
			settings -> new TurnableFlowerPotBlock(SNAKE_PLANT, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	public static final Block POTTED_PARLOUR_PALM = registerBlock("potted_parlour_palm",
			settings -> new TurnableFlowerPotBlock(PARLOUR_PALM, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	public static final Block POTTED_MONEY_TREE = registerBlock("potted_money_tree",
			settings -> new TurnableFlowerPotBlock(MONEY_TREE, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	public static final Block POTTED_DRAGON_TREE = registerBlock("potted_dragon_tree",
			settings -> new TurnableFlowerPotBlock(DRAGON_TREE, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	public static final Block POTTED_LOBELIA = registerBlock("potted_lobelia",
			settings -> new TurnableFlowerPotBlock(LOBELIA, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	public static final Block POTTED_IVY = registerBlock("potted_ivy",
			settings -> new TurnableFlowerPotBlock(IVY, settings),
			AbstractBlock.Settings.copy(Blocks.POTTED_CORNFLOWER), null);

	// ----------- Registration helpers

	private static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory,
			AbstractBlock.Settings settings, @Nullable RegistryKey<ItemGroup> group) {
		return registerBlock(name, factory, settings, group, true);
	}

	/**
	 * Since 1.21.2 block and item settings must carry their own registry key, so the block is
	 * constructed here (not at the call site) after the key has been applied to its settings.
	 */
	private static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory,
			AbstractBlock.Settings settings, @Nullable RegistryKey<ItemGroup> group, boolean withItem) {
		RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(VerdantVibes.MOD_ID, name));
		T block = factory.apply(settings.registryKey(blockKey));
		Registry.register(Registries.BLOCK, blockKey, block);
		if (withItem) {
			registerBlockItem(name, block, group);
		}
		return block;
	}

	private static void registerBlockItem(String name, Block block, @Nullable RegistryKey<ItemGroup> group) {
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VerdantVibes.MOD_ID, name));
		Item item = Registry.register(Registries.ITEM, itemKey,
				new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey()));
		if (group != null) {
			ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
		}
	}

	public static void registerModBlocks() {
		VerdantVibes.LOGGER.info("Registering Blocks from verdantvibes");
	}
}
