/**
 * La classe {@code Beamer} représente un téléporteur utilisable dans le jeu.
 * 
 * Un téléporteur peut être chargé avec une pièce spécifique, puis utilisé pour
 * téléporter le joueur directement vers cette pièce. Si le téléporteur n'est pas
 * chargé, il ne peut pas être utilisé.
 * 
 * Cette classe hérite de {@code Item} et peut donc être manipulée comme un objet
 * de l'inventaire du joueur.
 */
public class Beamer extends Item {
    /**
     * aChargedRoom
     */
    private Room aChargedRoom;  // La pièce où le téléporteur a été chargé.

    /**
     * Constructeur du Beamer.
     * 
     * @param pName Le nom du téléporteur.
     * @param pDesc La description du téléporteur.
     * @param pWeight Le poids du téléporteur.
     */
    public Beamer(final String pName, final String pDesc, final double pWeight) {
        super(pName, pDesc, pWeight);
        this.aChargedRoom = null;  // Initialement, il n'est pas chargé.
    }

    /**
     * Charge le téléporteur dans la pièce donnée.
     * 
     * @param pRoom La pièce dans laquelle charger le téléporteur.
     */
    public void charge(Room pRoom) {
        this.aChargedRoom = pRoom;
        System.out.println("Le téléporteur est chargé dans la pièce : " + pRoom.getDescription());
    }

    /**
     * Tire le téléporteur et téléporte le joueur vers la pièce où il a été chargé.
     * Si le téléporteur n'a pas été chargé, il ne peut pas être utilisé.
     * 
     * @param pCurrentRoom La pièce actuelle du joueur.
     * @return La nouvelle pièce après téléportation, ou la pièce actuelle si non chargé.
     */
    public Room fire(Room pCurrentRoom) {
        if (this.aChargedRoom != null) {
            System.out.println("Téléportation vers : " + this.aChargedRoom.getDescription());
            return this.aChargedRoom;  // Le joueur est téléporté dans la pièce où le téléporteur a été chargé.
        } else {
            System.out.println("Le téléporteur n'a pas été chargé. Impossible de se téléporter.");
            return pCurrentRoom;  // Le joueur reste dans la pièce actuelle.
        }
    }

    /**
     * Vérifie si le téléporteur a été chargé.
     * 
     * @return true si le téléporteur a été chargé, false sinon.
     */
    public boolean isCharged() {
        return this.aChargedRoom != null;
    }
}