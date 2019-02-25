package com.example.examplemod.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MathUtil
{
    // Get an array of values that represent a line from point A to point B
    public static BlockPos[] getBresehnamArrays(BlockPos src, BlockPos dest)
    {
	return getBresehnamArrays(src.getX(), src.getY(), src.getZ(), dest.getX(), dest.getY(), dest.getZ());
    }

    // Get an array of values that represent a line from point A to point B
    public static BlockPos[] getBresehnamArrays(int x1, int y1, int z1, int x2, int y2, int z2)
    {
	int i, dx, dy, dz, absDx, absDy, absDz, x_inc, y_inc, z_inc, err_1, err_2, doubleAbsDx, doubleAbsDy,
		doubleAbsDz;

	BlockPos pixel = new BlockPos(x1, y1, z1);
	BlockPos lineArray[];

	dx = x2 - x1;
	dy = y2 - y1;
	dz = z2 - z1;
	x_inc = (dx < 0) ? -1 : 1;
	absDx = Math.abs(dx);
	y_inc = (dy < 0) ? -1 : 1;
	absDy = Math.abs(dy);
	z_inc = (dz < 0) ? -1 : 1;
	absDz = Math.abs(dz);
	doubleAbsDx = absDx << 1;
	doubleAbsDy = absDy << 1;
	doubleAbsDz = absDz << 1;

	if ((absDx >= absDy) && (absDx >= absDz))
	{
	    err_1 = doubleAbsDy - absDx;
	    err_2 = doubleAbsDz - absDx;
	    lineArray = new BlockPos[absDx + 1];
	    for (i = 0; i < absDx; i++)
	    {
		lineArray[i] = pixel;
		if (err_1 > 0)
		{
		    pixel = pixel.up(y_inc);
		    err_1 -= doubleAbsDx;
		}
		if (err_2 > 0)
		{
		    pixel = pixel.south(z_inc);
		    err_2 -= doubleAbsDx;
		}
		err_1 += doubleAbsDy;
		err_2 += doubleAbsDz;
		pixel = pixel.east(x_inc);
	    }
	} else if ((absDy >= absDx) && (absDy >= absDz))
	{
	    err_1 = doubleAbsDx - absDy;
	    err_2 = doubleAbsDz - absDy;
	    lineArray = new BlockPos[absDy + 1];
	    for (i = 0; i < absDy; i++)
	    {
		lineArray[i] = pixel;
		if (err_1 > 0)
		{
		    pixel = pixel.east(x_inc);
		    err_1 -= doubleAbsDy;
		}
		if (err_2 > 0)
		{
		    pixel = pixel.south(z_inc);
		    err_2 -= doubleAbsDy;
		}
		err_1 += doubleAbsDx;
		err_2 += doubleAbsDz;
		pixel = pixel.up(y_inc);
	    }
	} else
	{
	    err_1 = doubleAbsDy - absDz;
	    err_2 = doubleAbsDx - absDz;
	    lineArray = new BlockPos[absDz + 1];
	    for (i = 0; i < absDz; i++)
	    {
		lineArray[i] = pixel;
		if (err_1 > 0)
		{
		    pixel = pixel.up(y_inc);
		    err_1 -= doubleAbsDz;
		}
		if (err_2 > 0)
		{
		    pixel = pixel.east(x_inc);
		    err_2 -= doubleAbsDz;
		}
		err_1 += doubleAbsDy;
		err_2 += doubleAbsDx;
		pixel = pixel.south(z_inc);
	    }
	}
	lineArray[lineArray.length - 1] = pixel;

	return lineArray;
    }
    
    public static BlockPos posAddVec(BlockPos pos, Vec3d vec)
    {
	return new BlockPos(pos.getX() + (int)(vec.x+0.5), pos.getY() + (int)(vec.y+0.5), pos.getZ() + (int)(vec.z+0.5));
    }
    
    public static Vec3d vec3dDiv(Vec3d vec, double div)
    {
	return new Vec3d(vec.x/div, vec.y/div, vec.z/div);
    }
    
    public static Vec3d vec3dMul(Vec3d vec, double mul)
    {
	return new Vec3d(vec.x*mul, vec.y*mul, vec.z*mul);
    }
    
    public static Vec3d posToVec3d(BlockPos pos)
    {
	return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
    }
    
    public static double vec3dMag(Vec3d vec)
    {
	return Math.sqrt((vec.x*vec.x)+(vec.y*vec.y)+(vec.z*vec.z));
    }


}
