header
{
/**
 * Lexer for the Mile language.
 * 
 *
 */

package mileCompiler.compilers;
}

class mileLexer extends Lexer;
options{
    charVocabulary = '\3'..'\377';
    exportVocab=mileLexerVocab;
    testLiterals=false;
    k=2; // lookahead
    defaultErrorHandler=false;
}

tokens
{
	CUERPO_PR 		="cuerpo";
	DEC_VAR_PR		="dec_var";
	TIPO_ENTERO_PR	="entero";
	TIPO_REAL_PR	="real";
	TIPO_CARAC_PR	="carac";
	CONST_PR		="const";
	TIPO_BOOLEANO	="booleano";
	REPETIR_PR  	="repetir";
	VERDADERO_PR	="verdadero";
	FALSO_PR		="falso";
	POTENCIA_PR		="potencia";
	FACTORIAL_PR	="factorial";	
	RESIDUO_PR		="residuo";
	RADICAL_PR		="radical";
	IMPRIMIR_PR		="imprimir";
	LEER_PR			="leer";
	MIENTRAS_PR		="mientras";
	SI_PR			="si";
	SINO_PR			="sino";
	REAL_LITERAL;
	ENTERO_LITERAL;
	CADENA_LITERAL;
		
}

//Puntuacion
//====================================
PUNTOYCOMA 	:	';';
COMA 		:	',';
PUNTO 		:	'.';
DOSPUNTOS 	:	':';
GUION_BAJO	:	'_';
NEGACION	:	'!';

//Parentesis
//====================================
LLAVE_IZQ		:	'{';
LLAVE_DER		:	'}';
CUADRADO_IZQ	:	'[';
CUADRADO_DER	:	']';
REDONDO_IZQ		:	'(';
REDONDO_DER		:	')';

//Operadores Matematicos y Basicos
//====================================
SUMA			:	'+';
GUION			:	'-';
MULTIPLICACION	:	'*';
DIVISION		:	'/';
INCREMENTAR		: SUMA SUMA;
DECREMENTAR		: GUION GUION;

//Operadores Logicos
//====================================
IGUAL_QUE		: 	'=''=';
DISTINTO_QUE	:	'!''=';
AND				:	'&''&';
OR				:	'|''|';
ASIGN			:	'=';


//Operadores Relacional
//====================================
MENOR_ESTRICTO	:	'<';
MAYOR_ESTRICTO	:	'>';
MENOR_IGUAL		:	'<''=';
MAYOR_IGUAL		:	'>''=';


//===================================================
// Digits

   protected DIGITO : '0'..'9';
   
//====================================   
//Numeros 

NUM_LITERAL : ((DIGITO)+ PUNTO (DIGITO)+ ) =>
(DIGITO)+ '.' ( DIGITO )+ { $setType (REAL_LITERAL); }
| (DIGITO)+ { $setType (ENTERO_LITERAL); }
;

//===================================================
// LETRA

 protected LETRA
      : 'a'..'z'
      | 'A'..'Z'
      ;

//===================================================
// Recognize literals and reserved words

  
   IDENTIFICADOR options {testLiterals=true;} // Checking Reserved Words
      : (LETRA) (LETRA|DIGITO|GUION_BAJO)*
      ;
      
   
//====================================================
//String literals

CADENA_LITERAL : '"' !
                  ( ~('"'|'\n'|'\r') )*
                  '"' !
                ;
                      
//==================================================
// White spaces

// \t : tab
// \f : form feed
// \n : newline feed
// \r : carriage return


WS :    ((' ' | '\t' | '\f') 
        | ( '\n' | '\r' ) { newline(); }
        ) { _ttype = Token.SKIP;  }
        ;

COMENTARIO_LINEA: ('/''/' ( ~('\n'))*) { _ttype = Token.SKIP;  };

COMENTARIO_BLOQUE: "/*" ('*' ~('/') 
| ~ ('*') 
)* "*/" {$setType(Token.SKIP);};

