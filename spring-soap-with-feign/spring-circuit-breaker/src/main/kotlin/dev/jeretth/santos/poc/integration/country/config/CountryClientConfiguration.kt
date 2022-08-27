package dev.jeretth.santos.poc.integration.country.config

import dev.jeretth.santos.poc.integration.country.CountryClient
import dev.jeretth.santos.poc.integration.country.decoder.CountryClientErrorDecoder
import feign.codec.Decoder
import feign.codec.Encoder
import feign.codec.ErrorDecoder
import feign.jaxb.JAXBContextFactory
import feign.soap.SOAPDecoder
import feign.soap.SOAPEncoder
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.context.annotation.Bean
import java.nio.charset.StandardCharsets

class CountryClientConfiguration {

    @Bean
    fun countryJaxbFactory(): JAXBContextFactory = JAXBContextFactory.Builder()
        .withMarshallerJAXBEncoding(StandardCharsets.UTF_8.name())
        .build()

    @Bean
    fun countryDecoder(): Decoder = SOAPDecoder(countryJaxbFactory())

    @Bean
    fun countryEncoder(): Encoder = SOAPEncoder(countryJaxbFactory())

    @Bean
    fun countryErrorDecoder(): ErrorDecoder = CountryClientErrorDecoder(countryJaxbFactory())

    @Bean
    fun fallbackFactory(): FallbackFactory<CountryClient> = CountryClientFallbackFactory()

}