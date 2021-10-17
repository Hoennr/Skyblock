package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuMonde {
	
	private static Inventory inventaire;
	public static final String NOMMENU = "Menu des mondes ";
	public static final String MONDEPVE = "Monde PVE";
	public static final String MONDEILE = "Mon �le";
	
	
	public static  void creerMenuMonde() {
		
		inventaire = Bukkit.createInventory(null, InventoryType.HOPPER,NOMMENU);
		ItemStack option1 = new ItemStack (Material.DRAGON_HEAD);
		ItemMeta itemMeta1 = option1.getItemMeta();
		itemMeta1.setDisplayName(MONDEPVE);
		option1.setItemMeta(itemMeta1);
		inventaire.setItem(0,option1 );
		

		ItemStack option2 = new ItemStack (Material.FLOWERING_AZALEA);
		ItemMeta itemMeta2 = option2.getItemMeta();
		itemMeta2.setDisplayName(MONDEILE);
		option2.setItemMeta(itemMeta2);
		inventaire.setItem(1,option2);
		
	}
	public static  ItemStack getMenuItem() {		
		//ItemStack item = new ItemStack(Material.COMPASS);
		ItemStack item= new ItemStack(Material.END_CRYSTAL);
		ItemMeta metaItem = item.getItemMeta();
		metaItem.setDisplayName(NOMMENU);
		//majLore();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add("�fClick droit");
		lore.add("�fpour ouvrir le menu");
		lore.add("�fdes mondes");
		metaItem.setLore(lore);
		
		metaItem.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		metaItem.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		metaItem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		metaItem.setUnbreakable(true);
		item.setItemMeta(metaItem);
		return item;
	}
	public static Inventory getInventaire() {
		return inventaire;
	}
	public static void setInventaire(Inventory inventaire) {
		MenuMonde.inventaire = inventaire;
	}
	
	public static void ouvrirMenuMonde(Player player) {
		
		player.openInventory(inventaire);
		
	}

}
