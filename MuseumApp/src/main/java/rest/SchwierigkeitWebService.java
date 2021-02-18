package rest;

import java.util.Collection;

import bean.Schwierigkeit;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import storage.SchwierigkeitDao;

@Path("Schwierigkeit")
public class SchwierigkeitWebService {
private SchwierigkeitDao schwierigkeitDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Schwierigkeit> getAllDifficulties() {
		initSchwierigkeitDao();
        return schwierigkeitDao.getAlleSchwierigkeiten();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Schwierigkeit getSchwierigkeitById(@QueryParam("id") String id)
	{
		initSchwierigkeitDao();
		return schwierigkeitDao.getSchwierigkeitById(id);
	}

	private void initSchwierigkeitDao() 
	{
		schwierigkeitDao = new SchwierigkeitDao();
        
    }
}
