package lab_1.models;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

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

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
    }

    public List<Student> getCurrentStudents() {
        List<Student> currentStudents = new ArrayList<>();
        for(Student student : students){
            if(!student.getGraduated()){
                currentStudents.add(student);
            }
        }
        return currentStudents;
    }


    public List<Student> getGraduates(){
        List<Student> graduates = new ArrayList<>();
        for(Student student : students){
            if(student.getGraduated()){
                graduates.add(student);
            }
        }
        return graduates;
    }

    public List<Student> getStudents() {
        return students;
    }

}
