package aggregate.book.entity.valueobject

import javax.naming.InvalidNameException

data class Author private constructor(val value: String) {

    init {
        require(isValidName(value))
    }

    companion object {

        private fun isValidName(name: String): Boolean {
            val nameRegex = """^[a-zA-Z0-9\s]{2,50}$""".toRegex()
            return name.matches(nameRegex)
        }

        internal fun makeNew(author:String):Result<Author>{
            return if (isValidName(author)){
                Result.success(Author(author))
            }else{
                Result.failure(InvalidNameOfAuthorException("invalid name of author exception"))
            }
        }
        fun fill(author:String): Author{
            return Author(author)
        }
        class InvalidNameOfAuthorException(message:String) : Exception(message)

    }

    override fun toString(): String {
        return value
    }
}