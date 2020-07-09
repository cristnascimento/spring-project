package com.github.cristnascimento.contactlistapp;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ContactListAppController {
	@Autowired
	ContactService contactService;

	@GetMapping("/")
	public String root(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
	    return "contact-home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("name", "hello");
		return "contact-home";
	}

	@GetMapping("/contact/add")
	public String add(Model model) {
	  	model.addAttribute("contact", new Contact());
	  	model.addAttribute("readonly", false);
	  	model.addAttribute("disabled", false);
	  	model.addAttribute("mode", "add");
	  	model.addAttribute("action", "/contact/post");
	    return "contact-form";
	}

	@GetMapping("/contact/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Contact contact = contactService.getContact(id);
		model.addAttribute("contact", contact);
		model.addAttribute("readonly", false);
		model.addAttribute("disabled", false);
		model.addAttribute("mode", "edit");
		model.addAttribute("action", "/contact/update/"+id);
	return "contact-form";
	}

	@GetMapping("/contact/view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		Contact contact = contactService.getContact(id);
		model.addAttribute("contact", contact);
		model.addAttribute("readonly", true);
		model.addAttribute("disabled", true);
		model.addAttribute("mode", "view");
		model.addAttribute("action", "/contact/edit");
	return "contact-form";
	}

	@GetMapping("/contact/list")
	public String list(Model model) {
		Collection<Contact> contacts = contactService.getContacts();
		model.addAttribute("contacts", contacts);
	return "contact-list";
	}

	@PostMapping("/contact/post")
	public ModelAndView post(@ModelAttribute Contact contact) {
		contactService.createContact(contact);
	return new ModelAndView("redirect:/contact/list");
	}

	@PostMapping("/contact/update/{id}")
	public ModelAndView post(@PathVariable("id") long id, @ModelAttribute Contact contact) {
		contactService.updateContact(id, contact);
	return new ModelAndView("redirect:/contact/view/"+id);
	}

	@GetMapping("/contact/delete/{id}")
	public ModelAndView post(@PathVariable("id") long id) {
		contactService.deleteContact(id);
	return new ModelAndView("redirect:/contact/list");
	}
}
