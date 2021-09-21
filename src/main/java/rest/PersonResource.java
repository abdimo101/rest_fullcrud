package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import facades.FacadeExample;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }



    //url skal fx være api/person/1
    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getPersonById(@PathParam("id") Integer id){
       PersonDTO pd =  FACADE.getPerson(id);
        return new Gson().toJson(pd);
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addPerson(String person){
        PersonDTO pd = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newpd = FACADE.addPerson(pd.getfName(), pd.getlName(), pd.getPhone());
      return GSON.toJson(newpd);
    }


}
