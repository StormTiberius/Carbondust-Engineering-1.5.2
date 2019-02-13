/**
 *
 * @author StormTiberius
 */

package cde.ember;

import java.util.Calendar;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBatEmber extends EntityBat
{
    public EntityBatEmber(World world)
    {
        super(world);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        int var1 = MathHelper.floor_double(this.boundingBox.minY);
        int var2 = MathHelper.floor_double(this.posX);
        int var3 = MathHelper.floor_double(this.posZ);
        int var4 = this.worldObj.getBlockLightValue(var2, var1, var3);
        byte var5 = 4;
        Calendar var6 = this.worldObj.getCurrentDate();

        if ((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3))
        {
            if (this.rand.nextBoolean())
            {
                return false;
            }
        }
        else
        {
            var5 = 7;
        }

        return var4 > this.rand.nextInt(var5) ? false : getCanSpawnHereEmber();
    }
    
    private boolean getCanSpawnHereEmber()
    {
        return this.worldObj.checkIfAABBIsClear(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
}
