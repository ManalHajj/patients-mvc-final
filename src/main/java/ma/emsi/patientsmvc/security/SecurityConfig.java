package ma.emsi.patientsmvc.security;

import ma.emsi.patientsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration //premiere classe que spring va instancier
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // comment spring va chercher les users et roles (bdd memoire annuaire d'entreprise)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
        String encoderPWD=passwordEncoder.encode("1234");
        System.out.println(encoderPWD);

        auth.inMemoryAuthentication().withUser("user1").password(encoderPWD).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1111")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("2345")).roles("USER","ADMIN");
        */

        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal , password as credentials,active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, roles as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
                */

        auth.userDetailsService(userDetailsService);

        /*

        {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        });

         */

    }


    //specifier les droit d'acces
    @Override
    protected void configure(HttpSecurity http) throws Exception {




        //formulaire par defaut
         http.formLogin();
         http.authorizeHttpRequests().antMatchers("/").permitAll();
         //link accesibility
         http.authorizeHttpRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
         http.authorizeHttpRequests().antMatchers("/user/**").hasAuthority("USER");
         http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
         // tte requete http necessite une authentif
         http.authorizeHttpRequests().anyRequest().authenticated();
         http.exceptionHandling().accessDeniedPage("/403");




    }


}
