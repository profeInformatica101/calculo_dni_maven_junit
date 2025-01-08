package com.endes;
public class ControladorDNI {

    private static final char[] LETRAS = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
    private static final int DIVISOR_DNI = 23;
    private static final int LONGITUD_NUMERO_DNI = 8;
    private static final int LONGITUD_DNI_COMPLETO = 9;

    /**
     * Calcula la letra correspondiente a un número de DNI.
     * 
     * @param numeroDni Cadena con el número del DNI (8 dígitos).
     * @return La letra correspondiente al DNI.
     * @throws IllegalArgumentException Si el número tiene un formato inválido.
     */
    private char calcularLetra(String numeroDni) {
        if (numeroDni == null || numeroDni.length() != LONGITUD_NUMERO_DNI || !numeroDni.matches("\\d+")) {
            throw new IllegalArgumentException("El número del DNI no es válido.");
        }
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
        if (dniCompleto == null || dniCompleto.length() != LONGITUD_DNI_COMPLETO) {
            return false;
        }
        try {
            String numero = dniCompleto.substring(0, LONGITUD_NUMERO_DNI);
            char letraProporcionada = Character.toUpperCase(dniCompleto.charAt(LONGITUD_NUMERO_DNI));
            return letraProporcionada == calcularLetra(numero);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Genera un DNI aleatorio válido.
     * 
     * @return Un DNI válido en formato de cadena.
     */
    public String generarAleatorioDNI() {
        int numeroAleatorio = (int) (Math.random() * 100000000);
        String numeroDni = String.format("%08d", numeroAleatorio);
        char letra = calcularLetra(numeroDni);
        return numeroDni + letra;
    }
}