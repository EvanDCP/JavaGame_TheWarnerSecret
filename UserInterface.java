import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a 
 * text entry area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2023)
 */
public class UserInterface implements ActionListener
{
    /** 
     * Le moteur principal du jeu qui gère la logique et les interactions. 
     */
    private GameEngine aEngine;
    
    /** 
     * La fenêtre principale (JFrame) de l'interface utilisateur graphique du jeu. 
     */
    private JFrame aMyFrame;
    
    /** 
     * Le champ de saisie utilisé pour entrer les commandes texte dans le jeu. 
     */
    private JTextField aEntryField;
    
    /** 
     * La zone de texte utilisée pour afficher les messages de journal (log) ou la narration du jeu. 
     */
    private JTextArea aLog;
    
    /** 
     * Le label qui affiche une image représentant l'état ou l'environnement actuel du jeu. 
     */
    private JLabel aImage;
    
    /** 
     * Le label qui affiche le compteur des commandes exécutées par le joueur. 
     */
    private JLabel aCommandCounterLabel;
    
    /** 
     * Le panneau qui contient les éléments graphiques associés à l'affichage de l'image du jeu. 
     */
    private JPanel aImagePanel;
    
    /** 
     * Le bouton permettant au joueur d'examiner ou d'observer l'environnement actuel (commande "look"). 
     */
    private JButton aLookButton;
    
    /** 
     * Le bouton permettant au joueur de ramasser un objet (commande "take"). 
     */
    private JButton aTakeButton;
    
    /** 
     * Le bouton permettant au joueur de quitter le jeu (commande "quit"). 
     */
    private JButton aQuitButton;
    
    /** 
     * Le bouton permettant au joueur de revenir en arrière dans le jeu (commande "back"). 
     */
    private JButton aBackButton;
    
    /** 
     * Le bouton permettant au joueur d'afficher l'inventaire actuel (commande "inventory"). 
     */
    private JButton aInventoryButton;
    
    /** 
     * Le bouton permettant au joueur d'afficher l'aide pour les commandes disponibles (commande "help"). 
     */
    private JButton aHelpButton;
    
    /** 
     * Le bouton permettant de déplacer le joueur vers le nord (commande "go north"). 
     */
    private JButton aNorthButton;
    
    /** 
     * Le bouton permettant de déplacer le joueur vers le sud (commande "go south"). 
     */
    private JButton aSouthButton;
    
    /** 
     * Le bouton permettant de déplacer le joueur vers l'est (commande "go east"). 
     */
    private JButton aEastButton;
    
    /** 
     * Le bouton permettant de déplacer le joueur vers l'ouest (commande "go west"). 
     */
    private JButton aWestButton;
    
    /** 
     * Le bouton permettant de déplacer le joueur vers le haut (commande "go up"). 
     */
    private JButton aUpButton;
    
    /** 
     * Le bouton permettant de déplacer le joueur vers le bas (commande "go down"). 
     */
    private JButton aDownButton;

    
    /**
     * Constructeur de la classe UserInterface.
     * Initialise l'interface graphique et lie un moteur de jeu a l'interface.
     * 
     * @param pGameEngine L'objet GameEngine qui gere la logique du jeu.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface()

    /**
     * Affiche un texte dans la zone de texte de l'interface.
     * 
     * @param pText Le texte a afficher dans la zone de texte.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print()

    /**
     * Affiche un texte dans la zone de texte, suivi d'un saut de ligne.
     * 
     * @param pText Le texte a afficher, suivi d'un saut de ligne.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println()

    /**
     * Affiche une image dans l'interface graphique.
     * L'image est chargee a partir du repertoire "Images/".
     * 
     * @param pImageName Le nom de l'image a afficher.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "GIF/" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage()

    /**
     * Active ou desactive la possibilite d'entrer du texte dans le champ de saisie.
     * 
     * @param pOnOff Si vrai, le champ de saisie est active. Si faux, il est desactive.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
        
        JButton[] buttons = {
        aLookButton, aTakeButton, aQuitButton, aBackButton, aInventoryButton, aHelpButton,
        aNorthButton, aSouthButton, aEastButton, aWestButton, aUpButton, aDownButton
        };
    
        for (JButton button : buttons) {
            if (button != null) {
                button.setEnabled(pOnOff);
            }
        }
    } // enable()

    /**
     * Cree et configure l'interface graphique.
     * Initialise les composants graphiques et les arrange dans la fenêtre.
     */
    private void createGUI()
    {
        // Créer la fenêtre principale
        this.aMyFrame = new JFrame("Le Secret des Warner");
        this.aEntryField = new JTextField(34);
    
        // Zone de texte non éditable avec défilement
        this.aLog = new JTextArea();
        this.aLog.setEditable(false);
        JScrollPane vListScroller = new JScrollPane(this.aLog);
        vListScroller.setPreferredSize(new Dimension(200, 200));
        vListScroller.setMinimumSize(new Dimension(100, 100));
    

        this.aImage = new JLabel();
        this.aImage.setHorizontalAlignment(SwingConstants.CENTER);
    
        this.aCommandCounterLabel = new JLabel("Nombre de mouvements : 0 / 75");
        this.aCommandCounterLabel.setForeground(Color.RED); 
        this.aCommandCounterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.aCommandCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);
    

        JLayeredPane vLayeredPane = new JLayeredPane();
        vLayeredPane.setPreferredSize(new Dimension(700, 500));
    
        this.aImage.setBounds(0, 0, 700, 500); 
        this.aCommandCounterLabel.setBounds(0, 10, 400, 30); 
    
