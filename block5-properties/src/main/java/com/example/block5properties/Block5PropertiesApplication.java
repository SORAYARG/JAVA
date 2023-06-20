package com.example.block5properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class Block5PropertiesApplication {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			String ruta = Block5PropertiesApplication.class.getClassLoader().getResource("application.properties").getPath();
			FileInputStream archivo = new FileInputStream(ruta);
			properties.load(archivo);
			archivo.close();

			String greeting = properties.getProperty("greeting");
			String myNumber = properties.getProperty("my.number");
// String newProperty = properties.getProperty("new.property", "new.property no tiene valor");

// Obtenemos la variable de fuera del código, la cogemos de las variables de entorno del sistema
// Para ello hemos tenido que editar las variables de entorno del sistema y añadir la que queríamos
			String newProperty = System.getenv("new.property");

			System.out.println("El valor de greeting es: " + greeting);
			System.out.println("El valor de my.number es: " + myNumber);
			System.out.println("El valor de new.property es: " + newProperty);
		}
		catch (IOException e) {
			System.out.println("No se pudo cargar el archivo application.properties");
			e.printStackTrace();
		}
}

}
