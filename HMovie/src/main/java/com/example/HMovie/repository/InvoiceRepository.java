package com.example.HMovie.repository;

import com.example.HMovie.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    @Query("SELECT i FROM Invoice i WHERE i.profile.profileId = :profileId ORDER BY i.createdAt DESC LIMIT 1")
    Optional<Invoice> findLatestInvoiceByProfileId(String profileId);

    List<Invoice> findByProfileProfileId(String profileId);
}
