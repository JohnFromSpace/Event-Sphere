package bg.sofia.uni.fmi.piss.project.eventsphere.repository;

import bg.sofia.uni.fmi.piss.project.eventsphere.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findById(long id);
}
