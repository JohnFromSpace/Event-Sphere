package bg.sofia.uni.fmi.piss.project.eventsphere.repository;

import bg.sofia.uni.fmi.piss.project.eventsphere.model.EventSphereUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSphereUserRepository extends JpaRepository<EventSphereUser, Long> {
  EventSphereUser findByUsername(String name);
}
