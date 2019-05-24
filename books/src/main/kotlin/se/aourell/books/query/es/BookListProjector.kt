package se.aourell.books.query.es

import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import se.aourell.books.cmd.es.BookCreatedEvent
import java.util.*
import javax.persistence.EntityManager

@Service
@Profile("query-docker")
class BookListProjector {
    @Autowired
    lateinit var em: EntityManager

    @EventHandler
    fun on(evt: BookCreatedEvent) {
        System.out.println("[BookListProjector] Event: BookCreatedEvent ${evt.id} ${evt.name}")
        val book = Book(evt.id, evt.name)
        em.persist(book)
    }

    fun getAll(): Collection<Book> =
            em.createQuery("select b from Book b").resultList as Collection<Book>

    fun getById(id: UUID): Book =
            em.find(Book::class.java, id)
}
