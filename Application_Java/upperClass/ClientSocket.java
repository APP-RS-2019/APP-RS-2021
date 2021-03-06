package upperClass;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;

import org.json.*;

import graphique.Frame;

public class ClientSocket {
	Socket soc;

	private BufferedOutputStream bos;
	private BufferedInputStream bis;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private byte[] b;
	public boolean open;
	private OutputStream outStreamToServer;
	
	private String name;
	private String ip;
	private int port;

	public ClientSocket(String ip, int port, String name) {
		this.name = name;
		this.ip = ip;
		this.port = port;
	}


	public void connect(String name){
		try {
			this.soc = new Socket(this.ip, this.port); 
			
			// init des buffers pour la comm serveur
			this.bis = new BufferedInputStream(soc.getInputStream());
			this.bos = new BufferedOutputStream(soc.getOutputStream());
			this.inFromServer = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
			this.outToServer = new DataOutputStream(soc.getOutputStream());
			this.b = b;

			byte[] b = this.name.getBytes();
			outToServer.write(b, 0, this.name.length()); // utiliser writeBytes
			outToServer.flush();

			this.open = true;
			
			Syst.getThreadReception().start();
			Frame.getLogger().ajouterLigne(ZonedDateTime.now()+" :  conexion au serveur réussie\n");
		} catch (Exception e) {
			System.err.println("Erreur de connexion");
			Frame.getLogger().ajouterLigne(ZonedDateTime.now()+" :  conexion au serveur échouée\n");
		}
	} 
	
	public void disconnect() {
		try {
			soc.close();
			Syst.getThreadReception().fermer();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// this.sentence = inFromServer.readLine();
		System.err.println("vous avez bien été déconnecté");
		this.open = false;
	}
	
	public void sendOrder (String destRobot, String order){
		String jsonOrder = this.formatJson(destRobot, order);
		this.send(jsonOrder);
	}
	
	public String formatJson(String destRobot, String order) {
		JSONObject jsonobject=new JSONObject();
		JSONObject robots = new JSONObject();
		JSONArray tab = new JSONArray();
		
		jsonobject.put("name", destRobot);
		jsonobject.put("order", order);
		tab.put(jsonobject);
		robots.put("robot", tab);
		String jsonformate=robots.toString();
	return jsonformate;
}


	public void send(String msg){
		b=msg.getBytes();
		try {
			outToServer.write(b,0,msg.length());
			//this.outStreamToServer.flush();
			outToServer.flush();
			Frame.getLogger().ajouterLigne(ZonedDateTime.now() + " :  message envoyé : " + msg + "\n" );
		} catch (IOException e) {
			Frame.getLogger().ajouterLigne(ZonedDateTime.now() + " :  échec d'envoie d'un message\n" );
			System.err.println("echec d'envoie");
		}
	}
	
	public String reciev(){
		try{
			String sentence = inFromServer.readLine();
			Frame.getLogger().ajouterLigne(ZonedDateTime.now() + " :  message reçu : " + sentence + "\n" );
			return sentence;
		}
		catch(Exception e){
			this.disconnect();
			Frame.getLogger().ajouterLigne(ZonedDateTime.now() + " :  une erreur est survenue vous avez ete deconnecte\n" );
			return "error recep";
		}
	}
	
	// getters
	public boolean isOpen() {
		return open;
	}

	public String getIp() {
		return this.ip;
	}

	public int getPort() {
		return this.port;
	}

	// setter pour la comm serveur
	public void setIp(String ip) throws Exception {
		this.ip = ip;
		if (this.open) {
			this.disconnect();
		}
		Frame.getLogger().ajouterLigne(ZonedDateTime.now() + " :  ip serveur modifié à : " + this.ip + "\n" );
	}

	public void setPort(int port) throws Exception {
		this.port = port;
		if(this.open) {
			this.disconnect();
		}
		Frame.getLogger().ajouterLigne(ZonedDateTime.now() + " :  port serveur modifié à : " + this.port + "\n" );
	}
}
