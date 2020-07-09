# Contact list with Spring Boot, Thymeleaf and Bootstrap

## Description

## Dependencies

* Ubuntu 18.04
* Java 8
* Maven
* Spring Boot
* Bootstrap

## Create Spring Boot project

* Set up and get your project with [Spring Initializr](https://start.spring.io/)
  * Maven
  * Java
  * Spring Boot 2.3.1
  * Group: com.github.cristnascimento
  * Artifact: contact-list-app
  * Name: contact-list-app
  * Packaging: jar
  * Java 8
  * Dependencies: Spring Web
* Click __Generate__

## Unpacking your project

```
$ unzip contact-list-app.zip
$ cd contact-list-app
$ ./mvnw spring-boot:run
```

Your files will stay here
```
src/main/java/com/github/cristnascimento/contactlistapp
```


## Start with hello world

create new file ContactListAppController.java

```java
package com.github.cristnascimento.contactlistapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ContactListAppController {

  @GetMapping("/")
  public String home() {
    return "Hello world!";
  }
}
```

Access http://localhost:8080

## Web content with Thymeleaf

Check [this tutorial](https://spring.io/guides/gs/serving-web-content/) on [Spring.io](https://spring.io)

add dependency in pom.xml
```
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

create your template src/main/resources/templates/contact-master.html

```xml
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```

It is important that html file is well formatted.

The following code is required, don't forget

```xml
<html xmlns:th="http://www.thymeleaf.org">
```

And the value must be within a html tag

```xml
<p th:text="'Hello, ' + ${name} + '!'" />
```



render your new template passing paramaters in ContactListController.java

```java
package com.github.cristnascimento.contactlistapp;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactListAppController {

  @GetMapping("/")
  public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    model.addAttribute("name", name);
    return "contact-master";
  }
```

Note that we have changed _@RestController_ to _@Controller_.

The line

```java
return "contact-master";
```

Is not returning a string anymore. It is rendering the file **_src/main/resources/templates/contact-master.html_**

Access the page http://localhost:8080/?name=John

## Create a layout with Thymeleaf

edit your contact-master.html

```xml
<body>
<div layout:fragment="content"></div>
...
</body>
```

edit your contact-form.html

```xml
<html xmlns:layout="http://www.w3.org/1999/xhtml" layout:decorate="~{contact-master}">
...
<div layout:fragment="content">
...
</div>
```

Add the reference to the dependency in pom.xml

```xml
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
    <version>2.3.0</version>
</dependency>
```

Learn more reading [this article](https://medium.com/@omeryazir/layout-with-thymeleaf-on-spring-boot-b604a46e7265) from Medium and [Thymeleaf Layout Documentation](https://www.thymeleaf.org/doc/articles/layouts.html).

## Handling form submission

create a class to hold the form attributes. Here it will be _Contact.java_

```java
package com.github.cristnascimento.contactlistapp;

public class Contact {

  private long id = 0;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String phoneCategory;
  private String address;
  private String city;
  private String state;
  private String closeFriend;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }
...
```

Ajust your to controller to send an empty object on GET method and to receive the object on POST method

```java
package com.github.cristnascimento.contactlistapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactListAppController {
...
  @GetMapping("/contact/add")
  public String add(Model model) {
    model.addAttribute("contact", new Contact());
    return "contact-form";
  }

  @PostMapping("/contact/post")
  public String post(@ModelAttribute Contact contact) {
    ...
  }
...
}
```
Edit the form to bind its controls with Contact attributes _contact-form.html_

```html
  <form action="#" th:action="@{/contact/post}" th:object="${contact}" method="post">
  ...
      <p>Id: <input type="text" th:field="*{id}" /></p>
      <p>Message: <input type="text" th:field="*{content}" /></p>
  ...

      <option th:value="Home">Home</option>
  ...
```
Learn more about Handling [Form Submission](https://spring.io/guides/gs/handling-form-submission/).

## Redirect with ModelAndView

```java
import org.springframework.web.servlet.ModelAndView;
...
@PostMapping("/contact/post")
@ResponseBody
public ModelAndView post(@ModelAttribute Contact contact) {
  this.contacts.add(contact);
  return new ModelAndView("redirect:/");
}
```

## Create a Service

create ContactService.java interface

```java
package com.github.cristnascimento.contactlistapp;

import java.util.Collection;

public interface ContactService {
   public abstract void createContact(Contact contact);
   public abstract void updateContact(long id, Contact contact);
   public abstract void deleteContact(long id);
   public abstract Collection<Contact> getContacts();
}
```

implement the interface ContactServiceHashMap.java

```java
package com.github.cristnascimento.contactlistapp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceHashMap implements ContactService {
   private static Map<Long, Contact> contactRepo = new HashMap<>();
   static {
      Contact honey = new Contact();
      honey.setId(1);
      honey.setName("Honey");
      contactRepo.put(honey.getId(), honey);

      Contact almond = new Contact();
      almond.setId(2);
      almond.setName("Almond");
      contactRepo.put(almond.getId(), almond);
   }
   @Override
   public void createContact(Contact contact) {
      contactRepo.put(contact.getId(), contact);
   }
   @Override
   public void updateContact(long id, Contact contact) {
      contactRepo.remove(id);
      contact.setId(id);
      contactRepo.put(id, contact);
   }
...
```
Here we included the repository (HashMap) inside the service. Later we can create a DAO class to access a repository. Then, the service can use the DAO.

autowire the service in the ContactListAppController.java

```java
import org.springframework.beans.factory.annotation.Autowired;
...
@Controller
public class ContactListAppController {
  @Autowired
  ContactService contactService;
...
```

use the service in the ContactListAppController.java

```java
contactService.createContact(contact);
```

no need to create a service object with the **new** operator.
See [this tutorial](https://www.tutorialspoint.com/spring_boot/spring_boot_service_components.htm) on [TutorialsPoint](https://www.tutorialspoint.com).
## Troubleshooting

Verify if the HTML is well-formatted.

## Learn More

* [Spring Framework](http://spring.io)


<html xmlns:layout="http://www.w3.org/1999/xhtml" layout:decorate="~{contact-master}">
<div layout:fragment="content" class="container col-md-6">




  {{#if message}}
            <div class="alert {{messageClass}}" role="alert">
                {{message}}
            </div>
        {{/if}}