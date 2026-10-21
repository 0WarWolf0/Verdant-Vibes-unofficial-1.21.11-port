package net.pandarix.verdantvibes.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.pandarix.verdantvibes.VerdantVibes;
import net.pandarix.verdantvibes.init.BlockInit;

public class VillagerInit {
	public static final RegistryKey<PointOfInterestType> GARDENING_POI_KEY = RegistryKey.of(
			RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(VerdantVibes.MOD_ID, "gardening_poi"));

	public static final PointOfInterestType GARDENING_POI = PointOfInterestHelper.register(
			Identifier.of(VerdantVibes.MOD_ID, "gardening_poi"), 1, 1, BlockInit.GARDENING_TABLE);

	public static final RegistryKey<VillagerProfession> GARDENER_KEY = RegistryKey.of(
			RegistryKeys.VILLAGER_PROFESSION, Identifier.of(VerdantVibes.MOD_ID, "gardener"));

	public static final VillagerProfession GARDENER = Registry.register(
			Registries.VILLAGER_PROFESSION,
			GARDENER_KEY,
			new VillagerProfession(
					Text.translatable("entity.minecraft.villager.gardener"),
					entry -> entry.matchesKey(GARDENING_POI_KEY),
					entry -> entry.matchesKey(GARDENING_POI_KEY),
					ImmutableSet.of(),
					ImmutableSet.of(),
					SoundEvents.BLOCK_FLOWERING_AZALEA_HIT));

	public static void registerVillagers() {
		VerdantVibes.LOGGER.info("Registering Villagers verdantvibes");
	}
}
