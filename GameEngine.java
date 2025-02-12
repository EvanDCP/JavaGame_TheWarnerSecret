import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe {@code GameEngine} gere la logique principale du jeu d'aventure "The Warner Secret".
 * Elle est responsable de la gestion des pieces, des commandes du joueur, et de l'interaction
 * avec l'interface utilisateur.
 * 
 * Cette classe utilise une pile ({@code Stack}) pour permettre au joueur de revenir dans les 
 * salles precedemment visitees via la commande "back".</p>
 * 
 * @author Evan Da Costa Pina
 * @version 16/12/2024
 */

public class GameEngine
{
    /**
     * L'analyseur syntaxique utilisé pour interpréter les commandes du joueur.
     */
    private Parser aParser;
    
    /**
     * La salle actuelle où se trouve le joueur.
     */
    private Room aCurrentRoom;
    
    /**
     * L'interface utilisateur graphique permettant l'interaction avec le joueur.
     */
    private UserInterface aGui;
    
    /**
     * Le joueur en cours de jeu, avec ses attributs tels que l'inventaire et les capacités.
     */
    private Player aPlayer;
    
    /**
     * La pile des salles précédemment visitées par le joueur, utilisée pour revenir en arrière dans les déplacements.
     */
    private Stack<Room> aPreviousRooms;
    
    /**
     * Le compteur du nombre de commandes exécutées depuis le début du jeu.
     */
    private int aCommandCount;
    
    /**
     * La liste de toutes les salles du jeu, utilisée pour des besoins tels que la génération aléatoire ou la navigation.
     */
    private List<Room> aAllRooms;
    
    /**
     * Le nombre maximal de commandes autorisées avant la fin du jeu.
     */
    private static final int MAX_COMMANDS = 74;
    
    /**
     * Indicateur permettant de savoir si le jeu est en mode test (test automatisé ou simulation).
     * Si activé, certaines fonctionnalités peuvent être ajustées pour les tests.
     */
    private boolean isTestMode = false;

    
    /**
     * Construit une instance de {@code GameEngine} et initialise les composants necessaires :
     * les pieces, l'analyseur de commandes et la pile des salles.
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
        this.aPlayer = new Player(this.aCurrentRoom, 8);
        this.aPreviousRooms = new Stack<>(); 
        this.aCommandCount = 0;
        this.aAllRooms = new ArrayList<>();
    }

    /**
     * Associe une interface utilisateur graphique au moteur de jeu.
     * 
     * @param pUserInterface l'interface utilisateur a associer.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    
     /**
     * Affiche un message de bienvenue au joueur et la description de la salle actuelle.
     */
    private void printWelcome()
    {
        this.aGui.updateCommandCount(this.aCommandCount);
        this.aGui.print( "\n" );
        this.aGui.println("Bienvenue dans Le Secret des Warner");
        this.aGui.println("Venez decouvrir les secrets de la famille tristement celebre des Warner\n");
        this.aGui.println("Taper 'aide' si vous avez besoin d'aide.\n");
        this.aGui.print( "\n" );
        
        this.aGui.println( this.aCurrentRoom.getLongDescription() );
        if ( this.aCurrentRoom.getImageName() != null )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
    }
    
