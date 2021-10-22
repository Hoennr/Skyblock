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
	
	public static void initialiserProduits() {
		
		listProduits = new ArrayList<Produits>();
		listProduits.add(new Produits( 10 , 2 , Material.COBBLESTONE , CATEGORIE_BUILD));
		listProduits.add(new Produits( 5 , 1 , Material.WHEAT_SEEDS , CATEGORIE_AGRICULTURE));
		listProduits.add(new Produits( 100 , 5 , Material.IRON_ORE , CATEGORIE_MINERAI));
		
		
	}

}