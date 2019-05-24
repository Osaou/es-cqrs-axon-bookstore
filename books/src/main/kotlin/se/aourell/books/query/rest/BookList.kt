package se.aourell.books.query.rest

import se.aourell.books.query.es.Book

data class BookList (val books: List<Book>)
