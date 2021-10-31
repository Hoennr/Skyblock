package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.ohnreihen.skyblock.Joueur.Joueur;
public class MenuBlocks {

	@SuppressWarnings("unchecked")
	private static ArrayList<Inventory>[] listInventaireCategorie = new ArrayList[Produits.NB_CATEGORIE];

public static ArrayList<Inventory>[] getListInventaireCategorie() {
	return listInventaireCategorie;
}
	@SuppressWarnings("unchecked")
	public static void creerMenu() {
		
		//Bukkit.createInventory(null, 54, Produits.NOM_CATEGORIE[i]);
		
		
		ArrayList<Produits>[] listProduitParCategorie = Produits.getProduitParCategorie();
		ArrayList<ArrayList<Produits>>[] listDeListDe45ProduitsParCategorie= new ArrayList [Produits.NB_CATEGORIE];
		
		for(int categorie=0; categorie<Produits.NB_CATEGORIE;categorie++) {
			listInventaireCategorie[categorie]= new ArrayList<Inventory>();
			
			listDeListDe45ProduitsParCategorie[categorie]=new ArrayList<ArrayList<Produits>>();
			
			int nombreListProduits = (listProduitParCategorie[categorie].size()+44) / 45; 
			//System.out.println("IL Y A "+ nombreListProduits + "inventaire  pour la categorie" + Produits.NOM_CATEGORIE[categorie]);
			for (int numeroListProduits = 0;numeroListProduits<nombreListProduits;numeroListProduits++) {
				Inventory inventaire = Bukkit.createInventory(null, 54, Produits.NOM_CATEGORIE[categorie]+" Page : "+(numeroListProduits+1));
				listInventaireCategorie[categorie].add(inventaire);
				
				ArrayList<Produits> listProduits = new ArrayList<Produits>(); 
				for(int i =0;  i<45 && (i+numeroListProduits*45)<listProduitParCategorie[categorie].size();i++) {
					ArrayList<Produits> listProduitCategorie = listProduitParCategorie[categorie];
					Produits produit = listProduitCategorie.get(i+numeroListProduits*45);
					//System.out.println("Le produit "+i+" :" +produit.getItem().toString()+"est dans la categorie "+ Produits.NOM_CATEGORIE[categorie]+ " page "+(numeroListProduits+1));
					listProduits.add(produit);
				}
				
				listDeListDe45ProduitsParCategorie[categorie].add(listProduits);
				
				//System.out.println("J'ai terminé d'enrgistrer les produits de la categorie "+ Produits.NOM_CATEGORIE[categorie]+ " page "+numeroListProduits);

				for(int i=0;i<listProduits.size();i++) {
					Produits produit = listDeListDe45ProduitsParCategorie[categorie].get(numeroListProduits).get(i);
					int premierVide = listInventaireCategorie[categorie].get(numeroListProduits).firstEmpty();
					ItemStack is = new ItemStack (produit.getItem());
					ItemMeta itemMeta = is.getItemMeta();

					ArrayList<String> lore = new ArrayList<String>();
					lore.add("  §b❙§r§fAchat : §b"+produit.getPrixAchat() +"￥  §7(click gauche)");
					lore.add("  §c❙§r§fVente : §c"+ produit.getPrixVente()+"￥/unité   §7(click droit)");
					itemMeta.setLore(lore);
					
					is.setItemMeta(itemMeta);
					listInventaireCategorie[categorie].get(numeroListProduits).setItem(premierVide, is);
				}
				
				
				for(int pos=listInventaireCategorie[categorie].get(numeroListProduits).firstEmpty(); pos<listInventaireCategorie[categorie].get(numeroListProduits).getSize();pos++) {

						ItemStack optionFiller = new ItemStack (Material.WHITE_STAINED_GLASS_PANE);
						ItemMeta itemMetaFiller = optionFiller.getItemMeta();
						itemMetaFiller.setDisplayName(" ");
						optionFiller.setItemMeta(itemMetaFiller);
						listInventaireCategorie[categorie].get(numeroListProduits).setItem(pos,optionFiller );
				}			
				
				for(int i=0; i<listInventaireCategorie[categorie].size();i++) {
					ItemStack boutonBack = new ItemStack (Material.RED_STAINED_GLASS_PANE);
					ItemMeta itemMeta = boutonBack.getItemMeta();
					itemMeta.setDisplayName("Retour");
					boutonBack.setItemMeta(itemMeta);
					listInventaireCategorie[categorie].get(i).setItem(46,boutonBack );
					

					if(nombreListProduits>0) {
						for(int pos=0; pos<nombreListProduits;pos++) {
							ItemStack boutonPage = new ItemStack (Material.BOOK);
							ItemMeta itemMetaPage = boutonBack.getItemMeta();
							itemMetaPage.setDisplayName("Page : " +(pos+1));
							boutonPage.setItemMeta(itemMetaPage);
							listInventaireCategorie[categorie].get(i).setItem(48+pos,boutonPage );
						}
					}
					
				}
				
				
				
			}
			
		}
	}

	public static void ouvrirMenuBlocks(Player player, String categorie, int numPage) {
		
		int num_categorie = Arrays.asList(Produits.NOM_CATEGORIE).indexOf(categorie);
		player.openInventory(listInventaireCategorie[num_categorie].get(numPage));
		
	}

	public static void utiliserMenu(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack itemUsed = event.getCurrentItem();
		
		if (!itemUsed.getItemMeta().getDisplayName().equals(" ")) {
			if (itemUsed.getItemMeta().getDisplayName().equals("Retour")) {
				MenuShopCategorie.ouvrirMenuCategorie(player);
			}else if(itemUsed.getItemMeta().getDisplayName().contains("Page :")) {
				ouvrirPage(player,event,itemUsed );
			}else if(event.getClick()==ClickType.LEFT) {
				MenuAchat.ouvrirMenu(player,itemUsed);
			}else if (event.getClick()==ClickType.RIGHT) {
				Joueur.vendreItem(player,itemUsed);
			}
			
		}
		event.setCancelled(true);
		
	}

	private static void ouvrirPage(Player player, InventoryClickEvent event, ItemStack itemUsed) {
		// TODO Auto-generated method stub
		int categorie=0;
		for (int i = 0; i<Produits.NB_CATEGORIE;i++) {
			if(event.getView().getTitle().contains(Produits.NOM_CATEGORIE[i])){
				categorie = i;
			}
		}
		String nom = itemUsed.getItemMeta().getDisplayName();
		//System.out.println("nom de l'item :" + nom);
		nom  = nom.replace("Page : ", "");
		//System.out.println("L'exp pour monter de niveau est de : " +"/"+expParNiveau[niveau] );
		
		int  numeroPage = Integer.parseInt(nom) - 1;

		ouvrirMenuBlocks(player, Produits.NOM_CATEGORIE[categorie],numeroPage );
		
	}
	

}
