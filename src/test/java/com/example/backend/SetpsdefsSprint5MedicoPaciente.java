package com.example.backend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContextManager;

import com.example.backend.models.dao.IPacienteMedicoDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetpsdefsSprint5MedicoPaciente {
	OkHttpClient client;
	Request request;
	@Autowired
	IPacienteMedicoDAO pacienteMedicoRepo;
	
	@Given("^ClienteHttpMedicoPaciente$")
	public void clientehttpmedicopaciente() {
		try {
			new TestContextManager(getClass()).prepareTestInstance(this);
		} catch (Exception e) {
		}
		client = new OkHttpClient();


	}
	
	
	@When("^Envio peticion crear MedicoPaciente dni-user \"([^\"]*)\", dni-medico \"([^\"]*)\", Response \"([^\"]*)\"$")
	public void envio_peticion_crear_MedicoPaciente_dni_user_dni_medico_Response(String arg1, String arg2, String arg3) {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"dniPaciente\":\""+arg1+"\",\"dniMedico\":\""+arg2+"\"}");
		 request = new Request.Builder()
		  .url("https://app-sanidad.herokuapp.com/crearMedicoPaciente")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "af9231cc-f85a-4fbc-a63a-3c3ea6a900a6")
		  .build();
	}
	@Then("^Recibo una respuesta  dni-user \"([^\"]*)\", dni-medico \"([^\"]*)\", Response \"([^\"]*)\"$")
	public void recibo_una_respuesta_dni_user_dni_medico_Response(String arg1, String arg2, String arg3) {
		try {
			Response response = client.newCall(request).execute();
			String prueba= response.body().string();
			JSONObject jsonObject = new JSONObject(prueba);
			if(arg1.equals("OK")) {
				if(jsonObject.get("type").equals("error")) {
					fail("Respuesta fallida pero debería ser correcta");
				}
			}else if(arg1.equals("Error")){
				if(!jsonObject.get("type").equals("error")) {
					fail("Respuesta debería ser fallida pero es correcta");
				}
			}
		} catch (Exception e) {
			fail("Error recibiendo la respuesta");
		}
	}
	@Then("^la relacion ha sido guardada dni-user \"([^\"]*)\", dni-medico \"([^\"]*)\", Response \"([^\"]*)\"$")
	public void la_relacion_ha_sido_guardada_dni_user_dni_medico_Response(String arg1, String arg2, String arg3) {
		if(arg3.equals("OK")) {
			try {
		//		pacienteMedico = pacienteMedicoRepo.findByCustom(arg1,arg2);
		//		assertNotNull(pacienteMedico);
			} catch (Exception e) {
				fail("debería poder encontrarse la relación");
			}
				
			}
		
	}

}
