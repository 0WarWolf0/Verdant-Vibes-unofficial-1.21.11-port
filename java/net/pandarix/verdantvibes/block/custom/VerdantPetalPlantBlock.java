package net.pandarix.verdantvibes.block.custom;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class VerdantPetalPlantBlock extends FlowerbedBlock {
	private final VoxelShape voxelShape;
	private final List<Block> mayPlaceOn;

	public VerdantPetalPlantBlock(Settings settings, List<Block> mayPlaceOn, VoxelShape voxelShape) {
		super(settings);
		this.mayPlaceOn = mayPlaceOn;
		this.voxelShape = voxelShape;
		this.setDefaultState(this.getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));
	}

	// Behaviour kept exactly as in the 1.20.1 build (note: it tests the block being placed, not the one below).
	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		if (this.mayPlaceOn.contains(state.getBlock())) {
			return state.isOf(Blocks.WATER) ? !world.getBlockState(pos.down()).isOf(Blocks.WATER) : true;
		}
		return super.canPlaceAt(state, world, pos);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return this.voxelShape;
	}
}
