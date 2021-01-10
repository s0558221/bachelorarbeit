package rest;

import java.util.Collection;

import bean.Answer;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import storage.AnswerDao;

@Path("answer")
public class AnswerWebService {
	private AnswerDao answerDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Answer> getAllAnswers() {
		initAnswerDao();
        return answerDao.getAllAnswers();
    }
	
	@GET 
	@Path("/FindByQuestionId")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Answer> getAnswerByQuestionId(@QueryParam("questionid") int id)
	{
		initAnswerDao();
		return answerDao.getAnswersByQuestionId(id);
	}

	private void initAnswerDao() 
	{
		answerDao = new AnswerDao();
        
    }
}
