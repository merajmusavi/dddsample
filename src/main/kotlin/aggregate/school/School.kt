package aggregate.school

import aggregate.school.valueobject.Address
import aggregate.school.valueobject.Name
import aggregate.school.valueobject.RegistrationNumber
import java.lang.IllegalArgumentException

class School private constructor() {
    lateinit var schoolName: Name
        private set
    lateinit var schoolAddress: Address
        private set
    lateinit var schoolRegistrationNumber: RegistrationNumber
        private set

    constructor(name: Name, address: Address, registrationNumber: RegistrationNumber) : this() {
        this.schoolName = name
        this.schoolAddress = address
        this.schoolRegistrationNumber = registrationNumber
    }

    companion object {
        internal fun makeNew(
            schoolName: String,
            schoolAddress: String,
            schoolRegistrationNumber: Long
        ): Result<School> {
            val resultName = Name.makeNew(schoolName)
            val resultAddress = Address.makeNew(schoolAddress)
            val resultRegistrationNumber = RegistrationNumber.makeNew(schoolRegistrationNumber)

            return if (resultName.isSuccess && resultAddress.isSuccess && resultRegistrationNumber.isSuccess) {
                Result.success(
                    School(
                        resultName.getOrNull()!!,
                        resultAddress.getOrNull()!!,
                        resultRegistrationNumber.getOrNull()!!
                    )
                )
            } else if (resultName.isFailure) {
                Result.failure(InvalidNameException("INVALID NAME EXCEPTION"))
            } else if (resultAddress.isFailure) {
                Result.failure(InvalidAddressException("INVALID ADDRESS EXCEPTION"))
            } else if (resultRegistrationNumber.isFailure) {
                Result.failure(InvalidRegistrationException("INVALID REGISTRATION EXCEPTION"))
            } else {
                Result.failure(IllegalArgumentException("unrecognizable error"))
            }


        }

        fun fill(name: String, address: String, registrationNumber: Long): School {
            return School(Name.fill(name), Address.fill(address), RegistrationNumber.fill(registrationNumber))
        }

        class InvalidNameException(message: String) : Exception(message)
        class InvalidAddressException(message: String) : Exception(message)
        class InvalidRegistrationException(message: String) : Exception(message)
    }


    fun changeName(name: String): Result<School> {
        val resultName = Name.makeNew(name).getOrThrow()

        this.schoolName = resultName

        return Result.success(this)
    }

    fun changeAddress(name: String): Result<School> {
        val resultAddress = Address.makeNew(name).getOrThrow()

        this.schoolAddress = resultAddress

        return Result.success(this)
    }



}