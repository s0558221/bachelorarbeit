package rest;

import java.util.Collection;

import bean.Difficulty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import storage.DifficultyDao;

@Path("difficulty")
public class DifficultyWebService {
private DifficultyDao difficultyDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Difficulty> getAllDifficulties() {
		initDifficultyDao();
        return difficultyDao.getAllDifficulties();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Difficulty getDifficultyById(@QueryParam("id") String id)
	{
		initDifficultyDao();
		return difficultyDao.getDifficultyById(id);
	}

	private void initDifficultyDao() 
	{
		difficultyDao = new DifficultyDao();
        
    }
}
