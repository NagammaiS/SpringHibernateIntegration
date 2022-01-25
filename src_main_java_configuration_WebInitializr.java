public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

 @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { WebConfig.class};
		//WebMvcConfig class
    }
  
    @Override
    protected Class[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}