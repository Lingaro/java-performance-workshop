package com.lingaro.web.person;

import com.lingaro.web.person.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Stream;

@Repository
interface PersonRepository extends JpaRepository<Person, Integer> {

}
