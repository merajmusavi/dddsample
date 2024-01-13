package aggregate.book.usecase

import aggregate.book.Book
import common.CommonUseCase
import repository.BookRepository

class SaveBookUseCase(private val repository:BookRepository) : CommonUseCase<SaveBookUseCase.SaveBookCommand,Book> {
    override suspend fun execute(command: SaveBookCommand): Result<Book> {
        val result = Book.makeNew(command.name, command.author)

        return if (result.isSuccess) {
            Result.success(result.getOrNull()!!)
            repository.saveBook(result.getOrNull())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }


    override fun toString(): String {
        return super.toString()
    }

    data class SaveBookCommand(var name: String, val author: String)
}