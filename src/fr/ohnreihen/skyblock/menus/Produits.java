package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.ProduitInexistantException;


public class Produits {
	private int prixAchat;
	private int prixVente;
	private Material item;
	//TODO: fonction progressionDeblocage : vendre au moins 100intemStack pour débloquer l'achat de  l'item
	//private int progressionDeblocage;
	private int categorie;
	public static final int CATEGORIE_BUILD = 0;
	public static final int CATEGORIE_AGRICULTURE = 1;
	public static final int CATEGORIE_MOB = 2;
	public static final int CATEGORIE_SPAWNER = 3;
	public static final int CATEGORIE_MINERAI = 4;
	public static final int CATEGORIE_FOOD = 5;
	public static final int CATEGORIE_PLANTE = 6;
	public static final int CATEGORIE_REDSTONE = 7;
	public static final int CATEGORIE_DECORATION = 8;
	public static final int CATEGORIE_UTILITAIRE = 9;
	public static final int CATEGORIE_COULEUR = 10;
	public static final int CATEGORIE_ROBOT = 11;
	
	public static final String NOM_CATEGORIE_BUILD = "Construction";
	public static final String NOM_CATEGORIE_AGRICULTURE = "Agriculture";
	public static final String NOM_CATEGORIE_MOB= "Monstres";
	public static final String NOM_CATEGORIE_SPAWNER = "Spawner";
	public static final String NOM_CATEGORIE_MINERAI = "Minerai";
	public static final String NOM_CATEGORIE_FOOD = "Nourriture";
	public static final String NOM_CATEGORIE_PLANTE = "Plante";
	public static final String NOM_CATEGORIE_REDSTONE = "Redstone";
	public static final String NOM_CATEGORIE_DECORATION = "Décoration";
	public static final String NOM_CATEGORIE_UTILITAIRE = "Utilitaire";
	public static final String NOM_CATEGORIE_COULEUR = "Couleur";
	public static final String NOM_CATEGORIE_ROBOT = "ROBOT";
	
	public static final ItemStack IS_CONSTRUCTION = new ItemStack (Material.STONE_BRICKS);
	public static final ItemStack IS_AGRICULTURE = new ItemStack (Material.WHEAT);
	public static final ItemStack IS_MONSTRE = new ItemStack (Material.ZOMBIE_HEAD);
	public static final ItemStack IS_SPAWNER = new ItemStack (Material.BARRIER);
	public static final ItemStack IS_MINERAI = new ItemStack (Material.DIAMOND_ORE);
	public static final ItemStack IS_NOURRITURE = new ItemStack (Material.COOKED_CHICKEN);
	public static final ItemStack IS_PLANTE = new ItemStack (Material.PEONY);
	public static final ItemStack IS_REDSTONE = new ItemStack (Material.REDSTONE_TORCH);
	public static final ItemStack IS_DECORATION = new ItemStack (Material.AMETHYST_CLUSTER);
	public static final ItemStack IS_UTILITAIRE = new ItemStack (Material.CRAFTING_TABLE);
	public static final ItemStack IS_COULEUR = new ItemStack (Material.GREEN_DYE);
	public static final ItemStack IS_ROBOT = new ItemStack (Material.BARRIER);
	
	public static final String[] NOM_CATEGORIE = {NOM_CATEGORIE_BUILD,NOM_CATEGORIE_AGRICULTURE,NOM_CATEGORIE_MOB,NOM_CATEGORIE_SPAWNER,NOM_CATEGORIE_MINERAI,NOM_CATEGORIE_FOOD,NOM_CATEGORIE_PLANTE,NOM_CATEGORIE_REDSTONE,NOM_CATEGORIE_DECORATION,NOM_CATEGORIE_UTILITAIRE,NOM_CATEGORIE_COULEUR,NOM_CATEGORIE_ROBOT};
	public static final ItemStack[] IS_CATEGORIE = { IS_CONSTRUCTION, IS_AGRICULTURE,IS_MONSTRE,IS_SPAWNER,IS_MINERAI,IS_NOURRITURE,IS_PLANTE,IS_REDSTONE,IS_DECORATION,IS_UTILITAIRE,IS_COULEUR,IS_ROBOT};
	public static final int NB_CATEGORIE = NOM_CATEGORIE.length;

