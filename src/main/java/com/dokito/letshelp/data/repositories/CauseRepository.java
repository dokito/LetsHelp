package com.dokito.letshelp.data.repositories;

import com.dokito.letshelp.data.models.Cause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CauseRepository extends JpaRepository<Cause, String> {
}
