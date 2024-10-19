package pe.upc.learningcenterplatform.profiles.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.upc.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenterplatform.profiles.domain.model.valueobjetcs.EmailAddress;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByEmail(EmailAddress emailAddress);

}