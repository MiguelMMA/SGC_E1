package com.example.backend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContextManager;

import com.example.backend.models.dao.IPacienteMedicoDAO;
import com.example.backend.models.entity.Medico;
import com.example.backend.models.entity.PacienteMedico;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetpsdefsSprint5MedicoPaciente extends JunitTests2 {
	OkHttpClient client;
	Request request;
	@Autowired
	IPacienteMedicoDAO pacienteMedicoRepo;
	String medicoDNI;
	String hora_inicio;
	String hora_fin;
	
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
		RequestBody body = RequestBody.create(mediaType, "{\"dniMedico\": \"05726690N\",\"dniPaciente\": \"97637789Y\"}\n");
		 request = new Request.Builder()
		  .url("http://localhost:8080/api/pacienteMedico")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("User-Agent", "PostmanRuntime/7.20.1")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "4d73c95e-5f10-4cf9-a56a-8e7918797b1a,fdc4d422-3f96-4a86-a4c0-ec962b2de69b")
		  .addHeader("Host", "localhost:8080")
		  .addHeader("Accept-Encoding", "gzip, deflate")
		  .addHeader("Content-Length", "54")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
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
				PacienteMedico pacienteMedico = pacienteMedicoRepo.findByCustom(arg1,arg2);
				assertNotNull(pacienteMedico);
			} catch (Exception e) {
				fail("debería poder encontrarse la relación");
			}
				
			}
		
	}
	@Then("^borro la relacion dni-user \"([^\"]*)\", dni-medico \"([^\"]*)\", Response \"([^\"]*)\"$")
	public void borro_la_relacion_dni_user_dni_medico_Response(String arg1, String arg2, String arg3) {
		if(arg3.equals("OK")) {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\"dniPaciente\":\""+arg1+"\",\"dniMedico\":\""+arg2+"\"}");
			 request = new Request.Builder()
			  .url("http://localhost:8080/eliminarPacienteMedico")
			  .post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("cache-control", "no-cache")
			  .addHeader("Postman-Token", "af9231cc-f85a-4fbc-a63a-3c3ea6a900a6")
			  .build();
		}
	}
	
	@Then("^la relacion ha sido borrada \"([^\"]*)\", dni-medico \"([^\"]*)\", Response \"([^\"]*)\"$")
	public void la_relacion_ha_sido_borrada_dni_medico_Response(String arg1, String arg2, String arg3) {
		   if(arg3.equals("OK")) {
				try {
					//		pacienteMedico = pacienteMedicoRepo.findByCustom(arg1,arg2);
					//		assertNull(pacienteMedico);
				} catch (Exception e) {
					fail("debería poder encontrarse la relación");
				}
			 
		   }
	}
	@Given("Tengo un medico y su horario dni_medico {string}, hora_inicio {string}, hora_fin {string}, Response {string}")
	public void tengo_un_medico_y_su_horario_dni_medico_hora_inicio_hora_fin_Response(String string, String string2, String string3, String string4) {
		 medicoDNI = string;
		 hora_inicio = string2;
		 hora_fin = string3;
	}
	
	@When("asigno el horario al medico {string}")
	public void asigno_el_horario_al_medico(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("El horario ha sido insertado correrctamente {string}")
	public void el_horario_ha_sido_insertado_correrctamente(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	@Then("Recibo una respuesta horario Response {string}")
	public void recibo_una_respuesta_horario_Response(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


}
