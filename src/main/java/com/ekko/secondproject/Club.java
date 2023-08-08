package com.ekko.secondproject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Club {
    private String name;
    private String description;
    private String clubEmail;
    private List<Link> links;
    private String activityDate;
    private List<Contact> contacts;

    public Club() {
        contacts = new ArrayList<>();
        links = new ArrayList<>();
    }
    public List<Contact> getContacts() {
        return contacts;
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
    public void addLink(Link link){
        links.add(link);
    }
}
