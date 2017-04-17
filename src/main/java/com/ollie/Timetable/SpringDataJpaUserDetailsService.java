/***************************************************************************************
*    Title: React.js and Spring Data REST
*    Author: Greg Turnquist
*    Date: 17/4/17
*    Code version: 1.4.2
*    Availability: https://github.com/spring-guides/tut-react-and-spring-data-rest/tree/master/security
*
***************************************************************************************/

package com.ollie.Timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final StaffRepo repository;

	@Autowired
	public SpringDataJpaUserDetailsService(StaffRepo repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Staff staff = this.repository.findByName(name);
		return new User(staff.getName(), staff.getPassword(),
				AuthorityUtils.createAuthorityList(staff.getRoles()));
	}

}
