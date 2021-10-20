package fr.ohnreihen.skyblock.Joueur;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OutilIncassable {
	
	public  int exp = 0;
	public  int niveau = 0;
	public static int expParNiveau[]= new int []{10,800,2000,4000,7000,11200,16800,24000,34000,47900,67200,94100};
	public boolean aLevelUp = false;
	public  ItemStack item = new ItemStack(Material.DIRT);
	public static Material listMaterialPickaxe[]= new Material[] {
			Material.WOODEN_PICKAXE,Material.STONE_PICKAXE,Material.IRON_PICKAXE,Material.GOLDEN_PICKAXE,Material.DIAMOND_PICKAXE,Material.NETHERITE_PICKAXE
	};
	
	//public static Material listMaterialMinerai[]= new Material[] {
		//	Material.STONE,Material.COBBLESTONE,Material.SMOOTH_STONE,Material.GRANITE,Material.POLISHED_GRANITE,Material.DIORITE,Material.POLISHED_DIORITE,Material.OBSIDIAN,Material.ANDESITE,Material.POLISHED_ANDESITE,Material.BRICKS,Material.TERRACOTTA,Material.SANDSTONE,Material.BONE_BLOCK,Material.RED_SANDSTONE,Material.PRISMARINE,Material.PRISMARINE_BRICKS,Material.DARK_PRISMARINE,Material.COAL_ORE,Material.COPPER_ORE,Material.DIAMOND_ORE,Material.EMERALD_ORE,Material.GOLD_ORE,Material.IRON_ORE,Material.LAPIS_ORE,Material.NETHER_GOLD_ORE,Material.NETHER_QUARTZ_ORE,Material.REDSTONE_ORE,Material.BASALT,Material.POLISHED_BASALT,Material.SMOOTH_BASALT,Material.NETHERITE_BLOCK,Material.ANCIENT_DEBRIS,Material.BLACKSTONE,Material.GILDED_BLACKSTONE,Material.POLISHED_BLACKSTONE,Material.CRYING_OBSIDIAN,Material.NETHER_BRICK
	//};
	public static Material listMaterialShovel[]= new Material[] {
			Material.WOODEN_SHOVEL,Material.WOODEN_SHOVEL,Material.IRON_SHOVEL,Material.GOLDEN_SHOVEL,Material.DIAMOND_SHOVEL,Material.NETHERITE_SHOVEL	
	};

	public static Material listMaterialAxe[]= new Material[] {
			Material.WOODEN_AXE,Material.STONE_AXE,Material.IRON_AXE,Material.GOLDEN_AXE,Material.DIAMOND_AXE,Material.NETHERITE_AXE
	};
	
	public static Material listMaterialSwod[] = new Material[] {
			Material.WOODEN_SWORD,Material.STONE_SWORD,Material.IRON_SWORD,Material.GOLDEN_SWORD,Material.DIAMOND_SWORD,Material.NETHERITE_SWORD
	};

		
	public  ItemStack customItem(ItemStack itemAPerso, String displayName) {		
		//ItemStack item = new ItemStack(Material.COMPASS);
		item=itemAPerso;
		ItemMeta metaItem = item.getItemMeta();
		metaItem.setDisplayName(displayName);
		//majLore();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§fNiveau : "+niveau );
		lore.add("§fProgression : ▌▌▌▌▌▌▌▌▌▌");
		lore.add("§f"+ exp+"/"+expParNiveau[niveau]);
		metaItem.setLore(lore);
		
		//metaItem.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		metaItem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		metaItem.setUnbreakable(true);
		item.setItemMeta(metaItem);
		return item;
	}

	
	
	public  void majItem(ItemStack itemTenue, Player player, int expObtenu) {
		
		//System.out.println("Le joueur à casser un block avec " + itemTenue.getItemMeta().getDisplayName());	
		ItemMeta itemMeta = itemTenue.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
	
		 lore = itemTenue.getItemMeta().getLore();
		 
		 int niveauActuel = getNiveau(itemTenue);
		 
		 int expActuel = getExp(itemTenue)+expObtenu;
		 
		 if(niveauActuel<13 && expActuel>=expParNiveau[niveauActuel]) {
				expActuel=0;
				niveauActuel++;
				aLevelUp=true;
		 }else aLevelUp=false;
		 
		 niveau=niveauActuel;
		 exp=expActuel;

		 lore.set(2,("§f"+expActuel+"/"+expParNiveau[niveau]));
		 lore.set(0,("§fNiveau : "+niveau));
		
		
		itemMeta.setLore(lore);
		itemTenue.setItemMeta(itemMeta);
		
	}

	private int getNiveau(ItemStack itemTenue) {
		
		List<String> lore = itemTenue.getItemMeta().getLore();
		int  niveauActuel = Integer.parseInt(lore.get(0).replace("§fNiveau : ", ""));
		
		niveau=niveauActuel; 
		return niveauActuel;
	}

	private int getExp(ItemStack itemTenue) {
		
		List<String> lore = itemTenue.getItemMeta().getLore();
		String lore2 = lore.get(2);
		lore2 = lore2.replace("§f", "");
		//System.out.println("L'exp pour monter de niveau est de : " +"/"+expParNiveau[niveau] );
		lore2 = lore2.replace("/"+expParNiveau[niveau], "");
		int  expActuel = Integer.parseInt(lore2);

		exp=expActuel;
		return expActuel;
	}
	
	
}
