package fr.ohnreihen.skyblock;

import org.bukkit.plugin.java.JavaPlugin;

import fr.ohnreihen.skyblock.menus.MenuBlocks;
import fr.ohnreihen.skyblock.menus.MenuPrincipal;
import fr.ohnreihen.skyblock.menus.MenuShopCategorie;
import fr.ohnreihen.skyblock.menus.Produits;



public class Main extends JavaPlugin {
	

	private static Main plugin; 
	

	@Override
	public void onEnable()  {
		
		System.out.println("Le serveur à bien démarré pour SKYBLOCK V1 !!!");
		super.onEnable();
		plugin = this;
		getServer().getPluginManager().registerEvents(new MonPluginListener(), this); 

		Produits.initialiserProduits();
		MenuPrincipal.creerMenu();
		MenuShopCategorie.creerMenu();
		MenuBlocks.creerMenu();

	}
	
	
	
	
	
	public static Main getPlugin() {
		
		return plugin;
		
	}



	
	
}