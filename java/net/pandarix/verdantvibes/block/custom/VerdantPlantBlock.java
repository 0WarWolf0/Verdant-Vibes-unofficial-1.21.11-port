package net.pandarix.verdantvibes.block.custom;

import com.mojang.serialization.MapCodec;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VerdantPlantBlock extends PlantBlock implements Fertilizable {
	// The codec only exists to satisfy Block#getCodec; it cannot carry the extra constructor arguments.
	public static final MapCodec<VerdantPlantBlock> CODEC = createCodec(
			settings -> new VerdantPlantBlock(settings, List.of(), VoxelShapes.fullCube()));

	public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;

	private final VoxelShape voxelShape;
	private final List<Block> mayPlaceOn;

	public VerdantPlantBlock(Settings settings, List<Block> mayPlaceOn, VoxelShape voxelShape) {
		super(settings);
		this.mayPlaceOn = mayPlaceOn;
		this.voxelShape = voxelShape;
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends PlantBlock> getCodec() {
		return CODEC;
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockToPlaceOn = world.getBlockState(pos.down());
		if (this.mayPlaceOn.contains(blockToPlaceOn.getBlock())) {
			return blockToPlaceOn.isOf(Blocks.WATER) ? !world.getBlockState(pos.down(2)).isOf(Blocks.WATER) : true;
		}
		return super.canPlaceAt(state, world, pos);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d offset = state.getModelOffset(pos);
		return this.voxelShape.offset(offset.x, offset.y, offset.z);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(FACING);
	}

	// ----- Fertilizable (bone meal drops another plant)

	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		Block.dropStack(world, pos, new ItemStack(this));
	}
}
