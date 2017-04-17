package com.ollie.Timetable;

import static com.ollie.Timetable.WebsocketConfig.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Shift.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleAfterCreate
	public void newShift(Shift shift) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newShift", getPath(shift));
	}

	@HandleAfterDelete
	public void deleteShift(Shift shift) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/deleteShift", getPath(shift));
	}

	@HandleAfterSave
	public void updateEmployee(Shift shift) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/updateShift", getPath(shift));
	}


	private String getPath(Shift shift) {
		return this.entityLinks.linkForSingleResource(shift.getClass(),
				shift.getId()).toUri().getPath();
	}

}
