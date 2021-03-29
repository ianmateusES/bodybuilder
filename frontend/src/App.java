
import utils.PersonalLogado;
import view.Access.AccessMainPersonal;
import view.Main;

public class App {
    public static void main(String[] args) {
        if(PersonalLogado.getInstance().getToken() == null){
            Main.getInstance().setVisible(true);
        }else{
            AccessMainPersonal.getInstance().setVisible(true);
        }
        
    }
}
