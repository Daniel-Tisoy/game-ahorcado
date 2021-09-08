package ahorcado;

import java.util.Scanner;

public class Juego {
	private String palabra;
	private char[] encontrado;

	Juego(String palabra) {
		this.palabra = palabra.toUpperCase();
		encontrado = new char[palabra.length()];
		for (int i = 0; i < encontrado.length; i++)
			encontrado[i] = '-';

	}

	public void iniciar() {

		Estados juego = new Estados();
		System.out.println("Palabra: " + "-".repeat(palabra.length()));
		juego.estado(0);
		int i = 1; // iterador
		do {			
			if(palabraEncontrada() == true) {
				imprimirResultados("Ganaste");
				break;
			}
			char l = solicitarLetra();
			if (buscarLetras(l) > 0) {
				encontrarLetras(l);
				imprimirLetras();

			} else{
				juego.estado(i);
				i++;
			}

		} while (i < 8);
		
		if (i==8) {
			imprimirResultados("Perdiste");
		}

	}

	public char solicitarLetra() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Adivina una letra: ");
		String entrada = teclado.next().toUpperCase();
		char letra = entrada.charAt(0);
		return letra;
	}

	private char[] encontrarLetras(char letra) {

		for (int i = 0; i < palabra.length(); i++) {
			if (encontrado[i] != '-') {
				continue;

			} else if (palabra.charAt(i) == letra) {
				encontrado[i] = letra;

			} else {
				encontrado[i] = '-';
			}
		}
		return encontrado;
	}

	private void imprimirLetras() {
		System.out.println("_".repeat(palabra.length()));
		for (int i = 0; i < encontrado.length; i++) {
			System.out.print(encontrado[i]);
		}
		System.out.println("\n" + "_".repeat(palabra.length()));

	}

	private int buscarLetras(char letra) {
		int numletras = 0;// numero de letras encontradas
		for (int i = 0; i < palabra.length(); i++) {
			if (palabra.charAt(i) == letra) {

				numletras++;
			}

		}
		return numletras;
	}

	private int buscarLetras() {
		int numletras = 0;// numero de letras encontradas
		for (int i = 0; i < palabra.length(); i++) {
			if (encontrado[i] != '-') {

				numletras++;
			}

		}
		return numletras;
	}

	private boolean palabraEncontrada() {
		boolean enc = false;
		if (buscarLetras() == palabra.length()) {
			enc = true;
		}
		return enc;
	}

	private void imprimirResultados(String resultado) {
		switch (resultado) {
		case "Ganaste":
			System.out.println("Ganaste....:)");
			break;
		case "Perdiste":
			System.out.println("Perdiste...");
			System.out.println("La palabra era "+palabra);
			break;
		default:
			System.out.println("Error 404");
			break;
		}
	}

}
