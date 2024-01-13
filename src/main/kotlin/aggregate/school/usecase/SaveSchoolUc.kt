package aggregate.school.usecase

import aggregate.school.School
import aggregate.school.model.command.SaveSchoolCm
import common.CommonUseCase
import repository.SchoolRepository
import java.lang.IllegalArgumentException

class SaveSchoolUc(private val repo:SchoolRepository):CommonUseCase<SaveSchoolCm,Unit>{
    override suspend fun execute(command: SaveSchoolCm): Result<Unit> {
        val result = School.makeNew(command.name,command.address,command.registrationNumber)
        return if (result.isSuccess){
            repo.save(result.getOrNull()!!)
            Result.success(Unit)
        }else{
        Result.failure(IllegalArgumentException("exception"))
        }

    }


}