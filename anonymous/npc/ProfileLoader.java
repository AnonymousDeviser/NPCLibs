package anonymous.npc;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ProfileLoader {
	
	private final String uuid;
	private final String name;
	private final String skinOwner;
	  
	public ProfileLoader(String uuid, String name){
	    this(uuid, name, name);
	}
	  
	public ProfileLoader(String uuid, String name, String skinOwner){
		this.uuid = (uuid == null ? null : uuid.replaceAll("-", ""));
	    

		String displayName = ChatColor.translateAlternateColorCodes('&', name);
		this.name = ChatColor.stripColor(displayName);
		this.skinOwner = skinOwner;
	}
	  
	public GameProfile loadProfile(){
		UUID id = this.uuid == null ? parseUUID(getUUID(this.name)) : parseUUID(this.uuid);
		GameProfile profile = new GameProfile(id, this.name);
		addProperties(profile);
		return profile;
	}
	  
	@SuppressWarnings("resource")
	private void addProperties(GameProfile profile){
		String uuid = getUUID(this.skinOwner);
		try{
			URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
			URLConnection uc = url.openConnection();
			uc.setUseCaches(false);
			uc.setDefaultUseCaches(false);
			uc.addRequestProperty("User-Agent", "Mozilla/5.0");
			uc.addRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
			uc.addRequestProperty("Pragma", "no-cache");
	      

			String json = new Scanner(uc.getInputStream(), "UTF-8").useDelimiter("\\A").next();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONArray properties = (JSONArray)((JSONObject)obj).get("properties");
			for (int i = 0; i < properties.size(); i++) {
				try{
					JSONObject property = (JSONObject)properties.get(i);
					String name = (String)property.get("name");
					String value = (String)property.get("value");
					String signature = property.containsKey("signature") ? (String)property.get("signature") : null;
					if (signature != null) {
						profile.getProperties().put(name, new Property(name, value, signature));
					} else {
						profile.getProperties().put(name, 
								new Property(value, name));
					}
				} catch (Exception e) {
					Bukkit.getLogger().log(Level.WARNING, "Erro ao aplicar propriedade", e);
				}
			}
		} catch (Exception localException1) {}
	}
	  
	@SuppressWarnings("deprecation")
	private String getUUID(String name){
		return Bukkit.getOfflinePlayer(name).getUniqueId().toString().replaceAll("-", "");
	}
	  
	private UUID parseUUID(String uuidStr){
		String[] uuidComponents = { uuidStr.substring(0, 8), uuidStr.substring(8, 12), uuidStr.substring(12, 16), uuidStr.substring(16, 20), uuidStr.substring(20, uuidStr.length()) };

		StringBuilder builder = new StringBuilder();
		for (String component : uuidComponents) {
			builder.append(component).append('-');
		}
		builder.setLength(builder.length() - 1);
		return UUID.fromString(builder.toString());
	}

}
