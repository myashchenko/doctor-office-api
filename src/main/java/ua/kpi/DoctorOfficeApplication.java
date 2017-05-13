package ua.kpi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = DoctorOfficeApplication.class)
@SpringBootApplication
@EnableJpaRepositories
public class DoctorOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorOfficeApplication.class, args);
	}
}
