import java.util.List;
import java.util.Random;

/**
 * La classe {@code RoomRandomizer} est responsable de sélectionner aléatoirement
 * une pièce parmi une liste de pièces disponibles.
 * 
 * Cette classe est utilisée pour des fonctionnalités comme les téléporteurs,
 * où une pièce aléatoire doit être choisie sans dépendre d'une direction spécifique.
 */
public class RoomRandomizer {
    /** La liste des pièces disponibles pour la sélection aléatoire. */
    private List<Room> aRooms;
    
    /** Générateur de nombres aléatoires pour choisir une pièce. */
    private Random aRandom;

    /**
     * Constructeur de la classe {@code RoomRandomizer}.
     * Initialise le générateur avec une liste de pièces parmi lesquelles une sélection aléatoire peut être faite.
     * 
     * @param pRooms La liste des pièces disponibles.
     */
    public RoomRandomizer(List<Room> pRooms) {
        this.aRooms = pRooms;
        this.aRandom = new Random();
    }

    /**
     * Retourne une pièce aléatoire parmi la liste des pièces disponibles.
     * 
     * @return Une pièce choisie aléatoirement dans {@code aRooms}.
     */
    public Room getRandomRoom() {
        int index = this.aRandom.nextInt(this.aRooms.size());
        return this.aRooms.get(index);
    }
}
