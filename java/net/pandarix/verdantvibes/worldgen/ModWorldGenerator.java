package net.pandarix.verdantvibes.worldgen;

import net.pandarix.verdantvibes.worldgen.tree.ModTreeGeneration;

public class ModWorldGenerator {
	public static void generateModWorldGen() {
		ModTreeGeneration.generateTrees();
		ModPlantGeneration.generatePlants();
	}
}
