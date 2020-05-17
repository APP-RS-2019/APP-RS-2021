import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Robot implements Runnable
{
	
	public Robot(){}
	
	//Appelle une méthode dont le nom est nomMethode avec les parametres associés s'ils existent
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

	//Boucle de communication avec le serveur, interprète et execute les ordres reçus
    public void run()
    {
        System.out.println("Robot started.");
        String bo="";
        
        try {
        	ClientSocket test=new ClientSocket("25.100.142.23",1933,"Robotino");
			boolean te=test.getOpen();
			while (te){
				bo = test.reciev();
				if (bo.equals("fin")){
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
			System.out.println("cc exit");
			test.close();
		}catch (Exception e){
			e.printStackTrace();
		}
     }

    
    public void avance() throws Exception
    {  
    	System.out.println("J'avance");
    }

/*avance sur une distance en m donnee*/
    public void avance(String d) throws Exception
    {
	  long startTime = System.currentTimeMillis();
	  long elapsedTime=0;
	  float distance = Float.parseFloat(d);
        while (elapsedTime<distance*16700)
        {
		elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("J'avance de "+distance);
        }
	    System.out.println("Stop"); // stop
    }
    
    protected void recule()
    {
       System.out.println("Je recule");;

    }
    
    protected void cercle()
    {
       System.out.println("Je fais un cercle");
    }

    protected void tourne()
    {
    	System.out.println("Je tourne sur moi même");
    }

    public void tourne(String n, String s) // nombre = Nombre de quarts de tour
    {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        int nombre = Integer.parseInt(n);
        boolean sens = Boolean.parseBoolean(s);
        
        while (elapsedTime<nombre*1750 )
        {
			elapsedTime = System.currentTimeMillis() - startTime;
			if(sens){ //Vers la gauche = trigo
				System.out.println("Je tourne en sens trigo de "+nombre+" quart de tours");
			}
			else{
				System.out.println("Je tourne en sens horaire de "+nombre+" quart de tours");
			}
        }
        System.out.println("Je m'arrete");
    }
    
  //Crée un objet Ordre depuis une chaine de caractères écrite selon le protocole action-param1_param2_...
  public Ordre splitOrder(String order) {
	  String[] tab1 = order.split("-");
	  String action = tab1[0];
	  Ordre ok;
	  
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