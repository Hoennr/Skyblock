package fr.ohnreihen.skyblock;

import org.bukkit.plugin.java.JavaPlugin;

import fr.ohnreihen.skyblock.commands.CommandKit;
import fr.ohnreihen.skyblock.commands.CommandMenu;
import fr.ohnreihen.skyblock.commands.CommandSell;
import fr.ohnreihen.skyblock.menus.MenuBlocks;
import fr.ohnreihen.skyblock.menus.MenuPrincipal;
import fr.ohnreihen.skyblock.menus.MenuShopCategorie;
import fr.ohnreihen.skyblock.menus.Produits;



public class Main extends JavaPlugin {
	

	private static Main plugin; 
	

	@Override
	public void onEnable()  {
		
		System.out.println("Le serveur à bien démarré pour SKYBLOCK V1 !!!");
		
		getCommand("sell").setExecutor(new CommandSell());
		getCommand("menu").setExecutor(new CommandMenu());
		getCommand("kit").setExecutor(new CommandKit());
		
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