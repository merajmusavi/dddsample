package aggregate.school.usecase

import aggregate.school.School
import aggregate.school.model.query.FindSchool
import aggregate.school.valueobject.RegistrationNumber
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class FindSchoolUseCase(val repo: SchoolRepository) : CommonUseCase<FindSchool, School> {
    override suspend fun execute(command: FindSchool): Result<School> {
        return try {
            if (RegistrationNumber.isValidRegistrationNumber(command.registrationId)) {
                val schoolExists = repo.schoolExists(command.registrationId)

                if (schoolExists) {
                    val school = repo.findSchoolByRegistration(command.registrationId)
                    Result.success(school)
                } else {
                    Result.failure(IllegalArgumentException("Book Not found"))
                }

            } else {
                Result.failure(IllegalArgumentException("Not valid Registration Id"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}