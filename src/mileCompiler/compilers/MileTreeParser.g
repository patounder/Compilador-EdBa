header
{
/**
 * Semantic analysis for the Mile Language.
 * 
 * @author Ricardo Soto
 * @since 1.5
 */
package mileCompiler.compilers;
import java.util.ArrayList;
}

  
class MileTreeParser extends TreeParser;
options
{
    importVocab=MileParserVocab;
    buildAST = false;
    defaultErrorHandler=false;
}

{
private SemanticInspector   sI = new SemanticInspector();
private ArrayList<AST> listVar = new ArrayList<AST>();

AST uno;
AST dos;
AST valor;
}

//==================================================
//Program

programa  		:	#(PROGRAMA var cuerpo);

var  			:	#(DEC_VAR (dec_constantes)* (dec_variables)*);

dec_variables	:	#(VAR (tipo:tipo_dato id:IDENTIFICADOR {sI.addVar(tipo,id);}));
          		
dec_constantes	:	#(CONST (tipo:tipo_dato id:IDENTIFICADOR  {sI.addConst(tipo,id);} (REAL_LITERAL | ENTERO_LITERAL |CADENA_LIERAL|VERDADERO_PR|FALSO_PR)) );
				
tipo_dato	:	TIPO_ENTERO_PR|TIPO_REAL_PR|TIPO_CARAC_PR|TIPO_BOOLEANO;

cuerpo 		:	#(CUERPO_PROG (assign|estruct_repeat|si)* );

assign 		:	#(ASIGNACION ( id:IDENTIFICADOR {listVar.clear();sI.checkVar(id); listVar.add(id);} expr));
//=================================================
op_mat	:	(SUMA|GUION|MULTIPLICACION|DIVISION|INCREMENTAR|DECREMENTAR);
op_rel	:	(MENOR_ESTRICTO|MAYOR_ESTRICTO|MENOR_IGUAL|MAYOR_IGUAL);
op_log	:	(IGUAL_QUE|DISTINTO_QUE|AND|OR);

//=================================================
//Estructuras Repetitivas


estruct_repeat	:	#(ESTRUC_REPET (repetir|mientras));

repetir		:	#(REPETIR rpt_ini rpt_cond rpt_inc_dec cuerpo);
rpt_ini		:	#(RPT_INI id:IDENTIFICADOR {listVar.clear();sI.checkVar(id);listVar.add(id);}
				(REAL_LITERAL {sI.checkLitReal(listVar);}|ENTERO_LITERAL {sI.checkLitEntero(listVar);}) );
rpt_cond	:	#(RPT_COND id1:IDENTIFICADOR{listVar.clear();sI.checkVar(id1);listVar.add(id1);} op_rel id2:IDENTIFICADOR {sI.checkVar(id2);listVar.add(id2); sI.checkTipos(listVar);});
rpt_inc_dec : 	#(RPT_INC_DEC (INCREMENTAR|DECREMENTAR) );


mientras	:	#(MIENTRAS cond_booleana cuerpo);
cond_booleana	:	#(COND_BOOLEANA (cond_un_op|cond_mas_un_op)((AND|OR) (cond_un_op|cond_mas_un_op))* );
cond_un_op		:	#(COND_UN_OP (NEGACION)? id:IDENTIFICADOR {sI.checkVar(id);} );
cond_mas_un_op	:	#(COND_MAS_UN_OP id1:IDENTIFICADOR{listVar.clear();sI.checkVar(id1);listVar.add(id1);} (op_log|op_rel) id2:IDENTIFICADOR {sI.checkVar(id2); listVar.add(id2);sI.checkTipos(listVar);});


//=================================================
//Estructuras Condicionales
si	:	#(SI cond_booleana cuerpo (sino)?);

sino : 	#(SINO cuerpo);

//=================================================
//Expressions

expr
  : (id2:IDENTIFICADOR {sI.checkVar(id2);listVar.add(id2); sI.checkTipos(listVar);} (op_mat expr)?)
  |	(lit (op_mat expr)?)
  ;
  
  
lit :  	(REAL_LITERAL {sI.checkLitReal(listVar);})
		|	(ENTERO_LITERAL {sI.checkLitEntero(listVar);})
		|	(CADENA_LITERAL {sI.checkLitCaracter(listVar);})
		|	((VERDADERO_PR|FALSO_PR) {sI.checkLitBooleano(listVar);})
		;