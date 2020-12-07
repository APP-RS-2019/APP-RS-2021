package robot;
import java.io.Serializable;
import action.Action;
import upperClass.ClientSocket;
import upperClass.Syst;


public class Pepper extends Robot implements Serializable{

	public Pepper(String idRobot, String name){
		super(idRobot, name);
	}

	public Pepper(String idRobot, String name, double posx, double posy, double angle){
		super( idRobot,  name,  posx,  posy, angle);
	}

	public void doAction(Action a) {
		ClientSocket server = Syst.getClientsocket();

		switch(a.getNom()){
		case "elephant":
			server.sendOrder(this.name,"elephant");
			break;
		case "avance":
			server.sendOrder(this.name,"avance");
			break;

		case "recule":
			server.sendOrder(this.name,"recule");
			break;

		case "demitour":
			server.sendOrder(this.name,"demitour");
			break;

		case "parler":
			server.sendOrder(this.name,"parler-message");
			break;
		}
	}
}
