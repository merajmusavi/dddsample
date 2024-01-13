package aggregate.student.usecase

import aggregate.student.model.command.DeleteStudentCm
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class DeleteStudentUc(private val repo: StudentRepository) :
    CommonUseCase<DeleteStudentCm, Unit> {


    override suspend fun execute(command: DeleteStudentCm): Result<Unit> {
        val existingStu = repo.get(command.idCard)
        return if (existingStu!= null) {
            repo.delete(command.idCard)
            return Result.success(Unit)

        } else {
            Result.failure(IllegalArgumentException("student not found"))
        }
    }



}