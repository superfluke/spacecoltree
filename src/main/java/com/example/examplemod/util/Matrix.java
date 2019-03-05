package com.example.examplemod.util;

public class Matrix 
{
	protected int row;
	protected int column;
	protected double[] data;
	
	public Matrix(int row, int col, double val)
	{
		this.row = row;
		this.column = col;
		this.data = new double[row*col];
		for(int n = 0; n < (row * col); n++)
			this.data[n] = val;
	}
	
	public Matrix(int row, int col)
	{
		this(row, col, 0.0);
	}
	
	public Matrix(Matrix m)
	{
		this.row = m.row;
		this.column = m.column;
		this.data = new double[m.row * m.column];
		for(int n = 0; n < (m.row * m.column); n++)
			this.data[n] = m.data[n];
	}
	
	public void set(int i, int j, double val)
	{
		this.data[i * this.column + j] = val;
	}
	
	public double get(int i, int j)
	{
		return this.data[i * this.column + j];
	}
	
	public Matrix add(Matrix other)
	{
		if(this.row != other.row || this.column != other.column)
			return null;
		
		Matrix out = new Matrix(this.row, this.column);
		for(int n = 0; n < (this.row * this.column); n++)
			out.data[n] = this.data[n] + other.data[n];
		
		return out;
	}
	
	public Matrix sub(Matrix other)
	{
		if(this.row != other.row || this.column != other.column)
			return null;
		
		Matrix out = new Matrix(this.row, this.column);
		for(int n = 0; n < (this.row * this.column); n++)
			out.data[n] = this.data[n] - other.data[n];
		
		return out;
	}
	
	public Matrix mul(Matrix other)
	{
		if(this.row != other.row || this.column != other.column)
			return null;
		
		Matrix out = new Matrix(this.row, this.column);
		for(int i = 0; i < this.row; i++)
		{
			for(int j = 0; j < this.column; j++)
			{
				double total = 0.0;
				for(int k = 0; k < this.column; k++)
				{
					total += this.get(i, k) * other.get(k, j);
				}
				
				out.set(i, j, total);
			}
		}
		
		return out;
	}
	
	public Matrix mul(double val)
	{
		Matrix out = new Matrix(this.row, this.column);
		for(int n = 0; n < (this.row * this.column); n++)
			out.data[n] = this.data[n] * val;
		
		return out;
	}
	
	public Matrix div(double val)
	{
		return this.mul(1.0 / val);
	}

}
