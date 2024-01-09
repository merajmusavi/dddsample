package aggregate.book.entity.valueobject

import java.time.Instant

data class ReservedOn private constructor(val value:Instant){

    init {
        require(isValidDate(value))
    }
    companion object{
        private fun isValidDate(date:Instant): Boolean {
            return date.isBefore(Instant.now()) || date.equals(Instant.now())
        }

        internal fun makeNew(date:Instant):Result<ReservedOn>{
           return if (isValidDate(date)){
                Result.success(ReservedOn(date))
            }else{
                Result.failure(InvalidDateException("invalid date exception"))
            }
        }
        fun fill(date:Instant):ReservedOn{
            return ReservedOn(date)
        }
        class InvalidDateException(message:String) : IllegalArgumentException(message)
    }

    override fun toString(): String {
        return value.toString()
    }
}