package Modules;

public class Student {
    private int studentId;
    private String nameStudent;
    private String studentSpecialization;

    public Student(int studentId, String nameStudent, String studentSpecialization) {
        this.studentId = studentId;
        this.nameStudent = nameStudent;
        this.studentSpecialization = studentSpecialization;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentSpecialization() {
        return studentSpecialization;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    @Override
    public String toString() {
        return nameStudent + " " + studentSpecialization;
    }
}