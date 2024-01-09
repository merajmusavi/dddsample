package aggregate.book.usecase

import aggregate.book.Book
import common.CommonUseCase

class SaveBookUseCase() : CommonUseCase<SaveBookUseCase.saveBookCommand> {
    override suspend fun execute(command: saveBookCommand): Result<Book> {
        val result = Book.makeNew(command.name, command.author)

        return if (result.isSuccess) {
            Result.success(result.getOrNull()!!)
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }


    override fun toString(): String {
        return super.toString()
    }

    data class saveBookCommand(var name: String, val author: String)
}