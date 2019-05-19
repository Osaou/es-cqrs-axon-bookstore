package se.aourell.axontest.bookqueries.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import se.aourell.axontest.bookqueries.queries.BookListProjector

@RestController
class Api {
    @Autowired
    private lateinit var bookListProjector: BookListProjector

    @GetMapping("/books")
    fun listBooks(): BookList {
        return BookList(bookListProjector.books)
    }
}
