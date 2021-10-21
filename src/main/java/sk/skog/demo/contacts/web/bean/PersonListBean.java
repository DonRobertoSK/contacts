package sk.skog.demo.contacts.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import sk.skog.demo.contacts.bl.ContactService;
import sk.skog.demo.contacts.data.Person;

@Named
@RequestScoped
public class PersonListBean implements Serializable {
    @EJB
    private ContactService contactService;
    private List<Person> dataModel;

    @PostConstruct
    public void init() {
        dataModel = contactService.findAll(Person.class);
    }

    public List<Person> getDataModel() {
        return dataModel;
    }

    public void setDataModel(List<Person> dataModel) {
        this.dataModel = dataModel;
    }

}
