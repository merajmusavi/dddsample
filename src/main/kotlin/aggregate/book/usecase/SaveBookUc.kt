package aggregate.book.usecase

import aggregate.book.Book
import aggregate.book.model.command.SaveBookCmd
import common.CommonUseCase
import repository.BookRepository

class SaveBookUc(private val repository:BookRepository) : CommonUseCase<SaveBookCmd,Book> {
    override suspend fun execute(command: SaveBookCmd): Result<Book> {
        val result = Book.makeNew(command.name, command.author)

        return if (result.isSuccess) {
            Result.success(result.getOrNull()!!)
            repository.save(result.getOrNull())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }


    override fun toString(): String {
        return super.toString()
    }

}