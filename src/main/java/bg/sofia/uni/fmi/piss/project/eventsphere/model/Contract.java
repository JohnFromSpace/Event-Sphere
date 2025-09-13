package bg.sofia.uni.fmi.piss.project.eventsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "performer_id", nullable = false)
    private long performerId;

    @Column(name = "event_id", nullable = false)
    private long eventId;

    @Column(name = "organizer_id", nullable = false)
    private long organizerId;

    public Contract(long id, long performerId, long eventId, long organizerId) {
        this.id = id;
        this.performerId = performerId;
        this.eventId = eventId;
        this.organizerId = organizerId;
    }

    public Contract() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPerformerId() {
        return performerId;
    }

    public void setPerformerId(long performerId) {
        this.performerId = performerId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }
}
