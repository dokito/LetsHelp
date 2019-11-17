package com.dokito.letshelp.data.repositories;

import com.dokito.letshelp.data.models.Fundraising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FundraisingRepository extends JpaRepository<Fundraising, UUID> {
}
