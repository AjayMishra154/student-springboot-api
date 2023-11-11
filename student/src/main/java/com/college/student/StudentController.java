package com.college.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StudentController {
    private final StudentRepository repository;

    private final StudentModelAssembler assembler;

    StudentController(StudentRepository repository, StudentModelAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }


  @PostMapping("/students")
  Student newStudent(@RequestBody Student newStudent) {
    return repository.save(newStudent);
  }


  @PutMapping("/students/{id}")
  Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(student -> {
        student.setName(newStudent.getName());
        student.setRollno(newStudent.getRollno());
        return repository.save(student);
      })
      .orElseGet(() -> {
        newStudent.setId(id);
        return repository.save(newStudent);
      });
  }

  @DeleteMapping("/students/{id}")
  void deleteStudent(@PathVariable Long id) {
    repository.deleteById(id);
  }




@GetMapping("/students/{id}")
public EntityModel<Student> one(@PathVariable Long id) {
    Student student = repository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException(id));

    // Create a link to the current resource
    Link selfLink = linkTo(methodOn(StudentController.class).one(id)).withSelfRel();

    // Create a link to all students
    Link studentsLink = linkTo(methodOn(StudentController.class).all()).withRel("students");

    // Add the links to the EntityModel
    EntityModel<Student> entityModel = EntityModel.of(student, selfLink, studentsLink);

    return entityModel;
}
@GetMapping("/students")
CollectionModel<EntityModel<Student>> all() {

  List<EntityModel<Student>> students = repository.findAll().stream()
      .map(student -> EntityModel.of(student,
          linkTo(methodOn(StudentController.class).one(student.getId())).withSelfRel(),
          linkTo(methodOn(StudentController.class).all()).withRel("students")))
      .collect(Collectors.toList());

  return CollectionModel.of(students, linkTo(methodOn(StudentController.class).all()).withSelfRel());
}

}