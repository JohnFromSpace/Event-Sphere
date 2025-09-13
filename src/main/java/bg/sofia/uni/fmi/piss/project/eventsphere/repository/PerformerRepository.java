package bg.sofia.uni.fmi.piss.project.eventsphere.repository;

import bg.sofia.uni.fmi.piss.project.eventsphere.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
    Performer findById(long id);
}