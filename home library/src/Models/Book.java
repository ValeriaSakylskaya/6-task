package Models;
import Enums.TypeOfBookCarrier;
public class Book {
    private String Name;
    private String Author;
    private TypeOfBookCarrier TypeOfBookCarrier;
    private String Description;
    private int ID;
    public Book(int id, String name, String author, TypeOfBookCarrier typeOfBookCarrier, String description){
        this.Author = author;
        this.Name = name;
        this.TypeOfBookCarrier = typeOfBookCarrier;
        this.Description = description;
        this.ID = id;
    }

    public String getAuthor() {
        return Author;
    }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public int getID() {
        return ID;
    }

    public Enums.TypeOfBookCarrier getTypeOfBookCarrier() {
        return TypeOfBookCarrier;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString(){
        return "Book " + Name + " Author " + Author + " carrier " + TypeOfBookCarrier + " " + Description;
    }
}
