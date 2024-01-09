package aggregate.student.valueobject

class Age private constructor(val value:Int){

    init {
        require(isValidAge(value))
    }
    companion object{
        private fun isValidAge(age:Int):Boolean{
            return age in 7..18
        }
        fun makeNew(age : Int): Result<Age>{
          return  if (isValidAge(age)){
                Result.success(Age(age))
            }else{
                Result.failure(IllegalAgeException("age is not in the legal range"))
            }
        }
        fun fill(age: Int):Age{
            return Age(age)
        }
        class IllegalAgeException(message:String) : IllegalArgumentException(message)
    }
}