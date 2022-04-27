package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Medecin;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.MedecinRepository;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientsMvcApplication.class, args);

    }
    //au demarrage cree un password encoder puis place le dans ton context
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Zouhair",new Date(), false,105,"BK58887"));
            patientRepository.save(new Patient(null,"Selma",new Date(), true,300,"BK57887"));
            patientRepository.save(new Patient(null,"Khalil",new Date(), true,444,"BK9987"));
            patientRepository.save(new Patient(null,"Yousra",new Date(), false,111,"BK58557"));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };

    }
    //@Bean
    CommandLineRunner commandLineRunnerMed(MedecinRepository medecinRepository){
        return args -> {
            medecinRepository.save(new Medecin(null,"Hamza","Hamza@gmail.com","ophotalmologie"));
            medecinRepository.save(new Medecin(null,"Abdelhadi","Abdelhadi@gmail.com","chardiologie"));
            medecinRepository.save(new Medecin(null,"Manal","Manal@gmail.com","néphrologie"));


            medecinRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };

    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("yasmine","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");


            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("mohamed","ADMIN");
            securityService.addRoleToUser("yasmine","USER");
            securityService.addRoleToUser("hassan","USER");


        };
    }

}