	@SuppressWarnings("unchecked")
	private static ArrayList<Produits>[] produitParCategorie = new ArrayList[NB_CATEGORIE];
	private static ArrayList<Produits> listProduits;
	
	public int getCategorie() {
		return categorie;
	}public Material getItem() {
		return item;
	}public int getPrixAchat() {
		return prixAchat;
	}public int getPrixVente() {
		return prixVente;
	}public static ArrayList<Produits> getListProduits() {
		return listProduits;
	}public static ArrayList<Produits>[] getProduitParCategorie() {
		return produitParCategorie;
	}
	
	public static Produits getProduit(ItemStack item) throws ProduitInexistantException {
		
		Produits produitTrouve = null;
		
		for(int i = 0; i<listProduits.size();i++) {
			Produits produit=listProduits.get(i);
			if(produit.getItem()==item.getType()) {
				produitTrouve = produit;
			}
		}
		if(produitTrouve==null) {
			throw (new ProduitInexistantException());
		}
		return produitTrouve;
	}

	public Produits(int prixAchat, int prixVente, Material item, int categorie) {
		this.prixAchat=prixAchat;
		this.prixVente=prixVente;
		this.item=item;
		this.categorie=categorie;
	}
	
	private static void trierProduitsParCategorie() {
		for(int categorie=0; categorie<NB_CATEGORIE;categorie++) {
			produitParCategorie[categorie]= new ArrayList<Produits>();
		}
		
		for(int i=0; i<listProduits.size();i++) {
			Produits produit = listProduits.get(i);
			int categorie = produit.getCategorie();
			
			produitParCategorie[categorie].add(produit);
			
		}
	}
	
