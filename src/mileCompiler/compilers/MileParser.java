// $ANTLR : "MileParser.g" -> "MileParser.java"$

/**
 * Parser for the Mile language.
 * 
 * @author Ricardo Soto
 * @since 1.5
 */

package mileCompiler.compilers;


import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class MileParser extends antlr.LLkParser       implements mileParserVocabTokenTypes
 {

protected MileParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public MileParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected MileParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public MileParser(TokenStream lexer) {
  this(lexer,2);
}

public MileParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void programa() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST programa_AST = null;
		
		match(DEC_VAR_PR);
		match(CUADRADO_IZQ);
		var();
		astFactory.addASTChild(currentAST, returnAST);
		match(CUADRADO_DER);
		match(CUERPO_PR);
		match(CUADRADO_IZQ);
		cuerpo();
		astFactory.addASTChild(currentAST, returnAST);
		match(CUADRADO_DER);
		programa_AST = (AST)currentAST.root;
		programa_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PROGRAMA,"PROGRAMA")).add(programa_AST));
		currentAST.root = programa_AST;
		currentAST.child = programa_AST!=null &&programa_AST.getFirstChild()!=null ?
			programa_AST.getFirstChild() : programa_AST;
		currentAST.advanceChildToEnd();
		programa_AST = (AST)currentAST.root;
		returnAST = programa_AST;
	}
	
	public final void var() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST var_AST = null;
		
		{
		{
		_loop1856:
		do {
			if ((LA(1)==CONST_PR)) {
				dec_constantes();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1856;
			}
			
		} while (true);
		}
		{
		_loop1858:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
				dec_variables();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1858;
			}
			
		} while (true);
		}
		}
		var_AST = (AST)currentAST.root;
		var_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DEC_VAR,"DEC_VAR")).add(var_AST));
		currentAST.root = var_AST;
		currentAST.child = var_AST!=null &&var_AST.getFirstChild()!=null ?
			var_AST.getFirstChild() : var_AST;
		currentAST.advanceChildToEnd();
		var_AST = (AST)currentAST.root;
		returnAST = var_AST;
	}
	
	public final void cuerpo() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cuerpo_AST = null;
		
		{
		_loop1866:
		do {
			switch ( LA(1)) {
			case IDENTIFICADOR:
			{
				assign();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case POTENCIA_PR:
			case FACTORIAL_PR:
			case RESIDUO_PR:
			case RADICAL_PR:
			{
				func_mat();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case IMPRIMIR_PR:
			case LEER_PR:
			{
				func_in_out();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case REPETIR_PR:
			case MIENTRAS_PR:
			{
				estruc_repeat();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SI_PR:
			{
				si();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				break _loop1866;
			}
			}
		} while (true);
		}
		cuerpo_AST = (AST)currentAST.root;
		cuerpo_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(CUERPO_PROG,"CUERPO_PROG")).add(cuerpo_AST));
		currentAST.root = cuerpo_AST;
		currentAST.child = cuerpo_AST!=null &&cuerpo_AST.getFirstChild()!=null ?
			cuerpo_AST.getFirstChild() : cuerpo_AST;
		currentAST.advanceChildToEnd();
		cuerpo_AST = (AST)currentAST.root;
		returnAST = cuerpo_AST;
	}
	
	public final void dec_constantes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dec_constantes_AST = null;
		
		match(CONST_PR);
		tipo_dato();
		astFactory.addASTChild(currentAST, returnAST);
		AST tmp8_AST = null;
		tmp8_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp8_AST);
		match(IDENTIFICADOR);
		match(ASIGN);
		{
		switch ( LA(1)) {
		case REAL_LITERAL:
		{
			AST tmp10_AST = null;
			tmp10_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp10_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp11_AST = null;
			tmp11_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp11_AST);
			match(ENTERO_LITERAL);
			break;
		}
		case CADENA_LIERAL:
		{
			AST tmp12_AST = null;
			tmp12_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp12_AST);
			match(CADENA_LIERAL);
			break;
		}
		case VERDADERO_PR:
		{
			AST tmp13_AST = null;
			tmp13_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp13_AST);
			match(VERDADERO_PR);
			break;
		}
		case FALSO_PR:
		{
			AST tmp14_AST = null;
			tmp14_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp14_AST);
			match(FALSO_PR);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(PUNTOYCOMA);
		dec_constantes_AST = (AST)currentAST.root;
		dec_constantes_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(CONST,"CONSTANTE")).add(dec_constantes_AST));
		currentAST.root = dec_constantes_AST;
		currentAST.child = dec_constantes_AST!=null &&dec_constantes_AST.getFirstChild()!=null ?
			dec_constantes_AST.getFirstChild() : dec_constantes_AST;
		currentAST.advanceChildToEnd();
		dec_constantes_AST = (AST)currentAST.root;
		returnAST = dec_constantes_AST;
	}
	
	public final void dec_variables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dec_variables_AST = null;
		
		{
		tipo_dato();
		astFactory.addASTChild(currentAST, returnAST);
		AST tmp16_AST = null;
		tmp16_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp16_AST);
		match(IDENTIFICADOR);
		match(PUNTOYCOMA);
		}
		dec_variables_AST = (AST)currentAST.root;
		dec_variables_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(VAR,"VARIABLE")).add(dec_variables_AST));
		currentAST.root = dec_variables_AST;
		currentAST.child = dec_variables_AST!=null &&dec_variables_AST.getFirstChild()!=null ?
			dec_variables_AST.getFirstChild() : dec_variables_AST;
		currentAST.advanceChildToEnd();
		dec_variables_AST = (AST)currentAST.root;
		returnAST = dec_variables_AST;
	}
	
	public final void tipo_dato() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tipo_dato_AST = null;
		
		switch ( LA(1)) {
		case TIPO_ENTERO_PR:
		{
			AST tmp18_AST = null;
			tmp18_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp18_AST);
			match(TIPO_ENTERO_PR);
			tipo_dato_AST = (AST)currentAST.root;
			break;
		}
		case TIPO_REAL_PR:
		{
			AST tmp19_AST = null;
			tmp19_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp19_AST);
			match(TIPO_REAL_PR);
			tipo_dato_AST = (AST)currentAST.root;
			break;
		}
		case TIPO_CARAC_PR:
		{
			AST tmp20_AST = null;
			tmp20_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp20_AST);
			match(TIPO_CARAC_PR);
			tipo_dato_AST = (AST)currentAST.root;
			break;
		}
		case TIPO_BOOLEANO:
		{
			AST tmp21_AST = null;
			tmp21_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp21_AST);
			match(TIPO_BOOLEANO);
			tipo_dato_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = tipo_dato_AST;
	}
	
	public final void assign() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST assign_AST = null;
		
		AST tmp22_AST = null;
		tmp22_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp22_AST);
		match(IDENTIFICADOR);
		match(ASIGN);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp24_AST = null;
			tmp24_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp24_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp25_AST = null;
			tmp25_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp25_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp26_AST = null;
			tmp26_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp26_AST);
			match(ENTERO_LITERAL);
			break;
		}
		case CADENA_LIERAL:
		{
			AST tmp27_AST = null;
			tmp27_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp27_AST);
			match(CADENA_LIERAL);
			break;
		}
		case VERDADERO_PR:
		{
			AST tmp28_AST = null;
			tmp28_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp28_AST);
			match(VERDADERO_PR);
			break;
		}
		case FALSO_PR:
		{
			AST tmp29_AST = null;
			tmp29_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp29_AST);
			match(FALSO_PR);
			break;
		}
		case POTENCIA_PR:
		case FACTORIAL_PR:
		case RESIDUO_PR:
		case RADICAL_PR:
		{
			func_mat();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		_loop1872:
		do {
			if (((LA(1) >= SUMA && LA(1) <= DECREMENTAR))) {
				{
				op_mat();
				astFactory.addASTChild(currentAST, returnAST);
				}
				{
				switch ( LA(1)) {
				case IDENTIFICADOR:
				{
					AST tmp30_AST = null;
					tmp30_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp30_AST);
					match(IDENTIFICADOR);
					break;
				}
				case REAL_LITERAL:
				{
					AST tmp31_AST = null;
					tmp31_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp31_AST);
					match(REAL_LITERAL);
					break;
				}
				case ENTERO_LITERAL:
				{
					AST tmp32_AST = null;
					tmp32_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp32_AST);
					match(ENTERO_LITERAL);
					break;
				}
				case CADENA_LIERAL:
				{
					AST tmp33_AST = null;
					tmp33_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp33_AST);
					match(CADENA_LIERAL);
					break;
				}
				case VERDADERO_PR:
				{
					AST tmp34_AST = null;
					tmp34_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp34_AST);
					match(VERDADERO_PR);
					break;
				}
				case FALSO_PR:
				{
					AST tmp35_AST = null;
					tmp35_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp35_AST);
					match(FALSO_PR);
					break;
				}
				case POTENCIA_PR:
				case FACTORIAL_PR:
				case RESIDUO_PR:
				case RADICAL_PR:
				{
					func_mat();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
			}
			else {
				break _loop1872;
			}
			
		} while (true);
		}
		match(PUNTOYCOMA);
		assign_AST = (AST)currentAST.root;
		assign_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ASIGNACION,"ASIGNACION")).add(assign_AST));
		currentAST.root = assign_AST;
		currentAST.child = assign_AST!=null &&assign_AST.getFirstChild()!=null ?
			assign_AST.getFirstChild() : assign_AST;
		currentAST.advanceChildToEnd();
		assign_AST = (AST)currentAST.root;
		returnAST = assign_AST;
	}
	
	public final void func_mat() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST func_mat_AST = null;
		
		{
		switch ( LA(1)) {
		case POTENCIA_PR:
		{
			potencia();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case FACTORIAL_PR:
		{
			factorial();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case RESIDUO_PR:
		{
			residuo();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case RADICAL_PR:
		{
			radical();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		func_mat_AST = (AST)currentAST.root;
		func_mat_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNC_MAT,"FUNCION_MATEMATICA")).add(func_mat_AST));
		currentAST.root = func_mat_AST;
		currentAST.child = func_mat_AST!=null &&func_mat_AST.getFirstChild()!=null ?
			func_mat_AST.getFirstChild() : func_mat_AST;
		currentAST.advanceChildToEnd();
		func_mat_AST = (AST)currentAST.root;
		returnAST = func_mat_AST;
	}
	
	public final void func_in_out() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST func_in_out_AST = null;
		
		{
		switch ( LA(1)) {
		case IMPRIMIR_PR:
		{
			imprimir();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LEER_PR:
		{
			leer();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		func_in_out_AST = (AST)currentAST.root;
		func_in_out_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNC_IN_OUT,"FUNCION_ENTRADA_SALIDA")).add(func_in_out_AST));
		currentAST.root = func_in_out_AST;
		currentAST.child = func_in_out_AST!=null &&func_in_out_AST.getFirstChild()!=null ?
			func_in_out_AST.getFirstChild() : func_in_out_AST;
		currentAST.advanceChildToEnd();
		func_in_out_AST = (AST)currentAST.root;
		returnAST = func_in_out_AST;
	}
	
	public final void estruc_repeat() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST estruc_repeat_AST = null;
		
		{
		switch ( LA(1)) {
		case REPETIR_PR:
		{
			repetir();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case MIENTRAS_PR:
		{
			mientras();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		estruc_repeat_AST = (AST)currentAST.root;
		estruc_repeat_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ESTRUC_REPET,"ESTRUCTURA_REPETITIVA")).add(estruc_repeat_AST));
		currentAST.root = estruc_repeat_AST;
		currentAST.child = estruc_repeat_AST!=null &&estruc_repeat_AST.getFirstChild()!=null ?
			estruc_repeat_AST.getFirstChild() : estruc_repeat_AST;
		currentAST.advanceChildToEnd();
		estruc_repeat_AST = (AST)currentAST.root;
		returnAST = estruc_repeat_AST;
	}
	
	public final void si() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST si_AST = null;
		
		match(SI_PR);
		match(REDONDO_IZQ);
		cond_booleana();
		astFactory.addASTChild(currentAST, returnAST);
		match(REDONDO_DER);
		match(LLAVE_IZQ);
		cuerpo();
		astFactory.addASTChild(currentAST, returnAST);
		match(LLAVE_DER);
		{
		switch ( LA(1)) {
		case SINO_PR:
		{
			sino();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case REPETIR_PR:
		case POTENCIA_PR:
		case FACTORIAL_PR:
		case RESIDUO_PR:
		case RADICAL_PR:
		case IMPRIMIR_PR:
		case LEER_PR:
		case MIENTRAS_PR:
		case SI_PR:
		case LLAVE_DER:
		case CUADRADO_DER:
		case IDENTIFICADOR:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		si_AST = (AST)currentAST.root;
		si_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SI,"SI")).add(si_AST));
		currentAST.root = si_AST;
		currentAST.child = si_AST!=null &&si_AST.getFirstChild()!=null ?
			si_AST.getFirstChild() : si_AST;
		currentAST.advanceChildToEnd();
		si_AST = (AST)currentAST.root;
		returnAST = si_AST;
	}
	
	public final void op_mat() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST op_mat_AST = null;
		
		{
		switch ( LA(1)) {
		case SUMA:
		{
			AST tmp42_AST = null;
			tmp42_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp42_AST);
			match(SUMA);
			break;
		}
		case GUION:
		{
			AST tmp43_AST = null;
			tmp43_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp43_AST);
			match(GUION);
			break;
		}
		case MULTIPLICACION:
		{
			AST tmp44_AST = null;
			tmp44_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp44_AST);
			match(MULTIPLICACION);
			break;
		}
		case DIVISION:
		{
			AST tmp45_AST = null;
			tmp45_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp45_AST);
			match(DIVISION);
			break;
		}
		case INCREMENTAR:
		{
			AST tmp46_AST = null;
			tmp46_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp46_AST);
			match(INCREMENTAR);
			break;
		}
		case DECREMENTAR:
		{
			AST tmp47_AST = null;
			tmp47_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp47_AST);
			match(DECREMENTAR);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		op_mat_AST = (AST)currentAST.root;
		returnAST = op_mat_AST;
	}
	
	public final void potencia() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST potencia_AST = null;
		
		match(POTENCIA_PR);
		match(REDONDO_IZQ);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp50_AST = null;
			tmp50_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp50_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp51_AST = null;
			tmp51_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp51_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp52_AST = null;
			tmp52_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp52_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(COMA);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp54_AST = null;
			tmp54_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp54_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp55_AST = null;
			tmp55_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp55_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp56_AST = null;
			tmp56_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp56_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(REDONDO_DER);
		potencia_AST = (AST)currentAST.root;
		potencia_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(POTENCIA,"POTENCIA")).add(potencia_AST));
		currentAST.root = potencia_AST;
		currentAST.child = potencia_AST!=null &&potencia_AST.getFirstChild()!=null ?
			potencia_AST.getFirstChild() : potencia_AST;
		currentAST.advanceChildToEnd();
		potencia_AST = (AST)currentAST.root;
		returnAST = potencia_AST;
	}
	
	public final void factorial() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST factorial_AST = null;
		
		match(FACTORIAL_PR);
		match(REDONDO_IZQ);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp60_AST = null;
			tmp60_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp60_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp61_AST = null;
			tmp61_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp61_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp62_AST = null;
			tmp62_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp62_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(REDONDO_DER);
		factorial_AST = (AST)currentAST.root;
		factorial_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FACTORIAL,"FACTORIAL")).add(factorial_AST));
		currentAST.root = factorial_AST;
		currentAST.child = factorial_AST!=null &&factorial_AST.getFirstChild()!=null ?
			factorial_AST.getFirstChild() : factorial_AST;
		currentAST.advanceChildToEnd();
		factorial_AST = (AST)currentAST.root;
		returnAST = factorial_AST;
	}
	
	public final void residuo() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST residuo_AST = null;
		
		match(RESIDUO_PR);
		match(REDONDO_IZQ);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp66_AST = null;
			tmp66_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp66_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp67_AST = null;
			tmp67_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp67_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp68_AST = null;
			tmp68_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp68_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(COMA);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp70_AST = null;
			tmp70_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp70_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp71_AST = null;
			tmp71_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp71_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp72_AST = null;
			tmp72_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp72_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(REDONDO_DER);
		residuo_AST = (AST)currentAST.root;
		residuo_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RESIDUO,"RESIDUO")).add(residuo_AST));
		currentAST.root = residuo_AST;
		currentAST.child = residuo_AST!=null &&residuo_AST.getFirstChild()!=null ?
			residuo_AST.getFirstChild() : residuo_AST;
		currentAST.advanceChildToEnd();
		residuo_AST = (AST)currentAST.root;
		returnAST = residuo_AST;
	}
	
	public final void radical() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST radical_AST = null;
		
		match(RADICAL_PR);
		match(REDONDO_IZQ);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp76_AST = null;
			tmp76_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp76_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp77_AST = null;
			tmp77_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp77_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp78_AST = null;
			tmp78_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp78_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(COMA);
		{
		switch ( LA(1)) {
		case IDENTIFICADOR:
		{
			AST tmp80_AST = null;
			tmp80_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp80_AST);
			match(IDENTIFICADOR);
			break;
		}
		case REAL_LITERAL:
		{
			AST tmp81_AST = null;
			tmp81_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp81_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp82_AST = null;
			tmp82_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp82_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(REDONDO_DER);
		radical_AST = (AST)currentAST.root;
		radical_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RADICAL,"RADICAL")).add(radical_AST));
		currentAST.root = radical_AST;
		currentAST.child = radical_AST!=null &&radical_AST.getFirstChild()!=null ?
			radical_AST.getFirstChild() : radical_AST;
		currentAST.advanceChildToEnd();
		radical_AST = (AST)currentAST.root;
		returnAST = radical_AST;
	}
	
	public final void imprimir() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST imprimir_AST = null;
		
		match(IMPRIMIR_PR);
		match(REDONDO_IZQ);
		AST tmp86_AST = null;
		tmp86_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp86_AST);
		match(CADENA_LITERAL);
		match(REDONDO_DER);
		match(PUNTOYCOMA);
		imprimir_AST = (AST)currentAST.root;
		imprimir_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(IMPRIMIR,"IMPRIMIR")).add(imprimir_AST));
		currentAST.root = imprimir_AST;
		currentAST.child = imprimir_AST!=null &&imprimir_AST.getFirstChild()!=null ?
			imprimir_AST.getFirstChild() : imprimir_AST;
		currentAST.advanceChildToEnd();
		imprimir_AST = (AST)currentAST.root;
		returnAST = imprimir_AST;
	}
	
	public final void leer() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST leer_AST = null;
		
		match(LEER_PR);
		match(REDONDO_IZQ);
		AST tmp91_AST = null;
		tmp91_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp91_AST);
		match(IDENTIFICADOR);
		match(REDONDO_DER);
		match(PUNTOYCOMA);
		leer_AST = (AST)currentAST.root;
		leer_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LEER,"LEER")).add(leer_AST));
		currentAST.root = leer_AST;
		currentAST.child = leer_AST!=null &&leer_AST.getFirstChild()!=null ?
			leer_AST.getFirstChild() : leer_AST;
		currentAST.advanceChildToEnd();
		leer_AST = (AST)currentAST.root;
		returnAST = leer_AST;
	}
	
	public final void repetir() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST repetir_AST = null;
		
		match(REPETIR_PR);
		match(REDONDO_IZQ);
		rpt_ini();
		astFactory.addASTChild(currentAST, returnAST);
		match(PUNTOYCOMA);
		rpt_cond();
		astFactory.addASTChild(currentAST, returnAST);
		match(PUNTOYCOMA);
		rpt_inc_dec();
		astFactory.addASTChild(currentAST, returnAST);
		match(REDONDO_DER);
		match(LLAVE_IZQ);
		cuerpo();
		astFactory.addASTChild(currentAST, returnAST);
		match(LLAVE_DER);
		repetir_AST = (AST)currentAST.root;
		repetir_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(REPETIR,"REPETIR")).add(repetir_AST));
		currentAST.root = repetir_AST;
		currentAST.child = repetir_AST!=null &&repetir_AST.getFirstChild()!=null ?
			repetir_AST.getFirstChild() : repetir_AST;
		currentAST.advanceChildToEnd();
		repetir_AST = (AST)currentAST.root;
		returnAST = repetir_AST;
	}
	
	public final void mientras() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST mientras_AST = null;
		
		match(MIENTRAS_PR);
		match(REDONDO_IZQ);
		cond_booleana();
		astFactory.addASTChild(currentAST, returnAST);
		match(REDONDO_DER);
		match(LLAVE_IZQ);
		cuerpo();
		astFactory.addASTChild(currentAST, returnAST);
		match(LLAVE_DER);
		mientras_AST = (AST)currentAST.root;
		mientras_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(MIENTRAS,"MIENTRAS")).add(mientras_AST));
		currentAST.root = mientras_AST;
		currentAST.child = mientras_AST!=null &&mientras_AST.getFirstChild()!=null ?
			mientras_AST.getFirstChild() : mientras_AST;
		currentAST.advanceChildToEnd();
		mientras_AST = (AST)currentAST.root;
		returnAST = mientras_AST;
	}
	
	public final void rpt_ini() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rpt_ini_AST = null;
		
		AST tmp106_AST = null;
		tmp106_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp106_AST);
		match(IDENTIFICADOR);
		match(ASIGN);
		{
		switch ( LA(1)) {
		case REAL_LITERAL:
		{
			AST tmp108_AST = null;
			tmp108_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp108_AST);
			match(REAL_LITERAL);
			break;
		}
		case ENTERO_LITERAL:
		{
			AST tmp109_AST = null;
			tmp109_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp109_AST);
			match(ENTERO_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		rpt_ini_AST = (AST)currentAST.root;
		rpt_ini_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RPT_INI,"INI_FOR")).add(rpt_ini_AST));
		currentAST.root = rpt_ini_AST;
		currentAST.child = rpt_ini_AST!=null &&rpt_ini_AST.getFirstChild()!=null ?
			rpt_ini_AST.getFirstChild() : rpt_ini_AST;
		currentAST.advanceChildToEnd();
		rpt_ini_AST = (AST)currentAST.root;
		returnAST = rpt_ini_AST;
	}
	
	public final void rpt_cond() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rpt_cond_AST = null;
		
		AST tmp110_AST = null;
		tmp110_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp110_AST);
		match(IDENTIFICADOR);
		op_rel();
		astFactory.addASTChild(currentAST, returnAST);
		AST tmp111_AST = null;
		tmp111_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp111_AST);
		match(IDENTIFICADOR);
		rpt_cond_AST = (AST)currentAST.root;
		rpt_cond_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RPT_COND,"COND_FOR")).add(rpt_cond_AST));
		currentAST.root = rpt_cond_AST;
		currentAST.child = rpt_cond_AST!=null &&rpt_cond_AST.getFirstChild()!=null ?
			rpt_cond_AST.getFirstChild() : rpt_cond_AST;
		currentAST.advanceChildToEnd();
		rpt_cond_AST = (AST)currentAST.root;
		returnAST = rpt_cond_AST;
	}
	
	public final void rpt_inc_dec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rpt_inc_dec_AST = null;
		
		match(IDENTIFICADOR);
		{
		switch ( LA(1)) {
		case INCREMENTAR:
		{
			AST tmp113_AST = null;
			tmp113_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp113_AST);
			match(INCREMENTAR);
			break;
		}
		case DECREMENTAR:
		{
			AST tmp114_AST = null;
			tmp114_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp114_AST);
			match(DECREMENTAR);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		rpt_inc_dec_AST = (AST)currentAST.root;
		rpt_inc_dec_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RPT_INC_DEC,"INC_DEC")).add(rpt_inc_dec_AST));
		currentAST.root = rpt_inc_dec_AST;
		currentAST.child = rpt_inc_dec_AST!=null &&rpt_inc_dec_AST.getFirstChild()!=null ?
			rpt_inc_dec_AST.getFirstChild() : rpt_inc_dec_AST;
		currentAST.advanceChildToEnd();
		rpt_inc_dec_AST = (AST)currentAST.root;
		returnAST = rpt_inc_dec_AST;
	}
	
	public final void cond_booleana() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cond_booleana_AST = null;
		
		{
		if ((LA(1)==NEGACION||LA(1)==IDENTIFICADOR) && (_tokenSet_1.member(LA(2)))) {
			cond_un_op();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else if ((LA(1)==IDENTIFICADOR) && (_tokenSet_2.member(LA(2)))) {
			cond_mas_un_op();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		_loop1907:
		do {
			if ((LA(1)==AND||LA(1)==OR)) {
				{
				switch ( LA(1)) {
				case AND:
				{
					AST tmp115_AST = null;
					tmp115_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp115_AST);
					match(AND);
					break;
				}
				case OR:
				{
					AST tmp116_AST = null;
					tmp116_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp116_AST);
					match(OR);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				if ((LA(1)==NEGACION||LA(1)==IDENTIFICADOR) && (_tokenSet_1.member(LA(2)))) {
					cond_un_op();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==IDENTIFICADOR) && (_tokenSet_2.member(LA(2)))) {
					cond_mas_un_op();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else {
				break _loop1907;
			}
			
		} while (true);
		}
		cond_booleana_AST = (AST)currentAST.root;
		cond_booleana_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(COND_BOOLEANA,"COND_BOOLEANA")).add(cond_booleana_AST));
		currentAST.root = cond_booleana_AST;
		currentAST.child = cond_booleana_AST!=null &&cond_booleana_AST.getFirstChild()!=null ?
			cond_booleana_AST.getFirstChild() : cond_booleana_AST;
		currentAST.advanceChildToEnd();
		cond_booleana_AST = (AST)currentAST.root;
		returnAST = cond_booleana_AST;
	}
	
	public final void sino() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST sino_AST = null;
		
		match(SINO_PR);
		match(LLAVE_IZQ);
		cuerpo();
		astFactory.addASTChild(currentAST, returnAST);
		match(LLAVE_DER);
		sino_AST = (AST)currentAST.root;
		sino_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SINO,"SINO")).add(sino_AST));
		currentAST.root = sino_AST;
		currentAST.child = sino_AST!=null &&sino_AST.getFirstChild()!=null ?
			sino_AST.getFirstChild() : sino_AST;
		currentAST.advanceChildToEnd();
		sino_AST = (AST)currentAST.root;
		returnAST = sino_AST;
	}
	
	public final void op_rel() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST op_rel_AST = null;
		
		{
		switch ( LA(1)) {
		case MENOR_ESTRICTO:
		{
			AST tmp120_AST = null;
			tmp120_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp120_AST);
			match(MENOR_ESTRICTO);
			break;
		}
		case MAYOR_ESTRICTO:
		{
			AST tmp121_AST = null;
			tmp121_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp121_AST);
			match(MAYOR_ESTRICTO);
			break;
		}
		case MENOR_IGUAL:
		{
			AST tmp122_AST = null;
			tmp122_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp122_AST);
			match(MENOR_IGUAL);
			break;
		}
		case MAYOR_IGUAL:
		{
			AST tmp123_AST = null;
			tmp123_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp123_AST);
			match(MAYOR_IGUAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		op_rel_AST = (AST)currentAST.root;
		returnAST = op_rel_AST;
	}
	
	public final void cond_un_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cond_un_op_AST = null;
		
		{
		switch ( LA(1)) {
		case NEGACION:
		{
			AST tmp124_AST = null;
			tmp124_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp124_AST);
			match(NEGACION);
			break;
		}
		case IDENTIFICADOR:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		AST tmp125_AST = null;
		tmp125_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp125_AST);
		match(IDENTIFICADOR);
		cond_un_op_AST = (AST)currentAST.root;
		cond_un_op_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(COND_UN_OP,"COND_UN_OP")).add(cond_un_op_AST));
		currentAST.root = cond_un_op_AST;
		currentAST.child = cond_un_op_AST!=null &&cond_un_op_AST.getFirstChild()!=null ?
			cond_un_op_AST.getFirstChild() : cond_un_op_AST;
		currentAST.advanceChildToEnd();
		cond_un_op_AST = (AST)currentAST.root;
		returnAST = cond_un_op_AST;
	}
	
	public final void cond_mas_un_op() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cond_mas_un_op_AST = null;
		
		AST tmp126_AST = null;
		tmp126_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp126_AST);
		match(IDENTIFICADOR);
		{
		switch ( LA(1)) {
		case IGUAL_QUE:
		case DISTINTO_QUE:
		case AND:
		case OR:
		{
			op_log();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case MENOR_ESTRICTO:
		case MAYOR_ESTRICTO:
		case MENOR_IGUAL:
		case MAYOR_IGUAL:
		{
			op_rel();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		AST tmp127_AST = null;
		tmp127_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp127_AST);
		match(IDENTIFICADOR);
		cond_mas_un_op_AST = (AST)currentAST.root;
		cond_mas_un_op_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(COND_MAS_UN_OP,"COND_MAS_UN_OP")).add(cond_mas_un_op_AST));
		currentAST.root = cond_mas_un_op_AST;
		currentAST.child = cond_mas_un_op_AST!=null &&cond_mas_un_op_AST.getFirstChild()!=null ?
			cond_mas_un_op_AST.getFirstChild() : cond_mas_un_op_AST;
		currentAST.advanceChildToEnd();
		cond_mas_un_op_AST = (AST)currentAST.root;
		returnAST = cond_mas_un_op_AST;
	}
	
	public final void op_log() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST op_log_AST = null;
		
		{
		switch ( LA(1)) {
		case IGUAL_QUE:
		{
			AST tmp128_AST = null;
			tmp128_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp128_AST);
			match(IGUAL_QUE);
			break;
		}
		case DISTINTO_QUE:
		{
			AST tmp129_AST = null;
			tmp129_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp129_AST);
			match(DISTINTO_QUE);
			break;
		}
		case AND:
		{
			AST tmp130_AST = null;
			tmp130_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp130_AST);
			match(AND);
			break;
		}
		case OR:
		{
			AST tmp131_AST = null;
			tmp131_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp131_AST);
			match(OR);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		op_log_AST = (AST)currentAST.root;
		returnAST = op_log_AST;
	}
	
	public final void operadores() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST operadores_AST = null;
		
		{
		switch ( LA(1)) {
		case SUMA:
		case GUION:
		case MULTIPLICACION:
		case DIVISION:
		case INCREMENTAR:
		case DECREMENTAR:
		{
			op_mat();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case IGUAL_QUE:
		case DISTINTO_QUE:
		case AND:
		case OR:
		{
			op_log();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case MENOR_ESTRICTO:
		case MAYOR_ESTRICTO:
		case MENOR_IGUAL:
		case MAYOR_IGUAL:
		{
			op_rel();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		operadores_AST = (AST)currentAST.root;
		returnAST = operadores_AST;
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
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 1472L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 72268837709414400L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 8708132091985920L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	
	}
