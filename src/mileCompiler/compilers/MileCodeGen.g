header
{
/**
 * Semantic analysis for the Mile Language.
 * 
 * @author Ricardo Soto
 * @since 1.5
 */

package mileCompiler.compilers;

}

  
class MileCodeGen extends TreeParser;
options
{
    importVocab=MileParserVocab;
    buildAST = false;
    defaultErrorHandler=false;
}

{
private CodeGenerator   cG = new CodeGenerator();
private AST auxAst ;

}

//==================================================
//Program

programa : #(PROGRAMA {cG.addHeader();} var cuerpo {cG.end();});

var			:	#(DEC_VAR (dec_constantes)* {cG.addMain();} (dec_variables)* {cG.println();cG.println();});

dec_constantes	:	#(CONST (tipo_dato id:IDENTIFICADOR  {cG.addConst(id);} lit {cG.printPtoYComa();cG.println();} ) );

dec_variables	:	#(VAR (tipo:tipo_dato id:IDENTIFICADOR {cG.addVar(tipo,id);}) );


tipo_dato	:	TIPO_ENTERO_PR|TIPO_REAL_PR|TIPO_CARAC_PR|TIPO_BOOLEANO;

cuerpo 		:	#(CUERPO_PROG (assign|estruct_repeat|si)* );

assign 		:	#(ASIGNACION ( id:IDENTIFICADOR {cG.printTab();cG.printId(id);cG.printAsign();} expr  {cG.printPtoYComa();cG.println();} ) {cG.println();});

//=================================================
//Expressions

expr
  : (id2:IDENTIFICADOR {cG.printEspacio();cG.printId(id2);} (op_mat expr)?)
  |	(lit (op_mat expr)?)
  ;
  
  
lit :  	(id:REAL_LITERAL {cG.printEspacio();cG.printLit(id.toString());})
		|(id2:ENTERO_LITERAL {cG.printEspacio();cG.printLit(id2.toString());})
		|(id3:CADENA_LITERAL {cG.printEspacio();cG.printLit(id3.toString());})
		|(id4:VERDADERO_PR {cG.printEspacio();cG.printLit(id4.toString());})
		|(id5:FALSO_PR {cG.printEspacio();cG.printLit(id5.toString());})
		;
//=================================================
//Estructuras Repetitivas

estruct_repeat	:	#(ESTRUC_REPET (repetir|mientras));
repetir		:	#(REPETIR {cG.printIniFor();} rpt_ini {cG.printEspacio();cG.printPtoYComa();} rpt_cond {cG.printEspacio();cG.printPtoYComa();} rpt_inc_dec {cG.printEndRepet();}cuerpo{cG.printTab();cG.printLlaveIzq();cG.println();});
rpt_ini		:	#(RPT_INI id:IDENTIFICADOR {cG.printEspacio();cG.printId(id);cG.printAsign();this.auxAst=id;}(id2:REAL_LITERAL {cG.printEspacio();cG.printLit(id2.toString());}|id3:ENTERO_LITERAL {cG.printEspacio();cG.printLit(id3.toString());}) );
rpt_cond	:	#(RPT_COND id1:IDENTIFICADOR{cG.printEspacio();cG.printId(id1);cG.printEspacio();} op_rel id2:IDENTIFICADOR {cG.printEspacio();cG.printId(id2);});
rpt_inc_dec : 	#(RPT_INC_DEC (INCREMENTAR{cG.printEspacio();cG.printId(this.auxAst); cG.printSuma();cG.printSuma();}|DECREMENTAR{cG.printEspacio();cG.printId(this.auxAst);cG.printGuion();cG.printGuion();} ));



mientras	:	#(MIENTRAS {cG.printTab();cG.printIniMientras();} cond_booleana {cG.printEndRepet();}cuerpo{cG.printTab();cG.printLlaveIzq();cG.println();});
cond_booleana	:	#(COND_BOOLEANA (cond_un_op|cond_mas_un_op)((AND{cG.printAnd();}|OR{cG.printOr();}) (cond_un_op|cond_mas_un_op))* );
cond_un_op		:	#(COND_UN_OP (NEGACION{cG.printNegacion();})? id:IDENTIFICADOR {cG.printId(id);});
cond_mas_un_op	:	#(COND_MAS_UN_OP id1:IDENTIFICADOR{cG.printId(id1);cG.printEspacio();} (op_log|op_rel) id2:IDENTIFICADOR {cG.printEspacio();cG.printId(id2);});


//=================================================
//Estructuras Condicionales
si	:	#(SI {cG.printTab();cG.printIniSi();} cond_booleana {cG.printEndRepet();}cuerpo {cG.printTab();cG.printLlaveIzq();} (sino)?);

sino : 	#(SINO {cG.printTab();cG.printIniSino();} cuerpo {cG.printTab();cG.printLlaveIzq();});


//=================================================
op_mat	:(SUMA{cG.printEspacio();cG.printSuma();})
		|(GUION{cG.printEspacio();cG.printGuion();})
		|(MULTIPLICACION {cG.printEspacio();cG.printMulti();})
		|(DIVISION {cG.printEspacio();cG.printDivision();})
		|(INCREMENTAR {cG.printIncrementar();})
		|(DECREMENTAR{cG.printDecrementar();});

op_rel	:	(MENOR_ESTRICTO{cG.printMenorEst();}|MAYOR_ESTRICTO{cG.printMayorEst();}|MENOR_IGUAL{cG.printMenorIgual();}|MAYOR_IGUAL{cG.printMayorIgual();});
op_log	:	(IGUAL_QUE|DISTINTO_QUE|AND|OR);