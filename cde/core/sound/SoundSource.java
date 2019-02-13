/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import java.net.URL;

public class SoundSource
{
    public final boolean priority;
    public final String sourceName;
    public final URL url;
    public final String identifier;
    public final boolean toLoop;
    public final float x;
    public final float y;
    public final float z;
    public final int attModel;
    public final float distOrRoll;
    
    public SoundSource(boolean priority, String sourceName, URL url, String identifier, boolean toLoop, float x, float y, float z, int attModel, float distOrRoll)
    {
        this.priority = priority;
        this.sourceName = sourceName;
        this.url = url;
        this.identifier = identifier;
        this.toLoop = toLoop;
        this.x = x;
        this.y = y;
        this.z = z;
        this.attModel = attModel;
        this.distOrRoll = distOrRoll;
    }
}
