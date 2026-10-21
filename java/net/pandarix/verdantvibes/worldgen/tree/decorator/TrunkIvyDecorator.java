package net.pandarix.verdantvibes.worldgen.tree.decorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.pandarix.verdantvibes.block.custom.VerdantVineBlock;
import net.pandarix.verdantvibes.init.BlockInit;
import net.pandarix.verdantvibes.init.TreeDecoratorInit;

/** Grows ivy on the sides of tree trunks. */
public class TrunkIvyDecorator extends TreeDecorator {
	public static final TrunkIvyDecorator INSTANCE = new TrunkIvyDecorator();
	public static final MapCodec<TrunkIvyDecorator> CODEC = MapCodec.unit(() -> INSTANCE);

	@Override
	protected TreeDecoratorType<?> getType() {
		return TreeDecoratorInit.TRUNK_IVY;
	}

	@Override
	public void generate(Generator generator) {
		Random random = generator.getRandom();
		generator.getLogPositions().forEach(pos -> {
			if (random.nextInt(3) > 0) {
				BlockPos target = pos.west();
				if (generator.isAir(target)) {
					generator.replace(target, BlockInit.IVY.getDefaultState().with(VerdantVineBlock.EAST, true));
				}
			}
			if (random.nextInt(3) > 0) {
				BlockPos target = pos.east();
				if (generator.isAir(target)) {
					generator.replace(target, BlockInit.IVY.getDefaultState().with(VerdantVineBlock.WEST, true));
				}
			}
			if (random.nextInt(3) > 0) {
				BlockPos target = pos.north();
				if (generator.isAir(target)) {
					generator.replace(target, BlockInit.IVY.getDefaultState().with(VerdantVineBlock.SOUTH, true));
				}
			}
			if (random.nextInt(3) > 0) {
				BlockPos target = pos.south();
				if (generator.isAir(target)) {
					generator.replace(target, BlockInit.IVY.getDefaultState().with(VerdantVineBlock.NORTH, true));
				}
			}
		});
	}
}
