package com.example.backend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContextManager;

import com.example.backend.models.dao.IMedicoDAO;
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
	@Autowired
	private IMedicoDAO medicoRepo;
	
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
		 request = new Request.Builder()
		  .url("https://servidor-mantenimiento.herokuapp.com/api/pacienteMedico/"+arg1+"/"+arg2)
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "063631ca-0b75-4509-bb8b-8c69201b1858")
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
			  .url("https://servidor-mantenimiento.herokuapp.com/eliminarPacienteMedico")
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
						PacienteMedico pacienteMedico = pacienteMedicoRepo.findByCustom(arg1,arg2);
							assertNull(pacienteMedico);
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
		 request = new Request.Builder()
				  .url("https://servidor-mantenimiento.herokuapp.com/api/medico/"+medicoDNI+"/"+hora_inicio+"/"+hora_fin)
				  .get()
				  .addHeader("cache-control", "no-cache")
				  .addHeader("Postman-Token", "063631ca-0b75-4509-bb8b-8c69201b1858")
				  .build();
	}

	@Then("El horario ha sido insertado correrctamente {string}")
	public void el_horario_ha_sido_insertado_correrctamente(String arg1) {
		   if(arg1.equals("OK")) {
				try {
							Medico medico = medicoRepo.findByDni(medicoDNI);
					assertNotNull(medico.getHoraI());
					assertNotNull(medico.getHoraF());
				} catch (Exception e) {
					fail("debería existir un horario");
				}
			 
		   }
	}
	@Then("Recibo una respuesta horario Response {string}")
	public void recibo_una_respuesta_horario_Response(String arg1) {
		try {
			Response response = client.newCall(request).execute();
			String prueba= response.body().string();
			JSONObject jsonObject = new JSONObject(prueba);
		} catch (Exception e) {
			fail("Error recibiendo la respuesta");
		}
	}


}
