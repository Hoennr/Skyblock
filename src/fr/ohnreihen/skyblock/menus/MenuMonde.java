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
	public static final String MONDEILE = "Mon île";
	public static final String MONDESPAWN = "Le spawn";
	
	
	public static  void creerMenuMonde() {
		
		inventaire = Bukkit.createInventory(null, InventoryType.HOPPER,NOMMENU);

		ItemStack option0 = new ItemStack (Material.BELL);
		ItemMeta itemMeta0 = option0.getItemMeta();
		itemMeta0.setDisplayName(MONDESPAWN);
		option0.setItemMeta(itemMeta0);
		inventaire.setItem(0,option0);

		ItemStack optionFiller = new ItemStack (Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta itemMetaFiller = optionFiller.getItemMeta();
		itemMetaFiller.setDisplayName(" ");
		optionFiller.setItemMeta(itemMetaFiller);
		inventaire.setItem(1,optionFiller );
		
		ItemStack option1 = new ItemStack (Material.DRAGON_HEAD);
		ItemMeta itemMeta1 = option1.getItemMeta();
		itemMeta1.setDisplayName(MONDEPVE);
		option1.setItemMeta(itemMeta1);
		inventaire.setItem(2,option1 );

		ItemStack optionFiller1 = new ItemStack (Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta itemMetaFiller1 = optionFiller1.getItemMeta();
		itemMetaFiller1.setDisplayName(" ");
		optionFiller1.setItemMeta(itemMetaFiller1);
		inventaire.setItem(3,optionFiller1 );
		
		ItemStack option2 = new ItemStack (Material.FLOWERING_AZALEA);
		ItemMeta itemMeta2 = option2.getItemMeta();
		itemMeta2.setDisplayName(MONDEILE);
		option2.setItemMeta(itemMeta2);
		inventaire.setItem(4,option2);
		

		
	
		
		
		
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
