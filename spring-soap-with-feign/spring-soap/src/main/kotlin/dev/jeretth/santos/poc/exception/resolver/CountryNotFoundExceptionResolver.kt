package dev.jeretth.santos.poc.exception.resolver

import dev.jeretth.santos.poc.exception.CountryNotFoundException
import org.springframework.ws.soap.SoapFault
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver
import santos.jeretth.poc.soap.GetCountryFault
import santos.jeretth.poc.soap.ObjectFactory
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

class CountryNotFoundExceptionResolver : SoapFaultMappingExceptionResolver() {

    private val factory: ObjectFactory = ObjectFactory()
    private val marshaller: Marshaller = JAXBContext.newInstance(GetCountryFault::class.java).createMarshaller()

    override fun customizeFault(endpoint: Any?, ex: java.lang.Exception?, fault: SoapFault?) {
        if (ex is CountryNotFoundException) {
            val faultDetail = fault!!.addFaultDetail().result

            val countryFault = factory.createGetCountryFault()
            countryFault.statusCode = "40400"
            countryFault.validationMessage = "Country is not found"

            marshaller.marshal(countryFault, faultDetail)
        }
    }
}