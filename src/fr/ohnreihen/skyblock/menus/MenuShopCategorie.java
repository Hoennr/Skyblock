package fr.ohnreihen.skyblock.menus;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class MenuShopCategorie {

	private static Inventory inventaire;
	public static final String NOMMENU = "Boutique - Catégories";
	
	
	public static Inventory getInventaire() {
		return inventaire;
	}
	public static  void creerMenu() {
		
		inventaire = Bukkit.createInventory(null, 27, NOMMENU);


		for(int i=0; i<Produits.NB_CATEGORIE;i++) {
			ItemStack is = Produits.IS_CATEGORIE[i];
			ItemMeta itemMeta = is.getItemMeta();
			itemMeta.setDisplayName(Produits.NOM_CATEGORIE[i]);
			is.setItemMeta(itemMeta);
			inventaire.setItem(i, is);
			
		}
		for (int i = Produits.NB_CATEGORIE; i<inventaire.getSize();i++) {
			ItemStack optionFiller = new ItemStack (Material.WHITE_STAINED_GLASS_PANE);
			ItemMeta itemMetaFiller = optionFiller.getItemMeta();
			itemMetaFiller.setDisplayName(" ");
			optionFiller.setItemMeta(itemMetaFiller);
			inventaire.setItem(i,optionFiller );
		}
		
		ItemStack boutonBack = new ItemStack (Material.RED_STAINED_GLASS_PANE);
		ItemMeta itemMeta = boutonBack.getItemMeta();
		itemMeta.setDisplayName("Retour");
		boutonBack.setItemMeta(itemMeta);
		inventaire.setItem(19,boutonBack );
	}
	
	public static void ouvrirMenuCategorie(Player player) {
		System.out.println("J'etais dans le menu principal et je vais dans le menu categorie");
		player.openInventory(inventaire);
	}
	
	public static void utiliserMenu(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();
		String [] nomCategorie = Produits.NOM_CATEGORIE;
		ItemStack itemUsed = event.getCurrentItem();
		String nomItem = itemUsed.getItemMeta().getDisplayName();
		
		if(Arrays.asList(nomCategorie).contains(nomItem)) {
			System.out.println("Le nom de l'item est compris dans la liste des categorie");
			MenuBlocks.ouvrirMenuBlocks(player, itemUsed.getItemMeta().getDisplayName() );
			
		}else if (itemUsed.getItemMeta().getDisplayName().equals("Retour")) {
			MenuPrincipal.ouvrirMenuPrincipal(player);
		}
		
	}
	
}
