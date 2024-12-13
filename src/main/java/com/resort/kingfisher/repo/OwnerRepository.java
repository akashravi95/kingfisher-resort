package com.resort.kingfisher.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resort.kingfisher.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    // You can add custom query methods here if needed
}
