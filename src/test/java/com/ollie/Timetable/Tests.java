package com.ollie.Timetable;


import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ollie.Timetable.*;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LaunchServer.class)
@WebAppConfiguration
public class Tests {

	
	
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	
	
	
	@Before
	public void setup() {
		
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@Test public void
    basicAuth() throws Exception {
		mvc
			.perform(get("/shifts/1").with(user("ollie").password("password").roles("MANAGER")))
			.andExpect(authenticated());
				
    }
	
	
	@Test public void
    testGetRoot() throws Exception {
		mvc
			.perform(get("/").with(user("ollie").password("password").roles("MANAGER")))
			.andExpect(status().isOk());
					
    }
	@Test public void
    testGetShifts() throws Exception {
		mvc
			.perform(get("/shifts").with(user("ollie").password("password").roles("MANAGER")))
			.andExpect(status().isOk());
					
    }
	@Test public void
    testGetShift() throws Exception {
		mvc
			.perform(get("/shifts/1").with(user("ollie").password("password").roles("MANAGER")))
			.andExpect(status().isOk());
					
    }
	
	@Test public void
    testPOSTShift() throws Exception {
		
		mvc
			.perform(post("/shifts").with(user("ollie").password("password").roles("MANAGER"))
					.contentType(MediaType.APPLICATION_JSON)
		            .param("date", "1/2/3")
		            .param("shiftType", "day")
		            .param("staffMember", "bob"))
		            .andExpect(status().isCreated());
    }
	


	
	
	


	
	

}








