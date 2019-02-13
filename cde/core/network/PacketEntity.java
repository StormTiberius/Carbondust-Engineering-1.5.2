package cde.core.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class PacketEntity extends PacketCDE
{
    public int entityId;

    public PacketEntity(){}
    
    public PacketEntity(int entityId)
    {
        this.entityId = entityId;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(entityId);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        entityId = data.readInt();
    }
}
