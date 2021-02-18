package rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("upload")
public class UploadWebService {
	
	private static String UPLOAD_PATH = "C:\\web\\apache-tomcat-10.0.0\\webapps\\data\\";
	
	@POST
	@Path("/file")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
	{
	    try
	    {
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
	        
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) 
	    {
	        throw new WebApplicationException("Fehler beim Upload!! Bitte nochmal versuchen!!");
	    }
	    return Response.ok("Datei-Upload erfolgreich!!").build();
	}
	
	@GET
	@Path("/GetByName")
    @Produces({"application/pdf","image/jpg"})
	public Response getFileById(@QueryParam("name") String name)
	{
		String fileType = "";
		
		File file1 = new File(UPLOAD_PATH+"\\"+name);
	    try {
	    	fileType = Files.probeContentType(file1.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return Response.ok(file1, fileType).header("Inline", "filename=\"" + file1.getName() + "\"")
	            .build();
	}
	
	@GET
	@Path("/GetFileList")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getFileList()
	{
		Collection<String> files = new ArrayList<String>();
		
		File folder = new File(UPLOAD_PATH);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        files.add(file.getName());
		    }
		}
		
		return files;
	}
}
