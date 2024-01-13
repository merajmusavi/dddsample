package aggregate.student

import aggregate.student.valueobject.Age
import aggregate.student.valueobject.IdCard
import aggregate.student.valueobject.Name
import java.lang.IllegalArgumentException

class Student private constructor(){
    lateinit var studentName:Name
        private set
    lateinit var studentAge:Age
        private set
    lateinit var studentIdCard: IdCard
        private set

    constructor(name: Name, age: Age, idCard:IdCard):this(){
        this.studentAge = age
        this.studentIdCard = idCard
        this.studentName =  name
    }
    companion object{
        internal fun makeNew(name:String,age:Int,studentIdCard:Long) : Result<Student>{
            val resultName = Name.makeNew(name)
            val resultAge = Age.makeNew(age)
            val resultStudentIdCard = IdCard.makeNew(studentIdCard)
            return if (resultAge.isSuccess && resultName.isSuccess && resultStudentIdCard.isSuccess){
                Result.success(Student(resultName.getOrNull()!!,resultAge.getOrNull()!!,resultStudentIdCard.getOrNull()!!))
            }else{
                Result.failure(IllegalArgumentException("invalid student exception"))
            }
        }
        fun fill(name:String,age:Int,idCard:Long):Student{
            return Student(Name.fill(name),Age.fill(age),IdCard.fill(idCard))
        }
        class IllegalStudentException(message:String) :Exception(message)
    }

fun updateStudent(name:String,age:Int):Result<Student>{

    val nameResult = Name.makeNew(name)
    val ageResult = Age.makeNew(age)
    this.studentName = nameResult.getOrThrow()
    this.studentAge = ageResult.getOrThrow()

    return Result.success(this)

    }
}