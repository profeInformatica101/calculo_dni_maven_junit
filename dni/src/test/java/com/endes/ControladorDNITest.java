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
    @DisplayName("Cálculo de la letra para un número de DNI válido")
    void testCalcularLetraValido() {
        // Caso de prueba válido
        String numeroDni = "12345678";
        char letraEsperada = 'Z';

        try {
            var metodo = ControladorDNI.class.getDeclaredMethod("calcularLetra", String.class);
            metodo.setAccessible(true);
            char letraCalculada = (char) metodo.invoke(controladorDNI, numeroDni);
            assertEquals(letraEsperada, letraCalculada, "La letra calculada no es correcta.");
        } catch (Exception e) {
            fail("Error al acceder al método calcularLetra: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Cálculo de la letra para un número de DNI válido incluyendo rangos extremos")
    void testCalcularLetraRangosExtremos() {
        // Caso para rango mínimo válido
        String numeroMinimo = "00000001";
        char letraEsperadaMinima = 'R';

        try {
            var metodo = ControladorDNI.class.getDeclaredMethod("calcularLetra", String.class);
            metodo.setAccessible(true);
            char letraCalculada = (char) metodo.invoke(controladorDNI, numeroMinimo);
            assertEquals(letraEsperadaMinima, letraCalculada, "La letra calculada para el rango mínimo no es correcta.");
        } catch (Exception e) {
            fail("Error al acceder al método calcularLetra: " + e.getMessage());
        }

        // Caso para rango máximo válido
        String numeroMaximo = "99999999";
        char letraEsperadaMaxima = 'R';

        try {
            var metodo = ControladorDNI.class.getDeclaredMethod("calcularLetra", String.class);
            metodo.setAccessible(true);
            char letraCalculada = (char) metodo.invoke(controladorDNI, numeroMaximo);
            assertEquals(letraEsperadaMaxima, letraCalculada, "La letra calculada para el rango máximo no es correcta.");
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
        String dniValido = "12345678Z";
        assertTrue(controladorDNI.esValido(dniValido), "El DNI válido no fue reconocido como tal.");
    }

    /**
     * Prueba para verificar que un DNI inválido sea reconocido como tal.
     */
    @Test
    @DisplayName("Validación de un DNI inválido")
    void testEsValido_DniInvalido() {
        String dniInvalido = "12345678A";
        assertFalse(controladorDNI.esValido(dniInvalido), "El DNI inválido fue reconocido como válido.");
    }

    /**
     * Prueba para manejar entradas nulas, longitud incorrecta y caracteres inválidos.
     */
    @Test
    @DisplayName("Validación de entradas inválidas para un DNI")
    void testEsValido_DniEntradasInvalidas() {
        assertFalse(controladorDNI.esValido(null), "Un DNI nulo fue reconocido como válido.");
        assertFalse(controladorDNI.esValido("1234567Z"), "Un DNI demasiado corto fue reconocido como válido.");
        assertFalse(controladorDNI.esValido("123456789Z"), "Un DNI demasiado largo fue reconocido como válido.");
        assertFalse(controladorDNI.esValido("abcdefghI"), "Un DNI con caracteres no numéricos fue reconocido como válido.");
    }

    /**
     * Prueba para verificar que la generación de un DNI aleatorio sea válida.
     */
    @Test
    @DisplayName("Generación de un DNI aleatorio válido")
    void testGenerarAleatorioDNI() {
        String dniAleatorio = controladorDNI.generarAleatorioDNI();
        assertTrue(controladorDNI.esValido(dniAleatorio), "El DNI aleatorio generado no es válido.");
    }

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