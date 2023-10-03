package lab_1.models;

import lab_1.enums.StudyField;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private  String name;
    private  String abbreviation;
    private List<Student> students;
    private  StudyField studyField;

    public Faculty(String name, String abbreviation, List<Student> students, StudyField studyField){
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = new ArrayList<>();
        this.studyField = studyField;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }


    public StudyField getStudyField() {
        return studyField;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean containsStudent(String firstName, String lastName) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", students=" + students +
                ", studyField=" + studyField +
                '}';
    }
}
