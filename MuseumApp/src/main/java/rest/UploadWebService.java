package rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

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

/**
 * Die Klasse UploadWebService dient zum Upload neuer Dateien auf den Server und zum Abruf der vorhandenen Dateien
 * @author Roy Beyer
 * @version 1.0
 */
@Path("upload")
public class UploadWebService {
	
	/**
	 * Die Methode dient zum Upload neuer Dateien auf den Server
	 * @param fileInputStream die hochzuladene Datei
	 * @param fileMetaData die Meta-Daten der Datei
	 * @return Antwort, dass Upload erfolgreich verlaufen ist
	 */
	@POST
	@Path("/file")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadNewFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData)
	{
		String savepath;
		
	    try
	    {
	    	savepath = getSavePath();
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        
	        OutputStream out = new FileOutputStream(new File(savepath + fileMetaData.getFileName()));
	        
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
	
	/**
	 * liefert eine Datei anhand ihres Namens an den Client zurueck
	 * @param name der Name der Datei
	 * @return die Datei
	 */
	@GET
	@Path("/GetByName")
    @Produces({"image/jpg","video/mp4","image/png","image/bmp","application/pdf"})
	public Response getFileByName(@QueryParam("name") String name)
	{
		String savepath = "";
		File file;
		String fileType = "";
		
		try {
			savepath = getSavePath();
			file = new File(savepath+"\\"+name);
	    	fileType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			throw new WebApplicationException("Datei nicht gefunden!");
		}
	    return Response.ok(file, fileType).header("Inline", "filename=\"" + file.getName() + "\"")
	            .build();
	}
	
	/**
	 * ruft alle gespeicherten Dateien ab und gibt die Dateinamen als Collection zurueck
	 * @return eine Collection aller gespeicherten Dateinamen
	 */
	@GET
	@Path("/GetFileList")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getFileList()
	{
		Collection<String> files = new ArrayList<String>();
		String savepath="";
		savepath = getSavePath();
		
		File folder = new File(savepath);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        files.add(file.getName());
		    }
		}
		
		return files;
	}
	
	/**
	 * laedt die config.properties und gibt den darin gespeicherten Pfad zurück
	 * @return der ausgelesene Zugriffpfad
	 */
	private String getSavePath()
	{
		Properties prop = new Properties();
    	String propFileName = "config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				throw new WebApplicationException("Fehler beim Zugriff auf die Konfig-Datei.");
			}
		}
		
		return prop.getProperty("savepath");
	}
}
