package br.com.wa_docs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.wa_docs.mockserver.repositories.MockserverRepository;
import br.com.wa_docs.user.auth.domains.Role;
import br.com.wa_docs.user.auth.enums.ERole;
import br.com.wa_docs.user.auth.repositories.RoleRepository;
import br.com.wa_docs.user.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class WaDocsApplication {
	public static void main(String[] args) {
		SpringApplication.run(WaDocsApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, MockserverRepository mockserverRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			for (String arg : args) {

				if (arg.isEmpty()) {
					arg = "ADMIN";
				}

				if (userRepository.findByEmail("admin@email.com").isPresent())
					return;
				if (roleRepository.findByAuthority(ERole.ADMIN).isPresent())
					return;
				roleRepository.save(new Role(ERole.ADMIN));
				roleRepository.save(new Role(ERole.USER));
				mockserverRepository.deleteAll();
			}

		};
	}
}