header
{
/**
 * Parser for the Mile language.
 * 
 * @author Ricardo Soto
 * @since 1.5
 */

package mileCompiler.compilers;

}
//======================================================
// Object allowing syntactic analysis

class MileParser extends Parser;
options
{
    k = 2;
    importVocab = mileLexerVocab;
    exportVocab = mileParserVocab;
    buildAST = true;
    defaultErrorHandler=false;
}


tokens
{
//=======================================================
// Imaginary Tokens 

    PROGRAMA;
    DEC_VAR;
    VAR;
    CUERPO_PROG;
    CONST;
    ASIGNACION;
    FUNC_MAT;
	POTENCIA;
	FACTORIAL;
	RESIDUO;
	RADICAL;
	FUNC_IN_OUT;
	IMPRIMIR;
	LEER;
	ESTRUC_REPET;
	REPETIR;
	RPT_INI;
	RPT_COND;
	RPT_INC_DEC;
	MIENTRAS;
	COND_BOOLEANA;
	COND_UN_OP;
	COND_MAS_UN_OP;
	SI;
	SINO;
}

//======================================================= 
//Main rule

programa : DEC_VAR_PR! CUADRADO_IZQ! var CUADRADO_DER! CUERPO_PR! CUADRADO_IZQ! cuerpo CUADRADO_DER!
          {## = #( #[PROGRAMA, "PROGRAMA"] ,##);}
        ;

//=======================================================
// Dec Var

var			:	((dec_constantes)* (dec_variables)*)
				{## = #( #[DEC_VAR, "DEC_VAR"] ,##);};

dec_variables	: 	(tipo_dato IDENTIFICADOR PUNTOYCOMA!)
          		{## = #( #[VAR, "VARIABLE"] ,##);};
          		
dec_constantes	:	CONST_PR! tipo_dato IDENTIFICADOR ASIGN! (REAL_LITERAL|ENTERO_LITERAL|CADENA_LIERAL|VERDADERO_PR|FALSO_PR) PUNTOYCOMA!
				{## = #( #[CONST, "CONSTANTE"] ,##);};
				
tipo_dato	:	TIPO_ENTERO_PR|TIPO_REAL_PR|TIPO_CARAC_PR|TIPO_BOOLEANO;


//======================================================
//Cuerpo
cuerpo 		:	(assign|func_mat|func_in_out|estruc_repeat|si)*
				{## = #( #[CUERPO_PROG, "CUERPO_PROG"] ,##);};

assign		:	IDENTIFICADOR ASIGN! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL|CADENA_LIERAL|VERDADERO_PR|FALSO_PR|func_mat)((op_mat) (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL|CADENA_LIERAL|VERDADERO_PR|FALSO_PR|func_mat))* PUNTOYCOMA!
				{## = #( #[ASIGNACION, "ASIGNACION"] ,##);};

//======================================================
//Funciones Matemaicas
func_mat	:	(potencia|factorial|residuo|radical)
				{## = #( #[FUNC_MAT, "FUNCION_MATEMATICA"] ,##);};		

potencia 	:	POTENCIA_PR! REDONDO_IZQ! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL) COMA! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL) REDONDO_DER!
				{## = #( #[POTENCIA, "POTENCIA"] ,##);};

factorial	:	FACTORIAL_PR! REDONDO_IZQ! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL) REDONDO_DER!
				{## = #( #[FACTORIAL, "FACTORIAL"] ,##);};

residuo 	:	RESIDUO_PR! REDONDO_IZQ! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL) COMA! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL)REDONDO_DER!
				{## = #( #[RESIDUO, "RESIDUO"] ,##);};
				
radical 	:	RADICAL_PR! REDONDO_IZQ! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL) COMA! (IDENTIFICADOR|REAL_LITERAL|ENTERO_LITERAL) REDONDO_DER!
				{## = #( #[RADICAL, "RADICAL"] ,##);};


//======================================================
//Funciones Entrada y Salida
				
func_in_out	:	(imprimir|leer)
				{## = #( #[FUNC_IN_OUT, "FUNCION_ENTRADA_SALIDA"] ,##);};

imprimir	:	IMPRIMIR_PR! REDONDO_IZQ! CADENA_LITERAL REDONDO_DER! PUNTOYCOMA!
				{## = #( #[IMPRIMIR, "IMPRIMIR"] ,##);};
				
leer		:	LEER_PR! REDONDO_IZQ! IDENTIFICADOR REDONDO_DER! PUNTOYCOMA!
				{## = #( #[LEER, "LEER"] ,##);};


//======================================================
//Estructuras Repetitivas

estruc_repeat 	:	(repetir|mientras)
					{## = #( #[ESTRUC_REPET, "ESTRUCTURA_REPETITIVA"] ,##);};

repetir			:	REPETIR_PR! REDONDO_IZQ! rpt_ini PUNTOYCOMA! rpt_cond PUNTOYCOMA! rpt_inc_dec REDONDO_DER! LLAVE_IZQ! cuerpo LLAVE_DER!
					{## = #( #[REPETIR, "REPETIR"] ,##);};

mientras		:	MIENTRAS_PR! REDONDO_IZQ! cond_booleana REDONDO_DER! LLAVE_IZQ! cuerpo LLAVE_DER!
					{## = #( #[MIENTRAS, "MIENTRAS"] ,##);};

//======================================================
//Estructuras Condicional SI

si				:	SI_PR! REDONDO_IZQ! cond_booleana REDONDO_DER! LLAVE_IZQ! cuerpo LLAVE_DER! (sino)?
					{## = #( #[SI, "SI"] ,##);};
					
sino			:	SINO_PR! LLAVE_IZQ! cuerpo LLAVE_DER!
					{## = #( #[SINO, "SINO"] ,##);};
					
//======================================================
//Componentes repetir

rpt_ini		:	IDENTIFICADOR ASIGN! (REAL_LITERAL|ENTERO_LITERAL)
				{## = #( #[RPT_INI, "INI_FOR"] ,##);};
				
rpt_cond	:	IDENTIFICADOR op_rel IDENTIFICADOR
				{## = #( #[RPT_COND, "COND_FOR"] ,##);};
				
rpt_inc_dec	:	IDENTIFICADOR! (INCREMENTAR|DECREMENTAR)
				{## = #( #[RPT_INC_DEC, "INC_DEC"] ,##);};

//======================================================
//Condiciones Booleanas

cond_booleana	:	(cond_un_op|cond_mas_un_op)((AND|OR) (cond_un_op|cond_mas_un_op))*
					{## = #( #[COND_BOOLEANA, "COND_BOOLEANA"] ,##);};

cond_un_op		:	(NEGACION)? IDENTIFICADOR
					{## = #( #[COND_UN_OP, "COND_UN_OP"] ,##);};

cond_mas_un_op	:	IDENTIFICADOR (op_log|op_rel) IDENTIFICADOR
					{## = #( #[COND_MAS_UN_OP, "COND_MAS_UN_OP"] ,##);};


//======================================================
//Operadores

operadores		:	(op_mat|op_log|op_rel); 	

op_mat	:	(SUMA|GUION|MULTIPLICACION|DIVISION|INCREMENTAR|DECREMENTAR);
op_log	:	(IGUAL_QUE|DISTINTO_QUE|AND|OR);
op_rel	:	(MENOR_ESTRICTO|MAYOR_ESTRICTO|MENOR_IGUAL|MAYOR_IGUAL);
