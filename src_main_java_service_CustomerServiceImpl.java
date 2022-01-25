@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
 
 CustomerDao customerDao;
 
 @Autowired
 public void setCustomerDao(CustomerDao customerDao) {
  this.customerDao = customerDao;
 }

 public List listAllCustomers() {
  return customerDao.listAllCustomers();
 }

 public void saveOrUpdate(Customer customer) {
  customerDao.saveOrUpdate(customer);
 }

 public Customer findCustomerById(int id) {
  return customerDao.findCustomerById(id);
 }

 public void deleteCustomer(int id) {
  customerDao.deleteCustomer(id);
 }

}

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List < Customer > getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer theCustomer) {
        customerDAO.saveCustomer(theCustomer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int theId) {
        return customerDAO.getCustomer(theId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }
}






/*3.3. @Service
We mark beans with @Service to indicate that they're holding the business logic. Besides being used in the service layer, there isn't any other special use for this annotation.*/



/*3.1  Spring Programmatic Transaction Management.
UserTransaction utx = entityManager.getTransaction(); 

try { 
    utx.begin(); 
    businessLogic();
    utx.commit(); 
} catch(Exception ex) { 
    utx.rollback(); 
    throw ex; 
} 

This way of managing transactions makes the scope of the transaction very clear in the code, but it has several disavantages:

it's repetitive and error prone
any error can have a very high impact
errors are hard to debug and reproduce
this decreases the readability of the code base
What if this method calls another transactional method?

3.2 Spring Declarative Transaction Management
Using Spring @Transactional
With Spring @Transactional, the above code gets reduced to simply this:

@Transactional
public void businessLogic() {
... use entity manager inside a transaction ...
}

This is much more convenient and readable, and is currently the recommended way to handle transactions in Spring.

By using @Transactional, many important aspects such as transaction propagation are handled automatically. In this case if another transactional method is called by businessLogic(), that method will have the option of joining the ongoing transaction.

One potential downside is that this powerful mechanism hides what is going on under the hood, making it hard to debug when things don't work.

From the above code, you can see that @Transactional annotation enables,

1. Rollback rules of the transactions

2. Transaction should be read-only

3. Isolation level of the transaction

4. Timeout of the operation wrapped by transaction

5. Propagation Type of the transaction*/

