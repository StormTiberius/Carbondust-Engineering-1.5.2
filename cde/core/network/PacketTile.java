package cde.core.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class PacketTile extends PacketCDE
{
    public int xCoord,yCoord,zCoord;
    
    public PacketTile(){}
    
    public PacketTile(int xCoord, int yCoord, int zCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(xCoord);
        data.writeInt(yCoord);
        data.writeInt(zCoord);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        xCoord = data.readInt();
        yCoord = data.readInt();
        zCoord = data.readInt();
    }
}
