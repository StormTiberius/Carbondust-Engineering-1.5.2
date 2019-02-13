/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class EmberEventManager
{
    private final int[] ALLOWED_BLOCK_IDS;
    private final boolean MOB_SPAWN_RULES;
    
    public EmberEventManager(boolean flag)
    {
        ALLOWED_BLOCK_IDS = new int[]
        {    
            Block.stone.blockID,
            Block.cobblestone.blockID,
            Block.cobblestoneMossy.blockID,
            Block.dirt.blockID,
            Block.gravel.blockID,
            Block.obsidian.blockID,
            Block.bedrock.blockID
        };
        
        MOB_SPAWN_RULES = flag;
    }
            
    @ForgeSubscribe
    public void psibe(PlayerSleepInBedEvent event)
    {
        if(!event.entityPlayer.worldObj.isRemote && event.entityPlayer.dimension == 0)
        {
            event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false);
        }
    }
    
    @ForgeSubscribe
    public void lse(LivingSpawnEvent event)
    {
        if(MOB_SPAWN_RULES && !event.entity.worldObj.isRemote && event.entity.dimension == EmberCore.getDimensionId())
        {
            if(event.entity instanceof EntityMob)
            {
                int id = event.world.getBlockId((int)event.x, (int)event.y - 1, (int)event.z);
                
                boolean flag = false;
                
                for(int blockId : ALLOWED_BLOCK_IDS)
                {
                    if(blockId == id)
                    {
                        flag = true;
                        break;
                    }
                }
                
                if(!flag)
                {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
