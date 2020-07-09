package com.github.cristnascimento.contactlistapp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceHashMap implements ContactService {
   private static Map<Long, Contact> contactRepo = new HashMap<>();
   private long nextId = 4;
   static {
      Contact zapata = new Contact();
      zapata.setId(1);
      zapata.setFirstName("Natasha");
      zapata.setLastName("zapata");
      zapata.setEmail("zapata@blindspot.com");
      zapata.setPhone("+1 797 377243743");
      zapata.setPhoneCategory("Home");
      zapata.setAddress("57 Calgary St");
      zapata.setCity("New York");
      zapata.setState("MG");
      zapata.setZip("678678");
      zapata.setCloseFriend("");
      contactRepo.put(zapata.getId(), zapata);

      Contact reade = new Contact();
      reade.setId(2);
      reade.setFirstName("Edgar");
      reade.setLastName("Reade");
      reade.setEmail("reade@blindspot.com");
      reade.setPhone("+1 234 89743743");
      reade.setPhoneCategory("Work");
      reade.setAddress("288 5th Av");
      reade.setCity("New York");
      reade.setState("SP");
      reade.setZip("123413");
      reade.setCloseFriend("on");     
      contactRepo.put(reade.getId(), reade);

      Contact paty = new Contact();
      paty.setId(3);
      paty.setFirstName("William");
      paty.setLastName("Patterson");
      paty.setEmail("patterson@blindspot.com");
      paty.setPhone("+1 819 122543743");
      paty.setPhoneCategory("Home");
      paty.setAddress("5190 11th Av");
      paty.setCity("New York");
      paty.setState("SC");
      paty.setZip("98345923");
      paty.setCloseFriend("on");     
      contactRepo.put(paty.getId(), paty);
   }

   @Override
   public void createContact(Contact contact) {
      System.out.println("Size: "+ contactRepo.size());
      contact.setId(this.nextId);
      this.nextId += 1;
      contactRepo.put(contact.getId(), contact);
      System.out.println("Size: "+ contactRepo.size());
   }
   @Override
   public void updateContact(long id, Contact contact) {
      contactRepo.remove(id);
      contact.setId(id);
      contactRepo.put(id, contact);
   }
   @Override
   public void deleteContact(long id) {
      contactRepo.remove(id);
   }

   @Override
   public Contact getContact(long id) {
      return contactRepo.get(id);
   }

   @Override
   public Collection<Contact> getContacts() {
      return contactRepo.values();
   }
}