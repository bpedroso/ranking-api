package com.bpedroso.ranking.controller;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.bpedroso.ranking.domain.User;
import com.bpedroso.ranking.service.ListService;
import com.bpedroso.ranking.service.UpdateService;

@Path("/ranking")
public class RankingController {
	
	private final static Logger LOGGER = Logger.getLogger(RankingController.class.getName());
	
	private final UpdateService updateService = new UpdateService();
	private final ListService listService = new ListService();
	
	@PUT
	@Path("/update/{userId}")
	public void update(@PathParam("userId") String userId, @QueryParam("score") String score) {
		LOGGER.info(format("Updating %1s = %2s ", userId, score));
		this.updateService.update(userId, score);
	}
	
	@GET
	@Produces(APPLICATION_JSON)
	@Path("/list/absolute/{value}")
	public Response listAbsolute(@PathParam("value") String value) {
		LOGGER.info(String.format("Listing absolute %1s ", value));
		List<User> listRanking = new ArrayList<>();
		ResponseBuilder status;
		if(validateRequest(value)) {			
			listRanking = this.listService.absolute(value);
			status = Response.status(Response.Status.OK).entity(toJson(listRanking));
		} else {
			status = Response.status(Response.Status.BAD_REQUEST);
		}
		return status.build();
	}

	@GET
	@Produces(APPLICATION_JSON)
	@Path("/list/relative/{position}/{around}")
	public Response listRelative(@PathParam("position") String position, @PathParam("around") String around) {
		LOGGER.info(String.format("Listing relative %1s ", position));
		List<User> listRanking = new ArrayList<>();
		ResponseBuilder status;
		if(validateRequest(position, around)) {			
			listRanking = this.listService.relative(position, around);
			status = Response.status(Response.Status.OK).entity(toJson(listRanking));
		} else {
			status = Response.status(Response.Status.BAD_REQUEST);
		}
		return status.build();
	}
	
	private boolean validateRequest(String...params) {
		return !asList(params).stream().filter(p -> (p==null || p=="") ).findAny().isPresent();
	}

	private static String toJson(final List<User> users) {
		final String message = "{\"userId\":\"%1s\",\"score\":%2d}";
		final String strUser = users.stream().map(u -> String.format(message, u.getUserId(), u.getScore()))
				.collect(Collectors.joining(","));
		return "[" + strUser + "]";
	}

}
