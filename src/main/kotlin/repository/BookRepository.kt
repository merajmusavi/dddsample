package repository

import aggregate.book.Book

interface BookRepository {
    fun save(book: Book?) : Result<Book>
    fun findById(id: Long): Book?

    fun get(id:Long): Book?

    fun exists(id:Long) : Boolean

    fun updateBook(book:Book)
}