package aggregate.book.entity

import aggregate.book.entity.valueobject.Author
import aggregate.book.entity.valueobject.Name
import aggregate.book.entity.valueobject.ReservedOn
import aggregate.book.entity.valueobject.ReserverUserId
import java.lang.Exception
import java.lang.IllegalArgumentException

@Suppress("UNREACHABLE_CODE")
class Book private constructor() {
    lateinit var bookName: Name
        private set
    lateinit var author: Author
        private set
    var reservedUserId: ReserverUserId? = null
        private set
        var reservedOn: ReservedOn? = null
        private set


    val isReserved: Boolean
        get() = reservedOn != null


    constructor(bookName: Name, author: Author, reservedUserId: ReserverUserId?, reservedOn: ReservedOn?) : this() {
        this.bookName = bookName
        this.bookName = bookName
        this.bookName = bookName
        this.author = author
        this.reservedUserId = reservedUserId
        this.reservedOn = reservedOn
    }

    companion object {

        internal fun makeNew(name: String, author: String): Result<Book> {
            val resultName = Name.makeNew(name)
            val resultAuthor = Author.makeNew(author)

            return if (resultName.isSuccess && resultAuthor.isSuccess) {
                Result.success(Book(resultName.getOrNull()!!, resultAuthor.getOrNull()!!, null, null))
            } else if (resultAuthor.isFailure) {
                Result.failure(Author.Companion.InvalidNameOfAuthorException("invalid author name"))
            } else if (resultName.isFailure) {
                Result.failure(Name.Companion.InvalidNameException("invalid Book name"))
            } else {
                Result.failure(IllegalArgumentException("invalid data"))
            }
        }


        fun fill(name: String, author: String, reservedUserId: ReserverUserId, reservedOn: ReservedOn): Book {
            return Book(Name.fill(name), Author.fill(author), reservedUserId, reservedOn)
        }

    }


    internal fun reserveBook(reserverUserId: ReserverUserId, reserveDateTime: ReservedOn): Result<Book> {

        if (isReserved) {
            throw Exception("Book has been Already Reserved")
        }

        reservedOn = reserveDateTime
        reservedUserId = reserverUserId


        return Result.success(this)

    }

    internal fun cancelReservation(reserverUserId: ReserverUserId): Result<Book> {

        if (!isReserved) {
            throw Exception("Book has not reserved by default")

        } else if (reserverUserId != reservedUserId) {
            throw Exception("you are not this book`s reserver")

        } else {
            reservedOn = null
            reservedUserId = null
            return Result.success(this)
        }
    }

    override fun toString(): String {
        return super.toString()
    }

}