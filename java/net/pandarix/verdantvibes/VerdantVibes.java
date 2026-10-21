package net.pandarix.verdantvibes;

import net.fabricmc.api.ModInitializer;
import net.pandarix.verdantvibes.init.BlockInit;
import net.pandarix.verdantvibes.init.ItemGroupInit;
import net.pandarix.verdantvibes.init.TreeDecoratorInit;
import net.pandarix.verdantvibes.villager.ModTrades;
import net.pandarix.verdantvibes.villager.VillagerInit;
import net.pandarix.verdantvibes.worldgen.ModWorldGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerdantVibes implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("verdantvibes");
	public static final String MOD_ID = "verdantvibes";

	@Override
	public void onInitialize() {
		ItemGroupInit.registerTab();
		BlockInit.registerModBlocks();
		VillagerInit.registerVillagers();
		ModTrades.registerCustomTrades();
		TreeDecoratorInit.registerModDecorators();
		ModWorldGenerator.generateModWorldGen();
		LOGGER.info("Hello Fabric world!");
	}
}
