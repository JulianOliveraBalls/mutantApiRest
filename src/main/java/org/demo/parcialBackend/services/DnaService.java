package org.demo.parcialBackend.services;
import org.demo.parcialBackend.entities.Dna;
import org.demo.parcialBackend.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DnaService {

    private final DnaRepository dnaRepository;
    private static final int SEQUENCE_LENGTH = 4;
    private static final String VALID_CHARACTERS = "AGTC";

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    private boolean validarAdn(String[] adn) {
        if (adn == null || adn.length == 0) {
            return false;  // Verifica que el ADN no sea nulo o vacío
        }

        int n = adn.length;
        for (String secuencia : adn) {
            if (secuencia == null || secuencia.length() != n || !secuencia.matches("[" + VALID_CHARACTERS + "]+")) {
                return false;  // Validar longitud y caracteres
            }
        }
        return true;  // Retorna verdadero si todas las validaciones son correctas
    }

    public boolean analizarAdn(String[] adn) {
        if (!validarAdn(adn)) {
            throw new IllegalArgumentException("Secuencia de ADN inválida");
        }

        String secuenciaAdn = String.join(",", adn);

        // Verifico si el ADN ya está en la base de datos
        return dnaRepository.findByDna(secuenciaAdn)
                .map(Dna::isMutant)
                .orElseGet(() -> {
                    boolean esMutante = esMutante(adn);
                    Dna entidadAdn = Dna.builder()
                            .dna(secuenciaAdn)
                            .isMutant(esMutante)
                            .build();
                    dnaRepository.save(entidadAdn);  // Guardo el ADN en la base de datos
                    return esMutante;  // Retorno si es mutante o no
                });
    }

    public boolean esMutante(String[] adn) {
        int contadorSecuencias = 0;

        // Convertir el ADN a una matriz de caracteres
        int n = adn.length;
        char[][] matrizAdn = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrizAdn[i] = adn[i].toCharArray(); // Asignamos cada fila del ADN a la matriz
        }

        // Verifico secuencias en filas
        contadorSecuencias += verificarFilas(adn);
        if (contadorSecuencias > 1) return true;

        // Verifico secuencias en columnas
        contadorSecuencias += verificarColumnas(adn);
        if (contadorSecuencias > 1) return true;

        // Verifico secuencias en diagonales
        contadorSecuencias += verificarDiagonales(matrizAdn); // Llamada a la nueva implementación
        return contadorSecuencias > 1;
    }

    private int verificarFilas(String[] adn) {
        int contadorSecuencias = 0;
        for (String fila : adn) {
            if (tieneSecuencia(fila)) {
                contadorSecuencias++;
                if (contadorSecuencias > 1) return contadorSecuencias;  // Retorno si hay más de una secuencia
            }
        }
        return contadorSecuencias;
    }

    private int verificarColumnas(String[] adn) {
        int contadorSecuencias = 0;
        for (int col = 0; col < adn.length; col++) {
            StringBuilder columna = new StringBuilder();
            for (String fila : adn) {
                columna.append(fila.charAt(col));
            }
            if (tieneSecuencia(columna.toString())) {
                contadorSecuencias++;
                if (contadorSecuencias > 1) return contadorSecuencias;
            }
        }
        return contadorSecuencias;
    }

    private int verificarDiagonales(char[][] adn) {
        int contadorSecuencias = 0;
        int n = adn.length;
        int secuencia;

        // Verificación de diagonales de izquierda a derecha (ascendentes y descendentes)
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                secuencia = 1;

                // Diagonal descendente de izquierda a derecha
                for (int k = 1; k < SEQUENCE_LENGTH; k++) {
                    // Comparación directa de caracteres en la matriz
                    if (adn[i + k][j + k] == adn[i][j]) {
                        secuencia++;
                    } else {
                        break; // Si no coinciden, salimos del bucle
                    }
                }
                if (secuencia == SEQUENCE_LENGTH) {
                    contadorSecuencias++;
                    if (contadorSecuencias > 1) return contadorSecuencias;
                }

                secuencia = 1;

                // Diagonal ascendente de derecha a izquierda
                for (int k = 1; k < SEQUENCE_LENGTH; k++) {
                    // Comparación directa de caracteres en la matriz
                    if (adn[i + k][n - j - k - 1] == adn[i][n - j - 1]) {
                        secuencia++;
                    } else {
                        break; // Si no coinciden, salimos del bucle
                    }
                }
                if (secuencia == SEQUENCE_LENGTH) {
                    contadorSecuencias++;
                    if (contadorSecuencias > 1) return contadorSecuencias;
                }
            }
        }

        // Verificación de diagonales de derecha a izquierda (ascendentes y descendentes)
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = SEQUENCE_LENGTH - 1; j < n; j++) {
                secuencia = 1;

                // Diagonal descendente de derecha a izquierda
                for (int k = 1; k < SEQUENCE_LENGTH; k++) {
                    // Comparación directa de caracteres en la matriz
                    if (adn[i + k][j - k] == adn[i][j]) {
                        secuencia++;
                    } else {
                        break; // Si no coinciden, salimos del bucle
                    }
                }
                if (secuencia == SEQUENCE_LENGTH) {
                    contadorSecuencias++;
                    if (contadorSecuencias > 1) return contadorSecuencias;
                }

                secuencia = 1;

                // Diagonal ascendente de izquierda a derecha
                for (int k = 1; k < SEQUENCE_LENGTH; k++) {
                    // Comparación directa de caracteres en la matriz
                    if (adn[n - 1 - i - k][j - k] == adn[n - 1 - i][j]) {
                        secuencia++;
                    } else {
                        break; // Si no coinciden, salimos del bucle
                    }
                }
                if (secuencia == SEQUENCE_LENGTH) {
                    contadorSecuencias++;
                    if (contadorSecuencias > 1) return contadorSecuencias;
                }
            }
        }

        return contadorSecuencias;
    }

    private boolean tieneSecuencia(String linea) {
        int count = 1;
        for (int i = 1; i < linea.length(); i++) {
            if (linea.charAt(i) == linea.charAt(i - 1)) {
                count++;
                if (count == SEQUENCE_LENGTH) return true;  // Retorno verdadero si encuentro una secuencia
            } else {
                count = 1;  // Reinicio el contador si los caracteres son diferentes
            }
        }
        return false;  // Retorno falso si no se encuentra una secuencia
    }
}
