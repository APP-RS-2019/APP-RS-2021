package upperClass;

public class ThreadReception extends Thread {
	private ClientSocket serveur;
	private String msg_recu;
	private boolean ouvert;

	public ThreadReception(ClientSocket serveur){
		this.serveur=serveur;
		this.msg_recu="";
		this.ouvert=false;
		
	}

	public void run(){
		System.err.println("Thread recepetion ouvert");
		this.ouvert=true;
		while (ouvert){
			msg_recu=serveur.reciev();
			System.out.println(msg_recu);
			Thread.yield();
		}
	}
	
	public void fermer() {
		this.ouvert=false;
	}
}
