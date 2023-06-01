package com.storemanager.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.storemanager.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
