package fr.ohnreihen.skyblock.Joueur;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class HachePerso extends OutilIncassable {
	
	public  ItemStack hache = new ItemStack(Material.WOODEN_AXE);
	public static String NOMHACHE = "§eMa Super hache";
	public static int enchantEfficiencyParNiveau[]= new int []{0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,2,2};
	public static int enchantHasteParNiveau[]= new int []{0,0,0,1,0,0,0,1,0,0,0,1};
	public static Material materialParNiveau[]= new Material []{Material.STONE_AXE,Material.STONE_AXE,Material.IRON_AXE,Material.IRON_AXE,Material.IRON_AXE,Material.IRON_AXE,Material.DIAMOND_AXE,Material.DIAMOND_AXE,Material.DIAMOND_AXE,Material.DIAMOND_AXE,Material.NETHERITE_AXE,Material.NETHERITE_AXE,Material.NETHERITE_AXE,Material.NETHERITE_AXE,Material.NETHERITE_AXE,Material.NETHERITE_AXE,Material.NETHERITE_AXE};

	public HachePerso() {
		customItem(hache, NOMHACHE);
		ItemMeta metaItem = hache.getItemMeta();
		metaItem.addEnchant(Enchantment.DIG_SPEED, 1, true);
		hache.setItemMeta(metaItem);

	}
	
	public ItemStack getHache() {
		return hache;
	}
	
	public HachePerso(ItemStack item) {
		hache=item;
		majStatsItem(item);
	}
	
	public void majItem(ItemStack item, Player player, int expObtenue) {
		super.majItem(item, player, expObtenue);
		if (this.aLevelUp) {
			monterDeNiveau(player, item);
		}
	}
	
	
	public  void monterDeNiveau(Player player, ItemStack item) {
		
		ItemMeta metaItem = item.getItemMeta();
		int itemEnchantLv = metaItem.getEnchantLevel(Enchantment.DIG_SPEED)+enchantEfficiencyParNiveau[niveau];
		metaItem.addEnchant(Enchantment.DIG_SPEED, itemEnchantLv, true);
		item.setItemMeta(metaItem);
		
		item.setType(materialParNiveau[niveau]);
		
		
		player.sendTitle(item.getItemMeta().getDisplayName(), "passe au niveau suivant : "+ niveau , 20, 100, 20);
		
	}
	
	public void majStatsItem(ItemStack item) {
		
		List<String> lore = item.getItemMeta().getLore();
		
		

		int  newNiveau = Integer.parseInt(lore.get(0).replace("Niveau : ", ""));
		niveau = newNiveau;
		System.out.println("Le niveau de la hache est : " + newNiveau);
		
		int  newExp = Integer.parseInt(lore.get(2).replace("/"+expParNiveau[niveau], ""));
		exp=newExp;
		System.out.println("Et elle a  : " + newExp + " d'exp");

	}


}
