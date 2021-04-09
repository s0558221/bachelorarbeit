package test;

import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
/**
 * Eine Testklassen um den UploadWebservice zu testen.
 * @author Roy Beyer
 * @version 1.0
 */
public class TestUploadWebService {

	private static String ssl_path = "P:\\web\\apache-tomcat-10.0.0\\tomcat.keystore";
	private static String ssl_password ="password";
	
	/**
	 * Eine Testmethode um den Abruf aller hochgeladenen Dateien zu testen.
	 */
	@Test
	public void getAllFilesTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/upload/GetFileList").then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um den Abruf einer bestimmten Datei anhand ihres Namens zu testen.
	 */
	@Test
	public void getExistingFileByNameTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/upload/GetByName?name=index.png").then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um den Abruf einer nicht vorhandenen Datei zu testen.
	 */
	@Test
	public void getNonExistingFileByNameTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/upload/GetByName?name=index.bmp").then().statusCode(500);
	}
	
	/**
	 * Eine Testmethode um das Hochladen einer neuen Datei zu testen.
	 */
	@Test
	public void uploadFileTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		File testUploadFile = new File("C:\\temp\\Sealife.jpg");
		 
        RestAssured.baseURI = "https://localhost:443/MuseumApp/rest/upload";
 
        Response response = given()
            .multiPart(testUploadFile)
            .when().
        post("/file");
 
 
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
 
        assertTrue(response.asString().contains("Datei-Upload erfolgreich!!"));
	}
}
