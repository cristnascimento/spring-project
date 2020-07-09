package com.github.cristnascimento.contactlistapp;

import java.util.Collection;

public interface ContactService {
	public abstract void createContact(Contact contact);
	public abstract void updateContact(long id, Contact contact);
	public abstract void deleteContact(long id);
	public abstract Contact getContact(long id);
	public abstract Collection<Contact> getContacts();
}