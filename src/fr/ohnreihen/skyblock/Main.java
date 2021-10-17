package fr.ohnreihen.skyblock;

import org.bukkit.plugin.java.JavaPlugin;

import fr.ohnreihen.skyblock.menus.MenuMonde;



public class Main extends JavaPlugin {
	

	private static Main plugin; 
	

	@Override
	public void onEnable()  {
		
		System.out.println("Le serveur à bien démarré pour SKYBLOCK V1 !!!");
		super.onEnable();
		plugin = this;
		getServer().getPluginManager().registerEvents(new MonPluginListener(), this); 
		MenuMonde.creerMenuMonde();


	}
	
	
	
	
	
	public static Main getPlugin() {
		
		return plugin;
		
	}



	
	
}
