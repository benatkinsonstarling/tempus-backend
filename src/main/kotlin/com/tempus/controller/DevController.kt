package com.tempus.controller

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.flywaydb.core.Flyway
import javax.sql.DataSource

@Controller("/api/admin/db")
@Requires(env = ["dev"])  // Only available in dev environment
class DevController(
    private val flyway: Flyway,
    private val dataSource: DataSource
) {
    @Post("/reset")
    fun resetDatabase(): HttpResponse<Map<String, String>> {
        flyway.clean()
        flyway.migrate()
        return HttpResponse.ok(mapOf("status" to "Database reset successfully"))
    }
}