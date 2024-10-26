# CourseConnect

CourseConnect is a Course Management System designed to facilitate the creation and enrollment of courses for teachers and students. This system allows teachers to manage courses efficiently while enabling students to connect and enroll in courses of their interest.

## Features

### Course Management (CRUD)
- **Create Courses**: Teachers can create new courses by providing essential details such as course name, description, and duration.
- **View Courses**: Teachers can view all available courses and their details.
- **Update Courses**: Teachers can update course information (e.g., adding prerequisites or changing schedules).
- **Delete Courses**: Teachers can delete a course if there are no enrolled students.
### Student Management (CRUD)
- **Register Students**: Students can enroll in any course without any restrictions after they create an account.
- **View Students**: Students can view a list of all courses.
- **Update Students**: Students can update their information as needed.
- **Delete Students**: Students can delete their profile if they are not enrolled in any active courses and don t want that account anymore.

### Enrollment Activity
- **Enroll in Courses**: Students can enroll in available courses, updating the course's enrollment count.
- **Capacity Management**: If a course reaches its maximum capacity, the system will prevent additional enrollments.
- **Withdraw from Courses**: Students can withdraw from courses, which will update the enrollment count accordingly.

### Optional Extension: Enrollment Records
- An entity for Enrollment Records can be added to track when students enroll in and withdraw from courses, creating relationships between Course and Student entities.

