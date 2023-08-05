package io.codelex.flightplanner.Repository;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    //ŠEIT ARĪ, LAIKAM QUERY??
}
