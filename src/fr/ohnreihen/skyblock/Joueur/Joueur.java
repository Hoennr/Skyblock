package fr.ohnreihen.skyblock.Joueur;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.ohnreihen.skyblock.Main;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.MoneyInsuffisantException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.NecessaireMineraiIleException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.NecessaireTailleIleException;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.ProduitInexistantException;
import fr.ohnreihen.skyblock.menus.MenuPrincipal;
import fr.ohnreihen.skyblock.menus.Produits;
import fr.ohnreihen.skyblock.monde.Monde;


public class Joueur   {
	
	///////////                VALEURS PERSONNALISÈES        ///////////////////
    private Player joueur = null;
    private int scoreChute = 0;
    private long money = 0;
    private int eclat = 0;
    private int niveauPrestige=0;
    private Monde mondePVE;
    private Monde mondeIle;    
    
	private static int NOMBRE_JOUEUR_MAX = 20;
	public static HashMap<UUID, Joueur>  listJoueur = new HashMap<UUID, Joueur>(NOMBRE_JOUEUR_MAX);
    public static String KEYSCORECHUTE = "scoreChute";
    public static String KEYMONEY = "money";
    public static String KEYECLAT = "eclat";
    public static String KEYNIVEAUPRESTIGE = "niveauPrestige";
    
    ///////////                   CONSTRUCTEUR GETTER SETTER           ////////////////////////////
    
    public Joueur(Player player) {
        this.joueur = player;
    }

    public Player getPlayer() {
    	return joueur;
    }
    public int getScoreChute() {
		return scoreChute;
	}public void setScoreChute(int scoreChute) {
		this.scoreChute = scoreChute;
	}
    public long getMoney() {
		return money;
	}public void setMoney(long money) {
		this.money = money;
	}public int getEclat() {
		return eclat;
	}public void setEclat(int eclat) {
		this.eclat = eclat;
	}public int getNiveauPrestige() {
		return niveauPrestige;
	}public void setNiveauPrestige(int niveau_prestige) {
		this.niveauPrestige = niveau_prestige;
	}
	public Monde getMondeIle() {
		return mondeIle;
	}public void setMondeIle(Monde mondeIle) {
		this.mondeIle = mondeIle;
	}public Monde getMondePVE() {
		return mondePVE;
	}public void setMondePVE(Monde mondePVE) {
		this.mondePVE = mondePVE;
	}
	
	public int getBlockMined() {
		Material[] listBlockMined = {Material.COBBLESTONE,Material.STONE,Material.GRANITE,Material.ANDESITE,Material.DIORITE,Material.BRICKS,Material.SMOOTH_STONE,Material.MOSSY_COBBLESTONE,Material.STONE_BRICKS,Material.MOSSY_STONE_BRICKS,Material.CRACKED_STONE_BRICKS,Material.CHISELED_STONE_BRICKS,Material.SANDSTONE,Material.CHISELED_SANDSTONE,Material.CUT_SANDSTONE,Material.SMOOTH_SANDSTONE,Material.RED_SANDSTONE,Material.CHISELED_RED_SANDSTONE,Material.CUT_RED_SANDSTONE,Material.SMOOTH_RED_SANDSTONE,Material.QUARTZ_BLOCK,Material.END_STONE,Material.NETHERRACK,Material.BASALT,Material.NETHER_BRICKS,Material.PRISMARINE,Material.PRISMARINE_BRICKS,Material.DARK_PRISMARINE,Material.COAL_ORE,Material.COPPER_ORE,Material.DIAMOND_ORE,Material.EMERALD_ORE,Material.GOLD_ORE,Material.IRON_ORE,Material.LAPIS_ORE,Material.NETHER_GOLD_ORE,Material.NETHER_QUARTZ_ORE,Material.REDSTONE_ORE};
		int nbBlockMine = 0;
		
		for(int i = 0; i<listBlockMined.length;i++) {
			nbBlockMine = nbBlockMine + joueur.getStatistic(Statistic.MINE_BLOCK, listBlockMined[i]);
		}
		
		return nbBlockMine;
		
	}

