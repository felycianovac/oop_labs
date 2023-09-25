package lab_1;

import java.util.Date;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;
    private Boolean isGraduated;

    public Student(String firstName, String lastName, String email, Date  enrollmentDate, Date dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.isGraduated = false;
    }

    //Getters and setters for accessing the variables outside the class

    public String getFirstName(){
        return firstName; //getter
    }
     public String getLastName(){
        return lastName;
     }

     public String getEmail(){
        return email;
     }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public Boolean getIsGraduated() {
        return isGraduated;
    }
    
}
