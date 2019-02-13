/**
 *
 * @author StormTiberius
 */

package cde.core.network;

import cde.api.INetwork;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload message, Player player)
    {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(message.data));  
        
        try
        {
            int packetId = data.read();
            
            EntityPlayer ep = (EntityPlayer)player;
            
            switch (packetId)
            {
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void sendPacketToEntity(Object object, EntityPlayer player)
    {
        PacketEntity packet = (PacketEntity)object;

        Entity entity = player.worldObj.getEntityByID(packet.entityId);

        if(entity instanceof INetwork)
        {
            ((INetwork)entity).receivePacket(object, player);
        }
    }
    
    private void sendPacketToTile(Object object, EntityPlayer player)
    {
        PacketTile packet = (PacketTile)object;
        
        TileEntity te = player.worldObj.getBlockTileEntity(packet.xCoord, packet.yCoord, packet.zCoord);
            
        if(te instanceof INetwork)
        {
            ((INetwork)te).receivePacket(object, player);
        }
    }
}
