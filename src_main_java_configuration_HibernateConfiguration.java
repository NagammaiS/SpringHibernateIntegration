@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.websystique.springmvc.configuration" })
@PropertySource(value = { "classpath:application/database.properties" })
public class HibernateConfiguration {
 
    @Autowired
    private Environment environment;
 
 
	//From here session is send to DAOImpl class to get session object SessionFactory.getSession() SessionFactory is autowired there
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.websystique.springmvc.model/entity class" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
     
	 //Used by above method sessionFactory()
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;

    }
       //Used by above method sessionFactory()
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;   		
    }
   
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}



//@ENABLETRANSACTIONMANAGEMENT
/*A database transaction is a sequence of actions that are treated as a single unit of work. These actions should either complete entirely or take no effect at all.
Spring 3.1 introduces the @EnableTransactionManagement annotation that we can use in a @Configuration class to enable transactional support:
The concept of transactions can be described with the following four key properties described as ACID −

Atomicity − A transaction should be treated as a single unit of operation, which means either the entire sequence of operations is successful or unsuccessful.

Consistency − This represents the consistency of the referential integrity of the database, unique primary keys in tables, etc.

Isolation − There may be many transaction processing with the same data set at the same time. Each transaction should be isolated from others to prevent data corruption.

Durability − Once a transaction has completed, the results of this transaction have to be made permanent and cannot be erased from the database due to system failure.

A real RDBMS database system will guarantee all four properties for each transaction. The simplistic view of a transaction issued to the database using SQL is as follows −

Begin the transaction using begin transaction command.

Perform various deleted, update or insert operations using SQL queries.

If all the operation are successful then perform commit otherwise rollback all the operations.*/


//@PROPERTYSOURCE
/*@PropertySource is a convenient annotation for including PropertySource to Spring's Environment and allowing to inject properties via @Value into class attributes. (PropertySource is an object representing a set of property pairs from a particular source.)

@PropertySource is used together with @Configuration.

The application uses Spring's @PropertySource to include properties from the application.properties file into the Environment and to inject them into class attributes.*/


//application.properties
resources/application.properties
app.name=My application
app.version=1.1
//

/*
//APPLICATION.JAVA
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public static void main(String[] args) {

        var ctx = new AnnotationConfigApplicationContext(Application.class);
        var app = ctx.getBean(Application.class);

        app.run();

        ctx.close();
    }

    private void run() {

        logger.info("From Environment");
        logger.info("Application name: {}", env.getProperty("app.name"));
        logger.info("Application version: {}", env.getProperty("app.version"));

        logger.info("Using @Value injection");
        logger.info("Application name: {}", appName);
        logger.info("Application version: {}", appVersion);
    }
}


In the Application, we get the properties using two methods.

@Autowired
private Environment env;
We inject the Environment. We can retrieve the properties with its getProperty() method.

@Value("${app.name}")
private String appName;

@Value("${app.version}")
private String appVersion;
We inject the properties with @Value annotation into the attributes.



*/