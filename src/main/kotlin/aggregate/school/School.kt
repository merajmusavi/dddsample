package aggregate.school

import aggregate.school.valueobject.Address
import aggregate.school.valueobject.Name
import aggregate.school.valueobject.RegistrationNumber

class School private constructor(){
    lateinit var schoolName:Name
        private set
    lateinit var schoolAddress: Address
        private set
    lateinit var schoolRegistrationNumber: RegistrationNumber
        private set

    constructor(name:Name, address:Address, registrationNumber:RegistrationNumber) : this(){
        this.schoolName = name
        this.schoolAddress = address
        this.schoolRegistrationNumber = registrationNumber
    }

    companion object {
        internal fun makeNew(){

        }
    }


}