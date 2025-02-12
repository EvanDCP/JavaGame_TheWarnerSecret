import java.util.HashMap;

/**
 * Classe représentant une collection d'objets (items).
 * Permet de gérer les objets ajoutés, retirés ou consultés.
 * 
 * @author Evan Da Costa Pina
 * @version 16/12/2024
 */
public class ItemList {
    /** Collection des objets, mappés par leur nom. */
    private HashMap<String, Item> aItems;

    /**
     * Constructeur par defaut pour initialiser la collection d'items.
     */
    public ItemList() 
    {
        this.aItems = new HashMap<>();
    }

    /**
     * Ajoute un item a la collection.
     * 
     * @param pItem L'item a ajouter.
     */
    public void addItem(final Item pItem) 
    {
        if (pItem != null) {
            this.aItems.put(pItem.getItemName(), pItem);
        }
    }

    /**
     * Supprime un item de la collection et le retourne.
     * 
     * @param pItemName Le nom de l'item a supprimer.
     * @return L'item supprime, ou null s'il n'existe pas.
     */
    public Item removeItem(final String pItemName) 
    {
        return this.aItems.remove(pItemName);
    }

    /**
     * Retourne un item specifique par son nom.
     * 
     * @param pItemName Le nom de l'item recherche.
     * @return L'item correspondant ou null s'il n'existe pas.
     */
    public Item getItem(final String pItemName) {
        return this.aItems.get(pItemName);
    }

    /**
     * Verifie si un item est present dans la collection.
     * 
     * @param pItemName Le nom de l'item.
     * @return true si l'item est present, false sinon.
     */
    public boolean hasItem(final String pItemName) 
    {
        return this.aItems.containsKey(pItemName);
    }

    /**
     * Retourne une description complete des items de la collection.
     * 
     * @return Une chaîne decrivant tous les items.
     */
    public String getItemListDescription() 
    {
        if (this.aItems.isEmpty()) {
            return "Aucun objet...";
        }

        StringBuilder vDescription = new StringBuilder("Objets disponibles : ");
        for (Item vItem : this.aItems.values()) {
            vDescription.append("\n- ").append(vItem.toString());
        }
        return vDescription.toString();
    }

    /**
     * Verifie si la liste d'items est vide.
     * 
     * @return true si aucun item n'est present, false sinon.
     */
    public boolean isEmpty() 
    {
        return this.aItems.isEmpty();
    }
        
    /**
     *  @return Le poids total 
     */
    public double getTotalWeight()
    {
        double vTotalWeight = 0;
        for (Item vItem : this.aItems.values())
        {
            vTotalWeight += vItem.getItemWeight();
        }
        return vTotalWeight;
    }
}
