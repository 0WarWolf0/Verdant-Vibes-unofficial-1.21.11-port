package net.pandarix.verdantvibes.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModPlantGeneration {
	public static void generatePlants() {
		GenerationStep.Feature step = GenerationStep.Feature.VEGETAL_DECORATION;

		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), step, ModPlacedFeatures.PERIWINKLE_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN), step, ModPlacedFeatures.CANDY_TUFT_PLACED_KEY);
		// Best guess, see README: the 1.20.1 build used a different (unrecoverable) tag here.
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN), step, ModPlacedFeatures.CANDY_TUFT_HIGH_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_SAVANNA), step, ModPlacedFeatures.DRAGON_TREE_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_SAVANNA), step, ModPlacedFeatures.LOBELIA_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP), step, ModPlacedFeatures.MONEY_TREE_KEY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), step, ModPlacedFeatures.MONSTERA_KEY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), step, ModPlacedFeatures.PARLOUR_PALM_KEY);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DESERT), step, ModPlacedFeatures.SNAKE_PLANT_KEY);
	}
}
