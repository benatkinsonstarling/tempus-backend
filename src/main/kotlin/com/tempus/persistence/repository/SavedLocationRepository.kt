package com.tempus.persistence.repository
import com.tempus.persistence.entity.SavedLocation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface SavedLocationRepository : PageableRepository<SavedLocation, Long> {

    fun existsByUserIdAndLatitudeAndLongitude(userId: String, latitude: Double, longitude: Double): Boolean
    fun findByUserId(userId: String): List<SavedLocation>
}