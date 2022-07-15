package dev.jeretth.santos.poc.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.thedeanda.lorem.LoremIpsum
import dev.jeretth.santos.poc.data.entity.Report
import dev.jeretth.santos.poc.report.JRRepositoryDataSource
import dev.jeretth.santos.poc.repository.ReportRepository
import net.sf.jasperreports.engine.JRParameter
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer
import net.sf.jasperreports.engine.util.JRSwapFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.ObjectOutputStream
import java.math.BigDecimal
import java.util.stream.LongStream
import kotlin.random.Random

interface V1ReportController {

    fun createReportValues(counter: Long): ResponseEntity<StreamingResponseBody>

    fun createReport(): ResponseEntity<StreamingResponseBody>

}

@RestController
internal class DefaultV1ReportController(
    private val repository: ReportRepository,
    @Value("\${report.invoice}") private val invoicePath: String
) : V1ReportController {

    private val lorem: LoremIpsum = LoremIpsum.getInstance()
    private val om: ObjectMapper = ObjectMapper()
    private val log: Logger = LoggerFactory.getLogger(DefaultV1ReportController::class.java)

    @PostMapping("/v1/reports/values")
    override fun createReportValues(@RequestParam("counter") counter: Long): ResponseEntity<StreamingResponseBody> {
        log.info("Generating report values")

        return ResponseEntity.status(OK).header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .body(StreamingResponseBody {
                val oos = ObjectOutputStream(it)
                LongStream.rangeClosed(0, counter).forEach { counter ->
                    log.info("Creating report value #$counter")
                    oos.use {
                        oos.write(
                            om.writeValueAsBytes(
                                repository.save(
                                    Report(
                                        description = lorem.getWords(10, 15),
                                        amount = BigDecimal.TEN.multiply(BigDecimal.valueOf(Random.nextLong(1, 1000)))
                                    )
                                )
                            )
                        )
                    }
                }
            })
    }


    @PostMapping("/v1/reports")
    override fun createReport(): ResponseEntity<StreamingResponseBody> {
        return ResponseEntity.status(OK)
            .header("Content-Type", "application/pdf")
            .body(StreamingResponseBody {
                val page: Pageable = PageRequest.of(0, 50, Sort.by(Order.asc("id")))
                val reportFileStream = ResourceUtils.getURL(invoicePath).openStream()
                val report = JasperCompileManager.compileReport(reportFileStream)
                val swap = JRSwapFile(System.getProperty("java.io.tmpdir"), 4096, 100)
                val virtualizer = JRSwapFileVirtualizer(5, swap)
                val params: MutableMap<String, Any> = HashMap()
                params[JRParameter.REPORT_VIRTUALIZER] = virtualizer

                val print = JasperFillManager.fillReport(report, params, JRRepositoryDataSource(repository, page))
                JasperExportManager.exportReportToPdfStream(print, it)
                virtualizer.cleanup()
                reportFileStream.close()
            })
    }

}