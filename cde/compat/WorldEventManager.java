/**
 *
 * @author StormTiberius
 */

package cde.compat;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class WorldEventManager
{
    private final ItemStack[] isa;
    private static final List<EntityItemPosition> EIP_LIST = new ArrayList();
    
    public WorldEventManager(ItemStack[] isa)
    {
        this.isa = isa;
    }
    
    @ForgeSubscribe
    public void ejwe(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityItem)
        {
            EntityItem ei = (EntityItem)event.entity;
            ItemStack is = ei.getEntityItem();

            if(is != null)
            {
                for(ItemStack stack : isa)
                {
                    if(ItemStack.areItemStacksEqual(stack, is))
                    {
                        EntityItemPosition eip = new EntityItemPosition((int)ei.posX, (int)ei.posY, (int)ei.posZ, is.itemID, is.getItemDamage());
                        
                        for (ListIterator<EntityItemPosition> iter = EIP_LIST.listIterator(); iter.hasNext();)
                        {
                            EntityItemPosition element = iter.next();
                                
                            if(isTwin(element,eip))
                            {
                                event.setCanceled(true);
                                return;
                            }
                        }

                        EIP_LIST.add(eip);                        
                        break;
                    }
                }
            }
        } 
    }
    
    private boolean isTwin(EntityItemPosition a, EntityItemPosition b)
    {
        return (a.x == b.x && a.y == b.y && a.z == b.z && a.id == b.id && a.md == b.md);
    }
    
    protected static void clearList()
    {
        if(!EIP_LIST.isEmpty())
        {
            EIP_LIST.clear();
        }
    }
}
