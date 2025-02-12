import java.util.List;
import java.util.Random;

/**
 * La classe {@code TransporterRoom} représente une pièce spéciale dans le jeu.
 * Contrairement aux autres pièces, les sorties de cette pièce ne sont pas fixes.
 * Chaque fois qu'un joueur tente de sortir de cette pièce, il est téléporté vers une pièce aléatoire.
 * 
 * Cette fonctionnalité est rendue possible grâce à un objet {@code RoomRandomizer} qui gère
 * la sélection aléatoire des pièces.
 * 
 * La classe hérite de {@code Room}.
 */
public class TransporterRoom extends Room {
    /** Gère la sélection aléatoire des pièces. */
    private RoomRandomizer aRoomRandomizer;


    /**
     * Constructeur de la classe {@code TransporterRoom}.
     * Initialise une pièce avec une description, un nom d'image, et un générateur de pièces aléatoires.
     * 
     * @param pDescription   La description de la pièce.
     * @param pImageName     Le nom de l'image associée à la pièce.
     * @param pRoomRandomizer Un objet {@code RoomRandomizer} utilisé pour sélectionner aléatoirement une pièce.
     */
    public TransporterRoom(String pDescription, String pImageName, RoomRandomizer pRoomRandomizer) {
        super(pDescription, pImageName);
        this.aRoomRandomizer = pRoomRandomizer;
    }

    /**
     * Retourne une pièce aléatoire au lieu d'une sortie basée sur une direction spécifique.
     * 
     * @param direction La direction spécifiée (ignorée dans cette implémentation).
     * @return Une pièce aléatoire sélectionnée par le {@code RoomRandomizer}.
     */
    @Override
    public Room getExit(String direction) {
        return this.aRoomRandomizer.getRandomRoom(); // Retourne une pièce aléatoire
    }
}
