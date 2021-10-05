package com.iqbusiness.onlineregistration;

import com.iqbusiness.onlineregistration.domain.Person;
import com.iqbusiness.onlineregistration.domain.PersonService;
import com.iqbusiness.onlineregistration.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegistrationServiceTests {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;
    @Test
    void registerRestControllerSuccess() {
        Person person =
                Person.builder()
                        .fullName("Test Full Name")
                        .idNumber("8002145459081")
                        .telephoneNumber("+27817722272")
                        .build();
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String idNumber = personService.savePerson(person);
        assertThat(idNumber).isEqualTo("8002145459081");
    }

    @Test
    void getAllPersonsSuccess() {
        Person person =
                Person.builder()
                        .fullName("Test Full Name")
                        .idNumber("8002145459081")
                        .telephoneNumber("+27817722272")
                        .build();
        Person person2 =
                Person.builder()
                        .fullName("Test Full Name 2")
                        .idNumber("8002155459081")
                        .telephoneNumber("+27817724472")
                        .build();
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person2);
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> personListResponse = personService.findAll();
        assertThat(personListResponse.size()).isEqualTo(personList.size());
    }
}
