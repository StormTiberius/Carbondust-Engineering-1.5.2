/**
 *
 * @author StormTiberius
 */

package cde.api;

import net.minecraft.entity.player.EntityPlayer;

public interface INetwork
{
    public abstract void receivePacket(Object packet, EntityPlayer player);
}
