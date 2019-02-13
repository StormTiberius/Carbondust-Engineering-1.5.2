/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import static net.minecraft.client.audio.SoundManager.sndSystem;

public class SoundHelper
{
    private static final ArrayList sources = new ArrayList<TileEntityWithSound>();
    private static Minecraft mc;
    private static boolean init;
    private static float soundVolume,masterVolume;
    
    public static void initSoundHelper()
    {
        mc = Minecraft.getMinecraft();
        soundVolume = mc.gameSettings.soundVolume;
        init = true;
    }
    
    public static void onUpdate()
    {
        if(init)
        {
            masterVolume = mc.gameSettings.soundVolume;
            
            if(soundVolume != masterVolume)
            {
                soundVolume = masterVolume;
                updateVolume();
            }
        }
    }
    
    private static void updateVolume()
    {
        if(!sources.isEmpty())
        {
            for(Object object : sources)
            {
                TileEntityWithSound te = (TileEntityWithSound)object;
                sndSystem.setVolume(te.getSourceName(), te.getVolume() * soundVolume);
            }
        }
    }
    
    public static void updateTileSound(TileEntityWithSound te, boolean active)
    {
        if(active)
        {
            playTileSound(te);
        }
        else
        {
            stopTileSound(te);
        }
    }
    
    private static void playTileSound(TileEntityWithSound te)
    {
        if(!te.isPlaying())
        {
            sndSystem.play(te.getSourceName());
            te.setPlaying(true);
        }
    }
    
    private static void stopTileSound(TileEntityWithSound te)
    {
        if(te.isPlaying())
        {
            sndSystem.stop(te.getSourceName());
            te.setPlaying(false);
        }
    }
    
    public static void addSource(TileEntityWithSound te)
    {
        SoundSource src = te.getSoundSource();
        
        sndSystem.newSource(src.priority, src.sourceName, src.url, src.identifier, src.toLoop, src.x, src.y, src.z, src.attModel, src.distOrRoll);
        sndSystem.setVolume(src.sourceName, te.getVolume() * soundVolume);
        sndSystem.setPitch(src.sourceName, te.getPitch());
        sources.add(te);
    }
    
    public static void removeSource(TileEntityWithSound a)
    {
        Object temp = null;
        
        for(Object object : sources)
        {
            TileEntityWithSound b = (TileEntityWithSound)object;
            
            if(a.xCoord == b.xCoord && a.yCoord == b.yCoord && a.zCoord == b.zCoord)
            {
                temp = object;
                break;
            }
        }
        
        if(temp != null)
        {
            sources.remove(temp);
            stopTileSound(a);
            sndSystem.removeSource(a.getSourceName());
        }
    }
    
    public static void removeAll()
    {
        if(!sources.isEmpty())
        {
            for(Object object : sources)
            {
                TileEntityWithSound te = (TileEntityWithSound)object;
                stopTileSound(te);
                sndSystem.removeSource(te.getSourceName());
            }

            sources.clear();
        }
    }
}
