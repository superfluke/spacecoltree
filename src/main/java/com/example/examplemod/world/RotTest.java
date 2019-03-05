package com.example.examplemod.world;

import java.util.Random;

import com.example.examplemod.util.MathUtil;
import com.example.examplemod.util.TreePos;

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
		int size = 5;
		TreePos[][][] possy = new TreePos[size][size][size];
		
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				for(int k = 0; k < size; k++)
					possy[i][j][k] = new TreePos(i, j, k);
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				for(int k = 0; k < size; k++)
				{
					TreePos tpos = possy[i][j][k];
					world.setBlockState(pos.add(tpos.toBlockPos()), state);
				}
			}
		}
		return true;
	}

}
