package test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import org.junit.Test;

import bean.Frage;
import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;

/**
 * Eine Testklasse fuer die Integrationstests des FrageWebservice.
 * @author Roy Beyer
 *@version 1.0
 */
public class TestFrageWebService {
	private static String ssl_path = "P:\\web\\apache-tomcat-10.0.0\\tomcat.keystore";
	private static String ssl_password ="password";
	
	/**
	 * Eine Testmethode um den Abruf aller Fragen zu testen.
	 */
	@Test
	public void getAllFragenTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/Frage").then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um das Abrufen einer bestimmten Frage zu testen.
	 */
	@Test
	public void getFragenByIdTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		given().
		queryParam("id",1).
    when().
        get("https://localhost:443/MuseumApp/rest/Frage/FindById").
    then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um den Abruf einer Liste von Frage anhand eines Themas und einer Schwierigkeit zu testen.
	 */
	@Test
	public void getFragenByThemaUndSchwierigkeitTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		given().
		queryParam("thema","1").queryParam("schwierigkeit", "1").
    when().
        get("https://localhost:443/MuseumApp/rest/Frage/FindByThemaUndSchwierigkeit").
    then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um das Hinzufuegen einer neuen Frage zu testen.
	 */
	@Test
	public void addFrageTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		with().body(new Frage(-1,"Das ist eine Frage.",1,1))
     	.when()
      .request("POST", "https://localhost:443/MuseumApp/rest/Frage/Add")
      .then()
      .statusCode(200);
	}
}
