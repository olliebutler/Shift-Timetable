package com.ollie.Timetable;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface ShiftRepo extends PagingAndSortingRepository<Shift, Long> {

	@Override
	@PreAuthorize("#shift?.staff == null or #shift?.staff?.name == authentication?.name")
	Shift save(@Param("shift") Shift shift);

	@Override
	@PreAuthorize("@shiftRepo.findOne(#id)?.staff?.name == authentication?.name")
	void delete(@Param("id") Long id);

	@Override
	@PreAuthorize("#shift?.staff?.name == authentication?.name")
	void delete(@Param("shift") Shift shift);

}

