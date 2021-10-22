package fr.ohnreihen.skyblock;



import org.bukkit.Location;
import org.bukkit.NamespacedKey;
//import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.ohnreihen.skyblock.Joueur.HachePerso;
import fr.ohnreihen.skyblock.Joueur.Joueur;
import fr.ohnreihen.skyblock.Joueur.PiochePerso;
import fr.ohnreihen.skyblock.Joueur.SwordPerso;
import fr.ohnreihen.skyblock.Joueur.TableauScore;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.menus.MenuAchat;
import fr.ohnreihen.skyblock.menus.MenuBlocks;
import fr.ohnreihen.skyblock.menus.MenuPrincipal;
import fr.ohnreihen.skyblock.menus.MenuShopCategorie;
import fr.ohnreihen.skyblock.menus.Produits;
import fr.ohnreihen.skyblock.monde.Monde;

public class MonPluginListener implements Listener{
	

	
	@EventHandler
	static void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Joueur joueur = new Joueur(player);
		
		player.teleport(Monde.WORLDPSPAWN.getSpawnLocation());
		System.out.println("Le joueur " + joueur.getPlayer().getName() + " à rejoind le serveur");
		//System.out.println("Le joueur " + joueur.getPlayer().getName() + " a " + joueur.getScoreChute()+ " de Score");


		if(estFirstConnection(player)) {
			
			player.getInventory().addItem(new PiochePerso().getPioche());
			player.getInventory().addItem(new SwordPerso().getSword());
			player.getInventory().addItem(new HachePerso().getHache());
			player.getInventory().addItem(MenuPrincipal.getMenuItem());
			
			Monde.creerIle(player);
			joueur.setMondeIle(new Monde(player, Monde.TYPE_ILE));
			joueur.setMondePVE(new Monde(player, Monde.TYPE_PVE));
			
			player.updateInventory();
			//System.out.println("C'est la premiere connection de  " + joueur.getPlayer().getName());
			
			
		}else {
			joueur = Joueur.recupererDataJoueur(player);
			Monde.recupererDataMonde(joueur);
			//System.out.println("Ce n'est pas la premiere connection de  " + joueur.getPlayer().getName());
		}
		

		
	
		
		TableauScore.creerTableau(joueur);
		Joueur.addListJoueur(joueur);
		//player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
		
		
		
	}
	
	@EventHandler
	static void onQuit(PlayerQuitEvent event) throws JoueurNonEnregistrerException {
		
		
		Player player = event.getPlayer();
		Joueur joueur = Joueur.getJoueur(player);
		
		Joueur.sauvegarderJoueur(player);
		Joueur.supprimerJoueurList(player);
		Monde.sauvegarderMondes(joueur);
		//Joueur joueur = recupJoueur(player);
		
		
	}
	

	private static boolean estFirstConnection(Player player) {

		PersistentDataContainer data = player.getPersistentDataContainer();
		
		if (!data.has(new NamespacedKey(Main.getPlugin(),"premiereConnection"), PersistentDataType.STRING)){
			data.set(new NamespacedKey(Main.getPlugin(), "premiereConnection"), PersistentDataType.STRING, "Oui");
			return true;
		}else {
			return false;
		}
		
	}

	@EventHandler 
	static void onDamageEntity(EntityDamageEvent eventDamage){
		
		Entity cible = eventDamage.getEntity();

		//Invincible sur l'ile
		if (cible instanceof Player) {
			Player player = (Player) cible;
			if (cible.getWorld().getName().equals(Monde.TYPE_ILE+player.getDisplayName())) {
				eventDamage.setCancelled(true);
			}
		
		}
			
	}
	
	@EventHandler
	static void onBlockBreakWOutilIncassable(BlockBreakEvent event) {
		Player player = event.getPlayer();
		ItemStack itemTenue = player.getInventory().getItemInMainHand();
		String nomItem = itemTenue.getItemMeta().getDisplayName();
		
		Boolean estBonOutil ;
		if (event.getBlock().isPreferredTool(itemTenue)) {
			estBonOutil = true;
		}else{
			estBonOutil = false;
		}
		
		//System.out.println("Le block a ete brisé par le bon outils: " + estBonOutil);
		if( (nomItem.equals(PiochePerso.NOMPIOCHE))  &  (estBonOutil)  ){
			//System.out.println("Le joueur à casser un block");
			PiochePerso piochePerso = new PiochePerso();
			piochePerso.majItem(itemTenue,player,1);				
		}
		
		if ( (nomItem.equals(HachePerso.NOMHACHE))  & (estBonOutil) ) {
			HachePerso hachePerso = new HachePerso();
			hachePerso.majItem(itemTenue, player, 1);
			
		}
	
		
	}
	
	@EventHandler
	static void onKillWSwordPerso(EntityDeathEvent event) {
		LivingEntity cibleMorte = event.getEntity();
		LivingEntity meurtrier = cibleMorte.getKiller();
		
		if(meurtrier instanceof Player) {
			Player player = (Player) meurtrier;
			ItemStack itemTenue = player.getInventory().getItemInMainHand();
			if(itemTenue.getItemMeta().getDisplayName().equals(SwordPerso.NOMSWORD)) {
				SwordPerso sword = new SwordPerso();
				sword.majItem(itemTenue, player, 1);
			}
		}
		
	
	}
	
	
