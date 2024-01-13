package aggregate.student.usecase

import aggregate.student.Student
import aggregate.student.model.command.SaveStuDentCommand
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class SaveStudentUc(private val repository : StudentRepository) :CommonUseCase<SaveStuDentCommand,Unit> {


    override suspend fun execute(command: SaveStuDentCommand): Result<Unit> {
        val existingStudent = repository.get(command.idCard)
       return if (existingStudent == null){
           val student = Student.makeNew(command.name,command.age,command.idCard)
           repository.save(student.getOrThrow())
           Result.success(Unit)
        }else{
            Result.failure(IllegalArgumentException("EXCEPTION"))
       }
    }







}