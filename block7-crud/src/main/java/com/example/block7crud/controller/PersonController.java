package com.example.block7crud.controller;

@RestController
@RequestMapping("/person")
public class Controller {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody StudentInputDto student) {
        URI location = URI.create("/student");
        return ResponseEntity.created(location).body(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
