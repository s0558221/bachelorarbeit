package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Antwort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.AntwortDao;

@Path("Antwort")
public class AntwortWebService {
	private AntwortDao antwortDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Antwort> getAlleAntworten() {
		initAntwortDao();
        return antwortDao.getAlleAntworten();
    }
	
	@GET 
	@Path("/FindByFragenId")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Antwort> getAntwortByFrageId(@QueryParam("frage") int id)
	{
		initAntwortDao();
		return antwortDao.getAntwortByFrageId(id);
	}

	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public void AddAntwort(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Antwort a = mapper.readValue(request.getInputStream(),Antwort.class);
		initAntwortDao();
		if(a.getId()==-1)
			antwortDao.addAntwort(a);
		else
			antwortDao.updateAntwort(a);
	}
	
	private void initAntwortDao() 
	{
		antwortDao = new AntwortDao();
        
    }
}
