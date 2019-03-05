package com.example.examplemod.world.spacecolony;

import java.util.Random;

import net.minecraft.util.math.BlockPos;

public class Leaf
{
    public static Random rand = new Random();
    public int x;
    public int y;
    public int z;
    public BlockPos pos;
    public static int leafRange;
    public boolean reached = false;
    
    public Leaf(BlockPos pos)
    {
		this.leafRange = 80;
		//this.x = pos.getX() + rand.nextInt(leafRange) - leafRange/2;
		
		int yOff = rand.nextInt(leafRange);
		this.y = pos.getY() + yOff + 20;
		
		int modRange = (int)((leafRange+1-yOff) * 0.9);	
		int xOff = rand.nextInt(modRange);
		this.x = pos.getX() + xOff - (modRange)/2;
		int zOff = rand.nextInt(modRange);
		this.z = pos.getZ() + zOff - (modRange)/2;
		this.pos = new BlockPos(this.x, this.y, this.z);
		
	//	int horzSpread = 200;
	//	this.y = pos.getY() + 40 + rand.nextInt(60);
	//	this.x = pos.getX() + rand.nextInt(horzSpread) - horzSpread/2;
	//	this.z = pos.getZ() + rand.nextInt(horzSpread) - horzSpread/2;
	//	this.pos = new BlockPos(this.x, this.y, this.z);
    }

}
