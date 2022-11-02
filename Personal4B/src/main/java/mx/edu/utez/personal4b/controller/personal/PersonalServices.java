package mx.edu.utez.personal4b.controller.personal;

import mx.edu.utez.personal4b.model.personal.BeanPersonal;
import mx.edu.utez.personal4b.model.personal.DaoPersonal;
import mx.edu.utez.personal4b.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Path("/api/personal")
public class PersonalServices {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List <BeanPersonal> getAll(){
        return new DaoPersonal().findAll();
    }

    @GET
    @Path("/{id}") //http://localhost:8080/api/personal/
    @Produces(MediaType.APPLICATION_JSON)
    public BeanPersonal getById(@PathParam("id") Long id){
        return new DaoPersonal().findById(id);
    }

    @POST
    @Path("/")
    @Produces(value = {"application/json"})
    public Map<String, Object> save(BeanPersonal personal){
        Response<BeanPersonal> result = new DaoPersonal().save(personal);
        response.put("result",result);
        return response;
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanPersonal> update(BeanPersonal personal){
        return new DaoPersonal().update(personal);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanPersonal> remove(Long id){
        return new DaoPersonal().remove(id);
    }

}
