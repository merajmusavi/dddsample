package aggregate.book.usecase

import aggregate.book.Book
import aggregate.book.valueobject.ReservedOn
import aggregate.book.valueobject.ReserverUserId
import common.CommonUseCase
import repository.BookRepository
import java.lang.IllegalArgumentException
import java.time.Instant

class ReservationUseCase(private val bookRepository: BookRepository) : CommonUseCase<ReservationUseCase.ReservationCommand> {


    override suspend fun execute(command: ReservationCommand): Result<Book> {
        return when(command){
            is ReservationCommand.ReserveBook -> reserveBook(command)
            is ReservationCommand.CancelReservation -> cancelReservation(command)
        }
    }

    private suspend fun reserveBook(command: ReservationCommand.ReserveBook):Result<Book> {
        val book = bookRepository.findBookById(command.bookId)
        return if (book!=null){
            val reservedOn = ReservedOn.makeNew(Instant.now()).getOrNull()!!
            val reserverUserId = ReserverUserId.makeNew(command.reservedUserId).getOrNull()!!
            val result = book.reserveBook(reserverUserId,reservedOn)
            bookRepository.saveBook(result.getOrNull())
        }else{
            Result.failure(IllegalArgumentException(""))
        }
    }
    private suspend fun cancelReservation(command: ReservationCommand.CancelReservation):Result<Book> {
        val book = bookRepository.findBookById(command.bookId)
        return if (book != null){
            val result = book.cancelReservation(command.reserverUserID)
            bookRepository.saveBook(result.getOrNull())
        }else{
            Result.failure(IllegalArgumentException("error exception"))
        }
    }
    sealed class ReservationCommand{
        data class ReserveBook(val bookId : Long ,val reservedUserId: Long) : ReservationCommand()
        data class CancelReservation(val bookId: Long,val reserverUserID : Long) : ReservationCommand()

    }


}