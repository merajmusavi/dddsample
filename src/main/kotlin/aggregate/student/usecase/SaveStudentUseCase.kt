package aggregate.student.usecase

import aggregate.student.Student
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class SaveStudentUseCase(val repository : StudentRepository) :CommonUseCase<SaveStudentUseCase.SaveStuDentCommand,Student> {


    override suspend fun execute(command: SaveStuDentCommand): Result<Student> {
        val result = Student.makeNew(command.name,command.age,command.idCard)
       return if (result.isSuccess){
           Result.success(result.getOrNull()!!)
           repository.saveStudent(result.getOrNull()!!)

        }else{
            Result.failure(IllegalArgumentException("EXCEPTION"))
       }
    }





    data class SaveStuDentCommand(val name:String, val age:Int, val idCard:Long )


}