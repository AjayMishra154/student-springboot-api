package com.college.student;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(StudentRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Student("Ajay Mishra", "20Q91A0580",20)));
      log.info("Preloading " + repository.save(new Student("Anivesh", "20Q91A05A0",21)));
      log.info("Preloading " + repository.save(new Student("sanyam soni", "20Q91A0565",22)));
      log.info("Preloading " + repository.save(new Student("lateef", "20Q91A05B7",22)));
    };
  }
}