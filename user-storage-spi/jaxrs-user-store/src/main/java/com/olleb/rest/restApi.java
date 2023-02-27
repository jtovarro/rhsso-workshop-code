package com.olleb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.olleb.model.Person;
import com.olleb.service.PersonModel;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class restApi {
    
    @GET
    public Response getList(){
        Person person = new Person("john", "willian", "jw@rex.do", "private");
        return Response.ok(person).build();
    }

    @POST
    public Response save(PersonModel model){
        Person person = new Person(model.firstName, model.lastName, model.email, model.password);
        return Response.ok(person).build();
    }

}
