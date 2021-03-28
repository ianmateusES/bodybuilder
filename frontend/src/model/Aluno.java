/**
* Classe aluno com todos os campos que o BD possuí, visto que o java
* é o frontend do sistema
*/
package model;

public class Aluno {

    private String _id;
    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String birthday;
    private String status;

    
    public Aluno() {}
    
    public Aluno(String _id, String name, String email, String password, String password_confirmation, String birthday, String status) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.birthday = birthday;
        this.status = status;
    }
    
    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    
}
