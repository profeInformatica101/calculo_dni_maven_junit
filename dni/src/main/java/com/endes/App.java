package com.endes;

/**
 * Hello world!
 *
 */
public class App 
{
	  public static void main(String[] args) {
	        ControladorDNI controladorDNI = new ControladorDNI();

	        // Ejemplo de validación de un DNI
	        String dniPrueba = "12345678Z";
	        if (controladorDNI.esValido(dniPrueba)) {
	            System.out.println("El DNI " + dniPrueba + " es válido.");
	        } else {
	            System.out.println("El DNI " + dniPrueba + " no es válido.");
	        }

	        // Generar un DNI aleatorio
	        String dniAleatorio = controladorDNI.generarAleatorioDNI();
	        System.out.println("DNI generado aleatoriamente: " + dniAleatorio);

	        // Probar un DNI con formato incorrecto
	        String dniIncorrecto = "1234567A";
	        if (!controladorDNI.esValido(dniIncorrecto)) {
	            System.out.println("El DNI " + dniIncorrecto + " es inválido, como era esperado.");
	        }
	    }
}
