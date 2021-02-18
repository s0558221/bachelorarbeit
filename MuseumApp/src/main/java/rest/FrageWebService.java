package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Frage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.FrageDao;

@Path("Frage")
public class FrageWebService {
	
	private FrageDao frageDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Frage> getAlleFragen() {
		initFrageDao();
        return frageDao.getAlleFragen();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Frage getFrageById(@QueryParam("id") String id)
	{
		initFrageDao();
		return frageDao.getFrageById(id);
	}
	
	@GET 
	@Path("/FindByThemaUndSchwierigkeit")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Frage> getFragenByThemaUndSchwierigkeit(@QueryParam("thema") String thema,@QueryParam("schwierigkeit") String schwierigkeit)
	{
		initFrageDao();
		return frageDao.getFragenByTopicIdAndDifficultyId(thema,schwierigkeit);
	}
	
	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public int AddFrage(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		int id = 0;
		ObjectMapper mapper = new ObjectMapper();
		Frage q = mapper.readValue(request.getInputStream(),Frage.class);
		initFrageDao();
		if(q.getId()==-1)
			id = frageDao.addFrage(q);
		else
			id = frageDao.updateFrage(q);
		return id;
	}

	private void initFrageDao() 
	{
		frageDao = new FrageDao();
        
    }
}
