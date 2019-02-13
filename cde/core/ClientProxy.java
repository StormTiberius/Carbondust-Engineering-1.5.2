/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.CDECore;
import cde.core.sound.SoundEventManager;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{   
    @Override
    public void preloadTextures()
    {
        MinecraftForgeClient.preloadTexture(CDECore.CDE_BLOCKS);
        MinecraftForgeClient.preloadTexture(CDECore.CDE_ITEMS);
    }
    
    @Override
    public void sendToServer(Packet packet)
    {
        FMLClientHandler.instance().getClient().getSendQueue().addToSendQueue(packet);
    }
    
    @Override
    public void setupSound()
    {
        MinecraftForge.EVENT_BUS.register(new SoundEventManager());
    }
}
