# Application Java

L’objectif pour la partie logicielle dans le cadre du projet de gestion de flotte de robot est de
permettre à un utilisateur de pouvoir organiser facilement un scénario d’actions avec plusieurs robots.
Vous retrouverez dans cette branche du projet, l'ensemble des classes permettant de faire fonctionner le code Java.

# Architecture

Le diagramme UML ci-dessous vous permettra de mieux comprendre l'architecture logiciel qui a était mis en place.
L'illustration possède l'ensemble des classes associées aux modèles (mais pas celles associées à l'interface graphique) avec leurs
attributs.

![Diagramme UML](/Application_Java/Illustrations_doc/Architecture.jpg)

# Interface graphique

![Diagramme UML](/Application_Java/Illustrations_doc/interface1.png)
![Diagramme UML](/Application_Java/Illustrations_doc/interface2.png)

# Améliorations possibles

Les améliorations qui peuvent être apportées au projet sont les suivantes :

- Uniformisation des ordres pour chaque robot. Les ordres ne découlent pas des mêmes
méthodes et il serait intéressant d’optimiser le programme pour qu’un même ordre donné
pour chaque robot implique la même réponse(actuellement, il existe plusieurs méthodes
“avancer” pour chacun des robots disponibles).

- Meilleure gestion du temps sur les scénarios : actuellement, les scénarios permettent de
donner une suite d’actions à faire à un robot, espacées de différentes durées. Cependant
nous n’avons pas géré la durée des actions en question. Ainsi, si une action doit être lancée
avant que la précédente soit terminée, elle sera retardée jusqu’à ce que l’action précédente
finisse.

- Amélioration des possibilités de l’application: N’ayant pas pu réaliser de tests avec les vrais
robots, certains bugs n’ont probablement pas été décelés. De plus, d’autres fonctionnalités
pourraient être intéressante à mettre en place, en utilisant mieux les capteurs des différents
robots et en incluant un retour des robots vers l’application.

- Affichage du déplacement des robots : Il faudrait ajouter à l’application une fenêtre
représentant les robots sur un plan vu de dessus. On pourrait ainsi suivre leur déplacements
et actions au cours du temps.
