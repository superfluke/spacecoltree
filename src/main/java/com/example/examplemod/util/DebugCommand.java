package com.example.examplemod.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.world.RotTest;
import com.example.examplemod.world.WorldGenSpaceTree;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class DebugCommand extends CommandBase 
{
	private final List<String> aliases;
	private static WorldGenSpaceTree tree = new WorldGenSpaceTree();
	private static RotTest rottest = new RotTest();
	
	public DebugCommand()
	{
        aliases = Lists.newArrayList(ExampleMod.MODID, "debugDeco", "dd");
    }
	
	@Override
    @Nonnull
    public String getName() 
	{
        return "thisbiome";
    }
	
	@Override
    @Nonnull
    public List<String> getAliases() 
    {
        return aliases;
    }
	
	@Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args)
    {
        if (sender instanceof EntityPlayer) 
        {
        	double x = ((EntityPlayer) sender).posX;
        	double z = ((EntityPlayer) sender).posZ;
//        	tree.generate(server.getEntityWorld(), new Random(), new BlockPos(x+1, 4, z+1));
        	
        	rottest.generate(server.getEntityWorld(), new Random(), new BlockPos(x+1, 4, z+1));
        	//sender.sendMessage(new TextComponentString("hello"));
        }
        
    }
	
	@Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) 
	{
        return true;
    }
	
	@Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) 
    {
        return Collections.emptyList();
    }

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "debugDeco";
	}

}