        vLayeredPane.add(this.aImage, Integer.valueOf(0)); 
        vLayeredPane.add(this.aCommandCounterLabel, Integer.valueOf(1));
    
        JPanel aImagePanel = new JPanel(new BorderLayout());
        aImagePanel.add(vLayeredPane, BorderLayout.CENTER);

        this.aNorthButton = createColoredButton("Nord", Color.GRAY);
        this.aSouthButton = createColoredButton("Sud", Color.GRAY);
        this.aEastButton = createColoredButton("Est", Color.GRAY);
        this.aWestButton = createColoredButton("Ouest", Color.GRAY);
        this.aUpButton = createColoredButton("Haut", Color.GRAY);
        this.aDownButton = createColoredButton("Bas", Color.GRAY);

        this.aBackButton = createColoredButton("Retour", Color.GRAY);
        this.aHelpButton = createColoredButton("Aide", Color.GRAY);

        this.aLookButton = createColoredButton("Observer", Color.GRAY);
        this.aInventoryButton = createColoredButton("Inventaire", Color.GRAY);
        this.aQuitButton = createColoredButton("Quitter", Color.BLACK);
        this.aQuitButton.setForeground(Color.WHITE);        
        
        JPanel vDirectionPanelEast = new JPanel(new GridLayout(3, 3, 3, 3));
        JPanel vDirectionPanelWest = new JPanel(new GridLayout(3, 3, 0, 0));

        vDirectionPanelEast.add(this.aBackButton);
        vDirectionPanelEast.add(this.aNorthButton);
        vDirectionPanelEast.add(this.aUpButton);
        vDirectionPanelEast.add(this.aWestButton);
        vDirectionPanelEast.add(new JLabel(""));
        vDirectionPanelEast.add(this.aEastButton);
        vDirectionPanelEast.add(this.aHelpButton);
        vDirectionPanelEast.add(this.aSouthButton);
        vDirectionPanelEast.add(this.aDownButton);

        vDirectionPanelWest.add(this.aLookButton);
        vDirectionPanelWest.add(this.aInventoryButton);
        vDirectionPanelWest.add(this.aQuitButton);
        
        JPanel vPanel = new JPanel(new BorderLayout());
        vPanel.add(aImagePanel, BorderLayout.NORTH);       // Panneau avec image et compteur
        vPanel.add(vListScroller, BorderLayout.CENTER);    // Zone de texte
        vPanel.add(this.aEntryField, BorderLayout.SOUTH);  // Champ de saisie
        vPanel.add(vDirectionPanelWest, BorderLayout.WEST);   
        vPanel.add(vDirectionPanelEast, BorderLayout.EAST);    
    
        this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);
    
        this.aEntryField.addActionListener(this);
    
        this.aMyFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent pE) {
                System.exit(0);
            }
        });
    
        this.aMyFrame.pack();
        this.aMyFrame.setVisible(true);
        this.aEntryField.requestFocus();
    }

    /**
     * Crée un bouton avec un texte spécifié et une couleur d'arrière-plan donnée.
     * Le bouton est également configuré pour écouter les événements d'action
     * en utilisant la classe courante comme gestionnaire d'événements.
     * 
     * @param text  Le texte à afficher sur le bouton.
     * @param color La couleur d'arrière-plan du bouton.
     * @return Un objet {@code JButton} configuré avec le texte, la couleur et un
     *         gestionnaire d'événements.
     */
    private JButton createColoredButton(String text, Color color) 
    {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.addActionListener(this);
        return button;
    }

    /**
     * Interface de l'action a effectuer lors d'une entree dans le champ de texte.
     * Cette methode est appelee lorsqu'une action se produit (par exemple, un bouton est clique ou du texte est saisi).
     * 
     * @param pE L'evenement d'action (par exemple, l'action de saisir du texte ou de cliquer sur un bouton).
     */
    @Override 
    public void actionPerformed( final ActionEvent pE ) 
    {
        if(pE.getSource() == this.aLookButton){
            this.aEngine.interpretCommand("observer");
        }else if(pE.getSource() == this.aQuitButton){
            this.aEngine.interpretCommand("quitter");
        }else if(pE.getSource() == this.aBackButton){
            this.aEngine.interpretCommand("retour");
        }else if(pE.getSource() == this.aInventoryButton){
            this.aEngine.interpretCommand("inventaire");
        }else if (pE.getSource() == this.aHelpButton) {
            this.aEngine.interpretCommand("aide");
        }else if (pE.getSource() == this.aNorthButton) {
            this.aEngine.interpretCommand("aller nord");
        } else if (pE.getSource() == this.aSouthButton) {
            this.aEngine.interpretCommand("aller sud");
        } else if (pE.getSource() == this.aEastButton) {
            this.aEngine.interpretCommand("aller est");
        } else if (pE.getSource() == this.aWestButton) {
            this.aEngine.interpretCommand("aller ouest");
        } else if (pE.getSource() == this.aUpButton) {
            this.aEngine.interpretCommand("aller haut");
        } else if (pE.getSource() == this.aDownButton) {
            this.aEngine.interpretCommand("aller bas");
        }
        else
        {
            this.processCommand();
        }
    } // actionPerformed()

    /**
     * Met à jour l'affichage du compteur de commandes.
     * 
     * @param pCommandCount Le nombre actuel de commandes.
     */
    public void updateCommandCount(final int pCommandCount) 
    {
        this.aCommandCounterLabel.setText("Nombre de mouvements : " + pCommandCount + " / 75");
    }
    
    /**
     * Un command a ete entre dans le champ de saisie.
     * Lire la commande et effectuer les actions necessaires pour la traiter.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
