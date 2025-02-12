# README

### Projet réalisé par Evan Da Costa Pina

## Description des fichiers

Ce projet de jeu java contient 13 classes :

- **Game** : Le moteur du jeu, Gere la creation des pieces, le traitement des commandes et la progression du jeu.
- **GameEngine** : gere la logique principale du jeu d'aventure "The Warner Secret". Elle est responsable de la gestion des pieces, des commandes du joueur, et de l'interaction avec l'interface utilisateur.
- **UserInterface** : Cette classe implémente une interface graphique simple avec une zone pour entrer du texte, des images, des boutons et des massages de sorties
- **Room** : Cette classe represente un lieu dans le jeu "Le secret des Warner". Une piece peut avoir des sorties vers d'autres pieces, une description, une image associee, et contenir des objets.
- **Player** : Représente un joueur dans le jeu.
- **Item** : Represente un objet dans le jeu avec une description et un poids. Cette classe permet de decrire des objets que le joueur peut manipuler.
- **ItemList** : Classe représentant une collection d'objets (items). Permet de gérer les objets ajoutés, retirés ou consultés.
- **Command** : Classe Command - une commande du jeu "Le secret des Warner".
Une commande est constituee d'un mot d'ordre (command word) et eventuellement
d'un second mot.
- **CommandWords** : Cette classe contient une table d'énumération de tous les mots de commande connus du jeu. Elle est utilisé pour reconnaître les commandes au fur et à mesure de leur saisie.
- **Parser** : Cette classe Parser prend les entrées de l'utilisateur et essaie de les interpréter si les commandes sont valides. Chaque fois qu'il est appelé, il prend une ligne sous forme de chaîne et
essaie d'interpréter la ligne comme une commande de deux mots. Il renvoie la commande en tant qu'objet de classe Command. L'analyseur dispose d'un ensemble de mots de commande connus. Il vérifie les entrées de l'utilisateur par rapport
aux commandes connues, et si l'entrée ne fait pas partie des commandes connues elle renvoie un objet de commande marqué comme commande inconnue.
- **TransporterRoom** : La classe TransporterRoom représente une pièce spéciale dans le jeu. Contrairement aux autres pièces, les sorties de cette pièce ne sont pas fixes. Chaque fois qu'un joueur tente de sortir de cette pièce, il est téléporté vers une pièce aléatoire.
- **RoomRandomizer** : La classe RoomRandomizer est responsable de sélectionner aléatoirement une pièce parmi une liste de pièces disponibles. Cette classe est utilisée pour des fonctionnalités comme les téléporteurs, où une pièce aléatoire doit être choisie sans dépendre d'une direction spécifique.
- **Beamer** : La classe Beamer représente un téléporteur utilisable dans le jeu. Un téléporteur peut être chargé avec une pièce spécifique, puis utilisé pour téléporter le joueur directement vers cette pièce. Si le téléporteur n'est pas chargé, il ne peut pas être utilisé.
Cette classe hérite de {@code Item} et peut donc être manipulée comme un objet
de l'inventaire du joueur.

---

## Exécution :

Pour le jeu "Le Secret Des Warner" il suffit de lancer le package BlueJ avec BlueJ puis de faire un clic droit sur la classe "Game" et de faire New Game.

---

## Comment jouer : 

Une fois le jeux lancé vous pourrez vous déplacer grâce aux boutons de déplacements (nord, sud, ouest, est, haut, bas), il est également conseillé de taper la commande "help" dans le chat infin de connaitre les commande disponible en jeu. Dans ce jeu vous avez la possibilité de manger, prendre des objets, jeter ces derniers, faire des retours en arrière, regarder, utiliser des objet, perdre et même gagner si vous en êtes capable :) 

---

## Auteur
Projet de création d'un jeu textuel, très amusant et passionnant à réaliser, les possibilités d'amélioration semblent infinis!
