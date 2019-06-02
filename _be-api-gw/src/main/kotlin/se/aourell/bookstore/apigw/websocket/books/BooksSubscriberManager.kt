package se.aourell.bookstore.apigw.websocket.books

import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service
import se.aourell.bookstore.apigw.websocket.ClientSubscriber
import se.aourell.bookstore.books.cmd.es.BookCreatedEvent

@Service
class BooksSubscriberManager {
    private val subscribers = mutableListOf<ClientSubscriber>()

    @EventHandler
    fun on(evt: BookCreatedEvent) {
        subscribers.map { it.update(evt) }
    }

    fun subscribe(subscriber: ClientSubscriber) {
        subscribers.add(subscriber)
    }

    fun unsubscribe(subscriber: ClientSubscriber) {
        subscribers.remove(subscriber)
    }
}
