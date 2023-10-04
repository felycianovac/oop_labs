package lab_1.models;

import java.util.Date;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Date enrollmentDate;
    private final Date dateOfBirth;
    private  Boolean graduated;

    public Student(String firstName, String lastName,
                   String email, Date enrollmentDate,
                   Date dateOfBirth, Boolean graduated){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.graduated = graduated;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail(){
        return email;
     }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
