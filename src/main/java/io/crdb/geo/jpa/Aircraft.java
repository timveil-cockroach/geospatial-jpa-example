package io.crdb.geo.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.locationtech.jts.geom.Point;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Aircraft {

    @Id
    private UUID id;

    @Column
    private Point location;

    public Aircraft() {
    }

    public Aircraft(UUID id, Point location) {
        this.id = id;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aircraft aircraft = (Aircraft) o;
        return Objects.equals(id, aircraft.id) && Objects.equals(location, aircraft.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
               "id=" + id +
               ", location=" + location +
               '}';
    }
}
