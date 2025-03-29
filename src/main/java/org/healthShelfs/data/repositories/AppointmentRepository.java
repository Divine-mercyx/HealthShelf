package org.healthShelfs.data.repositories;

import org.healthShelfs.data.models.appointment.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
