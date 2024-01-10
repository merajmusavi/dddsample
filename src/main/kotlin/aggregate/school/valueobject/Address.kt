package aggregate.school.valueobject

class Address private constructor(val value:String){

    init {
        require(isValidAddress(value))
    }


    companion object{
        fun isValidAddress(address: String): Boolean {
            val regex = """^[a-zA-Z0-9\s,.-]+$""".toRegex()
            return regex.matches(address)
        }
        internal fun makeNew(address:String):Result<Address>{
           return if (isValidAddress(address)){
               Result.success(Address(address))
           }else{
               return Result.failure(InvalidAddressException("invalid address exception"))
           }
        }
        fun fill(address:String) : Address{
            return Address(address)
        }

        class InvalidAddressException(message:String) : Exception(message)
    }

}