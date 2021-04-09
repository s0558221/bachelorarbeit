package test;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Eine Testklasse um den SchwierigkeitWebservice zu testen.
 * @author Roy Beyer
 * @version 1.0
 */
public class TestSchwierigkeitWebService {

	private static String ssl_path = "P:\\web\\apache-tomcat-10.0.0\\tomcat.keystore";
	private static String ssl_password ="password";
	
	/**
	 * Eine Testmethode um den Abruf aller Schwierigkeiten zu testen.
	 */
	@Test
	public void getAllSchwierigkeitenTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/Schwierigkeit").then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um die Anzahl der vorhandenen Themen zu testen.
	 */
	@Test
	public void getAllThemen_ShouldBe3Test() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		given().
	    when().
	        get("https://localhost:443/MuseumApp/rest/Schwierigkeit").
	    then().
	        assertThat().
	        body("id",hasSize(3));
	}
	
	/**
	 * Eine Testmethode um den Abruf einer nicht vorhandenen Schwierigkeit zu testen.
	 */
	@Test
	public void getNonExistingSchwierigkeitByIdTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		given().
		queryParam("id","4").
    when().
        get("https://localhost:443/MuseumApp/rest/Schwierigkeit/FindById").
    then().
        assertThat().
        body("id",is(nullValue()));
	}

}
