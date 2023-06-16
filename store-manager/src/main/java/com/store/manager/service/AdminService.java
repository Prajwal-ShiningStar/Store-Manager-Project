package com.store.manager.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.manager.constants.Constants;
import com.store.manager.constants.RegexPattern;
import com.store.manager.entity.Admin;
import com.store.manager.payload.AdminCreatePayload;
import com.store.manager.payload.AdminUpdatePayload;
import com.store.manager.payload.ErrorDetails;
import com.store.manager.payload.ResponseDetails;
import com.store.manager.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<Object> createAdmin(AdminCreatePayload adminCreatePayload) {

		System.out.println("Matched = " + "iasdasd@asdsd.sdf".matches(RegexPattern.EMAIL_REGEX));

		if (adminCreatePayload.getFirstName() == null || adminCreatePayload.getFirstName().isEmpty()
				|| adminCreatePayload.getFirstName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-003", Constants.FIRST_NAME_CANNOT_BE_EMPTY));

		}

		if (adminCreatePayload.getLastName() == null || adminCreatePayload.getLastName().isEmpty()
				|| adminCreatePayload.getLastName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-004", "Last name cannot be empty or null"));

		}
		if (Long.toString(adminCreatePayload.getPincode()).length() != 6) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-005", "Only six digits allowed in pincode field"));
		}

		long contact = adminCreatePayload.getContact();

		// Check if the contact number is not 10 digits long
		if (String.valueOf(contact).length() != 10) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-007", "Only ten digits allowed in contact field"));
		}

		List<Admin> adminContactResponse = adminRepository.findByContact(adminCreatePayload.getContact());
		if (!adminContactResponse.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-008", "Contact already exist."));
		}

		List<Admin> adminEmailResponse = adminRepository.findByEmail(adminCreatePayload.getEmail());
		if (!adminEmailResponse.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-008", "Email already exist."));
		}

		// Continue with the rest of the code if the contact number is valid

		Admin admin = getAdmin(adminCreatePayload);
		return ResponseEntity.ok(adminRepository.save(admin));
	}

	private Admin getAdmin(AdminCreatePayload adminCreatePayload) {
		Admin admin = new Admin();
		admin.setFirstName(adminCreatePayload.getFirstName());
		admin.setLastName(adminCreatePayload.getLastName());
		admin.setEmail(adminCreatePayload.getEmail());
		admin.setContact(adminCreatePayload.getContact());
		admin.setAddress(adminCreatePayload.getAddress());
		admin.setGender(adminCreatePayload.getGender());
		admin.setCity(adminCreatePayload.getCity());
		admin.setState(adminCreatePayload.getState());
		admin.setPincode(adminCreatePayload.getPincode());
		return admin;

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
		return ResponseEntity.ok(adminRepository.save(admin));
	}

	public ResponseEntity<Object> deleteAdmin(Long adminId) {
		Optional<Admin> response = adminRepository.findById(adminId);

		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorDetails("SM-001", "Admin with '" + adminId + "' not  found in the system."));
		}
		Admin admin = response.get();
		admin.setDeleted(true);
		adminRepository.save(admin);
		return ResponseEntity.ok(new ResponseDetails("Admin with Id '" + adminId + "'deleted successfully."));

	}

	public ResponseEntity<Object> getAdmin(Long adminId) {
		Optional<Admin> response = adminRepository.findById(adminId);

		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorDetails("SM-002", "Admin with '" + adminId + "' not  found in the system."));
		}

		return ResponseEntity.ok(adminRepository.save(response.get()));

	}

	public ResponseEntity<Object> getAdmins() {
		List<Admin> admins = adminRepository.findAllByIsDeleted(false);
		return ResponseEntity.ok(admins);
	}

	public ResponseEntity<Object> validateCredentials(String email, String password) {

		Optional<Admin> adminResponse = adminRepository.getByEmailAndPassword(email, password);
		if (!adminResponse.isPresent()) {
			// login failed "Email or password in incorrect."
		}

		// login sucess "Login Sucess."
		return null;
	}

}
