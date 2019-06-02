package se.aourell.bookstore.apigw.websocket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import se.aourell.bookstore.apigw.websocket.books.BooksSubscriberManager

@Configuration
@EnableWebSocket
class WebSocketConfiguration : WebSocketConfigurer {

    @Autowired
    private lateinit var booksManager: BooksSubscriberManager

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
                .addHandler(WebSocketHandler(booksManager), "/events")
                .setAllowedOrigins("*")
    }
}