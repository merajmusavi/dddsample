package aggregate.book.valueobject

class Name private constructor(val value: String) {
    init {
        require(isValidName(value))
    }

    companion object{
       private fun isValidName(name:String) : Boolean{
           val nameRegex = """^[a-zA-Z0-9\s]{2,50}$""".toRegex()
           return name.matches(nameRegex)
       }

        internal fun makeNew(name:String) : Result<Name>{
            return if (isValidName(name)){
                Result.success(Name(name))
            }else{
                Result.failure(InvalidNameException("invalid name"))
            }
        }

        fun fill(name:String) : Name {
            return Name(name)
        }
        class InvalidNameException(message:String): Exception(message)
    }

    override fun toString(): String {
        return value
    }
}