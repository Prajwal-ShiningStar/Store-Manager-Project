package com.storemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanager.entity.Admin;
import com.storemanager.payload.AdminCreationPayload;
import com.storemanager.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<Object> createAdmin(AdminCreationPayload adminCreationPayload) {

		Admin admin = new Admin();

		admin.setFirstName(adminCreationPayload.getFirstName());
		admin.setLastName(adminCreationPayload.getLastname());
		admin.setEmail(adminCreationPayload.getEmail());
		admin.setContact(adminCreationPayload.getContact());
		admin.setAddress(adminCreationPayload.getAddress());
		admin.setGender(adminCreationPayload.getGender());
		admin.setCity(adminCreationPayload.getCity());
		admin.setState(adminCreationPayload.getState());
		admin.setPincode(adminCreationPayload.getPincode());
		return ResponseEntity.ok(adminRepository.save(admin));
	}

//	public ReponseEntity<Object> createAdmin(AdminCreationPayload adminCreationPayload){
////		return null;
////		private String firstName ;
////		private String lastname;
////		private String email;
////		private Long contact;
////		private String address ;
////		private String gender;
////		private String city;
////		private String state;
////		private Long pincode;
//		
//		Admin amdin = new Admin()
//		
////		admin is a persistant object.
//				
//		admin.setFirstName
//	}
}
