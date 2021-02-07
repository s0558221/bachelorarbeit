package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Question;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.QuestionDao;

@Path("question")
public class QuestionWebService {
	
	private QuestionDao questionDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Question> getAllQuestions() {
		initQuestionDao();
        return questionDao.getAllQuestions();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Question getQuestionById(@QueryParam("id") String id)
	{
		initQuestionDao();
		return questionDao.getQuestionById(id);
	}
	
	@GET 
	@Path("/FindByTopicIdAndDifficultyId")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Question> getQuestionByTopicIdAndDifficultyId(@QueryParam("topicId") String topicid,@QueryParam("difficultyId") String difficultyid)
	{
		initQuestionDao();
		return questionDao.getQuestionsByTopicIdAndDifficultyId(topicid,difficultyid);
	}
	
	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public int AddQuestion(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		int id = 0;
		ObjectMapper mapper = new ObjectMapper();
		Question q = mapper.readValue(request.getInputStream(),Question.class);
		initQuestionDao();
		if(q.getId()==-1)
			id = questionDao.addQuestion(q);
		else
			id = questionDao.updateQuestion(q);
		return id;
	}

	private void initQuestionDao() 
	{
		questionDao = new QuestionDao();
        
    }
}
