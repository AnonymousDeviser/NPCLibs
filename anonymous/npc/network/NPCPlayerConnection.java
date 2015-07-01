package anonymous.npc.network;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayInBlockDig;
import net.minecraft.server.v1_7_R4.PacketPlayInBlockPlace;
import net.minecraft.server.v1_7_R4.PacketPlayInChat;
import net.minecraft.server.v1_7_R4.PacketPlayInFlying;
import net.minecraft.server.v1_7_R4.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_7_R4.PacketPlayInTransaction;
import net.minecraft.server.v1_7_R4.PacketPlayInUpdateSign;
import net.minecraft.server.v1_7_R4.PacketPlayInWindowClick;
import net.minecraft.server.v1_7_R4.PlayerConnection;

public class NPCPlayerConnection extends PlayerConnection {
	
	public NPCPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer){
	    super(minecraftserver, networkmanager, entityplayer);
	}
	  
	public void a(PacketPlayInWindowClick packet) {}
	  
	public void a(PacketPlayInTransaction packet) {}
	
	public void a(PacketPlayInFlying packet) {}
	  
	public void a(PacketPlayInUpdateSign packet) {}
	  
	public void a(PacketPlayInBlockDig packet) {}
	  
	public void a(PacketPlayInBlockPlace packet) {}
	  
	public void disconnect(String s) {}
	  
	public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {}
	
	public void a(PacketPlayInChat packetplayinchat) {}
	  
	public void sendPacket(Packet packet) {}

}
