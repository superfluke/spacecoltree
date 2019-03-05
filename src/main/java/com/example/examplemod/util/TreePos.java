package com.example.examplemod.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class TreePos extends Matrix
{
	public static final TreePos ZERO = new TreePos(0.0, 0.0, 0.0);
	
	public TreePos()
	{
		super(4, 4, 0.0);
		for(int n = 0; n < this.row; n++)
			this.set(n, n, 1.0);
	}
	
	public TreePos(Matrix m)
	{
		super(m);
	}
	
	public TreePos(double x, double y, double z)
	{
		this();
		this.translate(x, y, z);
	}
	
	public TreePos(BlockPos pos)
	{
		this(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public TreePos add(TreePos other)
	{
		return new TreePos(super.add(other));
	}
	
	public TreePos sub(TreePos other)
	{
		return new TreePos(super.sub(other));
	}
	
	public TreePos mul(TreePos other)
	{
		return new TreePos(super.mul(other));
	}
	
	@Override
	public TreePos mul(double val)
	{
		return new TreePos(super.mul(val));
	}
	
	@Override
	public TreePos div(double val)
	{
		return new TreePos(super.div(val));
	}
	
	public void translate(double x, double y, double z)
	{
		this.set(3, 0, x);
		this.set(3, 1, y);
		this.set(3, 2, z);
	}
	
	public void rotateX(double angle)
	{
		this.set(1, 1, Math.cos(Math.toRadians(angle)));
		this.set(1, 2, Math.sin(Math.toRadians(angle)));
		this.set(2, 1, -Math.sin(Math.toRadians(angle)));
		this.set(2, 2, Math.cos(Math.toRadians(angle)));
	}
	
	public void rotateY(double angle)
	{
		this.set(0, 0, Math.cos(Math.toRadians(angle)));
		this.set(0, 2, -Math.sin(Math.toRadians(angle)));
		this.set(2, 0, Math.sin(Math.toRadians(angle)));
		this.set(2, 2, Math.cos(Math.toRadians(angle)));
	}
	
	public void rotateZ(double angle)
	{
		this.set(0, 0, Math.cos(Math.toRadians(angle)));
		this.set(0, 1, Math.sin(Math.toRadians(angle)));
		this.set(1, 0, -Math.sin(Math.toRadians(angle)));
		this.set(1, 1, Math.cos(Math.toRadians(angle)));
	}
	
	public double getX()
	{
		return this.get(3, 0);
	}
	
	public double getY()
	{
		return this.get(3, 1);
	}
	
	public double getZ()
	{
		return this.get(3, 2);
	}
	
	public BlockPos toBlockPos()
	{
		return new BlockPos((int)(this.getX() + 0.5), (int)(this.getY() + 0.5), (int)(this.getZ() + 0.5));
	}
	
	public double getMagnitude()
	{
		return Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()) + (this.getZ() * this.getZ()));
	}
	
	public TreePos normalize()
	{
		double mag = this.getMagnitude();
		return mag < 1.0E-4D ? ZERO : new TreePos(this.getX() / mag, this.getY() / mag, this.getZ() / mag);
	}
}
