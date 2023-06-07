package com.storemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanager.payload.AdminCreationPayload;
import com.storemanager.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Description(value = "Create an admin")
	@PostMapping("/create")
	public ResponseEntity<Object> createAdmin(@RequestBody AdminCreationPayload adminCreationPayload) {
		return adminService.createAdmin(adminCreationPayload);
	}

}
