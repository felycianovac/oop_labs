package lab_1.models;

import java.util.Date;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String email;
    private Date enrollmentDate;
    private final Date dateOfBirth;
    private final Boolean graduated;

    public Student(String firstName, String lastName,
                   String email, Date enrollmentDate,
                   Date dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.graduated = false;
    }


    public String getEmail(){
        return email;
     }

    public Boolean getGraduated() {
        return graduated;
    }



}
