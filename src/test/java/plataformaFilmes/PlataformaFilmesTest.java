package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import maps.LoginMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;
import utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaFilmesTest {

    static String token;

    @Test
    public void validarLogin(){
        RestUtils.setBaseURI("http://localhost:8080/");

        String json = "{ + " +
                " \"email\": \"aluno@email.com\"," +
                "  \"senha\": \"123456\"" +
                "}";

        Response response = RestUtils.post(json, ContentType.JSON, "auth");
        assertEquals(200, response.statusCode());
        //String token = response.body().jsonPath().get("token");  caminho completo
        String token = response.jsonPath().get("token");
        //System.out.println(token);
    }

    @BeforeAll
    public static void validarLoginMap(){
        RestUtils.setBaseURI("http://localhost:8080/");
        /*Map<String, String> map = new HashMap<>();
        map.put("email","aluno@email.com");
        map.put("senha", "123456");*/
        LoginMap.initLogin();

        //Response response = RestUtils.post(map, ContentType.JSON, "auth");
        Response response = RestUtils.post(LoginMap.getLogin(), ContentType.JSON, "auth");
        assertEquals(200, RestUtils.getResponse().statusCode());
        //String token = RestUtils.getResponse().jsonPath().get("token");  caminho completo
        LoginMap.token = RestUtils.getResponse().jsonPath().get("token");
        //token = response.jsonPath().get("token");
        //System.out.println(token);


    }

    @Test  //AUTORIZAÇÃO DO SERVIÇO
    public void validarConsultaCategoria(){
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+LoginMap.token);

        Response response = RestUtils.get(header, "categorias");
        //para criar o metodo selecionar após o =
        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        assertEquals("Terror",response.jsonPath().get("tipo[2]"));
        List<String> listTipo = response.jsonPath().get("tipo");
        assertTrue(listTipo.contains("Terror"));


    }



}
