package aggregate.student.model.command

data class DeleteStudentCommand(val name: String, val age: Int, val idCard: Long)
