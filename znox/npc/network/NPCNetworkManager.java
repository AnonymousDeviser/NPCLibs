package znox.npc.network;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R4.NetworkManager;

public class NPCNetworkManager extends NetworkManager {

	public NPCNetworkManager() throws Exception {
		super(false);
		swapFields();
	}
		  
	protected void swapFields() throws Exception {
		Field channel = NetworkManager.class.getDeclaredField("m");
		Field address = NetworkManager.class.getDeclaredField("n");
		channel.setAccessible(true);
	    address.setAccessible(true);
	    channel.set(this, new NullChannel(null));
	    address.set(this, null);
	}

}
