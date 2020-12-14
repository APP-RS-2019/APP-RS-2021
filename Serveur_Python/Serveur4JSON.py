# -*- coding: utf-8 -*-
"""
Created on Thu Apr 11 12:26:46 2019

@author: anthoicl
"""

import socket
from threading import Thread
import sys
import json

port=int(input("[INIT]Sur quel port voulez vous faire tourner le Serveur?\n"))
host=''
listeClient={}
global testFinServeur
testFinServeur=False

class ThreadClient(Thread):
   
    def __init__(self,connexion):
        Thread.__init__(self)
        self.nom = "noname"
        self.connexion = connexion
        self.testFinClient=False
       
    def run(self):
        global testFinServeur
        global listeClient
        
        print("[SRV]:Connexion etablie.")

        self.nom = connexion.recv(1024).decode()
        print("[SRV]:Client "+ self.nom + " connecte")
        listeClient[self.nom]=connexion
        
        while ((testFinServeur == False) and ( self.testFinClient == False )) :
            try:
                message_recu=self.connexion.recv(1024).decode()
                x=json.loads(message_recu)
                print("[RX]:",x)
                for i in range (len(x["robot"])):
                    y=x["robot"][i]["name"]
                    z=x["robot"][i]["order"]
                    if y=="AppliTest" or y=="Robotino": #Si d'autres Robots tourne en language Java, les rajouter ici
                        listeClient[y].sendall((z+"\n").encode())
                    else:
                        listeClient[y].sendall((z).encode())
            except:
                  pass
           
    def stop(self):
        global testFinServeur
        testFinServeur = True
        for key,value in listeClient.items():
            value.close()
        mySocket.close()
       
    def stopClient(self):
        self.testFinClient=True
        
class ThreadEmission(Thread):
   
    def __init__(self):
        Thread.__init__(self)
        print("[TX]:Vous pouvez envoyer des messages.")
       
       
    def run(self):
        global testFinServeur
        while testFinServeur==False:
            destinataire = input("[TX]:Veuillez entrer le destinataire \n")
            message_emis = input("[TX]:Veuillez entrer le message à envoyer à "+destinataire +" \n")
            #message_emis = message_emis.encode()
            if message_emis == "fin" and destinataire == "Serveur":
                print("[SRV]:Arret du serveur")
                self.stop()
            else:
                try:
                    if destinataire=="Application" or destinataire=="Robotino": #Si d'autres Robots tourne en language Java, les rajouter ici
                        listeClient[destinataire].sendall((message_emis+"\n").encode())
                    else:
                        listeClient[destinataire].sendall((message_emis).encode()) 
                except:
                    print("[TX]:Le destinaire n'existe pas ou n'est pas connecte")
   
    def stop(self):
        global testFinServeur
        testFinServeur=True
        for key,value in listeClient.items():
            value.close()
        mySocket.close()   
       
       
           
   
           

# GERER LA CONNEXION/DECO DES GENS
# GERER FERMETURE PROPRE THREAD/SOCKET ET SERVEUR
           
mySocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    mySocket.bind((host,port))
    print("[SRV]:Le serveur tourne sur le port : "+str(port))
except socket.error:
    print("[SRV]:La connexion a echoue.")
    sys.exit()
print("[SRV]:Connexion en attente.")
mySocket.listen(5)
te = ThreadEmission()
te.start()

while testFinServeur == False:
    try:
        connexion, adresse = mySocket.accept()
        th = ThreadClient(connexion)
        th.start()  
        
    except:
        pass
