package utils;
// Singleton
import modelo.Personal;

public final class PersonalLogado {
    private static volatile PersonalLogado personalLogado;
    private Personal personal;
    private String token;
    
    private PersonalLogado() {}
    
    public static PersonalLogado getInstance() {
        if (personalLogado == null) {
            synchronized (PersonalLogado.class) {
                if (personalLogado == null) {
                    personalLogado = new PersonalLogado();
                }
            }
        }
        return personalLogado;
    }
    
    public void setLogin(Personal personal, String token) {
        this.personal = personal;
        this.token = token;
    }
    
    public void setUpdateUser(Personal personal) {
        this.personal = personal;
    }

    public void setLogout() {
        this.personal = null;
        this.token = null;
    }

    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setToken(String token) {
       this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}