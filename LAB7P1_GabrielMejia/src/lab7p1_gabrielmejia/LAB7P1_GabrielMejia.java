package lab7p1_gabrielmejia;

import java.util.Scanner;
import java.util.Random;

public class LAB7P1_GabrielMejia {

    public static void main(String[] args) { //Fila 3 Asiento 10
        Scanner sc = new Scanner(System.in);
        int menu = 0;

        while (menu != 3) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Tres en raya");
            System.out.println("2. Puntos de silla");
            System.out.println("3. Salir");

            System.out.print("Ingrese una opcion: ");
            menu = sc.nextInt();

            switch (menu) {
                case 1 -> {
                    Scanner scanner = new Scanner(System.in);
                    boolean jugarOtraVez = true;

                    while (jugarOtraVez) {
                        char[][] tablero = generarTablero();
                        mostrarTablero(tablero);

                        boolean juegoEnCurso = true;

                        while (juegoEnCurso) {
                            turnoUsuario(tablero, scanner);
                            mostrarTablero(tablero);
                            juegoEnCurso = !verificarFinJuego(tablero, 'X');

                            if (juegoEnCurso) {
                                turnoMaquina(tablero);
                                mostrarTablero(tablero);
                                juegoEnCurso = !verificarFinJuego(tablero, 'O');
                            }
                        }

                        System.out.println("¿Quieres jugar otra vez? (S para si, N para no): ");
                        char opcion = scanner.next().toUpperCase().charAt(0);
                        jugarOtraVez = (opcion == 'S');
                    }
                }// Fin de Case 1
                
                case 2 -> {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("numero de filas: ");
                    int filas = scanner.nextInt();

                    System.out.print("numero de columnas: ");
                    int columnas = scanner.nextInt();

                    int[][] matriz = generarIntMatrizAleatoria(filas, columnas);

                    System.out.println("Matriz generada:");
                    imprimirMatriz(matriz);

                    encontrarPuntosSilla(matriz);
                }//Fin de Case 2
                
                case 3 -> {
                    
                }// Fin de Case 3 / Salir
                default -> System.out.println("Opción no valida.");
            }
        }
    }//Fin de Main
    
    //Case 1 Comienzo
    public static char[][] generarTablero() {
        char[][] tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
        return tablero;
    }

    public static void mostrarTablero(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            System.out.print("[ ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j]);
                if (j < 2) {
                    System.out.print(", ");
                }
            }
            System.out.println(" ] ");
        }
    }

    public static boolean verificarPosicionValida(char[][] tablero, int fila, int columna) {
        return fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ';
    }

    public static void turnoUsuario(char[][] tablero, Scanner scanner) {
        int fila, columna;

        boolean movimientoValido = false;
        while (!movimientoValido) {
            System.out.println("Turno del usuario (X). ");
            System.out.println("Ingrese Fila (1, 2, 3): ");
            fila = scanner.nextInt() - 1;
            System.out.println("Ingrese Columna (1, 2, 3): ");
            columna = scanner.nextInt() - 1;

            if (verificarPosicionValida(tablero, fila, columna)) {
                tablero[fila][columna] = 'X';
                movimientoValido = true;
            } else {
                System.out.println("Posicion no valida. Intente otra vez");
            }
        }
    }

    public static void turnoMaquina(char[][] tablero) {
        System.out.println("Turno de la Máquina (O):");
        Random rand = new Random();
        int fila, columna;

        boolean movimientoValido = false;
        while (!movimientoValido) {
            fila = rand.nextInt(3);
            columna = rand.nextInt(3);

            if (verificarPosicionValida(tablero, fila, columna)) {
                tablero[fila][columna] = 'O';
                movimientoValido = true;
            }
        }
    }

    public static boolean verificarFinJuego(char[][] tablero, char jugador) {
    if (verificarVictoria(tablero, 'X')) {
        System.out.println("X ha ganado");
        return true;
    } else if (verificarVictoria(tablero, 'O')) {
        System.out.println("O ha ganado");
        return true;
    } else if (verificarEmpate(tablero)) {
        return true;
    }

    return false;
    }
    
    public static boolean verificarVictoria(char[][] tablero, char jugador) {
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) ||
                (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador)) {
                return true;
            }
        }

        if ((tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) ||
            (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador)) {
            return true;
        }

        return false;
    }
    
    public static boolean verificarEmpate(char[][] tablero) { 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("Empate.");
        return true;
    }
    // Case 1 Fin
    
    //Case 2 Comienzo
    private static int[][] generarIntMatrizAleatoria(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        Random random = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(100);
            }
        }

        return matriz;
    }

    private static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static void encontrarPuntosSilla(int[][] matriz) {
        boolean hayPuntosSilla = false;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                int elemento = matriz[i][j];

                if (esMinimoEnFila(elemento, matriz, i) && esMaximoEnColumna(elemento, matriz, j)) {
                    System.out.println("Punto de silla encontrado en (" + i + ", " + j + "): " + elemento);
                    hayPuntosSilla = true;
                }
            }
        }

        if (!hayPuntosSilla) {
            System.out.println("No se encontro ningun punto de silla en la matriz.");
        }
    }

    private static boolean esMinimoEnFila(int elemento, int[][] matriz, int fila) {
        for (int j = 0; j < matriz[0].length; j++) {
            if (matriz[fila][j] < elemento) {
                return false;
            }
        }
        return true;
    }

    private static boolean esMaximoEnColumna(int elemento, int[][] matriz, int columna) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] > elemento) {
                return false;
            }
        }
        return true;
    }
    //Case 2 Fin
    
    
}// Fin de Class
