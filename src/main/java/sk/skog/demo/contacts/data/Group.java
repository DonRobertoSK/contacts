package sk.skog.demo.contacts.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GRP")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "grp_contact",
	    joinColumns = @JoinColumn(name = "groupId"),
	    inverseJoinColumns = @JoinColumn(name = "personId"))
    private Set<Person> contacts = new HashSet<>();
    
    public void addContact(Person contact) {
    	getContacts().add(contact);
    	contact.getGroups().add(this);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Person> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Person> contacts) {
        this.contacts = contacts;
    }
    
    @Override
    public boolean equals(Object obj) {
    	return Objects.equals(getId(), ((Group) obj).getId());
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(getId());
    }
    
    @Override
    public String toString() {
    	return getClass().getSimpleName() + " " + getId().toString();
    }

}

