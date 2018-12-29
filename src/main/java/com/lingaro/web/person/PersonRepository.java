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

    @EntityGraph(value = Person.FETCH_ADDRESS, type = EntityGraph.EntityGraphType.FETCH)
    Stream<Person> findAllBy();

    @Query("select person from Person person join fetch person.address")
    Stream<Person> selectWithAddress();

    @Query("select concat(person.name, ' ', person.surname, ' @ ', address.name) from Person person left join person.address address")
    Set<String> queryNameAtAddress();

}