     /**
     * Cree les pieces du jeu, leurs objets et configure leurs sorties.
     */
    private void createRooms()
    {
        Room vDehors = new Room("devant le porche de la maison de Warner ", "DehorsFinalVideo.gif");
        Room vHall = new Room("dans le hall d'entree de la maison", "HallFinalVideo.gif");
        Room vSalon = new Room("dans le salon", "SalonFinalVideo.gif");
        Room vSalleAManger = new Room("dans la salle a manger", "SalleAMangerFinalVideo.gif");
        Room vCuisine = new Room("dans la cuisine", "CuisineFinalVideo.gif");
        Room vCouloir1 = new Room("dans la premiere partie du couloir principal", "Couloir1FinalVideo.gif");
        Room vCouloir2 = new Room("dans la deuxieme partir du couloir principal", "Couloir2FinalVideo.gif");
        Room vCouloir3 = new Room("dans la troisieme partie du couloir principal", "Couloir3FinalVideo.gif");
        Room vPenderie = new Room("dans la penderie", "PenderieFinalVideo.gif");
        Room vBiblio = new Room("dans la bibliotheque","BibliothequeFinalVideo.gif");
        Room vSousSol = new Room("dans le sous sol", "SousSolFinalVideo.gif");
        Room vEtage = new Room("dans le couloir de l'etage", "EtageFinalVideo.gif");
        Room vChambreParent = new Room("dans la chambre des parents", "ChambreParentFinalVideo.gif");
        Room vChambreEnfant = new Room("dans la chambre de l'enfant", "ChambreEnfantFinalVideo.gif");
        Room vSalleDeBain = new Room("dans la salle de bain","SalleDeBainFinalVideo.gif");
        Room vGrenier = new Room("dans le grenier", "GrenierFinalVideo.gif");
        Room vGrotteSecrete = new Room("dans une pièce secrete dans laquelle la famille Warner faisait ses rituels occultes. ", "GrotteFinalVideo.gif");

        //Creation d'item
        Item vLampe = new Item("lampe", "permet d'illuminer la piece", 1.5);
        Item vKeyAttic = new Item("cle", "permet d'ouvrir la porte du grenier", 0.5);
        Item vKeyBasement = new Item("cle", "permet d'ouvrir la porte du sous sol", 0.5);
        Item vCrucifix = new Item("crucifix", "ce dernier repousse les esprits", 2);
        Item vMedicament = new Item("medicament","remede contre la folie", 1);
        Item vAppareilPhoto = new Item("appareilphoto", "permet de libérer le fantome de la mère de la famille Warner", 3);
        Item vMontre = new Item("montre", "permet de libérer le fantome du père de la famille Warner", 3);
        Item vDoudou = new Item("doudou", "permet de libérer le fantome de la fille de la famille Warner", 2);
        Item vJournalIntime = new Item("journalintime", "permet de libérer le fantome du fils de la famille Warner", 2);
        Item vMagicCookie = new Item("cookie", "Une friandise magique qui augmente votre capacite de charge de 5 !", 0.2);
        Item vCarotte = new Item("Carotte","Une carotte comme les autres", 0.2);
        Beamer vGemme = new Beamer("gemme", "Une gemme imprégné d'ergie spectral qui vous permet de vous téléporter dans la pièce dans laquelle cette dernière à été chargée", 2);
        
        
        vHall.addItem(vLampe);
        vPenderie.addItem(vKeyAttic);
        vGrenier.addItem(vKeyBasement);
        vSalon.addItem(vCrucifix);
        vCuisine.addItem(vMedicament);
        vGrenier.addItem(vMedicament);
        vCuisine.addItem(vMagicCookie);
        vGrotteSecrete.addItem(vGemme);
        vBiblio.addItem(vMontre);
        vGrenier.addItem(vAppareilPhoto);
        vSalleDeBain.addItem(vDoudou);
        vSousSol.addItem(vJournalIntime);
        vSalleAManger.addItem(vCarotte);
        
        
        List<Room> aAllRooms = List.of(vHall, vSalon, vSalleAManger, vCuisine,
                                      vCouloir1, vCouloir2, vCouloir3, vPenderie, 
                                      vBiblio, vSousSol, vEtage, vChambreParent, 
                                      vChambreEnfant, vSalleDeBain);
                                  
        RoomRandomizer vRandomizer = new RoomRandomizer(aAllRooms);
        TransporterRoom vTransporterRoom = new TransporterRoom("dans une pièce magique", "TransporterRoom.png", vRandomizer);
        
        vDehors.setExits("nord", vHall); 
        vHall.setExits("nord", vCuisine);  
        vHall.setExits("est", vCouloir3);
        vHall.setExits("ouest", vSalon);     
        vHall.setExits("haut", vEtage);       
        vHall.setExits("bas", vSousSol);  
        
    
        vSalon.setExits("nord", vSalleAManger);
        vSalon.setExits("est", vHall);
        
        vSalleAManger.setExits("est", vCuisine); 
        vSalleAManger.setExits("sud", vSalon); 
        
        vCuisine.setExits("est", vCouloir1);    
        vCuisine.setExits("sud", vHall);       
        vCuisine.setExits("ouest", vSalleAManger); 
        
        vCouloir1.setExits("sud", vBiblio);
        vCouloir1.setExits("est", vCouloir2);
        vCouloir1.setExits("nord", vPenderie);
        
        vCouloir2.setExits("nord", vCouloir1);
        vCouloir2.setExits("sud", vCouloir3);
        
        vCouloir3.setExits("est", vCouloir2);
        vCouloir3.setExits("ouest", vHall);
        
        vPenderie.setExits("sud", vCouloir1);
        
        vBiblio.setExits("nord", vCouloir1);
        vBiblio.setExits("bas", vGrotteSecrete);
        
        vGrotteSecrete.setExits("ouest", vSousSol);
        
        vSousSol.setExits("haut", vHall);
        
        vEtage.setExits("nord", vSalleDeBain);  
        vEtage.setExits("ouest", vChambreEnfant); 
        vEtage.setExits("est", vChambreParent); 
        vEtage.setExits("haut", vGrenier);         
        vEtage.setExits("bas", vHall);          
        
        vChambreParent.setExits("ouest", vEtage);
        
        vChambreEnfant.setExits("est", vEtage); 
        
        vSalleDeBain.setExits("sud", vEtage);  
        
        vGrenier.setExits("bas", vEtage);  
        //Téléporteur aléatoire!
        vGrenier.setExits("ouest", vTransporterRoom);
        
        // Définir une Trap Door
        vDehors.setTrapDoor("nord", vHall); 
        //On set une trap au nord de vDehors avec vHall, impossible 
        //d'aller de vHall a vDehors apres
        vCuisine.setTrapDoor("est", vCouloir1);
        vBiblio.setTrapDoor("bas", vGrotteSecrete);
        vGrotteSecrete.setTrapDoor("ouest", vSousSol);
        
        this.aCurrentRoom = vDehors;
    }
    
