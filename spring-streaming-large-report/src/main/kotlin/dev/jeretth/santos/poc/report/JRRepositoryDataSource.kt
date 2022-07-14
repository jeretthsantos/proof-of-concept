package dev.jeretth.santos.poc.report

import net.sf.jasperreports.engine.JRField
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

class JRRepositoryDataSource<T, ID>(
    private val repository: JpaRepository<T, ID>,
    private val pageable: Pageable
) : JRAbstractBeanDataSource(false) {

    private val log = LoggerFactory.getLogger(JRRepositoryDataSource::class.java)
    private var currentBean: T? = null
    private lateinit var pagedData: Page<T>
    private lateinit var content: MutableIterator<T>

    init {
        queryPage(pageable)
    }

    override fun next(): Boolean {
        if (pagedData.isEmpty) {
            return false
        }

        if (content.hasNext()) {
            currentBean = content.next()
            return true
        }

        if (pagedData.hasNext()) {
            queryPage(pagedData.nextPageable())
            currentBean = content.next()
            return true
        }

        return false
    }

    override fun moveFirst() {
        queryPage(pageable)
    }

    override fun getFieldValue(jrField: JRField?): Any {
        return getFieldValue(currentBean, jrField)
    }

    private fun queryPage(pageable: Pageable) {
        log.info("Querying page: ${pageable.pageNumber}")
        pagedData = repository.findAll(pageable)
        content = pagedData.content.iterator()
    }

}