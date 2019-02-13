/**
 *
 * @author StormTiberius
 */

package cde.compat;

import net.minecraft.world.ChunkPosition;

public class EntityItemPosition extends ChunkPosition
{
    public final int id,md;
    
    public EntityItemPosition(int x, int y, int z, int id, int md)
    {
        super(x,y,z);
        this.id = id;
        this.md = md;
    }
}
