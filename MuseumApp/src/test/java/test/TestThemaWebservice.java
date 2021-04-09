package test;

import org.junit.Test;

import bean.Thema;
import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Eine Testklasse, um den ThemaWebservice zu testen.
 */
public class TestThemaWebservice{
	
	private static String ssl_path = "P:\\web\\apache-tomcat-10.0.0\\tomcat.keystore";
	private static String ssl_password ="password";
	
	/**
	 * Eine Testmethode um den Abruf aller Themen zu testen.
	 */
	@Test
	public void getAllThemenTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/Thema").then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um den Abruf eines bestimmten Themas zu testen.
	 */
	@Test
	public void getExistingThemaByIdTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		get("https://localhost:443/MuseumApp/rest/Thema/FindById?id=1").then().statusCode(200);
	}
	
	/**
	 * Eine Testmethode um den Abruf eines nicht vorhandenen Themas zu testen. 
	 */
	@Test
	public void getNonExistingThemaByIdTest() {
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		given().
		queryParam("id","80").
    when().
        get("https://localhost:443/MuseumApp/rest/Thema/FindById").
    then().
        assertThat().
        body("id",is(nullValue()));
	}
	
	/**
	 * Eine Testmethode um das Hinzufuegen neuer Themen zu testen.
	 */
	@Test
	public void addThemaTest() 
	{
		RestAssured.authentication = RestAssured.certificate(ssl_path, ssl_password, CertificateAuthSettings.certAuthSettings().allowAllHostnames());
		
		with().body(new Thema(5,"Mobilfunk"))
		     	.when()
		      .request("POST", "https://localhost:443/MuseumApp/rest/Thema/Add")
		      .then()
		      .statusCode(204);
	}
}
