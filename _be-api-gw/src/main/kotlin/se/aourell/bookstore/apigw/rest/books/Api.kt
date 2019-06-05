package se.aourell.bookstore.apigw.rest.books

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import se.aourell.bookstore.apigw.rest.ProxyApi


@RestController
class Api : ProxyApi() {

    @Value("\${SERVICE_BOOKS_QUERY:books-query}")
    val queryServer = ""
    val queryPort = 8080

    @Value("\${SERVICE_BOOKS_CMD:books-cmd}")
    val cmdServer = ""
    val cmdPort = 8080

    @GetMapping("/books/**")
    fun mirrorQueries(@RequestBody(required = false) body: String?, method: HttpMethod, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> =
            mirrorRest(body, method, request, response, queryServer, queryPort)

    @PostMapping("/books/**")
    fun mirrorCmds(@RequestBody(required = false) body: String?, method: HttpMethod, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> =
            mirrorRest(body, method, request, response, cmdServer, cmdPort)
}
