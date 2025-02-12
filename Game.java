 /**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 * Gere la creation des pieces, le traitement des commandes et la progression du jeu.
 * 
 *
 * @author Evan Da Costa Pina
 * @version 16/12/2024
 */
public class Game
{
    /** Interface utilisateur pour interagir avec le jeu. */
    private UserInterface aGui;
    
    /** Moteur du jeu qui gère la logique principale. */
    private GameEngine aEngine;


    /**
     * Creez le jeu et initialisez sa carte interne. Creez l'interface et creez un lien vers celle-ci.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }    
}// Game
