import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String ip;
      String port;

      System.out.println("Choisir adresse et port serveur ? (o/n):");
      String choix = reader.readLine();

      if(choix.equals("o") || choix.equals("O")){
        System.out.println("IP Serveur :");
        ip = reader.readLine();

        System.out.println("port Serveur :");
        port = reader.readLine();
      }
      else{
        ip = "193.48.125.71";
        port = "1933";
      }
      /*Cree un nouveau robot avec son adresse ip en parametre et appelle sa methode run()*/
      String hostname = System.getProperty("hostname", "193.48.125.37");

      new Robot(hostname,ip,port).taches();
    }
}
