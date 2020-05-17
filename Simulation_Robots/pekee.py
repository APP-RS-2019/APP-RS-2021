# -*- coding: utf-8 -*-
"""
Created on Tue Apr 23 11:39:41 2019
@author: beaurezm
"""

import socket
import select
import sys
import json
import time

from threading import Thread

class Msgrecu(Thread):
    def __init__(self):
        Thread.__init__(self)
        self.msg_recu=""
        self.test=False

    def run(self):
        self.test=True
        while self.test :
            try:
                self.msg_recu=client.recv(1024)
                self.msg_recu=self.msg_recu.decode()
                print(self.msg_recu)
            except :
                pass


    def getMsg_recu(self):
        return self.msg_recu
    
    def resetMsg_recu(self):
        self.Msg_recu=""

    def close(self):
        self.test=False
        Thread.stop()

    
class Msgenvoyer(Thread):
    def __init__(self):
        Thread.__init__(self)
        self.test=False
    
    def run(self):
        self.test=True
        while self.test:
            time.sleep(1)
            dest=input("destinataire?")
            ordre=input("ordre?")
            x={"robot":[{"name":dest,"order" : ordre}]}
            y=json.dumps(x)
            try: 
                y=y.encode()
                client.send(y)
            except :
                print("co perdu")
                test=False
                
    def close(self):
        self.test=False
        Thread.stop()
                

class MsgRE(Thread):
    def __init__(self):
        Thread.__init__(self)
        self.thread_test1=Msgrecu()
        self.thread_test2=Msgenvoyer()
        self.test=False
        
     
    def run (self):
        self.test=True
        self.thread_test1.start()
        self.thread_test2.start()
        while self.test:
            pass
            
    def close(self):
        self.thread_test1.close()
        self.thread_test2.close()

def avance(a):
    return "j'avance de "+a+" m"

def recule():
    return "je recule"

def splitOrder(ordre):
    tab1=ordre.split("-")
    action=tab1[0]
    if len(tab1)>1:
        param=tab1[1].split("_")
    else:
        param = ""
    return action,param



#création du client serveur
hote='25.100.142.23'
port=1933
client=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    client.connect((hote,port))
    print("connecte")
    time.sleep(1)
    client.send("Pekee".encode())
    print("ok1")
    thread_1=MsgRE()
    print("ok2")
    thread_1.start() 
    msg=""       
    while msg!="fin" :
        msg=thread_1.thread_test1.getMsg_recu()
        if msg=="fin":
            thread_1.close()
        action,param=splitOrder(msg)
        if action == "avance" and len(param)==1:
            print(avance(param[0]))
        elif action == "recule":
            print(recule())
        elif msg=="fin":
            thread_1.close()
        thread_1.thread_test1.resetMsg_recu()
    thread_1.join()
    print("ok3")
    
   
except: 
    print("error")
        
#def fullName(name = "noName", family = "noFamily"):
#    return name + family
#
#functionList = {'fullName': fullName}
#
#function = 'fullName'
#parameters = {'name': 'Foo', 'family': 'Bar'}
#
#print functionList[function](**parameters)
## prints FooBar
#
#parameters = {'name': 'Foo'}
#print functionList[function](**parameters)
## prints FoonoFamily
