package dev.jeretth.santos.poc.integration.country.decoder

import dev.jeretth.santos.poc.exception.NotFoundException
import feign.Response
import feign.jaxb.JAXBContextFactory
import feign.soap.SOAPErrorDecoder
import santos.jeretth.poc.soap.GetCountryFault
import javax.xml.bind.Unmarshaller
import javax.xml.ws.soap.SOAPFaultException

class CountryClientErrorDecoder(
    countryJaxbFactory: JAXBContextFactory
) : SOAPErrorDecoder() {

    private val unmarshaller: Unmarshaller = countryJaxbFactory.createUnmarshaller(GetCountryFault::class.java)

    override fun decode(methodKey: String?, response: Response?): Exception {
        val exception = super.decode(methodKey, response)

        if (exception is SOAPFaultException) {
            val faultDetail = unmarshaller.unmarshal(exception.fault.detail.firstChild) as GetCountryFault
            return NotFoundException("Status: ${faultDetail.statusCode}, Message: ${faultDetail.validationMessage}")
        }

        return exception
    }

}