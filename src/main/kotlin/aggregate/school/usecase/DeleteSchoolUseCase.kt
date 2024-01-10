package aggregate.school.usecase

import aggregate.school.School
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class DeleteSchoolUseCase(val repo: SchoolRepository) : CommonUseCase<DeleteSchoolUseCase.DeleteSchool,School> {


    override suspend fun execute(command: DeleteSchool): Result<School> {
        val existingSchool = repo.findSchoolByRegistration(command.registrationId)

     return   if (existingSchool){
            repo.deleteSchoolById(command.registrationId)
            val deletedSchool = School.fill(command.name,command.address,command.registrationId)

            Result.success(deletedSchool)


        }else{
           Result.failure(IllegalArgumentException("NOT FOUND"))
        }

    }


    data class DeleteSchool(val name:String,val address:String,val registrationId: Long)


}