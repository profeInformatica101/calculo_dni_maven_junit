package com.endes;

public class ControladorDNI {

    private static final char[] LETRAS = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
    private static final int DIVISOR_DNI = 23;
    private static final int MIN_DNI = 10000000;
    private static final int MAX_DNI = 99999999;

    /**
     * Calcula la letra correspondiente a un número de DNI.
     * 
     * @param numeroDni Cadena con el número del DNI (8 dígitos).
     * @return La letra correspondiente al DNI.
     * @throws NumberFormatException Si el número no es válido.
     */
    private char calcularLetra(String numeroDni) {
        int numero = Integer.parseInt(numeroDni);
        int resto = numero % DIVISOR_DNI;
        return LETRAS[resto];
    }

    /**
     * Verifica si un DNI completo es válido.
     * 
     * @param dniCompleto DNI completo con 8 dígitos y 1 letra.
     * @return true si el DNI es válido, false en caso contrario.
     */
    public boolean esValido(String dniCompleto) {
        if (dniCompleto == null || dniCompleto.length() != 9) {
            return false;
        }
        try {
            String numero = dniCompleto.substring(0, 8);
            char letraProporcionada = Character.toUpperCase(dniCompleto.charAt(8));
            return letraProporcionada == calcularLetra(numero);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Genera un DNI aleatorio válido.
     * 
     * @return Un DNI válido en formato de cadena.
     */
    public String generarAleatorioDNI() {
        int numeroAleatorio = (int) (Math.random() * (MAX_DNI - MIN_DNI + 1)) + MIN_DNI;
        String numeroDni = Integer.toString(numeroAleatorio);
        char letra = calcularLetra(numeroDni);
        return numeroDni + letra;
    }
}