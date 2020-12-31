package rest;

import java.util.Collection;

import bean.Question;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import storage.QuestionDao;

@Path("question")
public class QuestionWebService {
	
	private QuestionDao questionDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Question> getAllQuestions() {
		initFrageDao();
        return questionDao.getAllQuestions();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Question getQuestionById(@QueryParam("id") String id)
	{
		initFrageDao();
		return questionDao.getQuestionById(id);
	}

	private void initFrageDao() 
	{
		questionDao = new QuestionDao();
        
    }
}
