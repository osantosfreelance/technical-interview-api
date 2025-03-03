package com.ing.be.tia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.be.tia.model.Listings;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.ing.be.tia.service.ListService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//1. Load all files into H2-->Done
//2. create endpoint GET/listing/search for which input will be {"searchQuery":string}-->Done
//3. searchQuery will search of fields episode title, desc and showType-->Done
//4. Create tests
@SpringBootApplication
public class TechnicalInterviewApiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TechnicalInterviewApiApplication.class, args);
		Listings list = context.getBean(Listings.class);
		}
		@Bean
		CommandLineRunner runner(ListService listService){
			return args -> {

				PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				Resource[] resources = resolver.getResources("classpath*:/json/*");

				ObjectMapper mapper = new ObjectMapper();
				TypeReference<List<Listings>> typeReference = new TypeReference<List<Listings>>() {};

				try {
					for(Resource resource : resources){
						InputStream inputStream = resource.getInputStream();
						List<Listings> listValue = mapper.readValue(inputStream, typeReference);
						listService.save(listValue);
						System.out.println("Lists Saved!");
					}
				} catch (IOException e) {
					System.out.println("Unable to save Lists: " + e.getMessage());
				}

			};
		}
}
