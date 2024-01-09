package common

import aggregate.book.Book

interface CommonUseCase<COMMAND> {
   suspend fun execute(command:COMMAND): Result<Book>
}