package aggregate.book.model.command

import java.time.Instant

data class UpdateBookCommand(
    val bookName: String?,
    val author: String?,
    val reservedUserId: Long?,
    val reservedOn: Instant?
)