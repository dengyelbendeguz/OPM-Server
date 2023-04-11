package hu.opm.opm.password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
    @Query("SELECT s FROM Password s WHERE s.title = ?1")
    Optional<Password> findPasswordByTitle(String title);
}