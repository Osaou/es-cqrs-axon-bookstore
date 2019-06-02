package se.aourell.bookstore.apigw.websocket

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.AbstractWebSocketHandler
import se.aourell.bookstore.apigw.websocket.books.BooksSubscriberManager

class WebSocketHandler (private val booksManager: BooksSubscriberManager) : AbstractWebSocketHandler(), ClientSubscriber {

    private var session: WebSocketSession? = null

    override fun afterConnectionEstablished(session: WebSocketSession) {
        this.session = session
        booksManager.subscribe(this)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        this.session = null
        booksManager.unsubscribe(this)
    }

    override fun handleTextMessage(session: WebSocketSession, msg: TextMessage) {
        System.out.println("message received from client: ${msg.toString()}")
        session.sendMessage(TextMessage("you sent: ${msg.toString()}"))
    }

    override fun update(evt: Any, json: String) {
        session?.sendMessage(TextMessage(json))
    }

}