	public static boolean contientNomCategorie(String nom) {
		boolean contient = false;
		for (int i=0; i<NB_CATEGORIE;i++) {
			if (nom.contains( NOM_CATEGORIE[i])) {
				contient = true;
			}
		}
		
		return contient;
	}
	
	
	public static void initialiserProduits() {
		
		listProduits = new ArrayList<Produits>();
		listProduits.add(new Produits( 10 , 1 , Material.COBBLESTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 15 , 1 , Material.STONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 10 , 1 , Material.GRANITE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 15 , 1 , Material.POLISHED_GRANITE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 10 , 1 , Material.ANDESITE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 15 , 1 , Material.POLISHED_ANDESITE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 10 , 1 , Material.DIORITE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 15 , 1 , Material.POLISHED_DIORITE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 15 , 1 , Material.GRASS_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 10 , 1 , Material.DIRT , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.COARSE_DIRT , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.PODZOL , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.MYCELIUM , CATEGORIE_BUILD));
		listProduits.add(new Produits( 10 , 1 , Material.GRAVEL , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 1 , Material.BOOKSHELF , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.SMOOTH_STONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.MOSSY_COBBLESTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.STONE_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.MOSSY_STONE_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.CRACKED_STONE_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.CHISELED_STONE_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.CLAY , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 1 , Material.SNOW_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.OAK_PLANKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.SPRUCE_PLANKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.BIRCH_PLANKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.JUNGLE_PLANKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.ACACIA_PLANKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 20 , 1 , Material.DARK_OAK_PLANKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 20 , Material.OAK_LOG , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 20 , Material.SPRUCE_LOG , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 20 , Material.BIRCH_LOG , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 20, Material.JUNGLE_LOG , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 20 , Material.ACACIA_LOG , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 20 , Material.DARK_OAK_LOG , CATEGORIE_BUILD));
		listProduits.add(new Produits( 10 , 1 , Material.SAND , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 40 , 1 , Material.CHISELED_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 40 , 1 , Material.CUT_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 40 , 1 , Material.SMOOTH_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100, 1 , Material.RED_SAND , CATEGORIE_BUILD));
		listProduits.add(new Produits( 120 , 1 , Material.RED_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 130 , 1 , Material.CHISELED_RED_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 140 , 1 , Material.CUT_RED_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 150 , 1 , Material.SMOOTH_RED_SANDSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 200 , 1 , Material.QUARTZ_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 140 , 1 , Material.CHISELED_QUARTZ_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 140 , 1 , Material.QUARTZ_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 140 , 1 , Material.SMOOTH_QUARTZ , CATEGORIE_BUILD));
		listProduits.add(new Produits( 140 , 1 , Material.QUARTZ_PILLAR , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.SOUL_SAND , CATEGORIE_BUILD));
		listProduits.add(new Produits( 2000 , 1 , Material.END_STONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 125 , 1 , Material.NETHERRACK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 80 , 1 , Material.BASALT , CATEGORIE_BUILD));
		listProduits.add(new Produits( 125 , 1 , Material.POLISHED_BASALT , CATEGORIE_BUILD));
		listProduits.add(new Produits( 75 , 1 , Material.BLACKSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 200 , 1 , Material.POLISHED_BLACKSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 200 , 1 , Material.CHISELED_POLISHED_BLACKSTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 1 , Material.NETHER_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 175 , 1 , Material.CRACKED_NETHER_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 175 , 1 , Material.CHISELED_NETHER_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 1 , Material.WHITE_WOOL , CATEGORIE_BUILD));
		
		listProduits.add(new Produits( 25 , 1 , Material.WHITE_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.YELLOW_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.RED_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.PURPLE_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.PINK_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.ORANGE_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.MAGENTA_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.LIME_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.LIGHT_GRAY_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.LIGHT_BLUE_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.GREEN_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.GRAY_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.CYAN_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.BROWN_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.BLUE_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.BLACK_CONCRETE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.WHITE_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.YELLOW_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.RED_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.PURPLE_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.PINK_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.ORANGE_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.MAGENTA_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.LIME_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.LIGHT_GRAY_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.LIGHT_BLUE_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.GREEN_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.GRAY_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.CYAN_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.BROWN_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.BLUE_CONCRETE_POWDER , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25 , 1 , Material.BLACK_CONCRETE_POWDER , CATEGORIE_BUILD));
		
		listProduits.add(new Produits( 30 , 1 , Material.TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.BLACK_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.BLUE_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.BROWN_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.CYAN_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.GRAY_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.GREEN_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.LIGHT_BLUE_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.LIGHT_GRAY_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.LIME_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.MAGENTA_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.ORANGE_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.PINK_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.PURPLE_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.RED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.WHITE_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 30 , 1 , Material.YELLOW_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.BLACK_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.BLUE_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.BROWN_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.CYAN_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.GRAY_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.GREEN_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.LIGHT_BLUE_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.LIGHT_GRAY_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.LIME_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.MAGENTA_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.ORANGE_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.PINK_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.PURPLE_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.RED_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.WHITE_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50 , 1 , Material.YELLOW_GLAZED_TERRACOTTA , CATEGORIE_BUILD));
		
		
		listProduits.add(new Produits( 25 , 1 , Material.PRISMARINE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 40 , 1 , Material.PRISMARINE_BRICKS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100 , 1 , Material.SEA_LANTERN , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.DARK_PRISMARINE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25, 1 , Material.BRAIN_CORAL_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25, 1 , Material.BUBBLE_CORAL_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25, 1 , Material.FIRE_CORAL_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25, 1 , Material.HORN_CORAL_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 25, 1 , Material.TUBE_CORAL_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100, 1 , Material.ICE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 150, 1 , Material.PACKED_ICE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 300, 1 , Material.BLUE_ICE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 300, 1 , Material.FROSTED_ICE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 250, 1 , Material.DRIED_KELP_BLOCK , CATEGORIE_BUILD));
		listProduits.add(new Produits( 100, 1 , Material.SPONGE , CATEGORIE_BUILD));
		
		
		listProduits.add(new Produits( 50, 1 , Material.GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.BLACK_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.BLUE_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.BROWN_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.CYAN_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.GRAY_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.GREEN_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.LIGHT_BLUE_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.LIGHT_GRAY_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.LIME_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.MAGENTA_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.ORANGE_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.PINK_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.PURPLE_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.RED_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.WHITE_STAINED_GLASS , CATEGORIE_BUILD));
		listProduits.add(new Produits( 50, 1 , Material.YELLOW_STAINED_GLASS , CATEGORIE_BUILD));
		
		
		
		
		
		
		
		
		listProduits.add(new Produits( 1000 , 5 , Material.CACTUS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 2000 , 10 , Material.MELON_SLICE , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 15000 , 75 , Material.WHEAT , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 15000 , 75 , Material.CARROT , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 5000 , 25 , Material.SUGAR_CANE , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 10000 , 50 , Material.POTATO , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 10000 , 50 , Material.BEETROOT , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 8000 , 40 , Material.PUMPKIN , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 18000 , 90 , Material.MELON , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 16000 , 80 , Material.NETHER_WART , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 20000 , 100 , Material.COCOA_BEANS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 2000 , 10 , Material.MELON_SEEDS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 15000 , 1 , Material.WHEAT_SEEDS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 10000 , 1 , Material.BEETROOT_SEEDS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 8000 , 1 , Material.PUMPKIN_SEEDS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 2000 , 10 , Material.KELP , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 200 , 2 , Material.BAMBOO , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 100 , 10 , Material.ACACIA_SAPLING , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 100 , 10 , Material.BIRCH_SAPLING , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 100 , 10 , Material.DARK_OAK_SAPLING , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 100 , 10 , Material.JUNGLE_SAPLING , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 100 , 10 , Material.OAK_SAPLING , CATEGORIE_AGRICULTURE));
		
		
		
		
		listProduits.add(new Produits( 30000 , 300 , Material.BONE , CATEGORIE_MOB));
		listProduits.add(new Produits( 100 , 1 , Material.LEATHER , CATEGORIE_MOB));
		listProduits.add(new Produits( 500 , 30 , Material.ROTTEN_FLESH , CATEGORIE_MOB));
		listProduits.add(new Produits( 1000 , 75 , Material.MAGMA_CREAM , CATEGORIE_MOB));
		listProduits.add(new Produits( 1200 , 120 , Material.GUNPOWDER , CATEGORIE_MOB));
		listProduits.add(new Produits( 600 , 60 , Material.GLOWSTONE_DUST , CATEGORIE_MOB));
		listProduits.add(new Produits( 1500 , 150 , Material.BLAZE_ROD , CATEGORIE_MOB));
		listProduits.add(new Produits( 3000 , 300 , Material.GHAST_TEAR , CATEGORIE_MOB));
		listProduits.add(new Produits( 25000 , 2500 , Material.NETHER_STAR , CATEGORIE_MOB));
		
		
		
		listProduits.add(new Produits( 5 , 1 , Material.SADDLE , CATEGORIE_SPAWNER));
		
		
		
		listProduits.add(new Produits( 15000 , 150 , Material.CHARCOAL , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 4000 , 40 , Material.COAL , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 3600 , 360 , Material.COAL_BLOCK , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 50000 , 50 , Material.IRON_INGOT , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 450000 , 450 , Material.IRON_BLOCK , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 20000 , 20 , Material.LAPIS_LAZULI , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 180000 , 180 , Material.LAPIS_BLOCK , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 75000 , 75 , Material.GOLD_INGOT , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 675000 , 675 , Material.GOLD_BLOCK , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 100000 , 100 , Material.DIAMOND , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 900000 , 900 , Material.DIAMOND_BLOCK , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 150000 , 150 , Material.EMERALD , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 1350000 , 1350 , Material.EMERALD_BLOCK , CATEGORIE_MINERAI));
		
		
		
		listProduits.add(new Produits( 10 , 1 , Material.COD , CATEGORIE_FOOD));
		listProduits.add(new Produits( 400 , 25 , Material.COOKED_COD , CATEGORIE_FOOD));
		listProduits.add(new Produits( 10 , 1 , Material.SALMON , CATEGORIE_FOOD));
		listProduits.add(new Produits( 50 , 5 , Material.COOKED_SALMON , CATEGORIE_FOOD));
		listProduits.add(new Produits( 1000 , 100 , Material.PUFFERFISH , CATEGORIE_FOOD));
		listProduits.add(new Produits( 1000 , 100  , Material.TROPICAL_FISH , CATEGORIE_FOOD));
		listProduits.add(new Produits( 10 , 1  , Material.RABBIT , CATEGORIE_FOOD));
		listProduits.add(new Produits( 50 , 5  , Material.COOKED_RABBIT , CATEGORIE_FOOD));
		listProduits.add(new Produits( 200 , 20  , Material.RABBIT_STEW , CATEGORIE_FOOD));
		listProduits.add(new Produits( 10 , 1  , Material.CHICKEN , CATEGORIE_FOOD));
		listProduits.add(new Produits( 100 , 10  , Material.COOKED_CHICKEN , CATEGORIE_FOOD));
		listProduits.add(new Produits( 10 , 1  , Material.MUTTON , CATEGORIE_FOOD));
		listProduits.add(new Produits( 100 , 10  , Material.COOKED_MUTTON , CATEGORIE_FOOD));
		listProduits.add(new Produits( 10 , 1  , Material.PORKCHOP , CATEGORIE_FOOD));
		listProduits.add(new Produits( 100 , 10  , Material.COOKED_PORKCHOP , CATEGORIE_FOOD));
		listProduits.add(new Produits( 10 , 1  , Material.BEEF , CATEGORIE_FOOD));
		listProduits.add(new Produits( 200 , 20  , Material.COOKED_BEEF , CATEGORIE_FOOD));
		listProduits.add(new Produits( 200 , 20 , Material.BREAD , CATEGORIE_FOOD));
		listProduits.add(new Produits( 150 , 15 , Material.BAKED_POTATO , CATEGORIE_FOOD));
		listProduits.add(new Produits( 1000 , 100 , Material.CAKE , CATEGORIE_FOOD));
		listProduits.add(new Produits( 300 , 30 , Material.GOLDEN_CARROT , CATEGORIE_FOOD));
		listProduits.add(new Produits( 400 , 40 , Material.BEETROOT_SOUP , CATEGORIE_FOOD));
		listProduits.add(new Produits( 400 , 40 , Material.MUSHROOM_STEW , CATEGORIE_FOOD));

		
		
		
		
		listProduits.add(new Produits( 10 , 1 , Material.ACACIA_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 10 , 1 , Material.AZALEA_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 10 , 1 , Material.BIRCH_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 10 , 1 , Material.DARK_OAK_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 50 , 1 , Material.FLOWERING_AZALEA_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 10 , 1 , Material.JUNGLE_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 10 , 1 , Material.OAK_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 10 , 1 , Material.SPRUCE_LEAVES , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 50 , 1 , Material.DEAD_BUSH , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 100 , 1 , Material.BROWN_MUSHROOM_BLOCK , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 100 , 1 , Material.RED_MUSHROOM_BLOCK , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.BROWN_MUSHROOM , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.RED_MUSHROOM , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 100 , 1 , Material.FERN , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 500 , 1 , Material.COBWEB , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.SNOW , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.ALLIUM , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.BLUE_ORCHID , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 1100 , 110 , Material.POPPY , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.DANDELION , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.SEA_PICKLE , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.SEAGRASS , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.LILY_OF_THE_VALLEY , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.CORNFLOWER, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.OXEYE_DAISY, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.PINK_TULIP, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.WHITE_TULIP, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.RED_TULIP, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.ORANGE_TULIP, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.AZURE_BLUET, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.PEONY, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.ROSE_BUSH, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.LILAC, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 25 , 1 , Material.LILY_PAD, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 30 , 1 , Material.VINE, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 30 , 1 , Material.CAVE_VINES, CATEGORIE_PLANTE));
		listProduits.add(new Produits( 30 , 1 , Material.CAVE_VINES_PLANT, CATEGORIE_PLANTE));
		
		
		
		listProduits.add(new Produits( 2000 , 1 , Material.REDSTONE, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 18000 , 9 , Material.REDSTONE_BLOCK , CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 2500 , 1 , Material.REDSTONE_TORCH, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 6000 , 1 , Material.REPEATER, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 8000 , 1 , Material.COMPARATOR, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 10000 , 1 , Material.DISPENSER, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 90000 , 1 , Material.DROPPER, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 10000 , 1 , Material.OBSERVER, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 2000 , 1 , Material.REDSTONE_LAMP, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 4000 , 1 , Material.PISTON, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 8000 , 1 , Material.STICKY_PISTON, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 500 , 1 , Material.HONEY_BLOCK, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 1000 , 1 , Material.SLIME_BLOCK, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 2000 , 1 , Material.TARGET, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.OAK_BUTTON, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.LEVER, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.OAK_TRAPDOOR, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.OAK_PRESSURE_PLATE, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.TRIPWIRE_HOOK, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.DAYLIGHT_DETECTOR, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 1 , Material.MINECART, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 1000 , 1 , Material.RAIL, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 2500 , 1 , Material.POWERED_RAIL, CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 5000 , 1 , Material.DETECTOR_RAIL, CATEGORIE_REDSTONE));
		
		
		
		
		
		listProduits.add(new Produits( 100 , 5 , Material.CANDLE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 150 , 1 , Material.CRYING_OBSIDIAN , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.LODESTONE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 500 , 1 , Material.HONEYCOMB_BLOCK , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.LANTERN , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.LECTERN , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.NOTE_BLOCK , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.END_ROD , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.GRINDSTONE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 500 , 1 , Material.CAMPFIRE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.SMITHING_TABLE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 100 , 1 , Material.FURNACE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.SMOKER , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.BLAST_FURNACE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.STONECUTTER , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.BARREL , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.SCAFFOLDING , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.BEE_NEST , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.BELL , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.BEEHIVE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 50 , 1 , Material.CRAFTING_TABLE, CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.FLETCHING_TABLE, CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.CARTOGRAPHY_TABLE, CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.LOOM, CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.CHEST, CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.AMETHYST_BLOCK, CATEGORIE_DECORATION));
		listProduits.add(new Produits( 250 , 1 , Material.AMETHYST_CLUSTER, CATEGORIE_DECORATION));
		
		
		
		listProduits.add(new Produits( 50 , 1 , Material.WATER_BUCKET , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 100 , 1 , Material.LAVA_BUCKET , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 2500 , 1 , Material.ENCHANTING_TABLE , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 250 , 1 , Material.ANVIL , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 2500 , 1 , Material.JUKEBOX , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 2500 , 1 , Material.JUKEBOX , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 100 , 1 , Material.ARROW , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 250 , 1 , Material.SPECTRAL_ARROW , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 100000 , 1 , Material.GOLDEN_APPLE , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 250000 , 1 , Material.ENCHANTED_GOLDEN_APPLE , CATEGORIE_UTILITAIRE));
		
		
		
		listProduits.add(new Produits( 100 , 1 , Material.BLACK_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.BLUE_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.BROWN_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.CYAN_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.GRAY_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.LIGHT_BLUE_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.LIGHT_GRAY_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.LIME_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.MAGENTA_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.ORANGE_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.PINK_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.PURPLE_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.RED_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.WHITE_DYE , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 1 , Material.YELLOW_DYE , CATEGORIE_COULEUR));
		
		
		listProduits.add(new Produits( 100 , 5 , Material.AXOLOTL_SPAWN_EGG , CATEGORIE_ROBOT));
		
		
		
		
		trierProduitsParCategorie();
	}
}