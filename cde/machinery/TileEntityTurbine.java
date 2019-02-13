/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.MachineryCore;
import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class TileEntityTurbine extends TileEntityMachine implements IEnergySource 
{
    private int euOutput;
    private int steamBuffer;
    private final int STEAM_MULTIPLIER = 2;
    private TileEntity te;
    private ITankContainer itc;
    private ILiquidTank ilt;
    private LiquidStack ls;
    private final LiquidStack STEAM;
    private final String BOILER_LP = "railcraft.common.blocks.machine.beta.TileBoilerTankLow";
    private final String BOILER_HP = "railcraft.common.blocks.machine.beta.TileBoilerTankHigh";
    
    public TileEntityTurbine()
    {
        euOutput = 32;
        STEAM = LiquidDictionary.getLiquid("Steam", LiquidContainerRegistry.BUCKET_VOLUME);
    }
    
    private boolean isValidBoiler()
    {
        te = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);
        
        if(te instanceof ITankContainer)
        {
            String s = te.getClass().getCanonicalName();

            return s.equalsIgnoreCase(BOILER_LP) || s.equalsIgnoreCase(BOILER_HP);
        }
        
        return false;
    }
    
    @Override
    protected void doWorkCycle()
    {   
        if(isValidBoiler())
        {
            int amountToDrain = STEAM_MULTIPLIER * euOutput;
            STEAM.amount = amountToDrain;
            itc = (ITankContainer)te;
            ilt = itc.getTank(ForgeDirection.UP, STEAM);

            if(ilt != null && ilt.getLiquid() != null)
            {
                ls = ilt.getLiquid();
                
                STEAM.amount = ilt.getCapacity() / 2 + amountToDrain;
                
                if(steamBuffer < amountToDrain && ls.containsLiquid(STEAM))
                {
                    ls = ilt.drain(amountToDrain, true);
                    
                    if(ls != null)
                    {
                        steamBuffer += ls.amount;
                    }
                }
                
                if(steamBuffer >= amountToDrain)
                {
                    EnergyTileSourceEvent event = new EnergyTileSourceEvent(this, euOutput);
                    MinecraftForge.EVENT_BUS.post(event);

                    int excess = STEAM_MULTIPLIER * event.amount;

                    steamBuffer -= amountToDrain - excess;
                }
            }
        } 
    }
        
    @Override
    public String useWrench(boolean flag)
    {
        if(flag)
        {
            switch(euOutput)
            {
                case 32: euOutput = 128; break;
                case 128: euOutput = 512; break;
                case 512: euOutput = 2048; break;
                case 2048: euOutput = 32; break;
                default: euOutput = 32; break;
            }
            
            return "Turbine output set to: " + euOutput + " EU/t";
        }
            
        return "Turbine output: " + euOutput + " EU/t";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euOutput = par1NBTTagCompound.getInteger("euOutput");
        steamBuffer = par1NBTTagCompound.getInteger("steamBuffer");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euOutput", euOutput);
        par1NBTTagCompound.setInteger("steamBuffer", steamBuffer);
    }

    // IC2 Methods
    @Override
    public boolean emitsEnergyTo(TileEntity receiver, Direction direction)
    {
        return receiver instanceof IEnergyTile;
    }
    
    @Override
    public int getMaxEnergyOutput()
    {
        return euOutput;
    }
        
    // Ambient Sounds
    @Override
    public boolean isWorking()
    {
        return isPowered();
    }
    
    @Override
    public String getSoundFileName()
    {
        return "breather.wav";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * MachineryCore.turbineVolume;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * MachineryCore.turbinePitch;
    }
}
