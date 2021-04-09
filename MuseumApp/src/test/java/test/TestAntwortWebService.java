package test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import org.junit.Test;

import bean.Antwort;
import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;

/**
 * Ein Testklasse um die Funktionen des AntwortWebservice zu testen.
 * @author Roy Beyer
 * @version 1.0
 *
 */
public class TestAntwortWebService {

	private static String ssl_path = "P:\\web\\apache-tomcat-10.0.0\\tomcat.keystore";
	private static String ssl_password ="password";
	
	/**
	 * Eine Testmethode um den Abruf aller Antworten zu testen.
	 */
	@Test
	public void getAllAntwortenTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/Antwort").then().statusCode(200);
	}

	/**
	 * Eine Testmethode um den Abruf der Antworten, die zu einer bestimmten Fragen gehoeren, zu testen.
	 */
	@Test
	public void getExistingAntwortByFrageTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		given().
		queryParam("frage",1).
    when().
        get("https://localhost:443/MuseumApp/rest/Antwort/FindByFragenId").
    then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode, um das Hinzufuegen neuer Antworten zu testen.
	 */
	@Test
	public void addAntwortTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		with().body(new Antwort(-1,"Das ist eine Frage.",1,false))
     	.when()
      .request("POST", "https://localhost:443/MuseumApp/rest/Antwort/Add")
      .then()
      .statusCode(204);
	}
}
