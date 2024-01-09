package aggregate.student.valueobject

class IdCard private constructor(val value:Long){

    init {
        require(isValidCardNumber(value))
    }
    companion object{
        private fun isValidCardNumber(idCard: Long): Boolean {
            val idCardRegex = """^\d{8,20}$""".toRegex()
            return idCard.toString().matches(idCardRegex)
        }
        internal fun makeNew(idCard:Long) : Result<IdCard>{
            return if (isValidCardNumber(idCard))
            {
             Result.success(IdCard(idCard))
            }else{
                Result.failure(InvalidIdCardException("invalid id card exception"))
            }
        }
        fun fill(idCard: Long):IdCard{
            return IdCard(idCard)
        }

        class InvalidIdCardException(message:String) : Exception(message)
    }
}