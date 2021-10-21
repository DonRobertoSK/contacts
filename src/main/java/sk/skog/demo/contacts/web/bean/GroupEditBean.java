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
public class GroupEditBean implements Serializable {
    private Long id;
    private Group group;
    private List<Person> persons;
    private Set<Person> selectedPersons;
    @EJB
    private ContactService contactService;
    
    public void init() {
    	group = contactService.find(Group.class, id).orElse(new Group());
    	persons = contactService.findAll(Person.class);
    	selectedPersons = new LinkedHashSet<>(group.getContacts());
    }
    
    public void save() {
    	contactService.save(group, selectedPersons);
    	back();
    }
    
    public void delete() {
    	if (group.getId() != null) {
    		contactService.delete(group);
    	}
    	back();
    }
    
    public void back() {
    	Faces.redirect("grouplist.xhtml");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Set<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(Set<Person> selectedPersons) {
		this.selectedPersons = selectedPersons;
	}

}
