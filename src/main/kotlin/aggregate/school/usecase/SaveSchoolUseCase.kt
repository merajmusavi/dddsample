package aggregate.school.usecase

import aggregate.school.School
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class SaveSchoolUseCase(val repo:SchoolRepository):CommonUseCase<SaveSchoolUseCase.SaveSchool,School>{
    override suspend fun execute(command: SaveSchool): Result<School> {
        val result = School.makeNew(command.name,command.address,command.registrationNumber)
        return if (result.isSuccess){
            Result.success(result.getOrNull())
            repo.addSchool(result.getOrNull()!!)
        }else{
        Result.failure(IllegalArgumentException("exception"))
        }

    }

    data class SaveSchool(val name:String,val address:String,val registrationNumber:Long)

}