package se.aourell.bookstore.books.query.rest

import se.aourell.bookstore.books.query.es.Book

data class BookList (val books: List<Book>)
