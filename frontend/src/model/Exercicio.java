/**
* Classe exercicios com todos os campos que o BD possuí, visto que o java
* é o frontend do sistema
*/
package model;

public class Exercicio {
 
    private String _id;
    private String author;
    private String name;
    private String group;
    
    public Exercicio() {}
    
    public Exercicio(String _id, String author, String name, String group) {
        this._id = _id;
        this.author = author;
        this.name = name;
        this.group = group;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
}