package se.aourell.books.query.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import se.aourell.books.query.es.Book
import se.aourell.books.query.es.BookListProjector
import java.util.*

@RestController
@Profile("query-docker")
class Api {
    @Autowired
    private lateinit var bookListProjector: BookListProjector

    @GetMapping("/books")
    fun listBooks(): Collection<Book> =
            bookListProjector.getAll()

    @GetMapping("/books/:id")
    fun singleBook(@PathVariable id: UUID): Book =
            bookListProjector.getById(id)
}
