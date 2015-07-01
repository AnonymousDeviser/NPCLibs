package anonymous;

import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.server.v1_7_R4.WorldServer;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;

import anonymous.npc.PlayerNPC;
import anonymous.npc.ProfileLoader;

public class Exemplo {
	
	public ArrayList<PlayerNPC> npcs = new ArrayList<PlayerNPC>();
	
	public void criarNPC(Location location, String name){
		WorldServer world = ((CraftWorld)location.getWorld()).getHandle();
		try{
			PlayerNPC npc = new PlayerNPC(MinecraftServer.getServer(), world, new ProfileLoader(UUID.randomUUID().toString(), name, name).loadProfile(), new PlayerInteractManager(world));
			npc.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
			world.addEntity(npc);
			npc.setYaw(location.getYaw());
			npc.setPitch(location.getPitch());
			npcs.add(npc);
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	public void removerNPCs(){
		for(PlayerNPC npc : npcs){
			npc.getBukkitEntity().remove();
		}
		npcs.clear();
	}

}
