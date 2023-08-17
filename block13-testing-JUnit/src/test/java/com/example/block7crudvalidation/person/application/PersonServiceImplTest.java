package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonStudentOutputDto;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    public PersonRepository personRepository;

    @InjectMocks
    public PersonServiceImpl personService;


    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    void getTypeOfPersonWhenPersonDoesNotExistThenThrowException() {
        Integer id = 1;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> {
            personService.getTypeOfPerson(id);
        });

        // Verify
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return 'none' when the person is neither a student nor a teacher")
    void getTypeOfPersonWhenPersonIsNeitherStudentNorTeacher() {
        Integer personId = 1;
        Person person = new Person();
        person.setPersonId(personId);
        person.setStudent(null);
        person.setTeacher(null);

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        String typeOfPerson = personService.getTypeOfPerson(personId);

        assertEquals("none", typeOfPerson);
        verify(personRepository, times(1)).findById(personId);
    }

    @Test
    @DisplayName("Should return 'Teacher' when the person is a teacher")
    void getTypeOfPersonWhenPersonIsTeacher() {
        Integer personId = 1;
        Person person = new Person();
        person.setTeacher(new Teacher());

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        String result = personService.getTypeOfPerson(personId);

        assertEquals("Teacher", result);
    }

    @Test
    @DisplayName("Should return 'Student' when the person is a student")
    void getTypeOfPersonWhenPersonIsStudent() {
        Integer personId = 1;
        Person person = new Person();
        person.setPersonId(personId);
        person.setStudent(new Student());

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        String result = personService.getTypeOfPerson(personId);

        assertEquals("Student", result);
    }

    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    void deletePersonWhenPersonDoesNotExistThenThrowException() {
        Integer id = 1;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> personService.deletePerson(id));

        // Verify
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, never()).deleteById(id);
    }

    @Test
    @DisplayName("Should delete the person when the person exists and is neither a student nor a teacher")
    void deletePersonWhenPersonExistsAndIsNeitherStudentNorTeacher() {
        Integer id = 1;
        Person person = new Person();
        person.setStudent(null);
        person.setTeacher(null);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        personService.deletePerson(id);

        verify(personRepository, times(1)).deleteById(id);
    }

    /*@Test
    @DisplayName("Should delete the person when the person exists and is a student with a teacher")
    void deletePersonWhenPersonExistsAndIsStudentWithTeacher() {
        Integer id = 1;
        Person person = new Person();
        person.setStudent(new Student());
        person.getStudent().setTeacher(new Teacher());

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        personService.deletePerson(id);

        verify(personRepository, times(1)).deleteById(id);
        assertNull(person.getStudent().getTeacher());
    }*/

    /*@Test
    @DisplayName("Should delete the person when the person exists and is a teacher with students")
    void deletePersonWhenPersonExistsAndIsTeacherWithStudents() {
        Integer id = 1;
        Person person = new Person();
        person.setTeacher(new Teacher());
        person.getTeacher().setStudents(new ArrayList<>());
        person.getTeacher().getStudents().add(new Student());

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        personService.deletePerson(id);

        verify(personRepository, times(1)).deleteById(id);
        assertNull(person.getTeacher());
        assertNull(person.getTeacher().getStudents());
    }*/

    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    void updatePersonWhenPersonDoesNotExistThenThrowException() {
        Integer id = 1;
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setLastName("Doe");
        personInputDto.setImageUrl("https://example.com/image.jpg");
        personInputDto.setTerminationDate(new Date());

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> {
            personService.updatePerson(personInputDto, id);
        });

        verify(personRepository, times(1)).findById(id);
        verifyNoMoreInteractions(personRepository);
    }

    @Test
    @DisplayName("Should return empty list when there are no persons in the given page range")
    void getAllPersonsWhenNoPersonsInGivenPageRange() {
        int pageNumber = 0;
        int pageSize = 10;

        when(personRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        Iterable<PersonOutputDto> result = personService.getAllPersons(pageNumber, pageSize);

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
        verify(personRepository, times(1)).findAll(any(PageRequest.class));
    }

  /*  @Test
    @DisplayName("Should return all persons in the given page range")
    void getAllPersonsInGivenPageRange() {
        int pageNumber = 0;
        int pageSize = 10;

        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setUsername("john");
        persons.add(person1);
        Person person2 = new Person();
        person2.setUsername("Jane");
        persons.add(person2);
        persons.add(new Person());


        when(personRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(persons));

        Iterable<PersonOutputDto> result = personService.getAllPersons(pageNumber, pageSize);

        assertNotNull(result);
        List<PersonOutputDto> resultList = new ArrayList<>();
        result.forEach(resultList::add);

        assertEquals(3, resultList.size());
        assertEquals("john", resultList.get(0).getUsername());
        assertEquals("Jane", resultList.get(1).getFirstName());
        assertEquals(null, resultList.get(2).getUsername());


        verify(personRepository, times(1)).findAll(any(PageRequest.class));
    }
*/
    @Test
    @DisplayName("Should return an empty list when no persons with the given username exist")
    void getPersonNameWithNonExistingUsername() {
        String username = "nonexistinguser";
        when(personRepository.findAll()).thenReturn(new ArrayList<>());

        List<PersonOutputDto> result = personService.getPersonName(username);

        assertTrue(result.isEmpty());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return a list of persons with the given username")
    void getPersonNameWithExistingUsername() {
        String username = "john.doe";
        Person person1 = new Person();
        person1.setUsername("john.doe");
        Person person2 = new Person();
        person2.setUsername("john.doe");
        when(personRepository.findAll()).thenReturn(List.of(person1, person2));

        List<PersonOutputDto> result = personService.getPersonName(username);

        assertEquals(2, result.size());
        assertEquals(username, result.get(0).getUsername());
        assertEquals(username, result.get(1).getUsername());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw an EntityNotFoundException when the person with the given id does not exist")
    void getPersonByIdStudentWhenPersonDoesNotExistThenThrowException() {
        Integer id = 1;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            personService.getPersonByIdStudent(id);
        });

        verify(personRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the person student output DTO when the person with the given id exists")
    void getPersonByIdStudentWhenPersonExists() {
        Integer id = 1;
        Person person = new Person();
        person.setPersonId(id);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setCompanyEmail("john.doe@example.com");
        person.setPersonalEmail("john.doe@gmail.com");
        person.setCity("New York");
        person.setActive(true);
        person.setCreatedDate(new Date());
        person.setImageUrl("https://example.com/image.jpg");
        person.setTerminationDate(null);
        person.setStudent(new Student());
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        PersonStudentOutputDto result = personService.getPersonByIdStudent(id);

        assertNotNull(result);
        assertEquals(id, result.getPersonId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getCompanyEmail(), result.getCompanyEmail());
        assertEquals(person.getPersonalEmail(), result.getPersonalEmail());
        assertEquals(person.getCity(), result.getCity());
        assertEquals(person.getActive(), result.getActive());
        assertEquals(person.getCreatedDate(), result.getCreatedDate());
        assertEquals(person.getImageUrl(), result.getImageUrl());
        assertEquals(person.getTerminationDate(), result.getTerminationDate());

        verify(personRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw an exception when the id does not exist")
    void getPersonByIdWhenIdDoesNotExistThenThrowException() {
        Integer id = 1;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> {
            personService.getPersonById(id);
        });

        // Verify
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the person when the id exists")
    void getPersonByIdWhenIdExists() {
        Integer id = 1;
        Person person = new Person();
        person.setPersonId(id);
        person.setUsername("john.doe");
        person.setPassword("password");
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setCompanyEmail("john.doe@example.com");
        person.setPersonalEmail("john.doe@gmail.com");
        person.setCity("New York");
        person.setActive(true);
        person.setCreatedDate(new Date());
        person.setImageUrl("https://example.com/image.jpg");
        person.setTerminationDate(null);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        PersonOutputDto result = personService.getPersonById(id);

        assertNotNull(result);
        assertEquals(id, result.getPersonId());
        assertEquals("john.doe", result.getUsername());
        assertEquals("password", result.getPassword());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getCompanyEmail());
        assertEquals("john.doe@gmail.com", result.getPersonalEmail());
        assertEquals("New York", result.getCity());
        assertEquals(true, result.getActive());
        assertEquals("https://example.com/image.jpg", result.getImageUrl());
        assertEquals(null, result.getTerminationDate());

        verify(personRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should add a person and return the corresponding output DTO")
    void addPerson() {
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setUsername("john.doe");
        personInputDto.setPassword("password");
        personInputDto.setFirstName("John");
        personInputDto.setLastName("Doe");
        personInputDto.setCompanyEmail("john.doe@example.com");
        personInputDto.setPersonalEmail("john.doe@gmail.com");
        personInputDto.setCity("New York");
        personInputDto.setActive(true);
        personInputDto.setImageUrl("https://example.com/image.jpg");
        personInputDto.setTerminationDate(new Date());
        personInputDto.setCreatedDate(new Date());

        when(personRepository.save(any(Person.class))).thenReturn(new Person());

        PersonOutputDto result = personService.addPerson(personInputDto);

        assertNotNull(result);
        assertEquals(personInputDto.getUsername(), result.getUsername());
        assertEquals(personInputDto.getPassword(), result.getPassword());
        assertEquals(personInputDto.getFirstName(), result.getFirstName());
        // assertEquals(personInputDto.getLastName(), result.getLastName());
        assertEquals(personInputDto.getCompanyEmail(), result.getCompanyEmail());
        assertEquals(personInputDto.getPersonalEmail(), result.getPersonalEmail());
        assertEquals(personInputDto.getCity(), result.getCity());
        assertEquals(personInputDto.getActive(), result.getActive());
        //assertEquals(personInputDto.getImageUrl(), result.getImageUrl());
        //assertEquals(personInputDto.getTerminationDate(), result.getTerminationDate());
        //assertEquals(personInputDto.getCreatedDate(), result.getCreatedDate());

        verify(personRepository, times(1)).save(any(Person.class));
    }
}