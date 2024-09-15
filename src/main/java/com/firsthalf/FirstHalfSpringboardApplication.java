package com.firsthalf;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstHalfSpringboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstHalfSpringboardApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}
/*	@Bean
	public CommandLineRunner initialCreate(CourseService courseService, TutorService tutorService, StudentsService studentsService) {
		return (args) -> {
			Student student1 = new Student("Chirag Singh", "Chiragsingh@gmail.com", "B.Tech","9876876541","token1");
			studentsService.createStudent(student1);

			Student student2 = new Student("Darsh Singh2", "Darshsingh2@gmail.com", "B.Tech","9621345643","token2");
			studentsService.createStudent(student2);

			Course course1=new Course("30 min per day", "1 Month","3000","400","kdk");
           courseService.createCourse(course1);

			Course course2=new Course("45 min per day", "3 Month","8000","300","wwddf");
			courseService.createCourse(course2);

			Tutor tutor1=new Tutor("Divya","Divya@gmail.com","m.com","t1 white ohhj","9879876109");
			BankAccount bankAccount1=new BankAccount("a1","34216","785214678898999","brhjk");

			tutor1.setBankAccount(bankAccount1);
			tutorService.createTutor(tutor1);


		} ;}*/

}
