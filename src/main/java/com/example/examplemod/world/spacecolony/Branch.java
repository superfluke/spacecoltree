package com.example.examplemod.world.spacecolony;

import com.example.examplemod.util.MathUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Branch
{
    public BlockPos pos;
    public Branch parent;
    public Vec3d dir;
    public Vec3d originalDir;
    public int count = 0;
    public int length = 5;
    
    public Branch(BlockPos pos, Vec3d dir)
    {
	this.pos = new BlockPos(pos);
	this.dir = new Vec3d(dir.x, dir.y, dir.z);
	this.originalDir = new Vec3d(this.dir.x, this.dir.y, this.dir.z);
	this.parent = null;
    }
    
    public Branch(Branch parent)
    {
	this.pos = parent.next();
	this.dir = new Vec3d(parent.dir.x, parent.dir.y, parent.dir.z);
	this.originalDir = new Vec3d(parent.dir.x, parent.dir.y, parent.dir.z);
	this.parent = parent;
    }
    
    public BlockPos next()
    {
	this.length = 6;
	Vec3d nextDir = MathUtil.vec3dMul(this.dir, this.length);
	BlockPos newPos = MathUtil.posAddVec(this.pos, nextDir);
	return newPos;
    }

    public void drawBranch(World world, IBlockState state)
    {
	if(this.parent != null)
	{
	    BlockPos[] branchArray = MathUtil.getBresehnamArrays(this.pos, parent.pos);
	    for(BlockPos p : branchArray)
	    {
		world.setBlockState(p, state);
	    }
	}
    }
    
    public void reset()
    {
	this.dir = new Vec3d(originalDir.x, originalDir.y, originalDir.z); //TODO more copy 
	this.count = 0;
    }

}
