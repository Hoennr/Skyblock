package fr.ohnreihen.skyblock.monde;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.ohnreihen.skyblock.Main;
import fr.ohnreihen.skyblock.Joueur.Joueur;
import fr.ohnreihen.skyblock.Joueur.exceptionPerso.JoueurNonEnregistrerException;

public class Monde {

	private String nomMonde;
	private long valeurMonde = 0;
	private float vitesseSpawn = 1;
	private float chanceDoubleLoot = 0;
	private int niveauMinerai = 0; //lv0= stone / lv1=charbon / lv2= redstone / lvl3= gold / lvl4= iron / lvl5= diamand
	public static final int EXPPARMINERAIPARNIVEAU[]= {1000,5000,10000,20000,30000,50000,100000};
	public static final int PRIXPARMINERAIPARNIVEAU[]= {250000,500000,750000,1250000,2000000,3500000,5000000};

	public static final String NOMUNLOCKMINERAIPARNIVEAU []= {"Cobblestone","Charbon","Redstone","Or","Fer","Diamand","Emeraude"};
	public static final Material[] LISTMINERAI = {Material.COBBLESTONE,Material.COAL_ORE,Material.ANDESITE,Material.GRANITE,Material.DIORITE,Material.LAPIS_ORE,Material.REDSTONE_ORE,Material.GOLD_ORE,Material.COPPER_ORE,Material.IRON_ORE,Material.DIAMOND_ORE,Material.EMERALD_ORE,Material.ANCIENT_DEBRIS};
	//probabilité de minerai en fonction du niveau
	public static final int [][] PROBABILITEMINERAIPARNIVEAU = { {100,0,0,0,0,0,0,0,0,0,0,0,0 },
																{70,15,5,5,5,0,0,0,0,0,0,0,0},
																{50,15,5,5,5,10,10,0,0,0,0,0,0},
																{46,13,3,3,3,6,7,10,9,0,0,0,0},
																{44,12,2,2,2,6,7,8,6,11,0,0,0},
																{41,11,2,2,2,5,6,7,6,11,7,0,0},
																{40,11,2,2,2,4,5,7,5,11,7,2,2}};
	private float multiplicateurValeurItem = 1;
	public static final String TYPE_PVE= "PVE";
	public static final String TYPE_ILE= "ILE";
	public static final String TYPE_SPAWN= "SPAWN";
	private int niveauTaille = 0;
	private int taille = 75;
	public static final int TAILLEPARNIVEAU[]= {75,100,150,225,300,400,600,800,1200,2000,4000};
	public static final int EXPPARTAILLEPARNIVEAU[]= {1000,2000,3000,4000,5500,7000,10000,15000,20500,50000,100000};
	public static final int PRIXPARTAILLEPARNIVEAU[]= {100000,200000,300000,400000,550000,700000,1000000,1500000,2500000,5000000,10000000};
	private int xMax = taille/2;
	private int xMin = -taille/2;
	private int zMax = taille/2;
	private int zMin = -taille/2;
	private static String KEYVALEURMONDE = "ValeurMonde";
	private static String KEYVITESSESPAWN = "Vitessedespawn";
	private static String KEYCHANCEDOUBLELOOT = "Chancededoubleloot";
	private static String KEYNIVEAUMINERAI = "Niveaudeminerai";
	private static String KEYMULTIPLICATEURVALEURITEM = "Multiplicateurdelavaleurdesitems";
	private static String KEYTAILLE= "Tailleile";
	private World world;
	public static final World WORLDPSPAWN = Bukkit.getWorld("world");
	
	
	
	public float getChanceDoubleLoot() {
		return chanceDoubleLoot;
	}public void setChanceDoubleLoot(float chanceDoubleLoot) {
		this.chanceDoubleLoot = chanceDoubleLoot;
	}public float getMultiplicateurValeurItem() {
		return multiplicateurValeurItem;
	}public void setMultiplicateurValeurItem(float multiplicateurValeurItem) {
		this.multiplicateurValeurItem = multiplicateurValeurItem;
	}public int getNiveauMinerai() {
		return niveauMinerai;
	}public void setNiveauMinerai(int niveauMinerai) {
		this.niveauMinerai = niveauMinerai;
	}public String getNomMonde() {
		return nomMonde;
	}public void setNomMonde(String nomMonde) {
		this.nomMonde = nomMonde;
	}public long getValeurMonde() {
		return valeurMonde;
	}public void setValeurMonde(long valeurMonde) {
		this.valeurMonde = valeurMonde;
	}public float getVitesseSpawn() {
		return vitesseSpawn;
	}public void setVitesseSpawn(float vitesseSpawn) {
		this.vitesseSpawn = vitesseSpawn;
	}public int getNiveauTaille() {
		return niveauTaille;
	}public void setNiveauTaille(int niveauTaille) {
		this.niveauTaille = niveauTaille;
		this.setTaille(TAILLEPARNIVEAU[niveauTaille]);
	}
	public int getTaille() {
		return taille;
	}public void setTaille(int taille) {
		this.taille = taille;
		xMax = taille/2;
		xMin = -taille/2;
		zMax = taille/2;
		zMin = -taille/2;
	}public int getxMax() {
		return xMax;
	}public int getxMin() {
		return xMin;
	}public int getzMax() {
		return zMax;
	}public int getzMin() {
		return zMin;
	}public World getWorld() {
		return world;
	}public static int[] getTAILLEPARNIVEAU() {
		return TAILLEPARNIVEAU;
	}
	
