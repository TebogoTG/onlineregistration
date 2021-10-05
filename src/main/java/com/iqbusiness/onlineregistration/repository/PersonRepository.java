package com.iqbusiness.onlineregistration.repository;

import com.iqbusiness.onlineregistration.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

}
