package aggregate.school.usecase

import aggregate.school.School
import aggregate.school.model.command.DeleteSchool
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class DeleteSchoolUseCase(private val repo: SchoolRepository) : CommonUseCase<DeleteSchool, Unit> {


    override suspend fun execute(command: DeleteSchool): Result<Unit> {
        val existingSchool = repo.schoolExists(command.registrationId)

        return if (existingSchool) {
            repo.deleteSchoolById(command.registrationId)

            Result.success(Unit)


        } else {
            Result.failure(IllegalArgumentException("NOT FOUND"))
        }

    }




}