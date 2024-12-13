package com.resort.kingfisher.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resort.kingfisher.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsername(String username);
}
