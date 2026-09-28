package com.vpe

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlin.test.*

class ServerTest {
    @Test
    fun tasksCanBeFoundByPriority() = testApplication {
        configure()

        val response = client.get("/tasks/byPriority/Medium")
        val body = response.bodyAsText()

        assertEquals(HttpStatusCode.OK, response.status)
        assertContains(body, "Mow the lawn")
        assertContains(body, "Paint the fence")
    }

    @Test
    fun invalidPropertyProduces400() = testApplication {
        configure()

        val response = client.get("/tasks/byPriority/None")

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun unusedPropertyPropertyProduces404() = testApplication {
        configure()

        val response = client.get("/tasks/byPriority/Vital")

        assertEquals(HttpStatusCode.NotFound, response.status)
    }
}
