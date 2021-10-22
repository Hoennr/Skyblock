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
	//	listProduits.add(new Produits( 2000 , 1 , Material.END_STONE , CATEGORIE_BUILD));
	//	listProduits.add(new Produits( 125 , 1 , Material.NETHERRACK , CATEGORIE_BUILD));
	//	listProduits.add(new Produits( 80 , 1 , Material.BASALT , CATEGORIE_BUILD));
	//	listProduits.add(new Produits( 125 , 1 , Material.POLISHED_BASALT , CATEGORIE_BUILD));
	//	listProduits.add(new Produits( 125 , 1 , Material.BLACKSTONE , CATEGORIE_BUILD));
		
		
		
		
		
		
		listProduits.add(new Produits( 5 , 1 , Material.WHEAT_SEEDS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 5 , 1 , Material.BONE_MEAL , CATEGORIE_MOB));
		listProduits.add(new Produits( 5 , 1 , Material.SADDLE , CATEGORIE_SPAWNER));
		listProduits.add(new Produits( 100 , 5 , Material.IRON_ORE , CATEGORIE_MINERAI));
		listProduits.add(new Produits( 100 , 5 , Material.BREAD , CATEGORIE_FOOD));
		listProduits.add(new Produits( 100 , 5 , Material.DANDELION , CATEGORIE_PLANTE));
		listProduits.add(new Produits( 100 , 5 , Material.REDSTONE_BLOCK , CATEGORIE_REDSTONE));
		listProduits.add(new Produits( 100 , 5 , Material.CANDLE , CATEGORIE_DECORATION));
		listProduits.add(new Produits( 100 , 5 , Material.LAVA_BUCKET , CATEGORIE_UTILITAIRE));
		listProduits.add(new Produits( 100 , 5 , Material.INK_SAC , CATEGORIE_COULEUR));
		listProduits.add(new Produits( 100 , 5 , Material.AXOLOTL_SPAWN_EGG , CATEGORIE_ROBOT));
		
		
		
		
		trierProduitsParCategorie();
	}

}