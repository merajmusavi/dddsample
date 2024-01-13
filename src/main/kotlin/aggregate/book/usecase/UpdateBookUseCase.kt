package aggregate.book.usecase

import aggregate.book.model.command.UpdateBookCommand
import common.CommonUseCase
import repository.BookRepository
import java.lang.IllegalArgumentException

class UpdateBookUseCase (private val repository: BookRepository) : CommonUseCase<UpdateBookCommand,Unit>{
    override suspend fun execute(command: UpdateBookCommand): Result<Unit> {
        val book = repository.get(command.reservedUserId!!)
        return if (book!=null){
            val resultBook = book.update(
                command.bookName,
                command.author,
                command.reservedUserId,
                command.reservedOn
            )
            resultBook.isFailure
            repository.updateBook(resultBook.getOrThrow())
            Result.success(Unit)
        }else{
            Result.failure(IllegalArgumentException(""))
        }
    }


}