package fr.ohnreihen.skyblock.Joueur;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class PellePerso extends OutilIncassable {
	
	public  ItemStack pelle = new ItemStack(Material.WOODEN_SHOVEL);
	public static String NOMPELLE = "§eMa Super pelle";
	public static int enchantEfficiencyParNiveau[]= new int []{0,1,0,1,2,2,0,1,2,3,0,4};
	public static int enchantHasteParNiveau[]= new int []{0,0,0,1,0,0,0,1,0,0,0,1};
	public static Material materialParNiveau[]= new Material []{Material.STONE_SHOVEL,Material.STONE_SHOVEL,Material.IRON_SHOVEL,Material.IRON_SHOVEL,Material.IRON_SHOVEL,Material.IRON_SHOVEL,Material.DIAMOND_SHOVEL,Material.DIAMOND_SHOVEL,Material.DIAMOND_SHOVEL,Material.DIAMOND_SHOVEL,Material.NETHERITE_SHOVEL,Material.NETHERITE_SHOVEL};

	public PellePerso() {
		customItem(pelle, NOMPELLE);
		ItemMeta metaItem = pelle.getItemMeta();
		metaItem.addEnchant(Enchantment.DIG_SPEED, 1, true);
		pelle.setItemMeta(metaItem);

		// TODO Auto-generated constructor stub
	}
	
	public ItemStack getHache() {
		return pelle;
	}
	
	public PellePerso(ItemStack item) {
		pelle=item;
		majStatsItem(item);
		// TODO Auto-generated constructor stub
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
		System.out.println("Le niveau de la pelle est : " + newNiveau);
		
		int  newExp = Integer.parseInt(lore.get(2).replace("/"+expParNiveau[niveau], ""));
		exp=newExp;
		System.out.println("Et elle a  : " + newExp + " d'exp");

	}


}