	public Monde(Player player, String type) {
		
		WorldCreator wc= new WorldCreator("");
		
		if (type.equals(TYPE_PVE)) {
			wc= new WorldCreator(TYPE_PVE + player.getDisplayName());
			wc.environment(Environment.NORMAL);
			wc.type(WorldType.NORMAL);
			this.nomMonde=TYPE_PVE+player.getDisplayName();
			
		} else if (type.equals(TYPE_ILE)) {
			
			wc= new WorldCreator(TYPE_ILE + player.getDisplayName());
			this.nomMonde = TYPE_ILE + player.getDisplayName();
			System.out.println ("Le monde " + wc.name()+" est créé");
		}
		
		this.world=wc.createWorld();
	}
		
	
	
	
	public static void creerIle(Player player) {
		
		try {
			FileUtils.copyDirectory(new File("backup skyblock map debut"), new File(Monde.TYPE_ILE+player.getDisplayName()));
		} catch (IOException e) {
			System.out.println("Le monde de bakup ile n'a pas pu etre copié");
			e.printStackTrace();
		}
		
	}
	
	public void majMonde(Monde monde) {
		this.setChanceDoubleLoot(monde.getChanceDoubleLoot());
		this.setMultiplicateurValeurItem(monde.getMultiplicateurValeurItem());
		this.setNiveauMinerai(monde.getNiveauMinerai());
		this.setTaille(monde.getTaille());
		this.setValeurMonde(monde.getValeurMonde());
		this.setVitesseSpawn(monde.getVitesseSpawn());
				
	}
	public static void sauvegarderMondes(Joueur joueur) {

		
		Player player = joueur.getPlayer();
		PersistentDataContainer data = player.getPersistentDataContainer();		
		Monde mondeIle = joueur.getMondeIle();
		float chanceDoubleLoot = mondeIle.getChanceDoubleLoot();
		float multiplicateurValeurItem = mondeIle.getMultiplicateurValeurItem();
		int niveauMinerai = mondeIle.getNiveauMinerai();
		int niveauTaille = mondeIle.getNiveauTaille();
		long valeurMonde = mondeIle.getValeurMonde();
		float vitesseSpawn = mondeIle.getVitesseSpawn();
		
		
		System.out.println("Les données du mondeIle du perso sont sauvegardées");
		System.out.println(chanceDoubleLoot + multiplicateurValeurItem + niveauMinerai + niveauTaille + valeurMonde + vitesseSpawn);
		
		
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYVALEURMONDE), PersistentDataType.LONG, valeurMonde);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYMULTIPLICATEURVALEURITEM), PersistentDataType.FLOAT, multiplicateurValeurItem);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYNIVEAUMINERAI), PersistentDataType.INTEGER, niveauMinerai);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYCHANCEDOUBLELOOT), PersistentDataType.FLOAT, chanceDoubleLoot);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYTAILLE), PersistentDataType.INTEGER, niveauTaille);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYVITESSESPAWN), PersistentDataType.FLOAT, vitesseSpawn);
		
		
	}
	
	public static void recupererDataMonde(Joueur joueur) {
		
		Player player = joueur.getPlayer();
		Monde mondeIle = new Monde(player, Monde.TYPE_ILE);
	
		PersistentDataContainer data = player.getPersistentDataContainer();

		float chanceDoubleLoot =data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYCHANCEDOUBLELOOT), PersistentDataType.FLOAT);
		float multiplicateurValeurItem = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYMULTIPLICATEURVALEURITEM), PersistentDataType.FLOAT);
		int niveauMinerai =	data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYNIVEAUMINERAI), PersistentDataType.INTEGER);
		int niveauTaille = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYTAILLE), PersistentDataType.INTEGER);
		long valeurMonde = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYVALEURMONDE), PersistentDataType.LONG);
		float vitesseSpawn = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYVITESSESPAWN), PersistentDataType.FLOAT);
		
		
		mondeIle.setChanceDoubleLoot(chanceDoubleLoot);	
		mondeIle.setMultiplicateurValeurItem(multiplicateurValeurItem);
		mondeIle.setNiveauMinerai(niveauMinerai);
		mondeIle.setNiveauTaille(niveauTaille);
		mondeIle.setValeurMonde(valeurMonde);
		mondeIle.setVitesseSpawn(vitesseSpawn);
		
		System.out.println("Les données du mondeIle du perso sont chargées");
		System.out.println(chanceDoubleLoot + multiplicateurValeurItem + niveauMinerai + niveauTaille + valeurMonde + vitesseSpawn);
		
		joueur.setMondeIle(mondeIle);
		joueur.setMondePVE(new Monde(player, Monde.TYPE_PVE));
		
	}
	
	public static void changerMonde(Player player, String type) throws JoueurNonEnregistrerException {
		
		Joueur joueur = Joueur.getJoueur(player);
		World monde = null;
		if (type.equals(Monde.TYPE_ILE)) {
			monde = joueur.getMondeIle().getWorld();
		}else if(type.equals(Monde.TYPE_PVE)) {
			monde = joueur.getMondePVE().getWorld();
		}else if (type.equals(Monde.TYPE_SPAWN)) {
			monde = Monde.WORLDPSPAWN;
		}
		
		Bukkit.unloadWorld(player.getWorld(), true);
		player.teleport(monde.getSpawnLocation());
		
	}
	public static Monde getMondeIle(World world) {
		String nomJoueurMonde = world.getName().replace(Monde.TYPE_ILE, "");
		try {
			Joueur joueur = Joueur.getJoueur(nomJoueurMonde);
			Monde monde = joueur.getMondeIle();
			return monde;
		} catch (JoueurNonEnregistrerException e) {
			// TODO Auto-generated catch block
			System.out.println("Le joueur n'existe pas, n'est pas encore enregistrer ou n'est pas en ligne");
			e.printStackTrace();
		}
		return null;
	
	}
}