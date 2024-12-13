package com.resort.kingfisher.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resort.kingfisher.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // You can add custom query methods here if needed
}

