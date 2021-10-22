package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.ohnreihen.skyblock.Joueur.Joueur;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.MoneyInsuffisantException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.ProduitInexistantException;

public class MenuAchat {
	
	private static final String NOMMENU = "Achat"; 
	private static Inventory inventaire = Bukkit.createInventory(null, 18, NOMMENU);

	
	public static Inventory getInventaire() {
		return inventaire;
	}	
	
	public static void ouvrirMenu(Player player, ItemStack itemUsed) {
		creerMenu(itemUsed);
		player.openInventory(inventaire);
	}

	
	private static void creerMenu(ItemStack itemUsed) {
		Produits produitAACheter;
		try {
			produitAACheter = Produits.getProduit(itemUsed);
			for(int i=0; i<4;i++) {
				
				ItemStack is = new ItemStack (itemUsed);
				ItemMeta itemMeta = is.getItemMeta();
				int quantité =  (int) Math.pow(2,i*2);
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("  §c❙§r§fAchat : §c"+produitAACheter.getPrixAchat()*quantité +"§e￥");
				lore.add("  §b❙§r§fQuantité : §b"+quantité);
				
				itemMeta.setLore(lore);
				is.setItemMeta(itemMeta);
				inventaire.setItem(i, is);
			}
			for(int i=0;i<5;i++) {
				ItemStack is = new ItemStack (itemUsed);
				ItemMeta itemMeta = is.getItemMeta();
				int quantité =  (int) Math.pow(2,i);
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("  §c❙§r§fAchat : §c"+produitAACheter.getPrixAchat()*quantité*128 +"§e￥");
				lore.add("  §b❙§r§fQuantité : §b"+quantité*128);
				lore.add("§7("+quantité*2+"stacks)");
				
				itemMeta.setLore(lore);
				is.setItemMeta(itemMeta);
				inventaire.setItem(i+4, is);
			}
			
			ItemStack boutonBack = new ItemStack (Material.RED_STAINED_GLASS_PANE);
			ItemMeta itemMeta = boutonBack.getItemMeta();
			itemMeta.setDisplayName("Retour");
			boutonBack.setItemMeta(itemMeta);
			inventaire.setItem(10,boutonBack );

			for (int i=0; i<inventaire.getSize(); i++) {
				if (inventaire.getItem(i)==null) {
					ItemStack optionFiller = new ItemStack (Material.WHITE_STAINED_GLASS_PANE);
					ItemMeta itemMetaFiller = optionFiller.getItemMeta();
					itemMetaFiller.setDisplayName(" ");
					optionFiller.setItemMeta(itemMetaFiller);
					inventaire.setItem(i,optionFiller );
				}
			}
		} catch (ProduitInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public static void utiliserMenu(InventoryClickEvent event) {

		ItemStack is_Produit = event.getInventory().getItem(0);
		Player player = (Player) event.getWhoClicked();
		ItemStack itemUsed = event.getCurrentItem();
		Produits produitAchete;
		try {
			produitAchete = Produits.getProduit(is_Produit);
			if (itemUsed.getItemMeta().getDisplayName().equals("Retour")) {
				int categorie = produitAchete.getCategorie();
				MenuBlocks.ouvrirMenuBlocks(player, Produits.NOM_CATEGORIE[categorie],0);
			}else if (is_Produit.getType()==itemUsed.getType()) {
				acheterProduit(event, itemUsed, player);
			}
		} catch (ProduitInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.setCancelled(true);
	}

	private static void acheterProduit(InventoryClickEvent event, ItemStack produit, Player player) {
		
		List<String> lore = produit.getItemMeta().getLore();
		String lore1 = lore.get(1);
		lore1 = lore1.replace("  §b", "");
		lore1 = lore1.replace("❙", "");
		lore1 = lore1.replace("§r", "");
		lore1 = lore1.replace("§fQuantité", "");
		lore1 = lore1.replace("§e", "");
		lore1 = lore1.replace(" : §b", "");
		int quantite = Integer.parseInt(lore1);
		
		String lore0 = lore.get(0);
		lore0 = lore0.replace("Achat : §c", "");
		lore0 = lore0.replace("  §c","");
		lore0 = lore0.replace("§r§f","");
		lore0 = lore0.replace("❙","");
		lore0 = lore0.replace("§f","");
		lore0= lore0.replace("§e", "");
		lore0 = lore0.replace("￥", "");
		int  prixTotal = Integer.parseInt(lore0);
		
		
		HashMap<Integer, ItemStack> hm = player.getInventory().addItem(new ItemStack (produit.getType(),quantite));
		
		try {
			Produits produitAchete = Produits.getProduit(produit);
			
			if (hm.size()!=0) {  //S'il y a des objets qui n'ont pas pu rentré dans l'inventaire
				int quantiteNonAchete = hm.get(0).getAmount();

				try {
					player.sendRawMessage("Votre inventaire est trop plein");
					acheterProduit(player, produitAchete,quantite-quantiteNonAchete);
					player.sendRawMessage("Vous n'avez pas pu acheter "+ quantiteNonAchete + " de "+ produit.getType().toString());

				} catch (MoneyInsuffisantException e) {
					annulerAchat(player, produitAchete, quantite-quantiteNonAchete);
					player.sendRawMessage("Vous n'avez pas assez de money");
				}
				
			}else {
				try {
					acheterProduit(player, produitAchete,quantite);
					player.sendRawMessage("Vous avez payé " + prixTotal + " pour " + quantite + " de " + produit.getType().toString());
				} catch (MoneyInsuffisantException e) {
					annulerAchat(player, produitAchete, quantite);
					player.sendRawMessage("Vous n'avez pas assez de money");
				}
				
			}

			
		} catch (ProduitInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.updateInventory();
		
	}


	private static void acheterProduit(Player player, Produits produit, int quantite) throws MoneyInsuffisantException {

		Joueur joueur;
		try {
			joueur = Joueur.getJoueur(player);
			long moneyDepense = produit.getPrixAchat()*quantite;
			joueur.depenserMoney(moneyDepense);
		} catch (JoueurNonEnregistrerException e) {
			// TODO Auto-generated catch block
		}
		
		
	}

	private static void annulerAchat(Player player, Produits produit, int quantite) {
		ItemStack is = new ItemStack (produit.getItem(),quantite);
		player.getInventory().removeItem(is);
		
	}
}
