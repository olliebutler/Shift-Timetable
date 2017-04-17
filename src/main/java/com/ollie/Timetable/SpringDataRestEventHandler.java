package com.ollie.Timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(Shift.class)
public class SpringDataRestEventHandler {

	private final StaffRepo staffRepo;

	@Autowired
	public SpringDataRestEventHandler(StaffRepo staffRepo) {
		this.staffRepo = staffRepo;
	}

	@HandleBeforeCreate
	public void applyUserInformationUsingSecurityContext(Shift shift) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Staff staff = this.staffRepo.findByName(name);
		if (staff == null) {
			Staff newStaff = new Staff();
			newStaff.setName(name);
			newStaff.setRoles(new String[]{"ROLE_MANAGER"});
			staff = this.staffRepo.save(newStaff);
		}
		shift.setManager(staff);
	}
}