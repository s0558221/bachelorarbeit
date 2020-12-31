package rest;

import java.util.Collection;

import bean.Topic;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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

	private void initTopicDao() 
	{
		topicDao = new TopicDao();
        
    }
}