    ///////////                    METHODES PERSO            ////////////////////////////

	public static void sauvegarderJoueur(Player player) throws JoueurNonEnregistrerException {
		Joueur joueur = getJoueur(player);
		PersistentDataContainer data = player.getPersistentDataContainer();		
		int scoreChute = joueur.getScoreChute();
		long money = joueur.getMoney();
		int eclat = joueur.getEclat();
		int niveauPrestige = joueur.getNiveauPrestige();
		
		data.set(new NamespacedKey(Main.getPlugin(), Joueur.KEYSCORECHUTE), PersistentDataType.INTEGER, scoreChute);
		data.set(new NamespacedKey(Main.getPlugin(), Joueur.KEYMONEY), PersistentDataType.LONG, money);
		data.set(new NamespacedKey(Main.getPlugin(), Joueur.KEYECLAT), PersistentDataType.INTEGER, eclat);
		data.set(new NamespacedKey(Main.getPlugin(), Joueur.KEYNIVEAUPRESTIGE), PersistentDataType.INTEGER, niveauPrestige);
		
		Monde.sauvegarderMondes(joueur);
		
		//System.out.println("Les donnÈes de " + joueur.getPlayer().getName() + " ont ÈtÈ sauvegardÈ");
		//System.out.println(joueur.getPlayer().getName() + " a " + money + "money");
		//System.out.println(joueur.getPlayer().getName() + " a " + scoreChute + "chutes");
		//System.out.println(joueur.getPlayer().getName() + " a " + eclat + "Èclat");
		//System.out.println(joueur.getPlayer().getName() + " a " + niveauPrestige + "de prestige");
		
	}

	public static Joueur recupererDataJoueur(Player player) {
		Joueur joueur = new Joueur(player);
		PersistentDataContainer data = player.getPersistentDataContainer();
		int scoreChute = data.get(new NamespacedKey(Main.getPlugin(),Joueur.KEYSCORECHUTE), PersistentDataType.INTEGER);
		long money = data.get(new NamespacedKey(Main.getPlugin(),Joueur.KEYMONEY), PersistentDataType.LONG);
		int eclat = data.get(new NamespacedKey(Main.getPlugin(),Joueur.KEYECLAT), PersistentDataType.INTEGER);
		int niveauPrestige = data.get(new NamespacedKey(Main.getPlugin(),Joueur.KEYNIVEAUPRESTIGE), PersistentDataType.INTEGER);
		
		joueur.setScoreChute(scoreChute);
		joueur.setMoney(money);
		joueur.setEclat(eclat);
		joueur.setNiveauPrestige(niveauPrestige);
		
		//System.out.println("Les donnÈes de " + joueur.getPlayer().getName() + " ont ÈtÈ rÈcupÈrÈes lors de recupererDataJoueur()");
		//System.out.println(joueur.getPlayer().getName() + " a " + money + "money");
		//System.out.println(joueur.getPlayer().getName() + " a " + joueur.getScoreChute() + "chutes");
		//System.out.println(joueur.getPlayer().getName() + " a " + eclat + "Èclat");
		//System.out.println(joueur.getPlayer().getName() + " a " + niveauPrestige + "de prestige");
		
		
		return joueur;
		
	}
	
	public HashMap<UUID, Joueur> getListJoueur(){
		return listJoueur;
	}

	public static  void addListJoueur(Joueur newjoueur) {
		UUID idJoueur = newjoueur.getPlayer().getUniqueId();
		listJoueur.put(idJoueur, newjoueur);
	}
	
	public static Joueur getJoueur(Player player) throws JoueurNonEnregistrerException{
		
		Joueur joueur = listJoueur.get(player.getUniqueId());
		if (joueur==null) {
			throw (new JoueurNonEnregistrerException());
		}
		
		return joueur; 
	}
	
	public static Joueur getJoueur(String nomJoueur) throws JoueurNonEnregistrerException {
		Joueur joueur=null;
		Collection <Joueur> coll = listJoueur.values();
		for (Joueur element : coll) {
			if (element.getPlayer().getDisplayName().equals(nomJoueur)){
				joueur= element;
			}
		 }
		if (joueur==null) {
			throw (new JoueurNonEnregistrerException());
		}
		
		return joueur;
	}

