/*Objet ordre composé d'une action et de ses paramètres*/
public class Ordre {
	
	private String action;
    private String[] param;

    public Ordre(String action, String[] param) {
        this.action = action;
        this.param = param;
    }

    public String getAction() {
        return action;
    }

    public String[] getParam() {
        return param;
    }

}
