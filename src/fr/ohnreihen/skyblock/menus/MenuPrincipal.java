package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.monde.Monde;

public class MenuPrincipal {
	
	private static Inventory inventaire;
	public static final String NOMMENU = "Menu principal";
	public static final String MONDEPVE = "§7[Téléportation]§r PVE";
	public static final String MONDEILE = "§7[Téléportation]§r Ile";
	public static final String MONDESPAWN = "§7[Téléportation]§r Spawn";
	
	
	public static Inventory getInventaire() {
		return inventaire;
	}
	
	
	
	public static  void creerMenu() {
		
		inventaire = Bukkit.createInventory(null, 54, NOMMENU);

		
		ItemStack boutique = new ItemStack (Material.DIAMOND);
		ItemMeta itemMetaB = boutique.getItemMeta();
		itemMetaB.setDisplayName(MenuShopCategorie.NOMMENU);
		boutique.setItemMeta(itemMetaB);
		inventaire.setItem(10, boutique);
		
		ItemStack option0 = new ItemStack (Material.BELL);
		ItemMeta itemMeta0 = option0.getItemMeta();
		itemMeta0.setDisplayName(MONDESPAWN);
		option0.setItemMeta(itemMeta0);
		inventaire.setItem(21,option0);
		
		ItemStack option1 = new ItemStack (Material.DRAGON_HEAD);
		ItemMeta itemMeta1 = option1.getItemMeta();
		itemMeta1.setDisplayName(MONDEPVE);
		option1.setItemMeta(itemMeta1);
		inventaire.setItem(22,option1 );
		
		ItemStack option2 = new ItemStack (Material.FLOWERING_AZALEA);
		ItemMeta itemMeta2 = option2.getItemMeta();
		itemMeta2.setDisplayName(MONDEILE);
		option2.setItemMeta(itemMeta2);
		inventaire.setItem(23,option2);


		for (int i=0; i<inventaire.getSize(); i++) {
			if (inventaire.getItem(i)==null) {

				ItemStack optionFiller = new ItemStack (Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta itemMetaFiller = optionFiller.getItemMeta();
				itemMetaFiller.setDisplayName(" ");
				optionFiller.setItemMeta(itemMetaFiller);
				inventaire.setItem(i,optionFiller );
			}
		}
	
	}
	public static  ItemStack getMenuItem() {		
		//ItemStack item = new ItemStack(Material.COMPASS);
		ItemStack item= new ItemStack(Material.END_CRYSTAL);
		ItemMeta metaItem = item.getItemMeta();
		metaItem.setDisplayName(NOMMENU);
		//majLore();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§fClick droit");
		lore.add("§fpour ouvrir le menu");
		lore.add("§fdes mondes");
		metaItem.setLore(lore);
		
		metaItem.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		metaItem.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		metaItem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		metaItem.setUnbreakable(true);
		item.setItemMeta(metaItem);
		return item;
	}
	
	public static void ouvrirMenuPrincipal(Player player) {
		player.openInventory(inventaire);
	}

	public static void utiliserMenu(InventoryClickEvent event) throws JoueurNonEnregistrerException {
		Player player = (Player) event.getWhoClicked();

		ItemStack itemUsed = event.getCurrentItem();
		if(itemUsed.getItemMeta().getDisplayName().equals(MenuShopCategorie.NOMMENU)) {
			MenuShopCategorie.ouvrirMenuCategorie(player);
		}else if(itemUsed.getItemMeta().getDisplayName().equals(MenuPrincipal.MONDEPVE)) {
			Monde.changerMonde(player, Monde.TYPE_PVE);
			player.closeInventory();
			//System.out.println("Le joueur part dans le monde PVE");
		}else if (itemUsed.getItemMeta().getDisplayName().equals(MenuPrincipal.MONDEILE)){
			Monde.changerMonde(player, Monde.TYPE_ILE);
			player.closeInventory();
			//System.out.println("Le joueur part sur son ile");
		}else if (itemUsed.getItemMeta().getDisplayName().equals(MenuPrincipal.MONDESPAWN)){
			Monde.changerMonde(player, Monde.TYPE_SPAWN);
			player.closeInventory();
		}
		event.setCancelled(true);
	}
}
