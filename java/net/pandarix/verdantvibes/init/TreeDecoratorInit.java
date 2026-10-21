package net.pandarix.verdantvibes.init;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.pandarix.verdantvibes.VerdantVibes;
import net.pandarix.verdantvibes.worldgen.tree.decorator.TrunkIvyDecorator;

public class TreeDecoratorInit {
	// NOTE: registered as "verdantvibes:trunk_ivy" (the 1.20.1 build used the "minecraft:" namespace).
	// The configured_feature JSONs in this project reference the new id.
	public static final TreeDecoratorType<TrunkIvyDecorator> TRUNK_IVY = register("trunk_ivy", TrunkIvyDecorator.CODEC);

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, MapCodec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, Identifier.of(VerdantVibes.MOD_ID, name), new TreeDecoratorType<>(codec));
	}

	public static void registerModDecorators() {
		VerdantVibes.LOGGER.info("Registering TreeDecorators from verdantvibes");
	}
}
