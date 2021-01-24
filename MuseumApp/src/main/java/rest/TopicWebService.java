package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Topic;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.TopicDao;

@Path("topic")
public class TopicWebService {
private TopicDao topicDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Topic> getAllTopics() {
		initTopicDao();
        return topicDao.getAllTopics();
    }
	
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Topic getTopicById(@QueryParam("id") String id)
	{
		initTopicDao();
		return topicDao.getTopicById(id);
	}
	
	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public void AddTopic(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Topic t = mapper.readValue(request.getInputStream(),Topic.class);
		initTopicDao();
		topicDao.addTopic(t);
	}


	private void initTopicDao() 
	{
		topicDao = new TopicDao();
        
    }
}
