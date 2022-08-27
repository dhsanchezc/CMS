package com.example.contactmanager.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.contactmanager.pojo.Contact;
import com.example.contactmanager.services.ContactRepository;

@Controller
@SessionAttributes("contact")
public class ContactController {

	@Autowired
	ContactRepository repository;

	@GetMapping("contacts")
	public String readContacts(Model model) {
		model.addAttribute("contacts", repository.findAll());
		return "contacts";
	}

	@GetMapping("contactForm")
	public String showContactForm(Contact contact) {
		return "contactForm";
	}

	@PostMapping("contactForm")
	public String checkContactForm(@Valid Contact contact, SessionStatus status) {	
		repository.save(contact);
		status.setComplete();
		return "redirect:/contacts";
	}

	@GetMapping("contactForm/{id}")
	public String editContact(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("contact", repository.findById(id));
		return "contactForm";
	}

	@RequestMapping(value = "delete/{id}")
	public String deleteContact(@PathVariable(value = "id") Long id) {
		repository.deleteById(id);
		return "redirect:/contacts";
	}

}
