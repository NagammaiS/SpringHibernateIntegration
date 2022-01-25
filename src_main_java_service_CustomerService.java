public interface CustomerService {

    public List < Customer > getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);

    public void deleteCustomer(int theId);

}


public interface CustomerService {
 
 public List listAllCustomers();
 
 public void saveOrUpdate(Customer customer);
 
 public Customer findCustomerById(int id);
 
 public void deleteCustomer(int id);
 
}
