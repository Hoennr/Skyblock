package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

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

	public Produits(int prixAchat, int prixVente, Material item, int categorie) {
		this.prixAchat=prixAchat;
		this.prixVente=prixVente;
		this.item=item;
		this.categorie=categorie;
	}
	
	public static List<Produits> initialiserProduits() {
		
		ArrayList<Produits> listProduits = new ArrayList<Produits>();
		listProduits.add(new Produits( 10 , 5 , Material.COBBLESTONE , CATEGORIE_BUILD));
		
		
		
		
		
		return listProduits;
		
	}

}