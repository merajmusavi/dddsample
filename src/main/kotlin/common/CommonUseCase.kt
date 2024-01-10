package common
interface CommonUseCase<COMMAND,UNI> {
   suspend fun execute(command:COMMAND): Result<UNI>
}