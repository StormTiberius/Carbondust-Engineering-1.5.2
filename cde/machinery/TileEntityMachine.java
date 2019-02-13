/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.core.sound.TileEntityWithSound;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileEntityMachine extends TileEntityWithSound implements IEnergyTile
{
    protected abstract String useWrench(boolean flag);
    protected abstract void doWorkCycle();
    protected TileEntity tec;
    protected boolean isAddedToEUGrid = false;
    protected boolean isRedstonePowered = false;
    private boolean flag = false;
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        flag = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
        
        if(isRedstonePowered != flag)
        {
            isRedstonePowered = flag;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        
        if(!isAddedToEUGrid)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            isAddedToEUGrid = true;
        }
        
        if(!worldObj.isRemote && isPowered())
        {
            doWorkCycle();
        }
    }
    
    @Override
    public void invalidate()
    {
        if(isAddedToEUGrid)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            isAddedToEUGrid = false;
        }
              
        super.invalidate();
    }
    
    @Override
    public void onChunkUnload()
    {
        if(isAddedToEUGrid)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            isAddedToEUGrid = false;
        }
        
        super.onChunkUnload();
    }
    
    protected boolean isPowered()
    {
        return isRedstonePowered;
    }
    
    protected boolean isConnected(ForgeDirection side)
    {
        tec = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
        
        return tec instanceof IEnergyConductor;
    }
    
    // IC2 Method
    @Override
    public boolean isAddedToEnergyNet()
    {
        return isAddedToEUGrid;
    }
}
