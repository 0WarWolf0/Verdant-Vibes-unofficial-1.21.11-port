package net.pandarix.verdantvibes.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.pandarix.verdantvibes.init.BlockInit;

public class ModTrades {
	public static void registerCustomTrades() {
		// Level 1
		TradeOfferHelper.registerVillagerOffers(VillagerInit.GARDENER_KEY, 1, factories -> {
			factories.add(sell(4, BlockInit.MONSTERA, 1, 8, 4));
			factories.add(sell(4, BlockInit.IVY, 4, 16, 4));
			factories.add(sell(4, BlockInit.LOBELIA, 1, 8, 4));
			factories.add(sell(4, BlockInit.SNAKE_PLANT, 1, 8, 4));
		});
		// Level 2
		TradeOfferHelper.registerVillagerOffers(VillagerInit.GARDENER_KEY, 2, factories -> {
			factories.add(sell(4, BlockInit.DRAGON_TREE, 1, 8, 8));
			factories.add(sell(4, BlockInit.PARLOUR_PALM, 1, 8, 8));
			factories.add(sell(4, BlockInit.MONEY_TREE, 1, 8, 8));
			factories.add((world, entity, random) -> new TradeOffer(
					new TradedItem(Items.BONE, 16), new ItemStack(Items.EMERALD, 1), 8, 4, 0.02F));
		});
		// Level 3
		TradeOfferHelper.registerVillagerOffers(VillagerInit.GARDENER_KEY, 3, factories -> {
			factories.add(sell(4, Blocks.PODZOL, 2, 16, 8));
			factories.add(sell(8, Blocks.LILY_PAD, 4, 16, 4));
			factories.add(sell(8, Blocks.VINE, 4, 16, 4));
		});
		// Level 4
		TradeOfferHelper.registerVillagerOffers(VillagerInit.GARDENER_KEY, 4, factories -> {
			factories.add(sell(8, Blocks.FLOWER_POT, 2, 8, 8));
			factories.add(sell(4, Blocks.MOSS_BLOCK, 1, 16, 4));
			factories.add(sell(4, Blocks.SMALL_DRIPLEAF, 2, 8, 4));
		});
	}

	/** Emeralds for an item: price in emeralds, result item + count, max uses, villager xp. */
	private static TradeOffers.Factory sell(int emeralds, ItemConvertible result, int count, int maxUses, int xp) {
		return (world, entity, random) -> new TradeOffer(
				new TradedItem(Items.EMERALD, emeralds), new ItemStack(result, count), maxUses, xp, 0.02F);
	}
}
