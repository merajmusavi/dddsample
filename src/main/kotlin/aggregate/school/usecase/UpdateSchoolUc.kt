package aggregate.school.usecase

import aggregate.school.model.command.UpdateSchoolCm
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class UpdateSchoolUc(private val repo: SchoolRepository) :
    CommonUseCase<UpdateSchoolCm, Unit> {
    override suspend fun execute(command: UpdateSchoolCm): Result<Unit> {
        val school = repo.get(command.registrationId)
        return if (school != null) {
            if (command.name != null) {
                school.changeName(command.name)
            }
            if (command.address != null) {
                school.changeAddress(command.address)
            }
            repo.update(school)
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("un recognizable error"))
        }
    }


}