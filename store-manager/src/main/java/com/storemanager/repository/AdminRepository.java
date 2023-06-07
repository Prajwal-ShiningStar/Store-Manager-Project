package com.storemanager.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.storemanager.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("SELECT a FROM Admin a WHERE a.isDeleted = ?1")
	List<Admin> findAllByIsDeleted(boolean isDeleted);

}
