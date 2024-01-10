package repository

import aggregate.school.School

interface SchoolRepository {
    fun addSchool(school: School): Result<School>

    fun findSchoolByRegistration(registrationId: Long):Boolean
    fun updateSchool(school: School)

    fun deleteSchoolById(registrationId: Long)
}