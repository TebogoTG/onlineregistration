package com.iqbusiness.onlineregistration.domain;

import com.iqbusiness.onlineregistration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    final
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String savePerson(Person person){
        personRepository.save(person);
        return person.getIdNumber();
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