     /**
     * Interprete une commande saisie par le joueur et execute l'action correspondante.
     * 
     * @param pCommandLine la commande saisie par le joueur.
     */
    public void interpretCommand( final String pCommandLine ) 
    { 
        if (this.aCommandCount > MAX_COMMANDS) 
        {
            this.aGui.println("Perduuuu loser! Vous avez dépassé le nombre maximum de commandes autorisées.");
            this.endGame();
            return; 
        }
        
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "Je ne comprend pas cette commande..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "aide" ) )
        {
            this.printHelp();
        }
        else if ( vCommandWord.equals( "aller" ) )
        {
            this.goRoom( vCommand );
            this.aCommandCount++;
            this.aGui.updateCommandCount(this.aCommandCount);
        }
        else if (vCommandWord.equals("observer"))
        {
            look();
        }
        else if (vCommandWord.equals("manger"))
        {
            eat(vCommand);
        }
        else if (vCommandWord.equals("retour")) 
        { 
            this.aCommandCount++;
            this.aGui.updateCommandCount(this.aCommandCount);
            if ( vCommand.hasSecondWord() )
                this.aGui.println("Cette commande n'a pas de second mot");
            else
                back();
        }
        else if (vCommandWord.equals("prendre")) 
        {
            if (!vCommand.hasSecondWord()) 
            {
                this.aGui.println("Prendre quoi ?");
            } 
            else 
            {
                String vItemName = vCommand.getSecondWord();
                if (this.aPlayer.takeItem(vItemName)) {
                    this.aGui.println("Vous avez pris " + vItemName + ".");
                } else {
                    this.aGui.println("Cet objet n'est pas ici ou bien vous n'avez plus de place...");
                }
            }
        } 
        else if (vCommandWord.equals("lacher")) 
        {
            if (!vCommand.hasSecondWord()) 
            {
                this.aGui.println("Lacher quoi ?");
            } 
            else 
            {
                String vItemName = vCommand.getSecondWord();
                if (this.aPlayer.dropItem(vItemName)) 
                {
                    this.aGui.println("Vous avez depose " + vItemName + ".");
                } 
                else 
                {
                    this.aGui.println("Vous ne portez pas cet objet.");
                }
            }
        } 
        else if (vCommandWord.equals("inventaire")) 
        {
            this.aGui.println(this.aPlayer.getInventoryDescription());
        }
        
        else if (vCommandWord.equals("charger")) {
            if (!vCommand.hasSecondWord()) {
                this.aGui.println("Chargez quoi ?");
            } else {
                String vItemName = vCommand.getSecondWord();
                Beamer vBeamer = this.aPlayer.getBeamerByName(vItemName);
                if (vBeamer != null) {
                    this.chargeBeamer(vBeamer, this.aPlayer.getCurrentRoom());
                    this.aGui.println("Le téléporteur " + vItemName + " a été chargé.");
                } else {
                    this.aGui.println("Cet objet n'est pas dans votre inventaire.");
                }
            }
        } 
        else if (vCommandWord.equals("tirer")) {
            if (!vCommand.hasSecondWord()) {
                this.aGui.println("Tirer quoi ?");
            } else {
                String vItemName = vCommand.getSecondWord();
                Beamer vBeamer = this.aPlayer.getBeamerByName(vItemName);
                if (vBeamer != null) {
                    this.fireBeamer(vBeamer, this.aPlayer.getCurrentRoom());
                    this.aGui.println("Le téléporteur " + vItemName + " a été activé.");
                } else {
                    this.aGui.println("Cet objet n'est pas dans votre inventaire.");
                }
            }
        }
        else if ( vCommandWord.equals( "quitter" ) ) 
            {
                if ( vCommand.hasSecondWord() )
                    this.aGui.println( "Quitter quoi?" );
                else
                    this.endGame();
            }
            else if (vCommandWord.equals("test")) 
            { 
                this.testCommandsFromFile(vCommand);
            }
    }
    
