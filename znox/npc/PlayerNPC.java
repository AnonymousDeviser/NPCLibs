package znox.npc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import znox.npc.network.NPCNetworkManager;
import znox.npc.network.NPCPlayerConnection;
import net.minecraft.server.v1_7_R4.DamageSource;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.server.v1_7_R4.WorldServer;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class PlayerNPC extends EntityPlayer {
	
	public boolean LookClose = false;
	public int looktask;
	  
	public PlayerNPC(MinecraftServer MCServer, WorldServer WServer, GameProfile GProfile, PlayerInteractManager PIManager) throws Exception {
		super(MCServer, WServer, GProfile, PIManager);
	    NetworkManager NullManager = new NPCNetworkManager();
	    this.playerConnection = new NPCPlayerConnection(MCServer, NullManager, this);
	    NullManager.a(this.playerConnection);
	}
	  
	private final float getLocalAngle(Vector point1, Vector point2){
		double dx = point2.getX() - point1.getX();
		double dz = point2.getZ() - point1.getZ();
		float angle = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
		if (angle < 0.0F) {
			angle += 360.0F;
		}
		return angle;
	}
	  
	public void lookAt(Location location, Location loc2){
		setYaw(getLocalAngle(new Vector(this.locX, 0.0D, this.locZ), location.toVector()));
		double xDiff = loc2.getX() - getBukkitEntity().getLocation().getX();
	    double yDiff = loc2.getY() - getBukkitEntity().getLocation().getY();
	    double zDiff = loc2.getZ() - getBukkitEntity().getLocation().getZ();
	    double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
	    double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
	    double newPitch = Math.acos(yDiff / DistanceY) * 180.0D / 3.141592653589793D - 90.0D;
	    this.pitch = ((float)newPitch);
	}
	  
	public void setYaw(float yaw){
		this.yaw = yaw;
	    this.aP = yaw;
	    this.aO = yaw;
	}
	  
	public void setPitch(float pitch){
		this.pitch = pitch;
	}
	  
	@SuppressWarnings("deprecation")
	public void sendUpatePackets(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.hidePlayer(getBukkitEntity());
		}	
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.showPlayer(getBukkitEntity());
		}
	}
  
	public boolean damageEntity(DamageSource damagesource, float f){
		return false;
	}

}
