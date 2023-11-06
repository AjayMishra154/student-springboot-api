package com.college.student;



import java.util.Objects;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
class Student {

  private @Id @GeneratedValue Long id;
  private String name;
  private String rollno;
  private int age;

  Student() {}

  Student(String name, String rollno, int age) {

    this.name = name;
    this.rollno = rollno;
    this.age = age;
  }

  public Long getId() {
    return this.id;
  }

  public int getAge() {
    return this.age;
  }

  public String getName() {
    return this.name;
  }

  public String getRollno() {
    return this.rollno;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRollno(String rollno) {
    this.rollno = rollno;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Student))
      return false;
    Student student = (Student) o;
    return Objects.equals(this.id, student.id) && Objects.equals(this.name, student.name) && Objects.equals(this.age, student.age)
        && Objects.equals(this.rollno, student.rollno);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name,this.age, this.rollno);
  }

  @Override
  public String toString() {
    return "Student{" + "id=" + this.id + ", name='" + this.name +'\''+ "age=" + this.age +'\'' + ", rollno='" + this.rollno + '\'' + '}';
  }
}

