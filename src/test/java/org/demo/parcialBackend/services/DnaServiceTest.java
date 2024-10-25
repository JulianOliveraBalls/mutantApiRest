package org.demo.parcialBackend.services;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DnaServiceTest {

    private static DnaService dnaService;

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("Inicializando recursos compartidos antes de todos los tests");
        dnaService = new DnaService(null); // Usamos null solo para el test ya que no estamos probando el repositorio
    }

    @BeforeEach
    public void setUpBeforeEach() {
        System.out.println("Ejecutando precondiciones antes de cada test");
    }

    @AfterEach
    public void tearDownAfterEach() {
        System.out.println("Limpiando después de cada test");
    }

    @AfterAll
    public static void tearDownAfterAll() {
        System.out.println("Liberando recursos después de todos los tests");
    }

    @Test
    public void testMutantSequenceInRows() {
        String[] dna = {
                "AAAAAA",
                "CAGTGC",
                "TTGTAG",
                "CTCAGA",
                "GCTGTA",
                "CCTTAC"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en las filas");
    }

    @Test
    public void testMutantSequenceInColumns() {
        String[] dna = {
                "ATGACG",
                "TACGTT",
                "GCTTCT",
                "GTTGTC",
                "GTAGTT",
                "GGTCAC"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en las columnas");
    }

    @Test
    public void testMutantSequenceInMainDiagonals() {
        String[] dna = {
                "AGCTAG",
                "TACGGT",
                "GTACAC",
                "ATGATG",
                "GTAGCA",
                "TGGCTA"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en las diagonales principales");
    }

    @Test
    public void testMutantSequenceInSecondaryLeftDiagonals() {
        String[] dna = {
                "ATGTTG",
                "GTCAGT",
                "GTCGAG",
                "CTGTTG",
                "GTAGTC",
                "AGTCAC"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en las diagonales secundarias hacia la izquierda");
    }

    @Test
    public void testMutantSequenceInSecondaryRightDiagonals() {
        String[] dna = {
                "ATACCG",
                "GAAGTA",
                "GCATAC",
                "TTTAAG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en las diagonales secundarias hacia la derecha");
    }

    @Test
    public void testMutantSequenceInTertiaryLeftDiagonals() {
        String[] dna = {
                "AATGAT",
                "GTGTAG",
                "CCTGGT",
                "TCTGTC",
                "GGCTTT",
                "AGTCAA"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en otras diagonales hacia la izquierda");
    }

    @Test
    public void testMutantSequenceInTertiaryRightDiagonals() {
        String[] dna = {
                "ATGATG",
                "CTCTTG",
                "ACCGGT",
                "ACCTGT",
                "GAGCTC",
                "AGGCAA"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante en otras diagonales hacia la derecha");
    }

    @Test
    public void testNonMutantSequence() {
        String[] dna = {
                "ACGATG",
                "GTCCTA",
                "AATGGC",
                "ACTTGT",
                "GGTACC",
                "AGGCAA"
        };
        assertFalse(dnaService.esMutante(dna), "No debería detectar una secuencia mutante");
    }


    @Test
    public void testNonMutantSequenceSample1() {
        String[] dna = {
                "ACAT",
                "GCGG",
                "ATAC",
                "CGGA"
        };
        assertFalse(dnaService.esMutante(dna), "No debería detectar una secuencia mutante");
    }

    @Test
    public void testMutantSequenceSample2() {
        String[] dna = {
                "TGAC",
                "ACCT",
                "TCAC",
                "CATC"
        };
        assertTrue(dnaService.esMutante(dna), "Debería detectar una secuencia mutante");
    }


    @Test
    public void testNonMutantSequenceSample2() {
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAG",
                "CGTC"
        };
        assertFalse(dnaService.esMutante(dna), "No debería detectar una secuencia mutante");
    }
}
