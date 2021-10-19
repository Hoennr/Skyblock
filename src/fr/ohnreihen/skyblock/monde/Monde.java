package fr.ohnreihen.skyblock.monde;

import java.io.File;
import java.io.IOException;

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

public class Monde {

	private String nomMonde;
	private long valeurMonde = 0;
	private float vitesseSpawn = 1;
	private float chanceDoubleLoot = 0;
	private int niveauMinerai = 0; //lv0= stone / lv1=charbon / lv2= redstone / lvl3= gold / lvl4= iron / lvl5= diamand
	//probabilité de minerai en fonction du niveau
	private float multiplicateurValeurItem = 1;
	public static final String TYPE_PVE= "PVE";
	public static final String TYPE_ILE= "ILE";
	private int taille = 150;
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
	}public int getTaille() {
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
		int taille = mondeIle.getTaille();
		long valeurMonde = mondeIle.getValeurMonde();
		float vitesseSpawn = mondeIle.getVitesseSpawn();
		
		
		System.out.println("Les données du mondeIle du perso sont sauvegardées");
		System.out.println(chanceDoubleLoot + multiplicateurValeurItem + niveauMinerai + taille + valeurMonde + vitesseSpawn);
		
		
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYVALEURMONDE), PersistentDataType.LONG, valeurMonde);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYMULTIPLICATEURVALEURITEM), PersistentDataType.FLOAT, multiplicateurValeurItem);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYNIVEAUMINERAI), PersistentDataType.INTEGER, niveauMinerai);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYCHANCEDOUBLELOOT), PersistentDataType.FLOAT, chanceDoubleLoot);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYTAILLE), PersistentDataType.INTEGER, taille);
		data.set(new NamespacedKey(Main.getPlugin(), Monde.KEYVITESSESPAWN), PersistentDataType.FLOAT, vitesseSpawn);
		
		
	}
	
	public static void recupererDataMonde(Joueur joueur) {
		
		Player player = joueur.getPlayer();
		Monde mondeIle = new Monde(player, Monde.TYPE_ILE);
	
		PersistentDataContainer data = player.getPersistentDataContainer();

		float chanceDoubleLoot =data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYCHANCEDOUBLELOOT), PersistentDataType.FLOAT);
		float multiplicateurValeurItem = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYMULTIPLICATEURVALEURITEM), PersistentDataType.FLOAT);
		int niveauMinerai =	data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYNIVEAUMINERAI), PersistentDataType.INTEGER);
		int taille = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYTAILLE), PersistentDataType.INTEGER);
		long valeurMonde = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYVALEURMONDE), PersistentDataType.LONG);
		float vitesseSpawn = data.get(new NamespacedKey(Main.getPlugin(), Monde.KEYVITESSESPAWN), PersistentDataType.FLOAT);
		
		
		mondeIle.setChanceDoubleLoot(chanceDoubleLoot);	
		mondeIle.setMultiplicateurValeurItem(multiplicateurValeurItem);
		mondeIle.setNiveauMinerai(niveauMinerai);
		mondeIle.setTaille(taille);
		mondeIle.setValeurMonde(valeurMonde);
		mondeIle.setVitesseSpawn(vitesseSpawn);
		
		System.out.println("Les données du mondeIle du perso sont chargées");
		System.out.println(chanceDoubleLoot + multiplicateurValeurItem + niveauMinerai + taille + valeurMonde + vitesseSpawn);
		
		joueur.setMondeIle(mondeIle);
		joueur.setMondePVE(new Monde(player, Monde.TYPE_PVE));
		
		
	}
}