package com.tddinaction.patterns.fixture;

import com.tddinaction.data.person.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParameterizedCreationMethodExample {
    private Person alice, billy, clark;

    @Before
    public void setUp() throws Exception {
        clark = createPerson("Clark", "Cable");
        billy = createPerson("Billy", "Burke");
        alice = createPerson("Alice", "Adams");
        alice.isInLoveWith(billy);
    }

    private Person createPerson(String firstName, String lastName) {
        Person person = new Person();
        person.setFirstname(firstName);
        person.setLastname(lastName);
        person.setId(UniqueNumber.next());
        person.setSsn(String.valueOf(UniqueNumber.next()));
        return person;
    }

    @Test
    public void aliceShouldAcceptWhenProposedToByBilly()
            throws Exception {
        billy.proposeTo(alice);
        assertTrue(alice.isEngagedWith(billy));
    }
}