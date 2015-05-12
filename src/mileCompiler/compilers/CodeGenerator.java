package mileCompiler.compilers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import antlr.collections.AST;

/**
 * Performs the code generation for the Mile language.
 * 
 * 
 * @author Ricardo Soto<br>
 * @since 1.5<br>
 */

public class CodeGenerator {
    
	
	private PrintWriter pW = null;
    protected String code = "";
    static private String PATH = "output/";
    private String fileName = "test";
    
    public CodeGenerator() {
        this.buildFile();
    }
    
    public void buildFile() {
        this.createFile(PATH + fileName + ".c");
    }
 
    /**
     * Associates the print writer to an output file, the file name is given.
     *
     * @param st
     *            the name of the file
     */
    public void createFile(String st) {
        try {  
            FileWriter fW = new FileWriter(st);
            BufferedWriter bW = new BufferedWriter(fW);
            this.pW = new PrintWriter(bW);
        } catch (IOException ioe) {
            System.err.println("Path does not exist: '" + st + "'");
        }
    }

    public void addHeader() {
    	pW.println("#include <stdio.h>");
    	pW.println("");
        	
    }
    
    public void addMain() {
    	pW.println("");
    	pW.println("void main()");
    	pW.println("{");
    	
    	
    }
    
    public void inicioCuerpo(){
    	pW.println("");
    	pW.println("");
    } 
    
    public void addVar(AST type, AST id) {
    	String st = "";
    	if(type.toString().equals("entero")) {
    		st = "  int " + id.toString() + ";";
    	} else {
    		
    		if(type.toString().equals("real")){
    			st = "  float " + id.toString() + ";";
        	}else{
        		if(type.toString().equals("carac")){
        			st = "  char " + id.toString() + ";";
            	}else{
            		st = "  int " + id.toString() + ";";            		
            	}
        		
        	}
    		
    	}
    	pW.println(st);
    }	
      
    public void addConst(AST id) {
    	String st = "#define " + id.toString() + " ";
       	pW.print(st);
    }
    
    public void printId(AST id){
    	pW.print(id.toString());
    }
    
    public void printAsign(){
    	pW.print(" =");
    }
    
    public void printPtoYComa(){
    	pW.print(";");
    }
            
    public void println(){
    	pW.println();
    }
    
    public void printTab(){
    	pW.print("	");	
    }

    public void printEspacio(){
    	pW.print(" ");
    }
    
    public void printSuma(){
    	pW.print("+");
    }
    
    public void printGuion(){
    	pW.print("-");
    }
    
    public void printMulti(){
    	pW.print("*");
    }
    
    public void printDivision(){
    	pW.print("/");
    }
    
    public void printIncrementar(){
    	pW.print("++ ");
    }
    
    public void printDecrementar(){
    	pW.print("-- ");
    }
        
    public void printLit(Object num){
    	pW.print(num);
    }
   
    public void printIniFor(){
    	pW.println();
    	pW.print("	for(");
    }
    
    public void printEndRepet(){
    	pW.println("){");
    }
    
    public void printIniMientras(){
    	pW.print("while(");
    }
   
    public void printIniSi(){
    	pW.print("if(");
    }
    
    public void printIniSino(){
    	pW.print("else{");
    	
    }
        
    public void printLlaveIzq(){
    	pW.println("}");
    }
    
    public void printOr(){
    	pW.print("||");
    }
    
    public void printAnd(){
    	pW.print("&&");
    }
  
    
    public void printMenorEst(){
    	pW.print("<");
    }
    
    public void printMayorEst(){
    	pW.print(">");
    }
    
    public void printMenorIgual(){
    	pW.print("<=");
    }
    
    public void printMayorIgual(){
    	pW.print(">=");
    }
    
    public void printNegacion(){
    	pW.print("!");
    }
    
    
    
    
    public void end() {
    	pW.print("}");
       	this.closeFile();
    	
    }

    /**
     * Closes the output file.
     */
    public void closeFile() {
    	pW.close();
    }

    
}


 