package aggregate.book.entity.valueobject

data class ReserverUserId private constructor(val value: Long) {

    init {
        require(isValidUserId(value))
    }
    companion object{


        private fun isValidUserId(userId:Long):Boolean{
            val userIdRegex = "^[0-9]{8,20}\$\n".toRegex()
            val userid = userId.toString()
            return userid.matches(userIdRegex)
        }

        internal fun makeNew(userId: Long):Result<ReserverUserId>{
            return if (isValidUserId(userId)){
                Result.success(ReserverUserId(userId))
            }else{
                Result.failure(InvalidUserIdException("invalid user id exception"))
            }
        }

        fun fill(userId: Long):ReserverUserId{
            return ReserverUserId(userId)
        }



        class InvalidUserIdException(message:String) : Exception(message)
    }

    override fun toString(): String {
        return value.toString()
    }

}