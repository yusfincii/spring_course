package com.yusuf.springdemo.mvc;

import com.yusuf.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "is required!")
    @Size(min = 1, max = 18, message = "is required!")
    private String lastName;

    @NotNull(message = "is required!")
    @Min(value = 0, message = "must be greater than or equal to 0 !")
    @Max(value = 10, message = "must be less than or equal to 10 !")
    private Integer freePasses;
    // not int because can be take type conversion error

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits can be accept")
    private String postalCode;

    @CourseCode(value = "CMP", message = "must start with CMP")
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses){
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
