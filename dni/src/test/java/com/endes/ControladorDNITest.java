package com.endes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ControladorDNITest {


    private final ControladorDNI controladorDNI = new ControladorDNI();

    /**
     * Prueba para verificar que el cálculo de la letra del DNI es correcto.
     */
    @Test
    void testCalcularLetra() {
        // Casos de prueba: número del DNI y su letra correspondiente
        String numeroDni = "12345678"; // DNI conocido
        char letraEsperada = 'Z';       // Letra calculada manualmente

        // Usamos reflexión para probar el método privado calcularLetra
        try {
            var metodo = ControladorDNI.class.getDeclaredMethod("calcularLetra", String.class);
            metodo.setAccessible(true);
            char letraCalculada = (char) metodo.invoke(controladorDNI, numeroDni);
            assertEquals(letraEsperada, letraCalculada, "La letra calculada no es correcta.");
        } catch (Exception e) {
            fail("Error al acceder al método calcularLetra: " + e.getMessage());
        }
    }
    /**
     * Prueba para verificar que un DNI válido sea reconocido como tal.
     */
    @Test
    @DisplayName("Validación de un DNI válido")
    void testEsValido_DniValido() {
        // DNI completo válido
        String dniValido = "12345678Z"; // Número y letra calculados

        // Verificar que el método identifica correctamente el DNI como válido
        assertTrue(controladorDNI.esValido(dniValido), "El DNI válido no fue reconocido como tal.");
    }

    /**
     * Prueba para verificar que un DNI inválido sea reconocido como tal.
     */
    @Test
    @DisplayName("Validación de un DNI inválido")
    void testEsValido_DniInvalido() {
        // DNI completo inválido
        String dniInvalido = "12345678A"; // Letra incorrecta

        // Verificar que el método identifica correctamente el DNI como inválido
        assertFalse(controladorDNI.esValido(dniInvalido), "El DNI inválido fue reconocido como válido.");
    }

    /**
     * Prueba para verificar el manejo de entradas nulas o con longitud incorrecta.
     */
    @Test
    @DisplayName("Validación de formato incorrecto en un DNI")
    void testEsValido_DniFormatoIncorrecto() {
        // Casos de prueba con formato incorrecto
        String dniCorto = "1234567Z";  // Menos de 8 dígitos
        String dniLargo = "123456789Z"; // Más de 8 dígitos
        String dniNulo = null;          // Valor nulo
        String dniCaracteresNoNumericos = "abcdefghI"; // Caracteres no numéricos

        assertFalse(controladorDNI.esValido(dniCorto), "Un DNI corto fue reconocido como válido.");
        assertFalse(controladorDNI.esValido(dniLargo), "Un DNI largo fue reconocido como válido.");
        assertFalse(controladorDNI.esValido(dniNulo), "Un DNI nulo fue reconocido como válido.");
        assertFalse(controladorDNI.esValido(dniCaracteresNoNumericos), "Un DNI con caracteres no numéricos fue reconocido como válido.");
    }

    /**
     * Prueba para verificar que la generación de un DNI aleatorio sea válida.
     */
    @Test
    @DisplayName("Generación de un DNI aleatorio válido")
    void testGenerarAleatorioDNI() {
        // Generar un DNI aleatorio
        String dniAleatorio = controladorDNI.generarAleatorioDNI();

        // Verificar que el DNI generado sea válido
        assertTrue(controladorDNI.esValido(dniAleatorio), "El DNI aleatorio generado no es válido.");
    }
}
