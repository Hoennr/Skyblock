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
import fr.ohnreihen.skyblock.Joueur.TableauScore;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.ProduitInexistantException;

public class MenuBlocks {

	private static Inventory[] inventaireCategorie;

	public static Inventory[] getInventaireCategorie() {
		return inventaireCategorie;
	}
	
	public static void creerMenu() {
		
		inventaireCategorie= new Inventory [Produits.NB_CATEGORIE];
		for (int i=0;i<Produits.NB_CATEGORIE;i++) {
			inventaireCategorie[i]=Bukkit.createInventory(null, 54, Produits.NOM_CATEGORIE[i]);
		}
		
		ArrayList<Produits> listProduit = Produits.getListProduits();
		
		for(int i=0; i<listProduit.size();i++) {
			Produits produit = listProduit.get(i);
			int categorie = produit.getCategorie();
			int premierVide = inventaireCategorie[categorie].firstEmpty();
			
			ItemStack is = new ItemStack (produit.getItem());
			ItemMeta itemMeta = is.getItemMeta();

			ArrayList<String> lore = new ArrayList<String>();
			lore.add("  §b❙§r§fAchat : §b"+produit.getPrixAchat() +"￥  §7(click gauche)");
			lore.add("  §c❙§r§fVente : §c"+ produit.getPrixVente()+"￥/unité   §7(click droit)");
			itemMeta.setLore(lore);
			
			is.setItemMeta(itemMeta);
			inventaireCategorie[categorie].setItem(premierVide, is);
			
		}
		
		for(int i=0; i<Produits.NB_CATEGORIE;i++) {
			for(int pos=inventaireCategorie[i].firstEmpty(); pos<inventaireCategorie[i].getSize();pos++) {
				if (inventaireCategorie[i].getItem(pos)==null) {

					ItemStack optionFiller = new ItemStack (Material.WHITE_STAINED_GLASS_PANE);
					ItemMeta itemMetaFiller = optionFiller.getItemMeta();
					itemMetaFiller.setDisplayName(" ");
					optionFiller.setItemMeta(itemMetaFiller);
					inventaireCategorie[i].setItem(pos,optionFiller );
				}
			}
		}
		
		for(int i=0; i<Produits.NB_CATEGORIE;i++) {
			ItemStack boutonBack = new ItemStack (Material.RED_STAINED_GLASS_PANE);
			ItemMeta itemMeta = boutonBack.getItemMeta();
			itemMeta.setDisplayName("Retour");
			boutonBack.setItemMeta(itemMeta);
			inventaireCategorie[i].setItem(46,boutonBack );
		}
		
			
	}

	public static void ouvrirMenuBlocks(Player player, String categorie) {
		
		int num_categorie = Arrays.asList(Produits.NOM_CATEGORIE).indexOf(categorie);
		player.openInventory(inventaireCategorie[num_categorie]);
		
	}

	public static void utiliserMenu(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack itemUsed = event.getCurrentItem();
		
		if (!itemUsed.getItemMeta().getDisplayName().equals(" ")) {
			if (itemUsed.getItemMeta().getDisplayName().equals("Retour")) {
				MenuShopCategorie.ouvrirMenuCategorie(player);
			}else if(event.getClick()==ClickType.LEFT) {
				MenuAchat.ouvrirMenu(player,itemUsed);
			}else if (event.getClick()==ClickType.RIGHT) {
				vendreItem(player,itemUsed);
			}
			
		}
		
	}

	private static void vendreItem(Player player, ItemStack itemUsed) {

		int nbIS = 0;
		
		Inventory inventairePlayer = player.getInventory();
		if(inventairePlayer.contains(itemUsed.getType())) {
			for (int i =0 ; i<inventairePlayer.getContents().length; i++) {
				ItemStack[] items = inventairePlayer.getContents();
				if (items[i]!=null) {
					if (items[i].getType()==itemUsed.getType()) {
						nbIS = nbIS+items[i].getAmount();
					}
				}
				
			}
			
			inventairePlayer.remove(itemUsed.getType());
			
			try {
				Produits produit = Produits.getProduit(itemUsed);
				Joueur joueur = Joueur.getJoueur(player);
				int moneyObtenu = produit.getPrixVente()*nbIS;
				joueur.setMoney(joueur.getMoney()+moneyObtenu);
				TableauScore.creerTableau(joueur);
				
			} catch (ProduitInexistantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} catch (JoueurNonEnregistrerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("Vous avez +"+ nbIS + " de " + itemUsed.getType().toString()+ " dans votre inventaire");
			
		}else {
			
			player.sendRawMessage("Vous n'avez pas de " + itemUsed.getType().toString()+ " dans votre inventaire");
			player.closeInventory();

		}
		
	}
	
	

}
