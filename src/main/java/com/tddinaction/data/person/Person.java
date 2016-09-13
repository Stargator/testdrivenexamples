package com.tddinaction.data.person;

public class Person {
    private String firstname;
    private String lastname;
    private long id;
    private String ssn;
    private Person inLoveWith;
    private Person engagedWith;

    public void proposeTo(Person person) {
        if(person.acceptProposalFrom(this)) {
            this.engagedWith = person;
            person.engagedWith = this;
        }
    }

    private boolean acceptProposalFrom(Person person) {
        return this.inLoveWith.equals(person);
    }

    public boolean isEngagedWith(Person person) {
        return person.equals(engagedWith);
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if ((object != null) && (object.getClass().equals(getClass()))) {
            Person other = (Person) object;
            isEqual = this.id == other.getId() && this.ssn.equals(other.getSsn())
                    && this.firstname.equals(other.getFirstname()) && this.lastname.equals(other.getLastname());
        }

        return isEqual;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void isInLoveWith(Person person) {
        this.inLoveWith = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
