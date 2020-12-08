/*Objet ordre compos� d'une action et de ses param�tres*/
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
