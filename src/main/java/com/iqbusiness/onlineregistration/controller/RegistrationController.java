package com.iqbusiness.onlineregistration.controller;

import com.iqbusiness.onlineregistration.domain.InvalidInputException;
import com.iqbusiness.onlineregistration.domain.Person;
import com.iqbusiness.onlineregistration.domain.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags =  "ONLINEREG")
public class RegistrationController {

    final PersonService personService;

    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<String> register(@ApiParam(value = "Person JSON object", required = true)
                                           @Valid
                                           @RequestBody Person person, Errors errors) throws InvalidInputException {
        if (errors.hasErrors()){
            throw new InvalidInputException(String.format("\n%s",processConstraintExceptions(errors)));
        }
        log.debug("Received person create request");
        String idNumber = personService.savePerson(person);
        log.debug("Person object saved for id {}",person.getIdNumber());
        return ResponseEntity.ok(idNumber);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllPersons")
    public ResponseEntity<List<Person>> getAllPersons() {
        log.debug("Received get All persons request");
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok(personList);
    }

    private String processConstraintExceptions(Errors errors){
        List<ConstraintViolation> constraintViolations = new ArrayList<>();

        for (ObjectError objectError:errors.getAllErrors()){
            constraintViolations.add(objectError.unwrap(ConstraintViolation.class));
        }

        StringBuilder errorMessage = new StringBuilder();

        for (ConstraintViolation error:constraintViolations) {
            errorMessage.append(error.getPropertyPath()).append(" ").append(error.getMessage());
        }

        return errorMessage.toString();
    }
}
