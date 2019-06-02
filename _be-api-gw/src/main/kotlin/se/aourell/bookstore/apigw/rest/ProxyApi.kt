package se.aourell.bookstore.apigw.rest

import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


interface ProxyApi {

    val server: String
    val port: Int

    fun mirrorRest(body: String?, method: HttpMethod, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> {
        val requestUrl = request.requestURI

        val uri = UriComponentsBuilder.fromUri(URI("http", null, server, port, null, null, null))
                .path(requestUrl)
                .query(request.queryString)
                .build(true).toUri()

        val headers = HttpHeaders()
        val headerNames = request.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement()
            headers.set(headerName, request.getHeader(headerName))
        }

        val httpEntity = HttpEntity(body, headers)
        val restTemplate = RestTemplate()
        return try {
            restTemplate.exchange(uri, method, httpEntity, String::class.java)
        } catch (e: HttpStatusCodeException) {
            ResponseEntity.status(e.rawStatusCode)
                    .headers(e.responseHeaders)
                    .body(e.responseBodyAsString)
        }
    }
}
