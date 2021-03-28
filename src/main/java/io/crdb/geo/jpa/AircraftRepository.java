package io.crdb.geo.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AircraftRepository extends CrudRepository<Aircraft, UUID> {
}
