package com.app.admin_service.repo;

import com.app.admin_service.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {
    Optional<Admins> findByUsername(String username);
    Optional<Admins> findByEmail(String email);
}
