package se.aourell.bookstore.apigw.rest.books

import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import se.aourell.bookstore.apigw.rest.ProxyApi


@RestController
class Api : ProxyApi {

    override val server = "books-query"
    override val port = 8080

    @RequestMapping("/books/**")
    override fun mirrorRest(@RequestBody(required = false) body: String?, method: HttpMethod, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> =
            super.mirrorRest(body, method, request, response)
}
