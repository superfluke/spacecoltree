package com.example.examplemod.world;

import java.util.Random;

import com.example.examplemod.util.MathUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RotTest extends WorldGenerator
{
    public static IBlockState state = Blocks.NETHER_BRICK.getDefaultState();
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
//	int height = 10;
//	for(int n=0; n<height; n++)
//	{
//	    BlockPos rotpos = MathUtil.rotatePointOnYAxis(pos, 45, n);
//	    BlockPos rotpos2 = MathUtil.rotatePointOnXAxis(pos.add(0,n,0), 45, Math.abs(rotpos.getX() - pos.getX()));
//	    world.setBlockState(rotpos2, state);
//	}
	for(BlockPos possy : MathUtil.rotateRect(pos, pos.add(0,10,0), new Vec3d(0,0,0), 0))
	{
	    world.setBlockState(possy, state);
	}
	return true;
    }

}
