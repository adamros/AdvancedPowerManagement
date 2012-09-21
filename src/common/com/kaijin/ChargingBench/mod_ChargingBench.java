package net.minecraft.src;

import java.io.File;
import com.kaijin.ChargingBench.*;

import net.minecraft.src.forge.*;
import net.minecraft.src.*;
import net.minecraft.src.ic2.api.*;

public class mod_ChargingBench extends NetworkMod
{
    static Configuration configuration = CommonProxy.getConfiguration();
    static int ChargingBenchBlockID;
    static public boolean isDebugging;
    static { configurationProperties(); }

    public static final Block ChargingBench = new BlockChargingBench(ChargingBenchBlockID);
    
    public static mod_ChargingBench instance;
    
    public mod_ChargingBench()
    {
    	instance = this;
    }
    
    public static void configurationProperties()
    {
        try
        {
        	System.out.println("mod_IC2_ChargingBench.configurationProperties");
            configuration.load();
            ChargingBenchBlockID = Integer.valueOf(configuration.getOrCreateBlockIdProperty("blockChargingBench", 189).value);
            isDebugging = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("debug", configuration.CATEGORY_GENERAL, false).value));
            configuration.save();
        }
        catch (Exception var1)
        {
            System.out.println("[ChargingBench] Error while trying to access configuration!");
            throw new RuntimeException(var1);
        }
    }

    @Override
    public boolean clientSideRequired()
    {
            return true;
    }

    @Override
    public boolean serverSideRequired()
    {
            return false;
    }
   
    @Override
    public String getPriorities()
    {
    	return "required-after:mod_IC2";
    }
    
    public void load()
    {
    	if (Utils.isDebug()) System.out.println("mod_IC2_ChargingBench.load");
    	MinecraftForge.versionDetect("IC2_Charging Bench", 3, 3, 8);
        ModLoader.registerBlock(ChargingBench, ItemChargingBench.class);
        ModLoader.registerTileEntity(TileEntityChargingBench1.class, "Charging Bench Mk1");
        ModLoader.registerTileEntity(TileEntityChargingBench2.class, "Charging Bench Mk2");
        ModLoader.registerTileEntity(TileEntityChargingBench3.class, "Charging Bench Mk3");
        ModLoader.addLocalization("blockChargingBench1.name", "Charging Bench Mk1");
        ModLoader.addLocalization("blockChargingBench2.name", "Charging Bench Mk2");
        ModLoader.addLocalization("blockChargingBench3.name", "Charging Bench Mk3");
    	MinecraftForge.setGuiHandler(this.instance, new GuiHandlerIC2ChargingBench());
        CommonProxy.load();
    }

    public void modsLoaded()
    {
    	if (Utils.isDebug()) System.out.println("mod_IC2_ChargingBench.modsLoaded");
        ModLoader.addRecipe(new ItemStack(ChargingBench, 1, 0), new Object[] {"UUU", "W W", "WWW", 'U', Items.getItem("copperCableItem"), 'W', Block.planks});
        ModLoader.addRecipe(new ItemStack(ChargingBench, 1, 1), new Object[] {"UUU", "WCW", "WWW", 'U', Items.getItem("goldCableItem"), 'W', Block.planks, 'C', Items.getItem("electronicCircuit")});
        ModLoader.addRecipe(new ItemStack(ChargingBench, 1, 2), new Object[] {"UUU", "WCW", "WWW", 'U', Items.getItem("ironCableItem"), 'W', Block.planks, 'C', Items.getItem("advancedCircuit")});
    }
    
    public String getVersion()
    {
        return "MC 1.2.5 - IC 1.103 - beta 1";
    }
}