package net.pandarix.verdantvibes.worldgen;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.pandarix.verdantvibes.VerdantVibes;

/**
 * Keys of the placed features that ship as JSON in data/verdantvibes/worldgen/placed_feature.
 * (The 1.20.1 build generated those JSONs with a datagen bootstrap; in this port they are plain data files.)
 */
public class ModPlacedFeatures {
	public static final RegistryKey<PlacedFeature> BIRCH_IVY_PLACED_KEY = registerKey("birch_ivy_placed");
	public static final RegistryKey<PlacedFeature> OAK_IVY_PLACED_KEY = registerKey("oak_ivy_placed");
	public static final RegistryKey<PlacedFeature> CANDY_TUFT_PLACED_KEY = registerKey("candy_tuft_placed");
	public static final RegistryKey<PlacedFeature> CANDY_TUFT_HIGH_PLACED_KEY = registerKey("candy_tuft_high_placed");
	public static final RegistryKey<PlacedFeature> PERIWINKLE_PLACED_KEY = registerKey("periwinkle_placed");
	public static final RegistryKey<PlacedFeature> DRAGON_TREE_PLACED_KEY = registerKey("dragon_tree_placed");
	public static final RegistryKey<PlacedFeature> LOBELIA_PLACED_KEY = registerKey("lobelia_placed");
	public static final RegistryKey<PlacedFeature> MONEY_TREE_KEY = registerKey("money_tree_placed");
	public static final RegistryKey<PlacedFeature> MONSTERA_KEY = registerKey("monstera_placed");
	public static final RegistryKey<PlacedFeature> PARLOUR_PALM_KEY = registerKey("parlour_palm_placed");
	public static final RegistryKey<PlacedFeature> SNAKE_PLANT_KEY = registerKey("snake_plant_placed");

	private static RegistryKey<PlacedFeature> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(VerdantVibes.MOD_ID, name));
	}
}
