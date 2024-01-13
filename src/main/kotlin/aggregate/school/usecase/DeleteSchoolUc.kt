package aggregate.school.usecase

import aggregate.school.model.command.DeleteSchoolCm
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class DeleteSchoolUc(private val repo: SchoolRepository) : CommonUseCase<DeleteSchoolCm, Unit> {


    override suspend fun execute(command: DeleteSchoolCm): Result<Unit> {
        val existingSchool = repo.exists(command.registrationId)

        return if (existingSchool) {
            repo.delete(command.registrationId)

            Result.success(Unit)


        } else {
            Result.failure(IllegalArgumentException("NOT FOUND"))
        }

    }




}