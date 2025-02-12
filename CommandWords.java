 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 */
public class CommandWords
{
    /**
     * Les commandes valides
     */
    private final String[] aValidCommands;

    /**
     * Constructeur - initialise les commandes valides.
     * Ce constructeur crée et initialise le tableau contenant les mots de commande valides.
     */
    public CommandWords()
    {
        this.aValidCommands = new String[12];
        this.aValidCommands[0] = "aller";
        this.aValidCommands[1] = "aide";
        this.aValidCommands[2] = "quitter";
        this.aValidCommands[3] = "observer";
        this.aValidCommands[4] = "manger";
        this.aValidCommands[5] = "retour";
        this.aValidCommands[6] = "test";
        this.aValidCommands[7] = "prendre";
        this.aValidCommands[8] = "lacher";
        this.aValidCommands[9] = "inventaire";
        this.aValidCommands[10] = "charger";
        this.aValidCommands[11] = "tirer";
    } // CommandWords()

    /**
     * Vérifie si une chaîne donnée est un mot de commande valide.
     *
     * @param pString La chaîne de caractères à vérifier.
     * @return true si la chaîne est une commande valide, false sinon.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()
    
    /**
     * Retourne toutes les commandes valides disponibles dans le jeu.
     * 
     * Cette méthode permet au joueur de connaître toutes les commandes qu'il peut utiliser.
     *
     * @return Une chaîne contenant toutes les commandes valides séparées par un espace.
     */
    public String getCommandList()
    {
        String vString = "";
        for(String vCommand : this.aValidCommands)
        {
            vString += " " + vCommand;
        }
        return vString;
    }

} // CommandWords
