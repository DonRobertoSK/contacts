package sk.skog.demo.contacts.bl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sk.skog.demo.contacts.data.Group;
import sk.skog.demo.contacts.data.Person;

@Stateless
@TransactionAttribute
public class ContactService implements Serializable {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Person save(Person person, Set<Group> groups) {
		Person mergedPerson = entityManager.merge(person);
		mergedPerson.getGroups().forEach(group -> {
			if (!groups.contains(group)) {
				group.getContacts().remove(mergedPerson);
			}
		});
		mergedPerson.getGroups().removeIf(group -> !groups.contains(group));
		
		groups.forEach(group -> {
			if (!mergedPerson.getGroups().contains(group)) {
				mergedPerson.addGroup(entityManager.merge(group));
			}
		});
		return mergedPerson;
	}
	
	public Group save(Group group, Set<Person> persons) {
		Group mergedGroup = entityManager.merge(group);
		mergedGroup.getContacts().forEach(person -> {
			if (!persons.contains(person)) {
				person.getGroups().remove(group);
			}
		});
		mergedGroup.getContacts().removeIf(person -> !persons.contains(person));
		
		persons.forEach(person -> {
			if (!mergedGroup.getContacts().contains(person)) {
				mergedGroup.addContact(entityManager.merge(person));
			}
		});
		return mergedGroup;
	}
	
	public void delete(Person person) {
		Person mergedPerson = save(person, Collections.emptySet());
		entityManager.remove(mergedPerson);
	}
	
	public void delete(Group group) {
		Group mergedGroup = save(group, Collections.emptySet());
		entityManager.remove(mergedGroup);
	}
	
	public <T> Optional<T> find(Class<T> entityClass, Long id) {
		T entity = id == null ? null : entityManager.find(entityClass, id);
		return Optional.ofNullable(entity);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		return entityManager.createQuery("from " + entityClass.getName()).getResultList();
	}
	
}
