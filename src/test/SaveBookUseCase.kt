package aggregate.book.entity.usecase

import aggregate.book.entity.Book
import aggregate.book.entity.valueobject.Name
import common.ReserveBookIn
import repository.BookRepository

class SaveBookUseCase(private val bookRepository: BookRepository) : ReserveBookIn {
    override fun execute(book: Book): Result<Unit> {

    }

    private fun validateBook(book: Book) :Result<Unit>{
        val nameValidation = Result.success(Name.makeNew(book.bookName.value))
            .map {
                Result.success(Unit)
            }.recover {
                Result.failure(it)
            }
        return nameValidation.map {
            return it
        }
    }
}