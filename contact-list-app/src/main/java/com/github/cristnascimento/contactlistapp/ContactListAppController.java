package com.github.cristnascimento.contactlistapp;

import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactListAppController {

	private List<Contact> contacts;
	private long nextId;

	public ContactListAppController () {
		this.contacts = new ArrayList<Contact>();
		this.nextId = 1;
	}

  @GetMapping("/")
  public String root(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
  	model.addAttribute("name", name);
    return "contact-master";
  }

  @GetMapping("/home")
  public String home(Model model) {
  	model.addAttribute("name", "hello");
    return "contact-home";
  }

  @GetMapping("/contact/add")
  public String add(Model model) {
  	model.addAttribute("contact", new Contact());
    return "contact-form";
  }

  @GetMapping("/contact/list")
  public String list(Model model) {
  	model.addAttribute("name", "hi");
    return "contact-list";
  }

  @PostMapping("/contact/post")
  @ResponseBody
  public ModelAndView post(Model model, @ModelAttribute Contact contact) {
  	this.contacts.add(contact);
  	model.addAttribute("name", "hi");
    return new ModelAndView("redirect:/");
  }
}