    /**
     * Affiche l'aide sur les commandes disponibles.
     */
    private void printHelp()
    {
        this.aGui.println("Vous êtes seul et effraye.\nVous êtes actuellement dans la maison des Warner" + "\n" + this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.println("\nVos mots de commandes sont :\n" + this.aParser.getCommandString() );
        this.aGui.println("\nMais faites attention, vous avez déja utilisé " + getCommandCount() + "/75");
    }
    
    /**
     * Deplace le joueur vers une autre salle dans la direction indiquee.
     * 
     * @param pC la commande contenant la direction.
     */
    private void goRoom(final Command pC) 
    {
        if (!pC.hasSecondWord()) {
            this.aGui.println("aller ou ?");
            return;
        }
    
        String vDirection = pC.getSecondWord();
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
    
        if (vNextRoom == null) {
            this.aGui.println("Pas de porte de ce côte-la !");
            return;
        }
    
        if (vNextRoom instanceof TransporterRoom) // salle instance de la classe TransporterRoom ?
        {
            this.aGui.println("Vous entrez dans une pièce magique et vous êtes téléporté ailleurs !");
            vNextRoom = ((TransporterRoom) vNextRoom).getExit(vDirection); //pour acceder aux methodes de TransporterRoom
        }
        
        this.aPlayer.setCurrentRoom(vNextRoom);
    
        // Afficher la description de la nouvelle salle
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
        
        checkWinCondition();
    }


    /**
     * Affiche la decription de la salle actuelle ainsi que ses sorties.
     */
    private void printLocationInfo()
    {
        this.aGui.println(this.aCurrentRoom.getDescription());
        this.aGui.println("Les sorties : ");
        this.aGui.println(this.aCurrentRoom.getExitString());
        this.aGui.println("");
    }
    
    /**
     * Affiche la description de la salle actuelle.
     */
    private void look()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Permet au joueur de manger un Item specifique
     * 
     * @param pCommand la commande contenant le nom de l'objet a manger
     */
    private void eat(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Manger quoi ?");
            return;
        }
    

        String vItemName = pCommand.getSecondWord();
    

        if (!this.aPlayer.getInventory().hasItem(vItemName)) {
            this.aGui.println("Vous ne transportez pas cet objet !");
            return;
        }
    

        Item vItem = this.aPlayer.getInventory().removeItem(vItemName);
        if (vItem == null) 
        {
            this.aGui.println("Erreur : impossible de retirer l'objet de l'inventaire.");
            return;
        }
        
        double vNewCurrentWeight = this.aPlayer.getCurrentWeight() - vItem.getItemWeight();
        this.aPlayer.setCurrentWeight(vNewCurrentWeight);
        
        if ("cookie".equals(vItemName)) 
        {
            double vNewMaxWeight = this.aPlayer.getMaxWeight() + 5;
            this.aPlayer.setMaxWeight(vNewMaxWeight);
            this.aGui.println("Vous avez mange un cookie magique ! Votre capacite de charge est maintenant de : " + this.aPlayer.getMaxWeight());
        } 
        else 
        {
            this.aGui.println("Vous avez mange " + vItem.toString() + " !");
        }
    }

