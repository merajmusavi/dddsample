package aggregate.school.valueobject

class RegistrationNumber private constructor(val value: Long){

    init {
        require(isValidRegistrationNumber(value))
    }

    companion object{

        fun isValidRegistrationNumber(registrationNumber: Long): Boolean {
            val regex = """^\d{5,15}$""".toRegex()
            return regex.matches(registrationNumber.toString())
        }

       internal fun makeNew(registrationNumber: Long):Result<RegistrationNumber>{
            return if (isValidRegistrationNumber(registrationNumber)){
              Result.success(RegistrationNumber(registrationNumber))
            }else{
                Result.failure(InvalidRegistrationNumber("invalid registration number :("))
            }
        }

        fun fill(registrationNumber: Long) : RegistrationNumber{
            return RegistrationNumber(registrationNumber)
        }
        class InvalidRegistrationNumber(message : String): IllegalArgumentException(message)
    }
}