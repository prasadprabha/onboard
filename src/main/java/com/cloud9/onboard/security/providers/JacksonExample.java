package com.cloud9.onboard.security.providers;

	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloud9.onboard.pojo.FieldValue;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


	public class JacksonExample {
		public static void main(String[] args) {

			ObjectMapper mapper = new ObjectMapper();

			Map<String,FieldValue> variables = new HashMap<String,FieldValue>();
			variables.put("name", new FieldValue("Boolean","true"));
			
			try {
				//Convert object to JSON string and save into file directly 
				mapper.writeValue(new File("C:\\Users\\Admin\\Desktop\\input.json"), variables);
				System.out.println(variables);
				
				//Convert object to JSON string
			//	String jsonInString = mapper.writeValueAsString(user);
			//	System.out.println(jsonInString);
				
				//Convert object to JSON string and pretty print
			//	jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			//	System.out.println(jsonInString);
				
				
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}		}
