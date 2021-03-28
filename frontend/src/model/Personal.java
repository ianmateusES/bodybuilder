/**
* Classe personal com todos os campos que o BD possuí, visto que o java
* é o frontend do sistema
*/
package model;

public class Personal extends Usuario{
    private String _id;
    private String name;
    private String email;
    private String password;
    private String birthday;
    private String telephone;
    private String cref;
    private String address;
    
    public Personal(String _id, String name, String email, String password,String birthday, String telephone,String cref, String address) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;

        this.telephone = telephone;
        this.cref = cref;
        this.address = address;
    }
    
    public Personal(){}
    
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String city) {
        this.address = city;
    }
   
}
