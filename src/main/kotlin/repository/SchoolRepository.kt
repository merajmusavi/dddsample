package repository

import aggregate.school.School

interface SchoolRepository {
    fun addSchool(school: School)

    fun findSchoolByRegistration(registrationId: Long):List<School>
    fun schoolExists(registrationId: Long):Boolean
    fun get(registrationId: Long):School?
    fun updateSchool(school: School)

    fun deleteSchoolById(registrationId: Long)
}