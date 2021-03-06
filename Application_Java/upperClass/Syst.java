package upperClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import robot.Fleet;
import robot.Robot;

public class Syst implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Fleet> fleets;
	private static ClientSocket clientsocket;
	private static ThreadReception thrd_rcp;
	
	public Syst() {
		Syst.fleets = new ArrayList<Fleet> ();
		Syst.clientsocket = new ClientSocket("192.168.56.1",1933,"Application");
		Syst.thrd_rcp=new ThreadReception(Syst.clientsocket);
	}

	public static ArrayList<Fleet> getFleets() {
		return fleets;
	}

	public static void setFleets(ArrayList<Fleet> fleets) {
		Syst.fleets = fleets;
	}

	public static ClientSocket getClientsocket() {
		return clientsocket;
	}

	public static void setClientsocket(ClientSocket clientsocket) {
		Syst.clientsocket = clientsocket;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static ThreadReception getThreadReception() {
		return thrd_rcp;
	}
	
	public void addFleet(Fleet fleet) {
		this.fleets.add(fleet);
	}
	
	public void deleteFleet(Fleet fleet) {
		Syst.fleets.remove(fleet);
	}
	
	public int sizeFleets() {
		return fleets.size();
	}
	
	public static void save() throws IOException{
		String userHome = System.getProperty("user.home");
		File fichier =  new File(userHome+"/Desktop/APP/save/save.txt");
		
		// ouverture d'un flux sur un fichier
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;

		// sÃ©rialization de l'objet
		oos.writeObject(fleets);

		oos.close();
	}
	
	public void save(String file) throws IOException{
		File fichier =  new File(file);

		// ouverture d'un flux sur un fichier
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;

		// sÃ©rialization de l'objet
		oos.writeObject(fleets);

		oos.close();
	}
	
	public void update() throws FileNotFoundException, IOException, ClassNotFoundException{

		String userHome = System.getProperty("user.home");
		File fichier =  new File(userHome+"/Desktop/APP/save/save.txt");

		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;

		// dÃ©sÃ©rialization de l'objet
		ArrayList<Fleet> savedfleets = (ArrayList<Fleet>) ois.readObject();
		this.fleets = savedfleets;
		ois.close();
	}
	
	public void update(String file) throws FileNotFoundException, IOException, ClassNotFoundException{

		File fichier =  new File(file);

		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;

		// dÃ©sÃ©rialization de l'objet
		ArrayList<Fleet> savedfleets = (ArrayList<Fleet>) ois.readObject();
		this.fleets = savedfleets;
		ois.close();
	}

}
