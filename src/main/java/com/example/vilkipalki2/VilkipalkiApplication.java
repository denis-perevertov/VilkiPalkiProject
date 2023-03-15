package com.example.vilkipalki2;

import com.example.vilkipalki2.models.AppUser;
import com.example.vilkipalki2.repos.AppUserRepository;
import com.example.vilkipalki2.services.UserService;
import com.example.vilkipalki2.util.AppUserRole;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@Log
public class VilkipalkiApplication {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(VilkipalkiApplication.class, args);
	}

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "test";
	}

	@EventListener(ApplicationReadyEvent.class)
	public void createAdmin() {
		if(appUserRepository.findByEmail("admin@gmail.com").isEmpty()) {
			AppUser admin = new AppUser("admin@gmail.com", passwordEncoder.encode("admin"));
			admin.setRole(AppUserRole.ROLE_ADMIN);
			appUserRepository.save(admin);
			log.info("Saved admin user!");
		}
	}


}
