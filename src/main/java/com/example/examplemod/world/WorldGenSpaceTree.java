package com.example.examplemod.world;

import java.util.Random;

import com.example.examplemod.world.spacecolony.Branch;
import com.example.examplemod.world.spacecolony.Leaf;
import com.example.examplemod.world.spacecolony.Tree;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpaceTree extends WorldGenerator
{
    public Tree tree;
    public static IBlockState log = Blocks.LOG.getDefaultState();
    public static IBlockState green = Blocks.GREEN_GLAZED_TERRACOTTA.getDefaultState();
    
    public WorldGenSpaceTree()
    {
	
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
	this.tree = new Tree(pos);
	for(int n=0; n<100; n++)
	    this.tree.grow();
	drawBlocks(world, pos);
	
	
	return true;
    }
    
    public void drawBlocks(World world, BlockPos pos)
    {
	for(Leaf leaf : this.tree.leaves)
	{
	    world.setBlockState(leaf.pos, green);
	}
	
	for(Branch branch : this.tree.branches)
	{
	    branch.drawBranch(world, log);
	}
    }

}
