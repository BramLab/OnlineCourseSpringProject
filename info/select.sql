select id, username, role, email from user;

select id, instructor_id, title, description, start_date, end_date from course;

select id, course_id, student_id from enrollment;


select u1.id instructor_id, u1.username instructor_name, u1.role, #u1.email,
    c.id course_id, title, description,
    #DATE_FORMAT(start_date, "%Y:%m:%d") start_date, DATE_FORMAT(end_date, "%Y:%m:%d") end_date,
    #e.id, e.course_id, e.student_id,
    u2.id student_id, u2.username student_name, u2.role #, u2.email
from user u1
    left join course c on c.instructor_id = u1.id
    left join enrollment e on e.course_id = c.id
    left join user u2 on e.student_id = u2.id;

select c3.id course_id, c3.title, c3.description from course c3 where c3.instructor_id not in (select u3.id from user u3 where u3.role = 'INSTRUCTOR')