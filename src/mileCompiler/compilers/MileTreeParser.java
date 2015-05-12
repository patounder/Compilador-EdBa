// $ANTLR : "MileTreeParser.g" -> "MileTreeParser.java"$

/**
 * Semantic analysis for the Mile Language.
 * 
 * @author Ricardo Soto
 * @since 1.5
 */
package mileCompiler.compilers;
import java.util.ArrayList;

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;


public class MileTreeParser extends antlr.TreeParser       implements MileTreeParserTokenTypes
 {

private SemanticInspector   sI = new SemanticInspector();
private ArrayList<AST> listVar = new ArrayList<AST>();

AST uno;
AST dos;
AST valor;
public MileTreeParser() {
	tokenNames = _tokenNames;
}

	public final void programa(AST _t) throws RecognitionException {
		
		AST programa_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1921 = _t;
		AST tmp1_AST_in = (AST)_t;
		match(_t,PROGRAMA);
		_t = _t.getFirstChild();
		var(_t);
		_t = _retTree;
		cuerpo(_t);
		_t = _retTree;
		_t = __t1921;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void var(AST _t) throws RecognitionException {
		
		AST var_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1923 = _t;
		AST tmp2_AST_in = (AST)_t;
		match(_t,DEC_VAR);
		_t = _t.getFirstChild();
		{
		_loop1925:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==CONST)) {
				dec_constantes(_t);
				_t = _retTree;
			}
			else {
				break _loop1925;
			}
			
		} while (true);
		}
		{
		_loop1927:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==VAR)) {
				dec_variables(_t);
				_t = _retTree;
			}
			else {
				break _loop1927;
			}
			
		} while (true);
		}
		_t = __t1923;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void cuerpo(AST _t) throws RecognitionException {
		
		AST cuerpo_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1937 = _t;
		AST tmp3_AST_in = (AST)_t;
		match(_t,CUERPO_PROG);
		_t = _t.getFirstChild();
		{
		_loop1939:
		do {
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ASIGNACION:
			{
				assign(_t);
				_t = _retTree;
				break;
			}
			case ESTRUC_REPET:
			{
				estruct_repeat(_t);
				_t = _retTree;
				break;
			}
			case SI:
			{
				si(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				break _loop1939;
			}
			}
		} while (true);
		}
		_t = __t1937;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void dec_constantes(AST _t) throws RecognitionException {
		
		AST dec_constantes_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST tipo = null;
		AST id = null;
		
		AST __t1932 = _t;
		AST tmp4_AST_in = (AST)_t;
		match(_t,CONST);
		_t = _t.getFirstChild();
		{
		tipo = _t==ASTNULL ? null : (AST)_t;
		tipo_dato(_t);
		_t = _retTree;
		id = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		sI.addConst(tipo,id);
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case REAL_LITERAL:
		{
			AST tmp5_AST_in = (AST)_t;
			match(_t,REAL_LITERAL);
			_t = _t.getNextSibling();
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp6_AST_in = (AST)_t;
			match(_t,ENTERO_LITERAL);
			_t = _t.getNextSibling();
			break;
		}
		case CADENA_LIERAL:
		{
			AST tmp7_AST_in = (AST)_t;
			match(_t,CADENA_LIERAL);
			_t = _t.getNextSibling();
			break;
		}
		case VERDADERO_PR:
		{
			AST tmp8_AST_in = (AST)_t;
			match(_t,VERDADERO_PR);
			_t = _t.getNextSibling();
			break;
		}
		case FALSO_PR:
		{
			AST tmp9_AST_in = (AST)_t;
			match(_t,FALSO_PR);
			_t = _t.getNextSibling();
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		}
		_t = __t1932;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void dec_variables(AST _t) throws RecognitionException {
		
		AST dec_variables_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST tipo = null;
		AST id = null;
		
		AST __t1929 = _t;
		AST tmp10_AST_in = (AST)_t;
		match(_t,VAR);
		_t = _t.getFirstChild();
		{
		tipo = _t==ASTNULL ? null : (AST)_t;
		tipo_dato(_t);
		_t = _retTree;
		id = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		sI.addVar(tipo,id);
		}
		_t = __t1929;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void tipo_dato(AST _t) throws RecognitionException {
		
		AST tipo_dato_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case TIPO_ENTERO_PR:
		{
			AST tmp11_AST_in = (AST)_t;
			match(_t,TIPO_ENTERO_PR);
			_t = _t.getNextSibling();
			break;
		}
		case TIPO_REAL_PR:
		{
			AST tmp12_AST_in = (AST)_t;
			match(_t,TIPO_REAL_PR);
			_t = _t.getNextSibling();
			break;
		}
		case TIPO_CARAC_PR:
		{
			AST tmp13_AST_in = (AST)_t;
			match(_t,TIPO_CARAC_PR);
			_t = _t.getNextSibling();
			break;
		}
		case TIPO_BOOLEANO:
		{
			AST tmp14_AST_in = (AST)_t;
			match(_t,TIPO_BOOLEANO);
			_t = _t.getNextSibling();
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		_retTree = _t;
	}
	
	public final void assign(AST _t) throws RecognitionException {
		
		AST assign_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id = null;
		
		AST __t1941 = _t;
		AST tmp15_AST_in = (AST)_t;
		match(_t,ASIGNACION);
		_t = _t.getFirstChild();
		{
		id = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		listVar.clear();sI.checkVar(id); listVar.add(id);
		expr(_t);
		_t = _retTree;
		}
		_t = __t1941;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void estruct_repeat(AST _t) throws RecognitionException {
		
		AST estruct_repeat_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1950 = _t;
		AST tmp16_AST_in = (AST)_t;
		match(_t,ESTRUC_REPET);
		_t = _t.getFirstChild();
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case REPETIR:
		{
			repetir(_t);
			_t = _retTree;
			break;
		}
		case MIENTRAS:
		{
			mientras(_t);
			_t = _retTree;
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_t = __t1950;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void si(AST _t) throws RecognitionException {
		
		AST si_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1978 = _t;
		AST tmp17_AST_in = (AST)_t;
		match(_t,SI);
		_t = _t.getFirstChild();
		cond_booleana(_t);
		_t = _retTree;
		cuerpo(_t);
		_t = _retTree;
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case SINO:
		{
			sino(_t);
			_t = _retTree;
			break;
		}
		case 3:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_t = __t1978;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void expr(AST _t) throws RecognitionException {
		
		AST expr_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id2 = null;
		
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case IDENTIFICADOR:
		{
			{
			id2 = (AST)_t;
			match(_t,IDENTIFICADOR);
			_t = _t.getNextSibling();
			sI.checkVar(id2);listVar.add(id2); sI.checkTipos(listVar);
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case SUMA:
			case GUION:
			case MULTIPLICACION:
			case DIVISION:
			case INCREMENTAR:
			case DECREMENTAR:
			{
				op_mat(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			}
			break;
		}
		case VERDADERO_PR:
		case FALSO_PR:
		case REAL_LITERAL:
		case ENTERO_LITERAL:
		case CADENA_LITERAL:
		{
			{
			lit(_t);
			_t = _retTree;
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case SUMA:
			case GUION:
			case MULTIPLICACION:
			case DIVISION:
			case INCREMENTAR:
			case DECREMENTAR:
			{
				op_mat(_t);
				_t = _retTree;
				expr(_t);
				_t = _retTree;
				break;
			}
			case 3:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		_retTree = _t;
	}
	
	public final void op_mat(AST _t) throws RecognitionException {
		
		AST op_mat_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case SUMA:
		{
			AST tmp18_AST_in = (AST)_t;
			match(_t,SUMA);
			_t = _t.getNextSibling();
			break;
		}
		case GUION:
		{
			AST tmp19_AST_in = (AST)_t;
			match(_t,GUION);
			_t = _t.getNextSibling();
			break;
		}
		case MULTIPLICACION:
		{
			AST tmp20_AST_in = (AST)_t;
			match(_t,MULTIPLICACION);
			_t = _t.getNextSibling();
			break;
		}
		case DIVISION:
		{
			AST tmp21_AST_in = (AST)_t;
			match(_t,DIVISION);
			_t = _t.getNextSibling();
			break;
		}
		case INCREMENTAR:
		{
			AST tmp22_AST_in = (AST)_t;
			match(_t,INCREMENTAR);
			_t = _t.getNextSibling();
			break;
		}
		case DECREMENTAR:
		{
			AST tmp23_AST_in = (AST)_t;
			match(_t,DECREMENTAR);
			_t = _t.getNextSibling();
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
	}
	
	public final void op_rel(AST _t) throws RecognitionException {
		
		AST op_rel_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case MENOR_ESTRICTO:
		{
			AST tmp24_AST_in = (AST)_t;
			match(_t,MENOR_ESTRICTO);
			_t = _t.getNextSibling();
			break;
		}
		case MAYOR_ESTRICTO:
		{
			AST tmp25_AST_in = (AST)_t;
			match(_t,MAYOR_ESTRICTO);
			_t = _t.getNextSibling();
			break;
		}
		case MENOR_IGUAL:
		{
			AST tmp26_AST_in = (AST)_t;
			match(_t,MENOR_IGUAL);
			_t = _t.getNextSibling();
			break;
		}
		case MAYOR_IGUAL:
		{
			AST tmp27_AST_in = (AST)_t;
			match(_t,MAYOR_IGUAL);
			_t = _t.getNextSibling();
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
	}
	
	public final void op_log(AST _t) throws RecognitionException {
		
		AST op_log_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case IGUAL_QUE:
		{
			AST tmp28_AST_in = (AST)_t;
			match(_t,IGUAL_QUE);
			_t = _t.getNextSibling();
			break;
		}
		case DISTINTO_QUE:
		{
			AST tmp29_AST_in = (AST)_t;
			match(_t,DISTINTO_QUE);
			_t = _t.getNextSibling();
			break;
		}
		case AND:
		{
			AST tmp30_AST_in = (AST)_t;
			match(_t,AND);
			_t = _t.getNextSibling();
			break;
		}
		case OR:
		{
			AST tmp31_AST_in = (AST)_t;
			match(_t,OR);
			_t = _t.getNextSibling();
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
	}
	
	public final void repetir(AST _t) throws RecognitionException {
		
		AST repetir_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1953 = _t;
		AST tmp32_AST_in = (AST)_t;
		match(_t,REPETIR);
		_t = _t.getFirstChild();
		rpt_ini(_t);
		_t = _retTree;
		rpt_cond(_t);
		_t = _retTree;
		rpt_inc_dec(_t);
		_t = _retTree;
		cuerpo(_t);
		_t = _retTree;
		_t = __t1953;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void mientras(AST _t) throws RecognitionException {
		
		AST mientras_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1963 = _t;
		AST tmp33_AST_in = (AST)_t;
		match(_t,MIENTRAS);
		_t = _t.getFirstChild();
		cond_booleana(_t);
		_t = _retTree;
		cuerpo(_t);
		_t = _retTree;
		_t = __t1963;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void rpt_ini(AST _t) throws RecognitionException {
		
		AST rpt_ini_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id = null;
		
		AST __t1955 = _t;
		AST tmp34_AST_in = (AST)_t;
		match(_t,RPT_INI);
		_t = _t.getFirstChild();
		id = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		listVar.clear();sI.checkVar(id);listVar.add(id);
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case REAL_LITERAL:
		{
			AST tmp35_AST_in = (AST)_t;
			match(_t,REAL_LITERAL);
			_t = _t.getNextSibling();
			sI.checkLitReal(listVar);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp36_AST_in = (AST)_t;
			match(_t,ENTERO_LITERAL);
			_t = _t.getNextSibling();
			sI.checkLitEntero(listVar);
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_t = __t1955;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void rpt_cond(AST _t) throws RecognitionException {
		
		AST rpt_cond_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id1 = null;
		AST id2 = null;
		
		AST __t1958 = _t;
		AST tmp37_AST_in = (AST)_t;
		match(_t,RPT_COND);
		_t = _t.getFirstChild();
		id1 = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		listVar.clear();sI.checkVar(id1);listVar.add(id1);
		op_rel(_t);
		_t = _retTree;
		id2 = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		sI.checkVar(id2);listVar.add(id2); sI.checkTipos(listVar);
		_t = __t1958;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void rpt_inc_dec(AST _t) throws RecognitionException {
		
		AST rpt_inc_dec_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1960 = _t;
		AST tmp38_AST_in = (AST)_t;
		match(_t,RPT_INC_DEC);
		_t = _t.getFirstChild();
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case INCREMENTAR:
		{
			AST tmp39_AST_in = (AST)_t;
			match(_t,INCREMENTAR);
			_t = _t.getNextSibling();
			break;
		}
		case DECREMENTAR:
		{
			AST tmp40_AST_in = (AST)_t;
			match(_t,DECREMENTAR);
			_t = _t.getNextSibling();
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_t = __t1960;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void cond_booleana(AST _t) throws RecognitionException {
		
		AST cond_booleana_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1965 = _t;
		AST tmp41_AST_in = (AST)_t;
		match(_t,COND_BOOLEANA);
		_t = _t.getFirstChild();
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case COND_UN_OP:
		{
			cond_un_op(_t);
			_t = _retTree;
			break;
		}
		case COND_MAS_UN_OP:
		{
			cond_mas_un_op(_t);
			_t = _retTree;
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		{
		_loop1970:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==AND||_t.getType()==OR)) {
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case AND:
				{
					AST tmp42_AST_in = (AST)_t;
					match(_t,AND);
					_t = _t.getNextSibling();
					break;
				}
				case OR:
				{
					AST tmp43_AST_in = (AST)_t;
					match(_t,OR);
					_t = _t.getNextSibling();
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case COND_UN_OP:
				{
					cond_un_op(_t);
					_t = _retTree;
					break;
				}
				case COND_MAS_UN_OP:
				{
					cond_mas_un_op(_t);
					_t = _retTree;
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
			}
			else {
				break _loop1970;
			}
			
		} while (true);
		}
		_t = __t1965;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void cond_un_op(AST _t) throws RecognitionException {
		
		AST cond_un_op_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id = null;
		
		AST __t1972 = _t;
		AST tmp44_AST_in = (AST)_t;
		match(_t,COND_UN_OP);
		_t = _t.getFirstChild();
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case NEGACION:
		{
			AST tmp45_AST_in = (AST)_t;
			match(_t,NEGACION);
			_t = _t.getNextSibling();
			break;
		}
		case IDENTIFICADOR:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		id = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		sI.checkVar(id);
		_t = __t1972;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void cond_mas_un_op(AST _t) throws RecognitionException {
		
		AST cond_mas_un_op_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id1 = null;
		AST id2 = null;
		
		AST __t1975 = _t;
		AST tmp46_AST_in = (AST)_t;
		match(_t,COND_MAS_UN_OP);
		_t = _t.getFirstChild();
		id1 = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		listVar.clear();sI.checkVar(id1);listVar.add(id1);
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case IGUAL_QUE:
		case DISTINTO_QUE:
		case AND:
		case OR:
		{
			op_log(_t);
			_t = _retTree;
			break;
		}
		case MENOR_ESTRICTO:
		case MAYOR_ESTRICTO:
		case MENOR_IGUAL:
		case MAYOR_IGUAL:
		{
			op_rel(_t);
			_t = _retTree;
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		id2 = (AST)_t;
		match(_t,IDENTIFICADOR);
		_t = _t.getNextSibling();
		sI.checkVar(id2); listVar.add(id2);sI.checkTipos(listVar);
		_t = __t1975;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void sino(AST _t) throws RecognitionException {
		
		AST sino_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		AST __t1981 = _t;
		AST tmp47_AST_in = (AST)_t;
		match(_t,SINO);
		_t = _t.getFirstChild();
		cuerpo(_t);
		_t = _retTree;
		_t = __t1981;
		_t = _t.getNextSibling();
		_retTree = _t;
	}
	
	public final void lit(AST _t) throws RecognitionException {
		
		AST lit_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case REAL_LITERAL:
		{
			{
			AST tmp48_AST_in = (AST)_t;
			match(_t,REAL_LITERAL);
			_t = _t.getNextSibling();
			sI.checkLitReal(listVar);
			}
			break;
		}
		case ENTERO_LITERAL:
		{
			{
			AST tmp49_AST_in = (AST)_t;
			match(_t,ENTERO_LITERAL);
			_t = _t.getNextSibling();
			sI.checkLitEntero(listVar);
			}
			break;
		}
		case CADENA_LITERAL:
		{
			{
			AST tmp50_AST_in = (AST)_t;
			match(_t,CADENA_LITERAL);
			_t = _t.getNextSibling();
			sI.checkLitCaracter(listVar);
			}
			break;
		}
		case VERDADERO_PR:
		case FALSO_PR:
		{
			{
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case VERDADERO_PR:
			{
				AST tmp51_AST_in = (AST)_t;
				match(_t,VERDADERO_PR);
				_t = _t.getNextSibling();
				break;
			}
			case FALSO_PR:
			{
				AST tmp52_AST_in = (AST)_t;
				match(_t,FALSO_PR);
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			sI.checkLitBooleano(listVar);
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		_retTree = _t;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"cuerpo\"",
		"\"dec_var\"",
		"\"entero\"",
		"\"real\"",
		"\"carac\"",
		"\"const\"",
		"\"booleano\"",
		"\"repetir\"",
		"\"verdadero\"",
		"\"falso\"",
		"\"potencia\"",
		"\"factorial\"",
		"\"residuo\"",
		"\"radical\"",
		"\"imprimir\"",
		"\"leer\"",
		"\"mientras\"",
		"\"si\"",
		"\"sino\"",
		"REAL_LITERAL",
		"ENTERO_LITERAL",
		"CADENA_LITERAL",
		"PUNTOYCOMA",
		"COMA",
		"PUNTO",
		"DOSPUNTOS",
		"GUION_BAJO",
		"NEGACION",
		"LLAVE_IZQ",
		"LLAVE_DER",
		"CUADRADO_IZQ",
		"CUADRADO_DER",
		"REDONDO_IZQ",
		"REDONDO_DER",
		"SUMA",
		"GUION",
		"MULTIPLICACION",
		"DIVISION",
		"INCREMENTAR",
		"DECREMENTAR",
		"IGUAL_QUE",
		"DISTINTO_QUE",
		"AND",
		"OR",
		"ASIGN",
		"MENOR_ESTRICTO",
		"MAYOR_ESTRICTO",
		"MENOR_IGUAL",
		"MAYOR_IGUAL",
		"DIGITO",
		"NUM_LITERAL",
		"LETRA",
		"IDENTIFICADOR",
		"WS",
		"COMENTARIO_LINEA",
		"COMENTARIO_BLOQUE",
		"PROGRAMA",
		"DEC_VAR",
		"VAR",
		"CUERPO_PROG",
		"CONST",
		"ASIGNACION",
		"FUNC_MAT",
		"POTENCIA",
		"FACTORIAL",
		"RESIDUO",
		"RADICAL",
		"FUNC_IN_OUT",
		"IMPRIMIR",
		"LEER",
		"ESTRUC_REPET",
		"REPETIR",
		"RPT_INI",
		"RPT_COND",
		"RPT_INC_DEC",
		"MIENTRAS",
		"COND_BOOLEANA",
		"COND_UN_OP",
		"COND_MAS_UN_OP",
		"SI",
		"SINO",
		"CADENA_LIERAL"
	};
	
	}
	
