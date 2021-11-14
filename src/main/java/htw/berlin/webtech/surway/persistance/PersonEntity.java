package htw.berlin.webtech.surway.persistance;

import javax.persistence.*;

@Entity(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "is_vaccinated")
    private Boolean vaccinated;

    public PersonEntity(String firstName, String lastName, Boolean vaccinated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccinated = vaccinated;
    }

    protected PersonEntity() {}

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
}
