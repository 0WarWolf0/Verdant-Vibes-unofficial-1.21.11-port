package net.pandarix.verdantvibes.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pandarix.verdantvibes.VerdantVibes;

public class ItemGroupInit {
	public static final RegistryKey<ItemGroup> VERDANTVIBES_ITEMGROUP = RegistryKey.of(
			RegistryKeys.ITEM_GROUP, Identifier.of(VerdantVibes.MOD_ID, "verdantvibes"));

	public static void registerTab() {
		Registry.register(
				Registries.ITEM_GROUP,
				VERDANTVIBES_ITEMGROUP,
				FabricItemGroup.builder()
						.displayName(Text.translatable("itemGroup.verdantvibes"))
						.icon(() -> new ItemStack(BlockInit.DRAGON_TREE))
						.entries((context, entries) -> {
						})
						.build());
	}
}
