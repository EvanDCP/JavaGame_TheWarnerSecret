import java.util.HashMap;
import java.util.Set;

/**
 * Classe Room - represente un lieu dans le jeu "Le secret des Warner".
 * Une piece peut avoir des sorties vers d'autres pieces, une description,
 * une image associee, et contenir des objets.
 * 
 * @author Evan Da Costa Pina
 * @version 16/12/2024
 */
public class Room
{
    /** Description textuelle de la pièce. */
    private String aDescription;
    
    /** Sorties de la pièce, mappées par direction. */
    private HashMap<String, Room> aExits;
    
    /** Nom du fichier image associé à la pièce. */
    private String aImageName;
    
    /** Liste des objets présents dans la pièce. */
    private ItemList aItemList;
    
    /** Trappes disponibles dans certaines directions. */
    private HashMap<String, Boolean> aTrapDoors;
    
    
    /**
     * Constructeur de la classe Room.
     * Initialise la description, les sorties, l'image et les objets de la piece.
     *
     * @param pD La description textuelle de la piece.
     * @param pImage Le nom du fichier image associe e la piece.
     */
    public Room(final String pD, final String pImage)
    {
        this.aDescription = pD;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aItemList = new ItemList();
        this.aTrapDoors = new HashMap<>();
    }
    
     /**
     * Retourne une description courte de la piece.
     * 
     * @return La description textuelle de la piece.
     */
    public String getDescription()
    {
        return "Vous êtes " + this.aDescription;
    }
    
     /**
     * Definit une sortie pour cette piece dans une direction donnee.
     * 
     * @param pDirection La direction de la sortie (par exemple : "north", "south").
     * @param pNeighbor La piece voisine associee e cette direction.
     */
    public void setExits(String pDirection, Room pNeighbor)
    {
        aExits.put(pDirection, pNeighbor);
    }
    
    /**
     * Retourne la piece voisine situee dans une direction donnee.
     * 
     * @param pDirection La direction vers laquelle se deplacer.
     * @return La piece voisine, ou {@code null} si la direction n'existe pas.
     */
    public Room getExit(String pDirection)
    {
        return this.aExits.get(pDirection); // Retourne null si la direction n'existe pas
    }
    
    /**
     * Retourne une chaîne decrivant toutes les directions de sortie disponibles.
     * 
     * @return Une chaîne contenant toutes les sorties possibles depuis cette piece.
     */
    public String getExitString() 
    {
        String vReturnString = "Sorties :";
        Set<String> keys = this.aExits.keySet();
        for (String vExit : keys) 
        {
            vReturnString += " " + vExit;
        }
        return vReturnString;
    }
    
    /**
     * Retourne une description longue de la piece, incluant les sorties et les objets presents.
     * 
     * @return Une description complete de la piece.
     */
    public String getLongDescription() 
    {
        return "Vous êtes " + this.aDescription + "\n" + getExitString() + ".\n" + this.aItemList.getItemListDescription() ;
    }
    
    /**
     * Retourne le nom du fichier image associe a la piece.
     * 
     * @return Le nom du fichier image.
     */
    public String getImageName()
    {
        return this.aImageName;
    }
    
    /**
     * Ajoute un objet a la piece.
     * 
     * @param pItem L'objet a ajouter.
     */
    public void addItem(final Item pItem) 
    {
        this.aItemList.addItem(pItem);
    }

    /**
     * Retire un objet de la liste des items en fonction de son nom.
     * 
     * @param pItemName Le nom de l'objet a retirer.
     * @return L'objet retire s'il est trouve, ou null si l'objet n'existe pas dans la liste.
     */
    public Item removeItem(final String pItemName) 
    {
        return this.aItemList.removeItem(pItemName);
    }
    
        /**
     * Vérifie si une salle donnée est une sortie.
     * 
     * @param pRoom La salle à vérifier.
     * @return true si la salle donnée est une sortie, false sinon.
     */
    public boolean isExit(Room pRoom) 
    {
        return this.aExits.containsValue(pRoom);
    }
    
        /**
     * Vérifie si une direction donnée est une trappe
     * 
     * @param pDirection La direction à vérifier
     * @return true si la direction donnée est une trappe, false sinon.
     */
     public boolean isTrapDoor(String pDirection) {
        return this.aTrapDoors.getOrDefault(pDirection, false);
    }
    
        /**
     * Définit une trappe dans une direction spécifique
     * menant à une salle voisine donnée.
     * 
     * @param pDirection La direction de la trappe 
     * @param pNeighbor La salle voisine connectée à cette direction.
     */
    public void setTrapDoor(String pDirection, Room pNeighbor) 
    {
        this.aExits.put(pDirection, pNeighbor);
        this.aTrapDoors.put(pDirection, true);
    }
    
    /**
     * Verifie si un objet existe dans la liste des items en fonction de son nom.
     * 
     * @param pItemName Le nom de l'objet a verifier.
     * @return true si l'objet est present dans la liste, false sinon.
     */
    public boolean hasItem(String pItemName) {
        return this.aItemList.hasItem(pItemName);
    }
} // Room
