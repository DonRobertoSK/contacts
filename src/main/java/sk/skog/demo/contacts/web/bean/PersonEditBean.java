package sk.skog.demo.contacts.web.bean;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import sk.skog.demo.contacts.bl.ContactService;
import sk.skog.demo.contacts.data.Group;
import sk.skog.demo.contacts.data.Person;

@Named
@ViewScoped
public class PersonEditBean implements Serializable {
    private Long id;
    private Person person;
    private List<Group> groups;
    private Set<Group> selectedGroups;
    @EJB
    private ContactService contactService;
    
    public void init() {
    	person = contactService.find(Person.class, id).orElse(new Person());
		groups = contactService.findAll(Group.class);
		selectedGroups = new LinkedHashSet<>(person.getGroups());
    }
    
    public void save() {
    	contactService.save(person, selectedGroups);
    	back();
    }
    
    public void delete() {
    	if (person.getId() != null) {
    		contactService.delete(person);
    	}
    	back();
    }
    
    public void back() {
    	Faces.redirect("personlist.xhtml");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Set<Group> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(Set<Group> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

}
