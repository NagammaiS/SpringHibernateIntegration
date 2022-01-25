@Repository
public class CustomerDaoImpl implements CustomerDao {

 @Autowired
 private SessionFactory sessionFactory;

 protected Session getSession(){
  return sessionFactory.getCurrentSession();
 }
 
 @SuppressWarnings("unchecked")
 public List listAllCustomers() {
  Criteria criteria = getSession().createCriteria(Customer.class);
  return (List) criteria.list();
 }

 public void saveOrUpdate(Customer customer) {
  getSession().saveOrUpdate(customer);
 }

 public Customer findCustomerById(int id) {
  Customer customer = (Customer) getSession().get(Customer.class, id);
  return customer;
 }

 public void deleteCustomer(int id) {
  Customer customer = (Customer) getSession().get(Customer.class, id);
  getSession().delete(customer);
 }
}

@Repository
//@Repository’s job is to catch persistence-specific exceptions and re-throw them as one of Spring’s unified unchecked exceptions.
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List < Customer > getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery < Customer > cq = cb.createQuery(Customer.class);
        Root < Customer > root = cq.from(Customer.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer book = session.byId(Customer.class).load(id);
        session.delete(book);
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer theCustomer = currentSession.get(Customer.class, theId);
        return theCustomer;
    }
}






/*CRITERIA
Hibernate provides alternate ways of manipulating objects and in turn data available in RDBMS tables. One of the methods is Criteria API, which allows you to build up a criteria query object programmatically where you can apply filtration rules and logical conditions.

The Hibernate Session interface provides createCriteria() method, which can be used to create a Criteria object that returns instances of the persistence object's class when your application executes a criteria query.

Following is the simplest example of a criteria query is one, which will simply return every object that corresponds to the Employee class.

Criteria cr = session.createCriteria(Employee.class);
List results = cr.list();
Restrictions with Criteria
You can use add() method available for Criteria object to add restriction for a criteria query. Following is the example to add a restriction to return the records with salary is equal to 2000 −

Criteria cr = session.createCriteria(Employee.class);
cr.add(Restrictions.eq("salary", 2000));
List results = cr.list();
Following are the few more examples covering different scenarios and can be used as per the requirement −

Criteria cr = session.createCriteria(Employee.class);

// To get records having salary more than 2000
cr.add(Restrictions.gt("salary", 2000));

// To get records having salary less than 2000
cr.add(Restrictions.lt("salary", 2000));

// To get records having fistName starting with zara
cr.add(Restrictions.like("firstName", "zara%"));

// Case sensitive form of the above restriction.
cr.add(Restrictions.ilike("firstName", "zara%"));

// To get records having salary in between 1000 and 2000
cr.add(Restrictions.between("salary", 1000, 2000));

// To check if the given property is null
cr.add(Restrictions.isNull("salary"));

// To check if the given property is not null
cr.add(Restrictions.isNotNull("salary"));

// To check if the given property is empty
cr.add(Restrictions.isEmpty("salary"));

// To check if the given property is not empty
cr.add(Restrictions.isNotEmpty("salary"));
You can create AND or OR conditions using LogicalExpression restrictions as follows −

Criteria cr = session.createCriteria(Employee.class);

Criterion salary = Restrictions.gt("salary", 2000);
Criterion name = Restrictions.ilike("firstNname","zara%");

// To get records matching with OR conditions
LogicalExpression orExp = Restrictions.or(salary, name);
cr.add( orExp );

// To get records matching with AND conditions
LogicalExpression andExp = Restrictions.and(salary, name);
cr.add( andExp );

List results = cr.list();
Though all the above conditions can be used directly with HQL as explained in previous tutorial.*/


