import aggregate.book.entity.usecase.SaveBookUseCase
import aggregate.book.entity.valueobject.Author
import aggregate.book.entity.valueobject.Name
import repository.BookRepository

fun main(args: Array<String>) {

val addBookUseCase = SaveBookUseCase()

    val result = addBookUseCase.execute("dsfdsfsfds","fdsfds")

    if (result.isSuccess){
        println(result.getOrNull()?.bookName.toString())
    }else{
        println("not true information")
    }

}
