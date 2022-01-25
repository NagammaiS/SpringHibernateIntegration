@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")//full folder
public class AppConfig {
     
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
	 //If there are message .properties file for validation use this in bean class use @Size,@Notnull for annotations
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
	
	//For static resources .jss,.css
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}

/*
3. Spring MVC Using Java Configuration
To enable Spring MVC support through a Java configuration class, all we have to do is add the @EnableWebMvc annotation:

@EnableWebMvc
@Configuration
public class WebConfig {

    /// ...
}
This will set up the basic support we need for an MVC project, such as registering controllers and mappings, type converters, validation support, message converters and exception handling.

If we want to customize this configuration, we need to implement the WebMvcConfigurer interface:

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("index");
   }
   @Override
   public void addFormatters(FormatterRegistry formatterRegistry) {
         formatterRegistry.addConverter(new MyConverter());
     }

   @Override
   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
         converters.add(new MyHttpMessageConverter());
     }
   @Bean
   public ViewResolver viewResolver() {
      InternalResourceViewResolver bean = new InternalResourceViewResolver();

      bean.setViewClass(JstlView.class);
      bean.setPrefix("/WEB-INF/view/");
      bean.setSuffix(".jsp");

      return bean;
   }
}
In this example, we've registered a ViewResolver bean that will return .jsp views from the /WEB-INF/view directory.

Very important here is that we can register view controllers that create a direct mapping between the URL and the view name using the ViewControllerRegistry. This way, there's no need for any Controller between the two.*/


//Note: only one @Configuration class may have the @EnableWebMvc annotation to import the Spring Web MVC configuration. There can however be multiple @Configuration classes implementing WebMvcConfigurer in order to customize the provided configuration.