SOAP Web Service Troubleshooting Guide
Project Configuration Updates
pom.xml Modifications
Version Updates:

Spring Boot version changed to 3.1.5

Java version updated to 17.0.0

Dependencies Management:

Updated all dependencies to be compatible with Java 17 and Spring Boot 3.1.5

Replaced javax dependencies with jakarta:
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>4.0.0</version>
</dependency>
Plugin Configuration:

Modified the jaxb2-maven-plugin configuration:
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
Service Implementation
Request/Response Classes
ConvertCurrencyRequest:

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fromCurrency",
        "toCurrency",
        "amount"
})
@XmlRootElement(name = "ConvertCurrencyRequest", namespace = "http://example.com/currency")
public class ConvertCurrencyRequest {
    @XmlElement(namespace = "http://example.com/currency", required = true)
    private String fromCurrency;
    @XmlElement(namespace = "http://example.com/currency", required = true)
    private String toCurrency;
    @XmlElement(namespace = "http://example.com/currency")
    private double amount;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
ConvertCurrencyResponse:

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "convertedAmount"
})
@XmlRootElement(name = "ConvertCurrencyResponse", namespace = "http://example.com/currency")
public class ConvertCurrencyResponse {
    @XmlElement(namespace = "http://example.com/currency")
    private double convertedAmount;

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }


}
Web Service Configuration
Updated WebServiceConfig.java:
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "currency")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema currencySchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(currencySchema);
        definition.setLocationUri("/ws");
        definition.setPortTypeName("CurrencyPort");
        definition.setTargetNamespace("http://example.com/currency");
        return definition;
    }

    @Bean
    public XsdSchema currencySchema() {
        return new SimpleXsdSchema(new ClassPathResource("currency-converter.xsd"));
    }
}

Version Compatibility Matrix
Component	Version
Spring Boot	3.1.5
Java	17.0.0
Jakarta XML Bind	4.0.0
JAXB2 Maven Plugin	3.1.0
