package common

import aggregate.book.entity.Book
import aggregate.book.entity.valueobject.Author

interface CommonUseCase<COMMAND> {
   suspend fun execute(command:COMMAND): Result<Book>
}