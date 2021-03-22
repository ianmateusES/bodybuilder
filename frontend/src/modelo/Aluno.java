package modelo;

public class Aluno {

    /**
     * @return the _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(String _id) {
        this._id = _id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    
    private String _id;
    private String name;
    private String email;
    private String password;
    private String birthday;
    private boolean status;
    
    public Aluno(String _id, String name, String email, String password,String birthday, boolean status) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.status = status;

    }
}
