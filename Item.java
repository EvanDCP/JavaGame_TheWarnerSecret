/**
 * Represente un objet dans le jeu avec une description et un poids.
 * Cette classe permet de decrire des objets que le joueur peut manipuler.
 *
 * @author Evan Da Costa Pina
 * @version 04/12/2024
 */
public class Item
{
    /** Nom de l'objet. */
    private String aName;
    
    /** Description de l'objet. */
    private String aDescription;
    
    /** Poids de l'objet en kilogrammes. */
    private double aWeight;
    
    
    /**
     * Constructeur d'un objet de la classe Item.
     * Permet d'initialiser la description et le poids de l'objet.
     *
     * @param pDesc La description de l'objet 
     * @param pWeight Le poids de l'objet en kilogrammes.
     * @param pName Le nom de l'objet.
     */
    public Item(final String pName, final String pDesc, final double pWeight)
    {
        this.aName = pName;
        this.aDescription = pDesc;
        this.aWeight = pWeight;
    }
    
    /**
     * Retourne la description de l'objet.
     * La description peut être, par exemple, un nom ou un type de l'objet.
     *
     * @return La description de l'objet sous forme de chaîne de caracteres.
     */
    public String getItemDescription()
    {
        return this.aDescription; 
    }
    
    /**
     * Permet d'obtenir le nom d'un Item
     * 
     * @return le nom de l'Item
     */
    public String getItemName()
    {
        return this.aName;
    }
    
    /**
     * Retourne le poids de l'objet.
     * Le poids est exprime en kilogrammes.
     *
     * @return Le poids de l'objet en kilogrammes.
     */
    public double getItemWeight()
    {
        return this.aWeight; 
    }

    /**
     * Retourne une representation sous forme de chaîne de caracteres de l'objet.
     * La chaîne inclut la description de l'objet et son poids.
     *
     * @return Une chaîne representant l'objet avec sa description et son poids.
     */
    @Override
    public String toString() {
        return this.aName + " : " + this.aDescription + " (Poids : " + this.aWeight + "kg)";
    }
}
