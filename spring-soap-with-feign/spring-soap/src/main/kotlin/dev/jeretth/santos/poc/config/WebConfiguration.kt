package dev.jeretth.santos.poc.config

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema

@Configuration
@EnableWs
class WebConfiguration : WsConfigurerAdapter() {

    @Bean
    fun messageDispatcherServlet(context: ApplicationContext): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.isTransformWsdlLocations = true
        servlet.setApplicationContext(context)

        return ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*")
    }

    @Bean(
        name = [
            "countries"
        ]
    )
    fun defaultWsdl11Definition(): DefaultWsdl11Definition {
        val wsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("CountriesPort")
        wsdl11Definition.setLocationUri("/ws")
        wsdl11Definition.setTargetNamespace("http://jeretth.santos/poc/soap")
        wsdl11Definition.setSchema(countriesSchema())

        return wsdl11Definition
    }

    @Bean
    fun countriesSchema(): XsdSchema =
        SimpleXsdSchema(ClassPathResource("xsd/countries.xsd"))

}