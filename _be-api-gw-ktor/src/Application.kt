package se.aourell.axontest

import io.ktor.application.*
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.request.get
import io.ktor.client.response.readBytes
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*
import java.time.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(io.ktor.websocket.WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        proxyGet("/books", 8002)

        webSocket("/events") {
            send(Frame.Text("Hi from server"))
            while (true) {
                val frame = incoming.receive()
                if (frame is Frame.Text) {
                    send(Frame.Text("Client said: " + frame.readText()))
                }
            }
        }
    }
}

fun Routing.proxyGet(path: String, servicePort: Int) {
    val correctedPath = if (path.startsWith("/")) path else "/$path"
    get(correctedPath) {
        val client = HttpClient()
        val request = client.call("http://localhost:$servicePort$correctedPath") {
            method = HttpMethod.Get
        }
        call.respondOutputStream(contentType = request.response.contentType(), status = request.response.status) {
            write(request.response.readBytes())
        }
    }
}
