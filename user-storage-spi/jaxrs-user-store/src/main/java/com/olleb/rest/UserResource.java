package com.olleb.rest;

import java.lang.invoke.MethodHandles;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.olleb.model.User;
import com.olleb.service.UserService;

import io.smallrye.common.constraint.NotNull;

/**
 * @author Àngel Ollé Blázquez
 */
@Path("api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
    
    @Inject
    UserService userService;

    @GET
    public Set<User> getUsers() {
        LOGGER.info("getUsers");
        return userService.getUsers();
    }

    @GET
    @Path("{id}")
    public User getUserById(@NotNull @PathParam("id") final String id) {
        LOGGER.info("getUserById: " + id);
        return userService.getUserById(id).orElseThrow(NotFoundException::new);
    }

}
