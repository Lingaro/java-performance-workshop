package com.lingaro.web.person;

import com.lingaro.web.person.entity.Address;
import com.lingaro.web.person.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Service
public
class PersonService {

    public static final Logger LOG = Logger.getLogger(PersonService.class.getName());
    public static final int PERSON_COUNT = 100_000;

    private final TransactionTemplate tx;
    private final PersonRepository personRepository;
    private final EntityManager em;

    public PersonService(TransactionTemplate tx, PersonRepository personRepository, EntityManager em) {
        this.tx = tx;
        this.personRepository = personRepository;
        this.em = em;
    }

    @PostConstruct
    @Transactional
    public void populate() {
        tx.execute(status -> {
            long count = personRepository.count();
            if (count >= PERSON_COUNT) {
                return null;
            }
            for (int i = 0; i < PERSON_COUNT; i++) {
                em.persist(new Person(
                        "Jon", "Snow" + i,
                        new Address("Westeros" + i)
                ));
            }
            return null;
        });
    }

}
