/**
 *
 * @author StormTiberius
 */

package cde.compat;

import com.eloraam.redpower.RedPowerBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PlayerEventManager
{
    private static final String SCREEN = "com.eloraam.redpower.control.TileDisplay";
    private static final String DISK_DRIVE = "com.eloraam.redpower.control.TileDiskDrive";
    private static final String IO_EXPANDER = "com.eloraam.redpower.control.TileIOExpander";
    private static final String SORTRON = "com.eloraam.redpower.machine.TileSortron";
    private static final String LOGIC = "com.eloraam.redpower.logic.TileLogicPointer";
    private static final String SCREW_DRIVER_REGULAR = "com.eloraam.redpower.base.ItemScrewdriver";
    private static final String SCREW_DRIVER_SONIC = "com.eloraam.redpower.machine.ItemSonicDriver";
    private static final String CANVAS_BAG = "com.eloraam.redpower.base.ItemBag";
    
    private final boolean canvas,ioexpander,timergui;
    
    public PlayerEventManager(boolean canvas, boolean ioexpander, boolean timergui)
    {
        this.canvas = canvas;
        this.ioexpander = ioexpander;
        this.timergui = timergui;
    }
    
    @ForgeSubscribe
    public void pie(PlayerInteractEvent event)
    {
        ItemStack is = event.entityPlayer.getCurrentEquippedItem();
        String held = "";
        
        if(is != null)
        {
            Item item = is.getItem();

            if(item != null)
            {
                held = item.getClass().getCanonicalName();
            }
        }
        
        if(canvas && isCanvasBag(held) && (event.action.equals(Action.RIGHT_CLICK_AIR) || event.action.equals(Action.RIGHT_CLICK_BLOCK)))
        {
            if(!event.entityPlayer.worldObj.isRemote && !MinecraftServer.getServer().isSinglePlayer())
            {
                event.setCanceled(true);
            }
        }
        else if((ioexpander || timergui) && event.action.equals(Action.RIGHT_CLICK_BLOCK))
        {
            TileEntity te = event.entityPlayer.worldObj.getBlockTileEntity(event.x, event.y, event.z);
            
            if(te != null)
            {
                String s = te.getClass().getCanonicalName();
                
                if(event.entityPlayer.isSneaking() && hasRedBus(s) && hasTool(held) && is != null)
                {
                    if(is.getItemDamage() < is.getMaxDamage())
                    {
                        screwDriverUsed(event);
                        is.damageItem(1, event.entityPlayer);  
                    }
                    else if(canDestroy(held))
                    {
                        screwDriverUsed(event);
                        event.entityPlayer.destroyCurrentEquippedItem();
                    }
                }
                else if(!hasTool(held) && isLogic(s))
                {
                    if(is == null)
                    {
                        event.entityPlayer.swingItem();
                    }
                    else
                    {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    
    private void screwDriverUsed(PlayerInteractEvent event)
    {
        event.entityPlayer.swingItem();
        
        if(!event.entityPlayer.worldObj.isRemote)
        {
            event.entityPlayer.openGui(RedPowerBase.instance, 3, event.entityPlayer.worldObj, event.x, event.y, event.z);    
        }
    }
    
    private boolean hasRedBus(String s)
    {
        if(s.equalsIgnoreCase(SCREEN))
        {
            return true;
        }
        else if(s.equalsIgnoreCase(DISK_DRIVE))
        {
            return true;
        }
        else if(s.equalsIgnoreCase(IO_EXPANDER))
        {
            return true;
        }
        else if(s.equalsIgnoreCase(SORTRON))
        {
            return true;
        }
        
        return false;
    }
    
    private boolean hasTool(String s)
    {
        if(s.equalsIgnoreCase(SCREW_DRIVER_REGULAR))
        {
            return true;
        }
        else if(s.equalsIgnoreCase(SCREW_DRIVER_SONIC))
        {
            return true;
        }
        
        return false;
    }
    
    private boolean isLogic(String s)
    {
        return s.equalsIgnoreCase(LOGIC);
    }
    
    private boolean isCanvasBag(String s)
    {
        return s.equalsIgnoreCase(CANVAS_BAG);
    }
    
    private boolean canDestroy(String s)
    {
        return s.equalsIgnoreCase(SCREW_DRIVER_REGULAR);
    }
}
