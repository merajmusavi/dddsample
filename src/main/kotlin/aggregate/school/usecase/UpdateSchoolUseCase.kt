package aggregate.school.usecase

import aggregate.school.School
import common.CommonUseCase
import repository.SchoolRepository
import repository.StudentRepository
import java.lang.IllegalArgumentException

class UpdateSchoolUseCase(val repo: SchoolRepository): CommonUseCase<UpdateSchoolUseCase.UpdateSchoolUseCase,School> {
    override suspend fun execute(command: UpdateSchoolUseCase): Result<School> {
        val existingStu = repo.findSchoolByRegistration(command.registrationId)
        return if (existingStu){


            val school = School.fill(command.name,command.address,command.registrationId)

            val updatedSchool = School(school.schoolName,school.schoolAddress,school.schoolRegistrationNumber)

            repo.updateSchool(updatedSchool)
            Result.success(updatedSchool)
        }else{
            Result.failure(IllegalArgumentException("un recognizable error"))
        }

     }





    data class UpdateSchoolUseCase(val name:String,val address:String,val registrationId:Long)


}