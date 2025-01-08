package com.endes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControladorDNITest {

	ControladorDNI controlador;
	
	 @Test
	    void primeraPrueba() {
		 	boolean resultadoEsperado = true;
		 	boolean resultado = controlador.esValido("52669863R");
		 
	        assertEquals("ok","ok");
	    }


}
