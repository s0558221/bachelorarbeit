package rest;

import java.io.IOException;
import java.nio.file.FileSystems;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("qr")
public class QRWebService {

	@POST 
	@Path("/Create")
	@Produces(MediaType.APPLICATION_JSON)
   // @Produces("image/png")
	public void getNewQRCode(@Context HttpServletRequest request,@Context HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String link = mapper.readValue(request.getInputStream(),String.class);
		try {
			generateQRCodeImage(link,200,200,QR_CODE_IMAGE_PATH);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	//Quelle: https://www.callicoder.com/generate-qr-code-in-java-using-zxing/
	private static final String QR_CODE_IMAGE_PATH = "C:\\\\web\\\\apache-tomcat-10.0.0\\\\webapps\\\\data\\\\MyQRCode.png";

    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        java.nio.file.Path path =  FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
