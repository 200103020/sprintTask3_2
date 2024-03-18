package bitlab.springtask3_2.repositories;

import bitlab.springtask3_2.entities.ApplicationRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    ApplicationRequest findAllById(Long id);

}
