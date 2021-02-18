package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Thema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.ThemaDao;

@Path("Thema")
public class ThemaWebService {
private ThemaDao themaDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Thema> getAlleThemen() {
		initThemaDao();
        return themaDao.getAlleThemen();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Thema getThemaById(@QueryParam("id") String id)
	{
		initThemaDao();
		return themaDao.getThemaById(id);
	}
	
	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public void AddThema(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Thema t = mapper.readValue(request.getInputStream(),Thema.class);
		initThemaDao();
		themaDao.addThema(t);
	}


	private void initThemaDao() 
	{
		themaDao = new ThemaDao();
        
    }
}
