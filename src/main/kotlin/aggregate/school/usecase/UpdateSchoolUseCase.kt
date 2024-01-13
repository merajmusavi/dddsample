package aggregate.school.usecase

import aggregate.school.model.command.UpdateSchool
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class UpdateSchoolUseCase(private val repo: SchoolRepository) :
    CommonUseCase<UpdateSchool, Unit> {
    override suspend fun execute(command: UpdateSchool): Result<Unit> {
        val school = repo.get(command.registrationId)
        return if (school != null) {

            val resultSchool = school.update(
                command.name, command.address
            )
            resultSchool.isFailure


            repo.updateSchool(resultSchool.getOrThrow())
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("un recognizable error"))
        }
    }


}