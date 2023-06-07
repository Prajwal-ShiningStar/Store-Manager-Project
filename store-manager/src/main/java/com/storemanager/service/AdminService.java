package com.storemanager.service;

import java.util.*;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanager.entity.Admin;
import com.storemanager.payload.AdminCreationPayload;
import com.storemanager.payload.AdminUpdatePayload;
import com.storemanager.payload.ErrorDetails;
import com.storemanager.payload.ResponseDetails;
import com.storemanager.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<Object> createAdmin(AdminCreationPayload adminPayload) {
		Admin admin = new Admin();
		admin.setFirstName(adminPayload.getFirstName());
		admin.setLastName(adminPayload.getLastname());
		admin.setEmail(adminPayload.getEmail());
		admin.setContact(adminPayload.getContact());
		admin.setAddress(adminPayload.getAddress());
		admin.setGender(adminPayload.getGender());
		admin.setCity(adminPayload.getCity());
		admin.setState(adminPayload.getState());
		admin.setPincode(adminPayload.getPincode());
		return ResponseEntity.ok(adminRepository.save(admin));
	}

	public ResponseEntity<Object> updateAdmin(AdminUpdatePayload adminUpdatePayload) {
		Admin admin = new Admin();
		admin.setAdminId(adminUpdatePayload.getAdminId());
		admin.setFirstName(adminUpdatePayload.getFirstName());
		admin.setLastName(adminUpdatePayload.getLastName());
		admin.setEmail(adminUpdatePayload.getEmail());
		admin.setContact(adminUpdatePayload.getContact());
		admin.setAddress(adminUpdatePayload.getAddress());
		admin.setGender(adminUpdatePayload.getGender());
		admin.setCity(adminUpdatePayload.getCity());
		admin.setState(adminUpdatePayload.getState());
		admin.setPincode(adminUpdatePayload.getPincode());
		admin.setCreatedAt(adminUpdatePayload.getCreatedAt());
		admin.setUpdatedAt(LocalDateTime.now());
		admin.setDeleted(adminUpdatePayload.isDeleted());

		admin.setUpdatedAt(LocalDateTime.now());
		return ResponseEntity.ok(adminRepository.save(admin));
	}

	public ResponseEntity<Object> deleteAdmin(Long adminId) {
		Optional<Admin> response = adminRepository.findById(adminId);

		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-001", "Admin with given AdminId = '" + adminId + "' not found on the System"));

		}
		Admin admin = response.get();
		admin.setDeleted(true);
		adminRepository.save(admin);
		return ResponseEntity.ok(new ResponseDetails("Admin with Id = '" + adminId + "' Deleted Sucessfully."));
	}

	public ResponseEntity<Object> getAdmin(Long adminId) {
		Optional<Admin> response = adminRepository.findById(adminId);
		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-001", "Admin with given AdminId = '" + adminId + "' not found on the System"));
		}
		return ResponseEntity.ok(adminRepository.save(response.get()));
	}

	public ResponseEntity<Object> getAdmins() {
		List<Admin> admins = adminRepository.findAllByIsDeleted(false);
		return ResponseEntity.ok(admins);
	}

}
