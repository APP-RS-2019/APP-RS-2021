# Utilisation du Package

## MAVEN

### Pour du développement
ApplicationJavaPack contient l'application en projet Maven. L'ouvrir avec Java, en double cliquant dessus, lance l'application.

### Pour une utilisation rapide
L'ouvrir avec WinRar (ou autre explorateur de fichier) permet de récuperer tous le fichiers nécessaires à la construction du projet Maven.

## PIP

Taper dans Anaconda Prompt :

pip install -i https://test.pypi.org/simple/ example-pkg-Anthoicl

(Sinon ça : python -m pip install --index-url https://test.pypi.org/simple/ --no-deps example-pkg-Anthoicl)

Puis : 

python
import example_pkg

Vous devriez voir :
[SRV]:Le serveur tourne sur le port : 1933
[SRV]:Connexion en attente.
[TX]:Vous pouvez envoyer des messages.
[TX]:Veuillez entrer le destinataire
