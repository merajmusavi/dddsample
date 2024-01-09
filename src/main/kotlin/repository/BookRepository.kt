package repository

import aggregate.book.Book

interface BookRepository {
    fun saveBook(book: Book?) : Result<Book>
    fun findBookById(id: Long): Book
}