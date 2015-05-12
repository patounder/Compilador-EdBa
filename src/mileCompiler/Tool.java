package mileCompiler;

import java.io.FileInputStream;

import mileCompiler.compilers.MileCodeGen;


import mileCompiler.compilers.MileTreeParser;
import mileCompiler.compilers.MileParser;
import antlr.Token;
import antlr.TokenStreamException;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;
import mileCompiler.compilers.mileLexer;

/**
 * The main class of the Mile Language
 * 
 * @author Ricardo Soto
 * @since 1.5
 */

public class Tool {

	private static String fileName = "";
	private static FileInputStream fis = null;

	public static void main(String args[]) {
		try {
			System.out.println("Scanning file...");
			setSourceFile(args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * Set source file and arguments.
	 * 
	 * @param args
	 */
	public static void setSourceFile(String args[]) {
		int i = args.length - 1;
		try {
			setFileName(args[i]);
			setFis(new FileInputStream(args[i]));
CodeGenerationTest();
} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	   /**
     * Tests the code generation.
     * 
     * @param args
     */
    public static void CodeGenerationTest() {
    	try
    	{
    		System.out.println("Parsing file...");
    		mileLexer lexer = new mileLexer(fis);
    		lexer.setFilename(fileName);
    		lexer.setTokenObjectClass("antlraux.util.LexInfoToken");
    		MileParser parser = new MileParser(lexer);
            parser.setFilename(fileName);
            parser.programa();
            AST ast = parser.getAST();
            //final ASTFrame frame1 = new ASTFrame("", ast);
            //frame1.setVisible(true);
            
            System.out.println("Semantic Checking...");
            MileTreeParser treeParser = new MileTreeParser();
            treeParser.programa(ast);
            
            System.out.println("Code Generation...");
            MileCodeGen codeGen = new MileCodeGen();
            codeGen.programa(ast);
            System.out.println("Ok...");
            
    	}
    	catch (Exception ex)
    	{
    		  ex.printStackTrace();
    	}
    }

    /**
     * Tests the semantic checking.
     * 
     * @param args
     */
    public static void SemanticTest() {
    	try
    	{
    		mileLexer lexer = new mileLexer(fis);
    		lexer.setFilename(fileName);
    		lexer.setTokenObjectClass("antlraux.util.LexInfoToken");
    		MileParser parser = new MileParser(lexer);
            parser.setFilename(fileName);
            parser.programa();
            AST ast = parser.getAST();
            final ASTFrame frame1 = new ASTFrame("", ast);
            frame1.setVisible(false);
            MileTreeParser treeParser = new MileTreeParser();
            treeParser.programa(ast);
       	}
    	catch (Exception ex)
    	{
    		System.err.println("Error leyendo tokens: " + ex.getMessage());
    	}
    }


	
	/**
	 * Tests the parser.
	 * 
	 * @param args
	 */
	public static void SyntacticTest() {
		try {
			mileLexer lexer = new mileLexer(fis);
			lexer.setFilename(fileName);
			lexer.setTokenObjectClass("antlraux.util.LexInfoToken");
			MileParser parser = new MileParser(lexer);
			parser.setFilename(fileName);
			parser.programa();
			AST ast = parser.getAST();
			final ASTFrame frame1 = new ASTFrame("", ast);
			frame1.setVisible(true);
		} catch (Exception ex) {
			System.err.println("Error: " + ex.toString());
		}
	}

	/**
	 * Test our lexer.
	 * 
	 * @param args
	 */
	public static void lexicTest() {
		try {

			mileLexer lexer = new mileLexer(fis);
			lexer.setFilename(fileName);
			lexer.setTokenObjectClass("antlraux.util.LexInfoToken");
			Token tok = lexer.nextToken();

			while (tok.getType() != Token.EOF_TYPE) {
				System.out.println(tok.getFilename() + ":" + tok.getLine()
						+ ":" + tok.getColumn() + ": " + tok.getText());
				tok = lexer.nextToken();
			}

		} catch (TokenStreamException tse) {
			System.err.println("Error leyendo tokens: " + tse.toString());
		}
	}

	/**
	 * @param fis
	 *            The fis to set.
	 */
	public static void setFis(FileInputStream fisIn) {
		fis = fisIn;
	}

	/**
	 * @param fileName
	 *            The fileName to set.
	 */
	public static void setFileName(String fileNameIn) {
		fileName = fileNameIn;
	}

}
