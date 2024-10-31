package com.tempus.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant


@MappedEntity("user")
data class User (

    @Id
    @GeneratedValue
    var id: Long? = null,

    @DateCreated
    val createdAt: Instant? = Instant.now()
)