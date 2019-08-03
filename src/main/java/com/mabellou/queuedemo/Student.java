package com.mabellou.queuedemo;

import java.io.Serializable;

public class Student implements Serializable {
    public Long id;
    public String username;
    public Integer age;

    public Student(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
