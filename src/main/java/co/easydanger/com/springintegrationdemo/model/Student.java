package co.easydanger.com.springintegrationdemo.model;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionID = -7408851479146003262L;

    private String id;
    private String name;
    private String school;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "toString for the Student object -> {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                '}' + " ##Not to be confused with the similar looking JSON.";
    }
}
