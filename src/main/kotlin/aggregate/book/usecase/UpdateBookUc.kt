package aggregate.book.usecase

import aggregate.book.model.command.UpdateBookCmd
import common.CommonUseCase
import repository.BookRepository
import java.lang.IllegalArgumentException

class UpdateBookUc (private val repository: BookRepository) : CommonUseCase<UpdateBookCmd,Unit>{
    override suspend fun execute(command: UpdateBookCmd): Result<Unit> {
        val book = repository.get(command.reservedUserId!!)
        return if (book!=null){
            if (command.bookName!=null){
                book.changeName(command.bookName)
            }
            if (command.author != null){
                book.changeAuthor(command.author)
            }
            repository.updateBook(book)
            Result.success(Unit)
        }else{
            Result.failure(IllegalArgumentException("invalid argument exception"))
        }

    }


}