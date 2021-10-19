package fr.ohnreihen.skyblock.Joueur;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.ohnreihen.skyblock.Main;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;
import fr.ohnreihen.skyblock.monde.Monde;


public class Joueur   {
	
	///////////                VALEURS PERSONNALISéES        ///////////////////
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
		
		//System.out.println("Les données de " + joueur.getPlayer().getName() + " ont été sauvegardé");
		//System.out.println(joueur.getPlayer().getName() + " a " + money + "money");
		//System.out.println(joueur.getPlayer().getName() + " a " + scoreChute + "chutes");
		//System.out.println(joueur.getPlayer().getName() + " a " + eclat + "éclat");
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
		
		//System.out.println("Les données de " + joueur.getPlayer().getName() + " ont été récupérées lors de recupererDataJoueur()");
		//System.out.println(joueur.getPlayer().getName() + " a " + money + "money");
		//System.out.println(joueur.getPlayer().getName() + " a " + joueur.getScoreChute() + "chutes");
		//System.out.println(joueur.getPlayer().getName() + " a " + eclat + "éclat");
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

	public static void supprimerJoueurList(Player player) {
		
		listJoueur.remove(player.getUniqueId());
		
	}
}