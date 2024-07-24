package com.criptography.criptography;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CriptographyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptographyApplication.class, args);

		try {
			String json = "{\"pan\":\"6056800060703033\",\"expiration\":\"2505\",\"cvv\":\"420\"}";
//			String json = "Voltta@2024";//"password";
			String encryptedJson = Criptografia.encrypt(json);
			System.out.println("Encrypted JSON: " + encryptedJson);
			System.out.println("Decrypt JSON: " + Criptografia.decrypt(encryptedJson));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
