/**
 *
 * @author StormTiberius
 */

package cde.tweak;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class EntityEventManager
{
    private final boolean[] FLAGS;
    
    public EntityEventManager(boolean[] flags)
    {
        this.FLAGS = flags;
    }
    
    @ForgeSubscribe
    public void eem(LivingFallEvent event)
    {
        if(FLAGS[14] && event.entity instanceof EntityPlayerMP)
        {
            event.distance *= 0.3F;
        }
    }
    
    @ForgeSubscribe
    public void eem(EntityJoinWorldEvent event)
    {
        if(FLAGS[15] && !event.world.isRemote && event.entity instanceof EntityXPOrb)
        {
            event.setCanceled(true);
        }
    }
    
    @ForgeSubscribe
    public void eem(LivingUpdateEvent event)
    {
        if(FLAGS[34] && !event.entity.worldObj.isRemote)
        {
            if(event.entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)event.entity;

                PotionEffect pe = player.getActivePotionEffect(Potion.nightVision);
                
                if(pe != null && pe.getDuration() < 240)
                {
                    player.removePotionEffect(Potion.nightVision.id);
                }
            }
        }
    }
}
