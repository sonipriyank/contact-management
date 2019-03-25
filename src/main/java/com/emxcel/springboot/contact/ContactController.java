package com.emxcel.springboot.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/contacts")
	public List<Contact> getAllContacts(@RequestParam("start") int start, @RequestParam int size,
			@RequestParam(required = false) String name) {
		if (name != null) {
			return contactService.getContactByName(name);
		} else if (start > 0 && size > 0 && name == null) {
			return contactService.getAllContacts(start, size);
		}
		return contactService.getAllContacts(start, size);
	}

	@RequestMapping("/contacts/{id}")
	public Contact getContactById(@PathVariable int id) {
		return contactService.getContactById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/contacts")
	public void addContact(@RequestBody Contact contact) {
		contactService.addContact(contact);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/contacts/{id}")
	public void updateContact(@RequestBody Contact contact, @PathVariable int id) {
		contactService.updateContact(id, contact);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/contacts/{id}")
	public void deleteContact(@PathVariable int id) {
		contactService.deleteContact(id);
	}

}
