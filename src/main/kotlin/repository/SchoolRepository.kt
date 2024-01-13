package repository

import aggregate.school.School

interface SchoolRepository {
    fun save(school: School)

    fun findByRegistration(registrationId: Long):School
    fun exists(registrationId: Long):Boolean
    fun get(registrationId: Long):School?
    fun update(school: School)

    fun delete(registrationId: Long)


}