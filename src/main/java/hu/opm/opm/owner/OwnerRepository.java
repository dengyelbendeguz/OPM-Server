package hu.opm.opm.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("SELECT s FROM Owner s WHERE s.MasterUsername = ?1")
    Optional<Owner> findOwnerByMasterUsername(String masterUsername);
}
