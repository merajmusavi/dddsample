package aggregate.book.usecase

import aggregate.book.Book
import aggregate.book.model.command.SaveBookCommand
import common.CommonUseCase
import repository.BookRepository

class SaveBookUseCase(private val repository:BookRepository) : CommonUseCase<SaveBookCommand,Book> {
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

}