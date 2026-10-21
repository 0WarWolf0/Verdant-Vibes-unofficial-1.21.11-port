package net.pandarix.verdantvibes.worldgen.tree;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.pandarix.verdantvibes.worldgen.ModPlacedFeatures;

public class ModTreeGeneration {
	public static void generateTrees() {
		GenerationStep.Feature step = GenerationStep.Feature.VEGETAL_DECORATION;

		BiomeModifications.addFeature(
				BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
				step, ModPlacedFeatures.BIRCH_IVY_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), step, ModPlacedFeatures.OAK_IVY_PLACED_KEY);
	}
}
