# Utilisation du Package

## MAVEN

### Pour du développement
ApplicationJavaPack contient l'application en projet Maven. L'ouvrir avec Java, en double cliquant dessus, lance l'application.

### Pour une utilisation rapide
L'ouvrir avec WinRar (ou autre explorateur de fichier) permet de récuperer tous le fichiers nécessaires à la construction du projet Maven.

## PIP

Taper dans Anaconda Prompt :

pip install -i https://test.pypi.org/simple/ Serveur-pkg-APPRS2021

(Sinon ça : python -m pip install --index-url https://test.pypi.org/simple/ --no-deps Serveur-pkg-APPRS2021)

Puis : 

python

import Serveur

Vous devriez voir :
[SRV]:Le serveur tourne sur le port : 1933
[SRV]:Connexion en attente.
[TX]:Vous pouvez envoyer des messages.
[TX]:Veuillez entrer le destinataire
