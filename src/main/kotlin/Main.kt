import aggregate.book.usecase.SaveBookUseCase

suspend fun main(args: Array<String>) {

val addBookUseCase = SaveBookUseCase()

    val result = addBookUseCase.execute(SaveBookUseCase.saveBookCommand("sdfdsf","sdfds"))

    if (result.isSuccess){
        println(result.getOrNull()?.bookName.toString())
    }else{
        println("not true information")
    }

}
