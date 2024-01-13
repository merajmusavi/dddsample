package aggregate.school.usecase

import aggregate.school.School
import aggregate.school.model.command.SaveSchool
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class SaveSchoolUseCase(private val repo:SchoolRepository):CommonUseCase<SaveSchool,Unit>{
    override suspend fun execute(command: SaveSchool): Result<Unit> {
        val result = School.makeNew(command.name,command.address,command.registrationNumber)
        return if (result.isSuccess){
            repo.addSchool(result.getOrNull()!!)
            Result.success(Unit)
        }else{
        Result.failure(IllegalArgumentException("exception"))
        }

    }


}