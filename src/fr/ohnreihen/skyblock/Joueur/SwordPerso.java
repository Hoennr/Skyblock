package fr.ohnreihen.skyblock.Joueur;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class SwordPerso extends OutilIncassable {
	
	public  ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
	public static String NOMSWORD = "§eMa Super épée";
	public static int enchantDommageParNiveau[]= new int []{0,1,0,1,2,2,0,1,2,3,0,4};
	public static int enchantHasteParNiveau[]= new int []{0,0,0,1,0,0,0,1,0,0,0,1};
	public static Material materialParNiveau[]= new Material []{Material.STONE_SWORD,Material.STONE_SWORD,Material.IRON_SWORD,Material.IRON_SWORD,Material.IRON_SWORD,Material.IRON_SWORD,Material.DIAMOND_SWORD,Material.DIAMOND_SWORD,Material.DIAMOND_SWORD,Material.DIAMOND_SWORD,Material.NETHERITE_SWORD,Material.NETHERITE_SWORD};

	public SwordPerso() {
		customItem(sword, NOMSWORD);
		ItemMeta metaItem = sword.getItemMeta();
		metaItem.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		sword.setItemMeta(metaItem);

	}
	
	public ItemStack getSword() {
		return sword;
	}
	
	public SwordPerso(ItemStack item) {
		sword=item;
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
		int itemEnchantLv = metaItem.getEnchantLevel(Enchantment.DAMAGE_ALL)+enchantDommageParNiveau[niveau];
		metaItem.addEnchant(Enchantment.DAMAGE_ALL, itemEnchantLv, true);
		item.setItemMeta(metaItem);
		
		item.setType(materialParNiveau[niveau]);
		
		
		player.sendTitle(item.getItemMeta().getDisplayName(), "passe au niveau suivant : "+ niveau , 20, 100, 20);
		
	}
	
	public void majStatsItem(ItemStack item) {
		
		List<String> lore = item.getItemMeta().getLore();
		
		

		int  newNiveau = Integer.parseInt(lore.get(0).replace("Niveau : ", ""));
		niveau = newNiveau;
		System.out.println("Le niveau de la sword est : " + newNiveau);
		
		int  newExp = Integer.parseInt(lore.get(2).replace("/"+expParNiveau[niveau], ""));
		exp=newExp;
		System.out.println("Et elle a  : " + newExp + " d'exp");

	}


}
