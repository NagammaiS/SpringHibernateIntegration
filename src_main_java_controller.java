https://www.javaguides.net/2018/11/spring-mvc-5-hibernate-5-jsp-mysql-crud-tutorial.html

https://www.jackrutorial.com/2018/02/spring-4-mvc-hibernate-mysql-database-maven-crud-operations-integration-using-annotation-tutorial.html

@Controller
@RequestMapping(value="/customer")
public class CustomerController {

 @Autowired
 CustomerService customerService;
 
 @RequestMapping(value="/list", method=RequestMethod.GET)
 public ModelAndView list(){

//ModelAndView("customer/list") accepts String View Name 
  ModelAndView model = new ModelAndView("customer/list");
  List list = customerService.listAllCustomers();
  //list.jsp uses the list passed from here 
  //ModelAndView.addObject(String Attributename , String AtrributeValue)
  //attributeName name of the object to add to the model (never null)
  //attributeValue object to add to the model (can be null)

  model.addObject("list", list);
  
  return model;
 }
 
 @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
 public ModelAndView update(@PathVariable("id") int id){
  ModelAndView model = new ModelAndView("customer/form");
  Customer customer = customerService.findCustomerById(id);
  model.addObject("customerForm", customer);
  
  return model;
 }
 
 @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
 public ModelAndView delete(@PathVariable("id") int id){
  customerService.deleteCustomer(id);
  //Goes to list() method 
  return new ModelAndView("redirect:/customer/list");
 }
 
 @RequestMapping(value="/add", method=RequestMethod.GET)
 public ModelAndView add(){
  ModelAndView model = new ModelAndView("customer/form");
  Customer customer = new Customer();
  model.addObject("customerForm", customer);
  return model;
 }
 
 @RequestMapping(value="/save", method=RequestMethod.POST)
 public ModelAndView save(@ModelAttribute("customerForm") Customer customer){
  customerService.saveOrUpdate(customer);
  return new ModelAndView("redirect:/customer/list");
 }
}



@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List < Customer > theCustomers = customerService.getCustomers();
		//Here customers is used in the list-customers.jsp page to get the values , theCustomers is the list 
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Customer theCustomer = new Customer(); 
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }
//Differnece between @ModelAttribute and @ResponseBody
/*The simplest way for my understanding is, the @ModelAttribute will take a query string. so, all the data are being pass to the server through the url.

As for @RequestBody, all the data will be pass to the server through a full JSON body.*/

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
        Model theModel) {
        Customer theCustomer = customerService.getCustomer(theId);
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }
}


//Difference between @RequestParam and @PathVariable 
/*While @RequestParams extract values from the query string, @PathVariables extract values from the URI path:

@GetMapping("/foos/{id}")
@ResponseBody
public String getFooById(@PathVariable String id) {
    return "ID: " + id;
}
Then we can map based on the path:

http://localhost:8080/spring-mvc-basics/foos/abc
----
ID: abc
And for @RequestParam, it will be:

@GetMapping("/foos")
@ResponseBody
public String getFooByIdUsingQueryParam(@RequestParam String id) {
    return "ID: " + id;
}
which would give us the same response, just a different URI:

http://localhost:8080/spring-mvc-basics/foos?id=abc
----
ID: abc
*/




//MODEL ATTRIBUTE 

// UserController.java

This is our controller class in which we used @ModelAttribute and method parameters to fetch the model data into view file.

package com.studytonight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.studytonight.model.User;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@PostMapping("save")
	//From the form user is passed here and from here it is passed to response 
	public String save(@ModelAttribute("user") User user, Model model) {	
		model.addAttribute("user", user);
		return "response";
	}
}


/ index.jsp

In this JSP page, we used modelAttribute to map with the @ModelAttribute parameter to bind the form data with the model.

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>
<body>
	<form:form action="save" method="post" modelAttribute="user">
		User Name: <form:input type="text" path="name" />
		<br>
		<br>
		Email: <form:input type="email" path="email" style="margin-left:34px;" />
		<br>
		<br> 
		Password: <form:input type="password" path="password"  style="margin-left:10px;" />
		<br>
		<br>
		Confirm Password: <form:input type="password" path="confirm_password" />
		<br>
		<br>
		<input type="submit" value="Register">
	</form:form>
</body>
</html>


// response.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Response User Data</title>
</head>
<body>
	<h2>${user.name}</h2>
	<p>Email: ${user.email}</p>
	<p>Password: ${user.password}</p>
</body>
</html>