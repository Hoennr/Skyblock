package fr.ohnreihen.skyblock.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.ohnreihen.skyblock.Joueur.Joueur;
import fr.ohnreihen.skyblock.Joueur.TableauScore;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.MoneyInsuffisantException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.NecessaireMineraiIleException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.NecessaireTailleIleException;
import fr.ohnreihen.skyblock.monde.Monde;

public class MenuUPIle {
	
	private static Inventory inventaire;
	public static final String NOMMENU = "Upgrade Ile";
	public static final String MONUPGRADETAILLE = "§5Taille de l'Ile";
	public static final String NOMUPGRADEMINERAI = "§bGénérateur de Minerai";
	
	
	public static Inventory getInventaire() {
		return inventaire;
	}
	
	
	
	public static void creerMenu(Player player) {
		
		inventaire = Bukkit.createInventory(null, 27, NOMMENU);
		
		try {
			Joueur joueur = Joueur.getJoueur(player);
			Monde mondeIle = joueur.getMondeIle();
			mondeIle.getNiveauMinerai();
			int niveauTaille = mondeIle.getNiveauTaille();
			int niveauMinerai = mondeIle.getNiveauMinerai();
			
			ItemStack boutique = new ItemStack (Material.BARRIER);
			ItemMeta itemMetaB = boutique.getItemMeta();
			itemMetaB.setDisplayName(MONUPGRADETAILLE);
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(" ");
			lore.add("§fNiveau actuel : §e"+niveauTaille);
			lore.add("§7("+ Monde.TAILLEPARNIVEAU[niveauTaille]+"x"+Monde.TAILLEPARNIVEAU[niveauTaille] + ")");

			lore.add(" ");
			lore.add("§6Necessaire :" );
			lore.add("§fMiner "+ Monde.EXPPARTAILLEPARNIVEAU[niveauTaille] + " blocks");
			lore.add("§7("+ joueur.getBlockMined() + "/"+ Monde.EXPPARTAILLEPARNIVEAU[niveauTaille] + ")");
			lore.add(" ");
			lore.add("§6Prix :" );
			lore.add("§f"+Monde.PRIXPARTAILLEPARNIVEAU[niveauTaille] + "§e￥" );
			lore.add(" ");
			lore.add("§6Prochain niveau :" );
			lore.add("§f"+Monde.TAILLEPARNIVEAU[niveauTaille+1]+"x"+Monde.TAILLEPARNIVEAU[niveauTaille+1]);
			itemMetaB.setLore(lore);
			boutique.setItemMeta(itemMetaB);
			inventaire.setItem(12, boutique);
			
			
			
			ItemStack option0 = new ItemStack (Material.DIAMOND_ORE);
			ItemMeta itemMeta0 = option0.getItemMeta();
			itemMeta0.setDisplayName(NOMUPGRADEMINERAI);
			ArrayList<String> lore1 = new ArrayList<String>();

			lore1.add(" ");
			lore1.add("§fNiveau actuel : §e"+niveauMinerai);
			lore1.add("§7("+ Monde.NOMUNLOCKMINERAIPARNIVEAU[niveauMinerai]+ ")");
			lore1.add(" ");
			lore1.add("§6Necessaire :" );
			lore1.add("§fMiner "+ Monde.EXPPARMINERAIPARNIVEAU[niveauMinerai] + " blocks");
			lore1.add("§7("+ joueur.getBlockMined() + "/"+ Monde.EXPPARMINERAIPARNIVEAU[niveauMinerai] + ")");
			lore1.add("§6Prix :" );
			lore1.add("§f"+Monde.PRIXPARMINERAIPARNIVEAU[niveauMinerai] + "§e￥" );
			lore1.add(" ");lore1.add(" ");
			lore1.add("§6Prochain niveau :" );
			lore1.add("§fDébloque : "+Monde.NOMUNLOCKMINERAIPARNIVEAU[niveauTaille+1]);
			itemMeta0.setLore(lore1);
			option0.setItemMeta(itemMeta0);
			inventaire.setItem(14,option0);
			
		} catch (JoueurNonEnregistrerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		


		for (int i=0; i<inventaire.getSize(); i++) {
			if (inventaire.getItem(i)==null) {

				ItemStack optionFiller = new ItemStack (Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta itemMetaFiller = optionFiller.getItemMeta();
				itemMetaFiller.setDisplayName(" ");
				optionFiller.setItemMeta(itemMetaFiller);
				inventaire.setItem(i,optionFiller );
			}
		}
		

		ItemStack boutonBack = new ItemStack (Material.RED_STAINED_GLASS_PANE);
		ItemMeta itemMeta = boutonBack.getItemMeta();
		itemMeta.setDisplayName("Retour");
		boutonBack.setItemMeta(itemMeta);
		inventaire.setItem(19,boutonBack );
	
	}
	
	
	public static  ItemStack getMenuItem() {		
		//ItemStack item = new ItemStack(Material.COMPASS);
		ItemStack item= new ItemStack(Material.END_CRYSTAL);
		ItemMeta metaItem = item.getItemMeta();
		metaItem.setDisplayName(NOMMENU);
		//majLore();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§fClick droit");
		lore.add("§fpour ouvrir le menu");
		lore.add("§fdes mondes");
		metaItem.setLore(lore);
		
		metaItem.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		metaItem.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		metaItem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		metaItem.setUnbreakable(true);
		item.setItemMeta(metaItem);
		return item;
	}
	
	public static void ouvrirMenuUPIle(Player player) {
		player.openInventory(inventaire);
	}

	public static void utiliserMenu(InventoryClickEvent event) throws JoueurNonEnregistrerException {
		Player player = (Player) event.getWhoClicked();
		Joueur joueur = Joueur.getJoueur(player);
		ItemStack itemUsed = event.getCurrentItem();
		
		
		if(itemUsed.getItemMeta().getDisplayName().equals(MONUPGRADETAILLE)) {
			//System.out.println("Vous voulez upgrader l'ile");
			try {
				joueur.upgradeTailleIle();
				TableauScore.creerTableau(joueur);
				player.closeInventory();
				
			} catch (NecessaireTailleIleException e) {
				player.sendRawMessage("Vous n'avez pas les prérequis pour upgrader la taille de l'ile");

				// TODO Auto-generated catch block
			} catch (MoneyInsuffisantException e) {
				player.sendRawMessage("Vous n'avez pas l'argent necessaure pour upgrader la taille de l'ile");

				// TODO Auto-generated catch block
			}
		}else if(itemUsed.getItemMeta().getDisplayName().equals(NOMUPGRADEMINERAI)) {
			try {
				joueur.upgradeMinerai();
				player.closeInventory();
			} catch (NecessaireMineraiIleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MoneyInsuffisantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (itemUsed.getItemMeta().getDisplayName().equals("Retour")) {
			MenuPrincipal.ouvrirMenuPrincipal(player);
		}
		event.setCancelled(true);
	}
}
