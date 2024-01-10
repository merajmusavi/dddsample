package common

import aggregate.book.Book
import aggregate.student.Student
import sun.tools.jconsole.Plotter

interface CommonUseCase<COMMAND,UNI> {
   suspend fun execute(command:COMMAND): Result<UNI>
}