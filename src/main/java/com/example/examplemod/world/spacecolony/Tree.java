package com.example.examplemod.world.spacecolony;

import java.util.ArrayList;

import com.example.examplemod.util.MathUtil;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Tree
{ 
    public ArrayList<Leaf> leaves = new ArrayList<Leaf>();
    public ArrayList<Branch> branches = new ArrayList<Branch>();
    public BlockPos base;
    public Branch root;
    public static int maxDist;
    public static int minDist;
    

    public Tree(BlockPos pos)
    {
	this.maxDist = 100;
	this.minDist = 4;
	
	this.base = pos;
	initLeaves(500);
	this.root = new Branch(pos, new Vec3d(0,1,0));
	this.branches.add(this.root);	
	makeTrunk();
	
    }
    
    public void initLeaves(int numLeaves)
    {
	for(int n=0; n<numLeaves; n++)
	{
	    leaves.add(new Leaf(this.base));
	}
    }
    
    public void makeTrunk()
    {
	Branch current = this.root;
	boolean found = false;

	while (!found)
	{
	    for (Leaf leaf : this.leaves)
	    {
		double dist = Math.sqrt(current.pos.distanceSq(leaf.pos));
		if (dist < maxDist)
		{
		    found = true;
		}
	    }
	    
	    if(!found)
	    {
		Branch newBranch = new Branch(current);
		current = newBranch;
		branches.add(newBranch);
	    }
	}
    }
    
    public void grow()
    {
	for(Leaf leaf : this.leaves)
	{
	    Branch closestBranch = null;
	    Vec3d closestDir = null;
	    double closestDist = 999999;
	    for(Branch branch : this.branches)
	    {
		Vec3d dir = MathUtil.posToVec3d(leaf.pos.subtract(branch.pos));
		double d = MathUtil.vec3dMag(dir);
		if(d < this.minDist)
		{
		    leaf.reached = true;
		    closestBranch = null;
		    break;
		}
		else if(d > maxDist)
		{
//		    continue;
		}
		else if(closestBranch == null || d < closestDist)
		{
		    closestBranch = branch;
		    closestDir = dir;
		    closestDist = d;
		}
	    }
	    
	    if(closestBranch != null)
	    {
		closestDir = closestDir.normalize();
		closestBranch.dir = closestBranch.dir.add(closestDir);
		closestBranch.count++;
	    }
	}
	
	for(int n=this.leaves.size()-1; n>=0; n--)
	{
	    Leaf leaf = this.leaves.get(n);
	    if(leaf.reached)
	    {
		this.leaves.remove(leaf);
	    }
	}
	
	for(int n=this.branches.size()-1; n>=0; n--)
	{
	    Branch branch = this.branches.get(n);
	    if(branch.count > 0)
	    {
		branch.dir = MathUtil.vec3dDiv(branch.dir, branch.count);
		branch.dir = branch.dir.normalize();
		Branch newBranch = new Branch(branch);
		//if(!newBranch.pos.equals(newBranch.parent.pos))
		    this.branches.add(newBranch);
		branch.reset();
	    }
	    
	}
	
    }
    
    
    
}