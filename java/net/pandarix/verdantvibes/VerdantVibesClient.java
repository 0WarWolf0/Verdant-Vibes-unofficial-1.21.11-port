package net.pandarix.verdantvibes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.biome.FoliageColors;
import net.pandarix.verdantvibes.block.custom.VerdantPetalPlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantPlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantTallPlantBlock;
import net.pandarix.verdantvibes.block.custom.VerdantVineBlock;
import net.pandarix.verdantvibes.init.BlockInit;

@Environment(EnvType.CLIENT)
public class VerdantVibesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				BlockInit.TALL_DRAGON_TREE, BlockInit.DRAGON_TREE, BlockInit.POTTED_DRAGON_TREE,
				BlockInit.MONEY_TREE, BlockInit.POTTED_MONEY_TREE,
				BlockInit.MONSTERA, BlockInit.POTTED_MONSTERA,
				BlockInit.IVY, BlockInit.POTTED_IVY,
				BlockInit.LOBELIA, BlockInit.POTTED_LOBELIA,
				BlockInit.PARLOUR_PALM, BlockInit.POTTED_PARLOUR_PALM,
				BlockInit.PERIWINKLE, BlockInit.CANDY_TUFT,
				BlockInit.SNAKE_PLANT, BlockInit.POTTED_SNAKE_PLANT);

		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) -> world != null && pos != null
						? BiomeColors.getFoliageColor(world, pos)
						: FoliageColors.DEFAULT,
				BlockInit.PARLOUR_PALM, BlockInit.DRAGON_TREE, BlockInit.MONSTERA, BlockInit.CANDY_TUFT,
				BlockInit.PERIWINKLE, BlockInit.LOBELIA, BlockInit.IVY, BlockInit.MONEY_TREE,
				BlockInit.TALL_DRAGON_TREE);

		// Latin plant name shown under the item name. (Block#appendTooltip was removed in 1.21.5+,
		// so this is done through Fabric API's tooltip event instead.)
		ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
			if (stack.getItem() instanceof BlockItem blockItem && hasInfoTooltip(blockItem.getBlock())) {
				lines.add(Text.translatable(blockItem.getBlock().getTranslationKey() + ".info")
						.formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
			}
		});
	}

	private static boolean hasInfoTooltip(Block block) {
		return block instanceof VerdantPlantBlock
				|| block instanceof VerdantTallPlantBlock
				|| block instanceof VerdantPetalPlantBlock
				|| block instanceof VerdantVineBlock;
	}
}
