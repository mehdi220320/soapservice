# TP SOAP troubleshooting
## pox.xml 
- Change Spring Boot version to 3.4.4
- Change java version to 17.0.0
- Change dependencies version to be comppatible with the java 17 and spring boot 3.4.4 
- Dependencies : Change javax dependency to jakarta :

      <dependency>
          <groupId>jakarta.xml.bind</groupId>
          <artifactId>jakarta.xml.bind-api</artifactId>
      </dependency>

- Plugins : delete the plugin version to be by default and edit it as follows :
            
                  <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>jaxb2-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>xjc</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <schemaDirectory>src/main/resources/</schemaDirectory>
                    <outputDirectory>src/main/java</outputDirectory>
                </configuration>
            </plugin>

## Add ConvertCurrencyRequest and ConvertCurrencyResponse 
  -  Create the following two classes with attributes corresponding to the elements defined in currency-converter.xsd:
     -ConvertCurrencyRequest
     -ConvertCurrencyResponse

-Annotate each class with:
    @XmlAccessorType
    @XmlType
## WebServiceConfig.java :
- Modify the webServiceConfig and make it as follows:        
   -   @EnableWs
    -  @Configuration
     - public class WebServiceConfig extends WsConfigurerAdapter {
      -    @Bean
       -   public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        -      MessageDispatcherServlet servlet = new MessageDispatcherServlet();
         -     servlet.setApplicationContext(context);
          -    servlet.setTransformWsdlLocations(true);
           -   return new ServletRegistrationBean<>(servlet, "/ws/*");
          -}
      
     -     @Bean(name = "currency")
      -    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema currencySchema) {
       -       DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        -      definition.setSchema(currencySchema);
         -     definition.setLocationUri("/ws");
          -    definition.setPortTypeName("CurrencyPort");
           -   definition.setTargetNamespace("http://example.com/currency");
            -  return definition;
          -}
      
        -  @Bean
         - public XsdSchema currencySchema() {
          -    return new SimpleXsdSchema(new ClassPathResource("currency-converter.xsd"));
          -}
      -}
 
