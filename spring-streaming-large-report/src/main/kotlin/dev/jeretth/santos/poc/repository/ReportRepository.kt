package dev.jeretth.santos.poc.repository

import dev.jeretth.santos.poc.data.entity.Report
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<Report, Long> {
}