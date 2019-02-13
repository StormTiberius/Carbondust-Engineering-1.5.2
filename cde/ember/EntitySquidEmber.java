/**
 *
 * @author StormTiberius
 */

package cde.ember;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

public class EntitySquidEmber extends EntitySquid
{
    public EntitySquidEmber(World world)
    {
        super(world);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkIfAABBIsClear(this.boundingBox);
    }
}
