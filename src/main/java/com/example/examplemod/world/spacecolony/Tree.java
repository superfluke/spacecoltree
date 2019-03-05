package com.example.examplemod.world.spacecolony;

import java.util.ArrayList;

import com.example.examplemod.util.MathUtil;
import com.example.examplemod.util.TreePos;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

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
		this.root = new Branch(pos, new TreePos(0, 1, 0));
		this.branches.add(this.root);
		makeTrunk();

	}

	public void initLeaves(int numLeaves)
	{
		for (int n = 0; n < numLeaves; n++)
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
				double dist = Math.sqrt(current.pos.toBlockPos().distanceSq(leaf.pos));
				if (dist < maxDist)
				{
					found = true;
				}
			}

			if (!found)
			{
				Branch newBranch = new Branch(current);
				current = newBranch;
				branches.add(newBranch);
			}
		}
	}

	public void grow()
	{
		for (Leaf leaf : this.leaves)
		{
			Branch closestBranch = null;
			TreePos closestDir = null;
			double closestDist = 999999;
			for (Branch branch : this.branches)
			{
				TreePos dir = new TreePos(leaf.pos.subtract(branch.pos.toBlockPos()));
				double d = dir.getMagnitude();
				if (d < this.minDist)
				{
					leaf.reached = true;
					closestBranch = null;
					break;
				} else if (d > maxDist)
				{
					// continue;
				} else if (closestBranch == null || d < closestDist)
				{
					closestBranch = branch;
					closestDir = dir;
					closestDist = d;
				}
			}

			if (closestBranch != null)
			{
				closestDir = closestDir.normalize();
				closestBranch.dir = closestBranch.dir.add(closestDir);
				closestBranch.count++;
			}
		}

		for (int n = this.leaves.size() - 1; n >= 0; n--)
		{
			Leaf leaf = this.leaves.get(n);
			if (leaf.reached)
			{
				this.leaves.remove(leaf);
			}
		}

		for (int n = this.branches.size() - 1; n >= 0; n--)
		{
			Branch branch = this.branches.get(n);
			if (branch.count > 0)
			{
				branch.dir = branch.dir.div(branch.count);
				branch.dir = branch.dir.normalize();
				Branch newBranch = new Branch(branch);
				// if(!newBranch.pos.equals(newBranch.parent.pos))
				this.branches.add(newBranch);
				branch.reset();
			}

		}

	}

}