package com.resort.kingfisher.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.resort.kingfisher.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // You can define custom query methods here if needed.
}

