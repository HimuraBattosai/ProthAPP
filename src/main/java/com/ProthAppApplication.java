package com;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entities.Patient;
import com.enumeration.GrpSanguin;
import com.enumeration.Sexe;
import com.repository.UserRepository;

@SpringBootApplication
public class ProthAppApplication {
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
	Patient p = new Patient();
	p.setUsername("elouali");
	p.setPassword("456");
	p.setNom("ELOUALI");
	p.setPrenom("SALAH");
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

	//pu.save(p);
	}
	
	
	
	
}