//	@EventHandler 
//	static void onGetExp(PlayerExpChangeEvent event) {
//		Player player = event.getPlayer();
//		Joueur joueur = Joueur.getJoueur(player);
//		TableauScore.creerTableau(joueur);
//	}

	@EventHandler	
	static void onFallVoid(PlayerMoveEvent playerMEvent) throws JoueurNonEnregistrerException {
		
		Player player = playerMEvent.getPlayer();
		Location destination = playerMEvent.getTo();
		if (destination.getBlockY()<0) {
			Location spawn = player.getBedSpawnLocation();
			if (spawn != null) {
			
				player.teleport(spawn);
			
			}else {
			
				player.teleport(player.getWorld().getSpawnLocation());
				
			}
			
			Joueur joueur = Joueur.getJoueur(player);
			joueur.setScoreChute(joueur.getScoreChute()+1);
			//Monde mondeIle = joueur.getMondeIle();
			//mondeIle.setTaille(mondeIle.getTaille()+2);
			TableauScore.creerTableau(joueur);
			System.out.println("Le joueur " + joueur.getPlayer().getName() + "viens de chuter et a " + joueur.getScoreChute()+ " de Score");
			
		
		}
	}
	
	@EventHandler
	static void onPlayerClickMenu (InventoryClickEvent event) throws JoueurNonEnregistrerException {
		
		Player player = (Player) event.getWhoClicked();
		//InventoryView inventoryView = event.getView();
		Inventory inventaire = event.getClickedInventory();
		
		//if(inventoryView.getTitle().equals(MenuMonde.NOMMENU)) {
		
		if (inventaire !=null ) {
			if(player!=inventaire.getHolder()) {
				
				String nomInventaire = event.getView().getTitle();
				if (nomInventaire.contains(nomInventaire))
				
				if (inventaire==MenuPrincipal.getInventaire()) {
					//System.out.println("J'ai cliqué dans le menu");
					MenuPrincipal.utiliserMenu(event);
				}else if (inventaire==MenuShopCategorie.getInventaire()) {
					MenuShopCategorie.utiliserMenu(event);
				}else if (inventaire==MenuAchat.getInventaire()) {
					MenuAchat.utiliserMenu(event);
				}else if (Produits.contientNomCategorie(event.getView().getTitle())){
					MenuBlocks.utiliserMenu(event);
				}				
				
				
			}
		}
		
	}

	@EventHandler
	static void onUseItemMenu(PlayerInteractEvent event) {
		
	    
		Player p = event.getPlayer();
	    if(event.getItem()!=null) {

			if(event.getItem().getItemMeta().getDisplayName().equals(MenuPrincipal.NOMMENU)){
				MenuPrincipal.ouvrirMenuPrincipal(p);
				event.setCancelled(true);
		    }
	    }
	}


	@EventHandler
	static void onOutofTaille(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location destination = event.getTo();
		try {
			Joueur joueur = Joueur.getJoueur(player);
			Monde mondeIle = joueur.getMondeIle();
			String nomMondeIle = mondeIle.getNomMonde();
			//System.out.println ("Voici destination.getWorld().getName() : " +destination.getWorld().getName());
			//System.out.println ("Voici nomMondeIle : " +nomMondeIle);

			if (destination.getWorld().getName().equals(nomMondeIle)) {
				//System.out.println ("Le joueur se deplace sur son ile");
				if(mondeIle.getxMin()>destination.getX() || destination.getX()>mondeIle.getxMax() || mondeIle.getzMin()>destination.getZ() || destination.getZ()>mondeIle.getzMax() ) {
					//System.out.println ("Le joueur est hors de son ile");
					event.setCancelled(true);				
				}
			}
		}catch (JoueurNonEnregistrerException e) {
				System.out.println("Le joueur n'est pas encore enregistré. Veuillez attendre la fin de création de ses mondes");
		}
	}
		
}
