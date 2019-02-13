/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class EventManagerTropics
{
    private final int[] ALLOWED_BLOCK_IDS;
    private final int DIMENSION_ID;
    
    public EventManagerTropics(int dimensionId)
    {
        ALLOWED_BLOCK_IDS = new int[]
        {    
            Block.stone.blockID, 
            Block.cobblestone.blockID, 
            Block.cobblestoneMossy.blockID, 
            Block.dirt.blockID, 
            Block.grass.blockID, 
            Block.sand.blockID, 
            Block.gravel.blockID, 
            Block.obsidian.blockID,
            Block.blockSnow.blockID,
            Block.bedrock.blockID
        };
        
        DIMENSION_ID = dimensionId;
    }
    
    @ForgeSubscribe
    public void lse(LivingSpawnEvent event)
    {
        if(event.entity.dimension == DIMENSION_ID)
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
