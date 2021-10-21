package sk.skog.demo.contacts.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import sk.skog.demo.contacts.bl.ContactService;
import sk.skog.demo.contacts.data.Group;

@Named
@RequestScoped
public class GroupListBean implements Serializable {
	@EJB
    private ContactService contactService;
    private List<Group> dataModel;

    @PostConstruct
    public void init() {
        dataModel = contactService.findAll(Group.class);
    }

    public List<Group> getDataModel() {
        return dataModel;
    }

    public void setDataModel(List<Group> dataModel) {
        this.dataModel = dataModel;
    }

}
