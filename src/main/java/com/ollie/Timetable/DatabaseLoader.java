package com.ollie.Timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final ShiftRepo shifts;
	private final StaffRepo staffs;

	@Autowired
	public DatabaseLoader(ShiftRepo shiftRepo,
						  StaffRepo staffRepo) {

		this.shifts = shiftRepo;
		this.staffs =staffRepo;
	}

	@Override
	public void run(String... strings) throws Exception {

		Staff ollie = this.staffs.save(new Staff("ollie", "password",
							"ROLE_MANAGER"));
		Staff tom = this.staffs.save(new Staff("tom", "password",
							"ROLE_STAFF"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("ollie", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.shifts.save(new Shift("1/2/3", "Afternoon", "ollie", ollie));
		this.shifts.save(new Shift("2/2/3", "Afternoon","ollie", ollie));
		this.shifts.save(new Shift("3/2/3", "Afternoon","ollie", ollie));
		this.shifts.save(new Shift("1/2/3", "Evening", "tom",ollie));
		this.shifts.save(new Shift("2/2/3", "Evening", "tom",ollie));
		this.shifts.save(new Shift("3/2/3", "Evening", "tom",ollie));

		SecurityContextHolder.clearContext();
	}
}
