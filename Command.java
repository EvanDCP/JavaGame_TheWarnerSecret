 /**
 * Classe Command - une commande du jeu "Le secret des Warner".
 * Une commande est constituee d'un mot d'ordre (command word) et eventuellement
 * d'un second mot.
 * 
 * @author Evan Da Costa Pina
 * @version 04/12/2024
 */
public class Command
{
    /** Mot d'ordre de la commande. */
    private String aCommandWord;
    
    /** Second mot de la commande (peut être null). */
    private String aSecondWord;
        
    /**
     * Constructeur pour les objets de la classe Command.
     * 
     * @param pW1 Le premier mot (mot d'ordre) de la commande.
     * @param pW2 Le second mot de la commande (peut être null).
     */
    public Command(final String pW1,final String pW2)
    {
        this.aCommandWord = pW1;
        this.aSecondWord = pW2;
    }
    
    /**
     * Retourne le mot d'ordre de la commande.
     * 
     * @return Le mot d'ordre de la commande sous forme de chaîne de caracteres.
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    }
    
    /**
     * Retourne le second mot de la commande.
     * 
     * @return Le second mot de la commande, ou null si la commande n'a pas de second mot.
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }
    
    /**
     * Verifie si la commande a un second mot.
     * 
     * @return true si la commande a un second mot, false sinon (si le second mot est null).
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    }
    
    /**
     * Verifie si la commande est inconnue (mot d'ordre nul).
     * 
     * @return true si le mot d'ordre est null, false sinon (si le mot d'ordre est defini).
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    }
} // Command
