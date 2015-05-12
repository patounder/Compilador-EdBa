// $ANTLR : "mileLexer.g" -> "mileLexer.java"$

/**
 * Lexer for the Mile language.
 * 
 *
 */

package mileCompiler.compilers;

public interface mileLexerVocabTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int CUERPO_PR = 4;
	int DEC_VAR_PR = 5;
	int TIPO_ENTERO_PR = 6;
	int TIPO_REAL_PR = 7;
	int TIPO_CARAC_PR = 8;
	int CONST_PR = 9;
	int TIPO_BOOLEANO = 10;
	int REPETIR_PR = 11;
	int VERDADERO_PR = 12;
	int FALSO_PR = 13;
	int POTENCIA_PR = 14;
	int FACTORIAL_PR = 15;
	int RESIDUO_PR = 16;
	int RADICAL_PR = 17;
	int IMPRIMIR_PR = 18;
	int LEER_PR = 19;
	int MIENTRAS_PR = 20;
	int SI_PR = 21;
	int SINO_PR = 22;
	int REAL_LITERAL = 23;
	int ENTERO_LITERAL = 24;
	int CADENA_LITERAL = 25;
	int PUNTOYCOMA = 26;
	int COMA = 27;
	int PUNTO = 28;
	int DOSPUNTOS = 29;
	int GUION_BAJO = 30;
	int NEGACION = 31;
	int LLAVE_IZQ = 32;
	int LLAVE_DER = 33;
	int CUADRADO_IZQ = 34;
	int CUADRADO_DER = 35;
	int REDONDO_IZQ = 36;
	int REDONDO_DER = 37;
	int SUMA = 38;
	int GUION = 39;
	int MULTIPLICACION = 40;
	int DIVISION = 41;
	int INCREMENTAR = 42;
	int DECREMENTAR = 43;
	int IGUAL_QUE = 44;
	int DISTINTO_QUE = 45;
	int AND = 46;
	int OR = 47;
	int ASIGN = 48;
	int MENOR_ESTRICTO = 49;
	int MAYOR_ESTRICTO = 50;
	int MENOR_IGUAL = 51;
	int MAYOR_IGUAL = 52;
	int DIGITO = 53;
	int NUM_LITERAL = 54;
	int LETRA = 55;
	int IDENTIFICADOR = 56;
	int WS = 57;
	int COMENTARIO_LINEA = 58;
	int COMENTARIO_BLOQUE = 59;
}
