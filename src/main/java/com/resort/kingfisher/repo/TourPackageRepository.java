package com.resort.kingfisher.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.resort.kingfisher.model.TourPackage;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
    // You can define custom query methods here if needed.
}
