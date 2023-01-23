package mk.ukim.finki.wp.exam.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//CEKOR 1 -
@Entity // stanuva zbor za entitet
public class Category {

    //konstruktor
    public Category(){
    }
    public Category(String name){
        this.name = name;
    }
    //cekor 2
    @Id // identifikatorite treba da se generirani vrednosti
    @GeneratedValue
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
