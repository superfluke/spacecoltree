package com.example.examplemod.world.spacecolony;

import com.example.examplemod.util.MathUtil;
import com.example.examplemod.util.TreePos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Branch
{
    public Branch parent;
    public TreePos pos;
    public TreePos dir;
    public TreePos originalDir;
    public int count = 0;
    public int length = 5;
    
    public Branch(BlockPos pos, TreePos dir)
    {
		this.pos = new TreePos(pos);
		this.dir = new TreePos(dir);
		this.originalDir = new TreePos(dir);
		this.parent = null;
    }
    
    public Branch(Branch parent)
    {
		this.pos = parent.next();
		this.dir = new TreePos(parent.dir);
		this.originalDir = new TreePos(parent.dir);
		this.parent = parent;
    }
    
    public TreePos next()
    {
		this.length = 6;
		TreePos nextDir = this.dir.mul(this.length);
		TreePos newPos = this.pos.add(nextDir);
		return newPos;
    }

    public void drawBranch(World world, IBlockState state, int radius)
    {
		if(this.parent != null)
		{
			BlockPos pos = this.pos.toBlockPos();
	    	BlockPos parpos = parent.pos.toBlockPos();
		    for(int x=0; x<=radius; x++)
		    {
			for(int z=0; z<=radius; z++)
			{
			    BlockPos[] branchArray = MathUtil.getBresehnamArrays(pos.add(x,0,z), parpos.add(x,0,z));
			    for(BlockPos p : branchArray)
			    {
				world.setBlockState(p, state);
			    }  
			}
		    }
		    
		}
    }
    
    public void reset()
    {
		this.dir = new TreePos(this.originalDir); //TODO more copy 
		this.count = 0;
    }

}
