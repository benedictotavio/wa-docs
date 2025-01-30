package br.com.wa_docs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.wa_docs.user.auth.domains.Role;
import br.com.wa_docs.user.auth.repositories.RoleRepository;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.repositories.UserRepository;

@SpringBootApplication
public class WaDocsApplication {
	public static void main(String[] args) {
		SpringApplication.run(WaDocsApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository,
	// 		PasswordEncoder passwordEncoder) {
	// 	return args -> {
	// 		if (userRepository.findByEmail("admin@email.com").isPresent())
	// 			return;
	// 		if (roleRepository.findByAuthority("ADMIN").isPresent())
	// 			return;
	// 		Role adminRole = roleRepository.save(new Role("ADMIN"));
	// 		roleRepository.save(new Role("USER"));

	// 		Role[] roles = { adminRole };

	// 		User admin = new User(
	// 				"admin",
	// 				"admin@email.com",
	// 				passwordEncoder.encode("password"),
	// 				roles);

	// 		userRepository.save(admin);

	// 	};
	// }
}
