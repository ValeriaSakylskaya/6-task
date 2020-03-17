package Modules;

public class FileStudent {
    private int Id;
    private String nameStudent;
    private String Specialization;
    public FileStudent(int id, String nameStudent, String specialization ){
        this.Id = id;
        this.nameStudent = nameStudent;
        this.Specialization = specialization;
    }

    public int getId() {
        return Id;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    @Override
public String toString(){
return nameStudent + " " + Specialization;
    }
}