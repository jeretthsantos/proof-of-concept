package dev.jeretth.santos.poc.exception

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault

@SoapFault(
    faultCode = FaultCode.CLIENT,
    faultStringOrReason = "Country is not found"
)
class CountryNotFoundException(
    override val message: String?
) : Exception(message)