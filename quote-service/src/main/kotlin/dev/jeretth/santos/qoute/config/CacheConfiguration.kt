package dev.jeretth.santos.qoute.config

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.map.IMap
import dev.jeretth.santos.qoute.dto.QuoteDto
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CacheConfiguration {

    @Bean
    fun quotesCache(i: HazelcastInstance): IMap<String, QuoteDto> = i.getMap("quotes")

}