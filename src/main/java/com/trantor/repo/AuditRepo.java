package com.trantor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trantor.entity.Audit;

public interface AuditRepo extends JpaRepository<Audit, Integer>{
}
