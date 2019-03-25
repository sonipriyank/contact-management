package com.emxcel.springboot.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public List<Contact> getAllContacts(int start, int size) {
		List<Contact> contacts = new ArrayList<Contact>();
		contactRepository.findAll().forEach(contacts::add);
		if (start + size > contacts.size()) {
			return new ArrayList<Contact>();
		}
		return contacts.subList(start, start + size);
	}

	public Contact getContactById(int id) {

		return contactRepository.findById(id).get();
	}

	public void addContact(Contact contact) {
		contactRepository.save(contact);
	}

	public void updateContact(int id, Contact contact) {
		contactRepository.save(contact);
	}

	public void deleteContact(int id) {
		contactRepository.deleteById(id);
	}

	public List<Contact> getContactByName(String name) {
		return contactRepository.findByName(name);
	}
}
