import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import rec.robotino.com.Bumper;
import rec.robotino.com.Com;
import rec.robotino.com.Motor;
import rec.robotino.com.OmniDrive;

import static java.lang.Math.*;
import java.util.*;


public class Robot
{
	protected final Com com;
	protected final String hostname;
	protected final Motor motor1;
	protected final Motor motor2;
	protected final Motor motor3;
	protected final OmniDrive omniDrive;
	protected final Bumper bumper;
	protected final float[] startVector = new float[]
			{
			200.0f, 0.0f
			};
	protected final String ipServeur;
	protected final int portServeur;


	public Robot(String hostname, String ipServeur, String portServeur){
		this.hostname = hostname;
		com = new Com();
		motor1 = new Motor();
		motor2 = new Motor();
		motor3 = new Motor();
		omniDrive = new OmniDrive();
		bumper = new Bumper();
		this.ipServeur = ipServeur;
		this.portServeur = Integer.parseInt(portServeur);
	}

	//Appelle une methode dont le nom est nomMethode avec les parametres associes s'ils existent
	public static void executerMethode(Object objet, String nomMethode, Object[] parametres) throws Exception {
		    Object retour;
		    Class[] typeParametres = null;

		    if (parametres != null) {
		      typeParametres = new Class[parametres.length];
		      for (int i = 0; i < parametres.length; ++i) {
		        typeParametres[i] = parametres[i].getClass();
		      }
		    }

		    Method m = objet.getClass().getMethod(nomMethode, typeParametres);
		    if (Modifier.isStatic(m.getModifiers())) {
		      retour = m.invoke(null, parametres);
		    } else {
		      retour = m.invoke(objet, parametres);
		    }
		  }

	//Boucle de communication avec le serveur, interprete et execute les ordres recus
    public void taches()
    {
        System.out.println("Robot started.");
        System.out.println("Initializing...");
        init();
		System.out.println("Connecting...");
		connect(hostname);
		System.out.println("Connected.");
		System.out.println("Driving...");

        String bo="";

        try {
        	ClientSocket test=new ClientSocket(this.ipServeur,this.portServeur,"Robotino");
        	test.connect("Robotino");
			boolean te=test.isOpen();
			while (te){
					if(bumper.value()==true){
						bo="";
						this.stop();
						System.out.println("bumper"+bo);
					}
					else{
						bo = test.reciev();
						System.out.println("oui" + bo);
						if (bo.equals("fin") || bo.equals("error")){
							te = false;
						}
						else {
							Ordre ordre = splitOrder(bo);
							String action = ordre.getAction();
							String[] param = ordre.getParam();

							try {
								executerMethode(this, action, param);
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}
			}
			System.out.println("Server : Disconnected");
			test.disconnect();
		}catch (Exception e){
			e.printStackTrace();
		}
     }

    protected void init()
	{
		motor1.setComId(com.id());
		motor1.setMotorNumber(0);

		motor2.setComId(com.id());
		motor2.setMotorNumber(1);

		motor3.setComId(com.id());
		motor3.setMotorNumber(2);

		omniDrive.setComId(com.id());

		bumper.setComId(com.id());

	}

	protected void connect(String hostname)
	{
		com.setAddress(hostname);
		com.connect();
	}

	protected void disconnect()
	{
		com.disconnect();
	}

    public void avance() throws Exception
    {
			System.out.println("avance");
			omniDrive.setVelocity(100, 0, 0);
    }

	/*avance sur une distance en m donnee, cette distance est calculee par rapport au temps et a la vitesse du robot*/
    public void avance(String d) throws Exception
    {
		long startTime = System.currentTimeMillis();
		long elapsedTime=0;
		double distance = Double.parseDouble(d);
		omniDrive.setVelocity(60, 0, 0);
		while (elapsedTime<distance*16700 && bumper.value() != true)
		{
			elapsedTime = System.currentTimeMillis() - startTime;
		}
		omniDrive.setVelocity(0, 0, 0); // stop
 	}


    public void recule()
    {
		omniDrive.setVelocity(-100, 0, 0);
    }

    public void stop()
    {
		System.out.println("stop");
		omniDrive.setVelocity(0, 0, 0);
    }

/*tourne sur lui meme*/
    public void tourne()
    {
            omniDrive.setVelocity(0, 0, 60);
    }

/*tourne sur lui m�me de n quarts de tour dans sens trigo ou antitrigo selon s*/
    public void tourne(String n, String s) throws Exception // n = Nombre de quarts de tour
    {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
	   int nombre = Integer.parseInt(n);
        while (elapsedTime<nombre*1750 && bumper.value() != true)
        {
			elapsedTime = System.currentTimeMillis() - startTime;
			if(s.equals("trigo")){ //Vers la gauche
				omniDrive.setVelocity(10, 0, 60);
			}
			else{
				omniDrive.setVelocity(10, 0, -60);
			}
        }
        omniDrive.setVelocity(0, 0, 0); // stop
    }

  //Cree un objet Ordre depuis une chaine de caracteres ecrite selon le protocole action-param1_param2_...
  public Ordre splitOrder(String order) {
        /*Separe le message a "-", stocke l'action (premi�re partie du message)*/
	  String[] tab1 = order.split("-");
	  String action = tab1[0];
	  Ordre ok;

/*Stocke les parametre s'il y en a, et cree un objet Ordre*/
	  if(tab1.length>1) {
		  String[] param = tab1[1].split("_");
		  ok = new Ordre(action,param);
	  }else {
		  String[] param = new String[0];
		  ok = new Ordre(action,param);
	  }
	  return ok;
  }
}
