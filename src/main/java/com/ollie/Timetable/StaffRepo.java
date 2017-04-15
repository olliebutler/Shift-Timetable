package com.ollie.Timetable;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StaffRepo extends Repository<Staff, Long> {

	Staff save(Staff staff);

	Staff findByName(String name);

}