	public static void supprimerJoueurList(Player player) {
		
		listJoueur.remove(player.getUniqueId());
		
	}
	
	public void depenserMoney(long money) throws MoneyInsuffisantException {
		
		long moneyJoueur = this.getMoney();
		if(money > moneyJoueur) {
			throw (new MoneyInsuffisantException());
		}else {
			this.setMoney(moneyJoueur-money);
			TableauScore.creerTableau(this);
		}

		
	}

	public void gainMoney(long moneyObtenu) {

		this.setMoney(this.getMoney()+moneyObtenu);
		
	}

	public void upgradeTailleIle() throws NecessaireTailleIleException, MoneyInsuffisantException {
		
		int niveauTaille = this.getMondeIle().getNiveauTaille();
		if (this.getBlockMined()< Monde.EXPPARTAILLEPARNIVEAU[niveauTaille]) {
			throw(new NecessaireTailleIleException());
		}else {
			depenserMoney(Monde.PRIXPARTAILLEPARNIVEAU[niveauTaille]);
			this.getMondeIle().setNiveauTaille(niveauTaille+1);
			joueur.sendTitle("Votre ile,","deviens plus grande " , 20, 100, 20);
			
		}
		// TODO Auto-generated method stub
		
	}

	public void upgradeMinerai() throws NecessaireMineraiIleException, MoneyInsuffisantException {
		
		int niveauMinerai = this.getMondeIle().getNiveauMinerai();
		if (this.getBlockMined()< Monde.EXPPARTAILLEPARNIVEAU[niveauMinerai]) {
			throw (new NecessaireMineraiIleException());
		}else {
			depenserMoney(Monde.PRIXPARMINERAIPARNIVEAU[niveauMinerai]);
			this.getMondeIle().setNiveauMinerai(niveauMinerai+1);
			joueur.sendTitle("Vous dÈbloquez", Monde.NOMUNLOCKMINERAIPARNIVEAU[niveauMinerai+1] , 20, 100, 20);

		}
		// TODO Auto-generated method stub
		
	}


	public static void vendreItem(Player player, ItemStack itemUsed) {

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
			
			
			
			try {
				Produits produit = Produits.getProduit(itemUsed);
				Joueur joueur = Joueur.getJoueur(player);
				int moneyObtenu = produit.getPrixVente()*nbIS;
				//joueur.setMoney(joueur.getMoney()+moneyObtenu);
				inventairePlayer.remove(itemUsed.getType());
				joueur.gainMoney(moneyObtenu);
				TableauScore.creerTableau(joueur);
				
			} catch (ProduitInexistantException e) {
				// TODO Auto-generated catch block
			
			} catch (JoueurNonEnregistrerException e) {
				// TODO Auto-generated catch block
			}
			
			//System.out.println("Vous avez +"+ nbIS + " de " + itemUsed.getType().toString()+ " dans votre inventaire");
			
		}else {
			
			//player.sendRawMessage("Vous n'avez pas de " + itemUsed.getType().toString()+ " dans votre inventaire");

		}
		
	}
	
	
	public static void toutVendre(Player player) {
		
		ItemStack[] listIS = player.getInventory().getContents();
		for (int i=0; i<listIS.length;i++) {
			
			if(listIS[i]!=null) {

				try {
					Produits produit = Produits.getProduit(listIS[i]);
					if (Produits.getListProduits().contains(produit)) {
						vendreItem(player,listIS[i] );
					}
				} catch (ProduitInexistantException e) {
					// TODO Auto-generated catch block
				}
				
			}
			
		}
		// TODO Auto-generated method stub
		
	}
	
	public static void donnerKit(Player player) {

		player.getInventory().addItem(new PiochePerso().getPioche());
		player.getInventory().addItem(new SwordPerso().getSword());
		player.getInventory().addItem(new PellePerso().getPelle());
		player.getInventory().addItem(new HachePerso().getHache());
		player.getInventory().addItem(MenuPrincipal.getMenuItem());
		
	}
}