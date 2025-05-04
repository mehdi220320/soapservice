package com.tekup.soapservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import
        org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
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