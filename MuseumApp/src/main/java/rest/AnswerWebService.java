package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Answer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
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

	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public void AddAnswer(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Answer a = mapper.readValue(request.getInputStream(),Answer.class);
		initAnswerDao();
		if(a.getId()==-1)
			answerDao.addAnswer(a);
		else
			answerDao.updateAnswer(a);
	}
	
	private void initAnswerDao() 
	{
		answerDao = new AnswerDao();
        
    }
}
