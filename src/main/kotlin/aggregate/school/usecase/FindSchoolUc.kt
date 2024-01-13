package aggregate.school.usecase

import aggregate.school.School
import aggregate.school.model.qry.FindSchoolQry
import aggregate.school.valueobject.RegistrationNumber
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class FindSchoolUc(private val repo: SchoolRepository) : CommonUseCase<FindSchoolQry, School> {
    override suspend fun execute(command: FindSchoolQry): Result<School> {
        return try {
            if (RegistrationNumber.isValidRegistrationNumber(command.registrationId)) {
                val schoolExists = repo.exists(command.registrationId)

                if (schoolExists) {
                    val school = repo.findByRegistration(command.registrationId)
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