package aggregate.book.entity.usecase

import aggregate.book.entity.Book
import repository.BookRepository

class SaveBookUseCase() {

    fun execute(name: String, author: String):Result<Book> {
    val result = Book.makeNew(name,author)
        return if (result!!.isSuccess){
            Result.success(result.getOrNull()!!)
        }else{
            Result.failure(result.exceptionOrNull()!!)
        }

    }

    override fun toString(): String {
        return super.toString()
    }
}