    /**
     * Permet au joueur de revenir a la salle precedente.
     */
    private void back() 
    {
        if (!this.aPlayer.canGoBack()) {
            this.aGui.println("Vous ne pouvez pas retourner en arrière.");
            return;
        }
    
        Room vPreviousRoom = this.aPlayer.getPreviousRoom();
    
        // Vérifier si la salle précédente est une sortie valide
        if (!this.aPlayer.getCurrentRoom().isExit(vPreviousRoom)) {
            this.aGui.println("Impossible de revenir en arrière à travers une Trap Door !");
            return;
        }
    
        // Effectuer le retour en arrière
        this.aPlayer.goBack();
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
    }

    /**
     * Lit et execute toutes les commandes d'un fichier texte.
     * 
     * @param pCommand La commande contenant le nom du fichier (sans extension).
     */
    private void testCommandsFromFile(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Specifiez le nom du fichier a tester.");
            return;
        }

        String vFileName = pCommand.getSecondWord() + ".txt"; 
        
        // lire le fichier ligne par ligne
        try (BufferedReader vBr = new BufferedReader(new FileReader(vFileName))) // bufferedreader pour lire ligne par ligne
        {
            String vLine;
            this.aGui.println("Executer des commandes a partir d'un fichier : " + vFileName);
            setTestMode(true); // mode test activé
            
            while ((vLine = vBr.readLine()) != null) // methode pour lire une ligne du fichier tant il y a une ligne 
            { 
                this.aGui.println(">> " + vLine);
                this.interpretCommand(vLine);
            }
        } catch (IOException e) // gérer les exceptions
        {
            this.aGui.println("Erreur pour lire le fichier : " + e.getMessage());
        }
    }
    
    /**
     * Permet d'obtenir le nombre de commande déja consommer par le joueur avant sa défaite
     * 
     * @return le nombre de commande entré par le joueur.
     */
    public int getCommandCount()
    {
        return this.aCommandCount;
    }
    
    /**
     * Termine le jeu en affichant un message d'adieu et en bloquant les commandes
     */
    private void endGame()
    {
        this.aGui.println( "Merci d'avoir joué au Secret Des Warner, à bientôt." );
        this.aGui.enable( false );
    }

    /**
     * Charge un téléporteur (Beamer) avec la pièce actuelle.
     * Si le téléporteur est présent dans l'inventaire du joueur, il est chargé avec la pièce actuelle.
     * Sinon, un message indique que le téléporteur est absent de l'inventaire.
     *
     * @param pBeamer Le téléporteur à charger.
     * @param pCurrentRoom La pièce actuelle à utiliser pour charger le téléporteur.
     */
    public void chargeBeamer(Beamer pBeamer, Room pCurrentRoom) {
        if (this.aPlayer.getInventory().hasItem(pBeamer.getItemName())) {
            pBeamer.charge(pCurrentRoom); 
            this.aGui.println("Le téléporteur a été chargé avec la pièce actuelle.");
        } else {
            this.aGui.println("Vous n'avez pas ce téléporteur dans votre inventaire.");
        }
    }
    
    /**
     * Utilise un téléporteur (Beamer) pour téléporter le joueur.
     * Si le téléporteur n'est pas chargé, un message est affiché pour indiquer qu'il doit être chargé.
     * Si le téléporteur est chargé, il est activé pour téléporter le joueur dans la pièce cible.
     * Après la téléportation, le téléporteur est retiré de l'inventaire du joueur.
     *
     * @param pBeamer Le téléporteur à utiliser.
     * @param pCurrentRoom La pièce actuelle du joueur avant la téléportation.
     */
    public void fireBeamer(Beamer pBeamer, Room pCurrentRoom) 
    {
        if (!pBeamer.isCharged()) {
            this.aGui.println("Le téléporteur n'est pas chargé. Chargez-le avant de l'utiliser.");
            return;
        }
    
        Room vTargetRoom = pBeamer.fire(pCurrentRoom); 
        if (vTargetRoom != null) {
            this.aPlayer.setCurrentRoom(vTargetRoom); 
            this.aCurrentRoom = vTargetRoom; 
    
            this.aGui.println("Vous avez été téléporté à : " + vTargetRoom.getLongDescription());
            Item vRemovedItem = this.aPlayer.getInventory().removeItem(pBeamer.getItemName());
            this.aPlayer.setCurrentWeight(this.aPlayer.getCurrentWeight() - vRemovedItem.getItemWeight());
            if (vTargetRoom.getImageName() != null) {
                this.aGui.showImage(vTargetRoom.getImageName());
            }
        } else {
            this.aGui.println("Échec de la téléportation. Essayez de recharger le téléporteur.");
        }
    }
    
    /**
     * Active le mode test pour utiliser la commande alea qui ne fonctionne que dans ce mode
     * @param pIsTestMode test pour savoir si on est dans le test ou pas
     */
    public void setTestMode(boolean pIsTestMode) 
    {
        this.isTestMode = pIsTestMode;
        this.aGui.println("Mode test " + (pIsTestMode ? "activé." : "désactivé."));
    }
    
    /**
     * Vérifie si le joueur a gagné le jeu.
     * Les conditions pour gagner sont :
     * - Le joueur doit être dans la salle "grotte secrete".
     * - Le joueur doit posséder les objets "montre", "appareilphoto", "JournalIntime", et "doudou".
     * Si toutes les conditions sont remplies, le jeu se termine avec un message de victoire.
     */
    private void checkWinCondition() {
        if (this.aPlayer.getCurrentRoom().getDescription().contains("pièce secrete")) {
            
            if (this.aPlayer.getInventory().hasItem("montre") && this.aPlayer.getInventory().hasItem("journalintime") && this.aPlayer.getInventory().hasItem("doudou") && this.aPlayer.getInventory().hasItem("appareilphoto")) {
                this.aGui.println("Félicitations ! Vous avez découvert les secrets des Warner et libéré les âmes tourmentées.");
                this.endGame();
            }
        }
    }

}
