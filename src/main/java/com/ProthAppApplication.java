package com;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.entities.Patient;
import com.entities.Therapeute;
import com.enumeration.GrpSanguin;
import com.enumeration.Sexe;
import com.repository.PatientRepository;
import com.repository.TherapeuteRepository;
import com.repository.UserRepository;

@SpringBootApplication
public class ProthAppApplication {
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		ApplicationContext context=	SpringApplication.run(ProthAppApplication.class, args);
		
		PatientRepository up =context.getBean(PatientRepository.class);
		Patient p = new Patient();
		
		p.setId((long) 1);
		p.setUsername("root");
		p.setPassword("root");
		p.setNom("KADAOUI");
		p.setPrenom("Rachid");
		p.setAdresse("MARACKECH");
		p.setTelephone("090909090");
		p.setAccident("TrainAccident");
		p.setCreatedAt(new Date());
		p.setDate_accident(new Date());
		p.setGrp_sanguin(GrpSanguin.A);
		p.setPoids(122);
		p.setTaille(189);
		p.setDate_naissance(new Date());
		p.setCreatedAt(new Date());
		p.setSexe(Sexe.MALE);

		up.save(p);
		
		TherapeuteRepository ut =context.getBean(TherapeuteRepository.class);
		Therapeute t = new Therapeute();
		
		t.setId((long) 7);
		t.setUsername("root");
		t.setPassword("root");
		t.setNom("KADAOUI");
		t.setPrenom("Rachid");
		t.setAdresse("MARACKECH");
		t.setTelephone("090909090");
		t.setCreatedAt(new Date());
		t.setCreatedAt(new Date());
		
		ut.save(t);
		
		
	}
	
	
}
