    import java.util.Stack;
import java.util.HashMap;

/**
 * Classe Player - represente un joueur dans le jeu.
 * 
 * @author Evan Da Costa Pina
 * @version 16/12/2024
 */
public class Player {
    /**
     * La salle actuelle où se trouve le joueur.
     */
    private Room aCurrentRoom;
    
    /**
     * La pile des salles précédemment visitées par le joueur. 
     * Permet de revenir en arrière dans les déplacements.
     */
    private Stack<Room> aPreviousRooms;
    
    /**
     * L'inventaire du joueur, contenant les objets qu'il transporte.
     */
    private ItemList aInventory;
    
    /**
     * Le poids maximal que le joueur peut transporter.
     */
    private double aMaxWeight;
    
    /**
     * Le poids total des objets actuellement transportés par le joueur.
     */
    private double aCurrentWeight;

    
    /**
     * Constructeur pour initialiser le joueur avec une salle initiale.
     * 
     * @param pInitialRoom La salle de depart du joueur.
     * @param pMaxWeight Le poids maximum que le joueur peut transporter.
     */
    public Player(final Room pInitialRoom, final double pMaxWeight) {
        this.aCurrentRoom = pInitialRoom;
        this.aPreviousRooms = new Stack<>();
        this.aInventory = new ItemList(); 
        this.aMaxWeight = pMaxWeight;
        this.aCurrentWeight = 0.0;
    }

    /**
     * Retourne la salle actuelle du joueur.
     * 
     * @return La salle actuelle.
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }
    
    /**
     * Permet d'obtenir le piece precedente 
     * 
     * @return la piece dans laquelle le joueur etait precedement 
     */
    public Room getPreviousRoom() 
    {
        return this.aPreviousRooms.peek();
    }
    
    /**
     * Definit le poids maximal que le joueur peut transporter.
     * 
     * @param pI Le nouveau poids maximal.
     */
    public void setMaxWeight(final double pI)
    {
        this.aMaxWeight = pI;
    }
    
    /**
     * Retourne la valeur actuelle du poids maximal que le joueur peut transporter.
     * 
     * @return Le poids maximal transportable.
     */
    public double getMaxWeight()
    {
        return this.aMaxWeight;
    }

    /**
     * Definit la salle actuelle du joueur.
     * 
     * @param pRoom La nouvelle salle.
     */
    public void setCurrentRoom(Room pRoom) {
        this.aPreviousRooms.push(this.aCurrentRoom); // Ajoute l'ancienne salle a la pile.
        this.aCurrentRoom = pRoom;
    }

    /**
     * Retourne le poids actuel des objets transportes par le joueur.
     * 
     * @return Le poids actuel transporte.
     */
    public double getCurrentWeight()
    {
        return this.aCurrentWeight;
    }
    
    /**
     * Met a jour le poids actuel des objets transportes par le joueur.
     * 
     * @param pI Le nouveau poids actuel.
     */
    public void setCurrentWeight(final double pI)
    {
        this.aCurrentWeight = pI;
    }
    
    /**
     * Retourne le joueur a la salle precedente.
     * 
     * @return La salle precedente ou null si aucune salle precedente.
     */
    public Room goBack() {
        if (this.aPreviousRooms.isEmpty()) {
            return null;
        }
        this.aCurrentRoom = this.aPreviousRooms.pop(); // Revient a la salle precedente.
        return this.aCurrentRoom;
    }

    /**
     * Verifie si le joueur peut retourner en arriere.
     * 
     * @return true si une salle precedente existe, false sinon.
     */
    public boolean canGoBack() {
        return !this.aPreviousRooms.isEmpty();
    }
    
    /**
     * Permet au joueur de prendre un objet dans la salle actuelle.
     * 
     * @param pItemName Le nom de l'objet a prendre.
     * @return true si l'objet a ete pris, false sinon.
     */
    public boolean takeItem(String pItemName) {
        if (this.aCurrentRoom.hasItem(pItemName) ) 
        {
            Item vItem = this.aCurrentRoom.removeItem(pItemName);
            double vItemWeight = vItem.getItemWeight();
            
            if (this.aCurrentWeight + vItemWeight <= this.aMaxWeight) 
            {
                this.aInventory.addItem(vItem);
                this.aCurrentWeight += vItemWeight; // Mise a jour du poids
                return true;
            } else 
            {
                // Remet l'objet dans la salle si trop lourd
                this.aCurrentRoom.addItem(vItem);
                System.out.println("Cet objet est trop lourd pour être transporte !");
            }
        }
        return false;
    }

    /**
     * Permet au joueur de deposer un item dans la piece actuelle.
     * 
     * @param pItemName Nom de l'item a deposer.
     * @return true si l'item est depose avec succes, false sinon.
     */
    public boolean dropItem(String pItemName) {
        if (this.aInventory.hasItem(pItemName)) 
        {
            Item vItem = this.aInventory.removeItem(pItemName);
            this.aCurrentRoom.addItem(vItem);
            this.aCurrentWeight -= vItem.getItemWeight();
            return true;
        }
        return false;
    }
    
    /**
     * Retourne l'inventaire du joueur.
     * 
     * @return L'inventaire du joueur sous forme d'ItemList.
     */
    public ItemList getInventory() 
    {
        return this.aInventory;
    }
    
    /**
     * Retourne la description des items transportes par le joueur.
     * 
     * @return Une chaîne de caracteres decrivant les items et le poids total transporte.
     */
     public String getInventoryDescription() 
    {
        return this.aInventory.getItemListDescription() + "\nPoids total transporte : " + this.aCurrentWeight + " kg / " + getMaxWeight();
    }
    
     /**
     * Récupère un Beamer (ou téléporteur) par son nom dans l'inventaire du joueur.
     * 
     * @param pItemName le nom de l'objet Beamer à rechercher.
     * @return le Beamer trouvé ou null si l'objet n'est pas dans l'inventaire.
     */
    public Beamer getBeamerByName(final String pItemName) {
        Item vItem = this.aInventory.getItem(pItemName); 
        if (vItem instanceof Beamer) {
            return (Beamer) vItem; 
        }
        return null;
    }
}


