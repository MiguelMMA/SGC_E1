package com.example.backend;

import java.io.IOException;

import javax.xml.ws.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.backend.models.dao.IMedicoDAO;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;





@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTests2 {
	@Autowired
	IMedicoDAO userRepo;
	
	@Test
	public void pruebas() throws IOException {
		try {
			new TestContextManager(getClass()).prepareTestInstance(this);
		} catch (Exception e1) {

		}
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"dniMedico\": \"65278762R\",\"especialidad\": \"Podologia\"}\n");
		Request request = new Request.Builder()
		  .url("http://localhost:8080/api/medico")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("User-Agent", "PostmanRuntime/7.20.1")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "d0e85ccf-ae92-4b5e-abb5-bde6a18e63fb,892e5c4f-2b09-407e-9ce2-dcfe2da06088")
		  .addHeader("Host", "localhost:8080")
		  .addHeader("Accept-Encoding", "gzip, deflate")
		  .addHeader("Content-Length", "55")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
		  .build();

		okhttp3.Response response = client.newCall(request).execute();
		
	}
	
	
	
	
}
