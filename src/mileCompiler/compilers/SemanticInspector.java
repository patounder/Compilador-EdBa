package mileCompiler.compilers;

import java.util.ArrayList;
import antlr.collections.AST;



/**
 * Performs the semantic checking for the Mile language.
 * 
 * 
 * @author Ricardo Soto<br>
 * @since 1.5<br>
 */

public class SemanticInspector {
    
	private ArrayList<String> listaNombreId = new ArrayList<String>(); // Lista de todos los identificadores que hay actualmente en el programa
	private ArrayList<String>listaOperadores = new ArrayList<String>();//lista de los operadores en una expresion
	private ArrayList<Variable> listaVariables = new ArrayList<Variable>();//lista de las variables ocupadas hasat ahora, incluye nombre y tipo de la variable
	private ArrayList<Constantes> listaConstantes = new ArrayList<Constantes>();
	
	
	Variable auxVar; 	//auxiliar para agregar nuvas variables a la listaVariables
	Constantes auxConst;
	
	/**
	 * Add the variable to the symbol table 
	 * 
	 * @param var
	 *           the AST containing the variable.
	 */ 
	public void addVar(AST tipo,AST id) {
	
		if(listaNombreId.contains(id.toString())){
			this.semanticError(id, "redefinicion de variable '" + id + "'");
			
			}
		else
			auxVar = new Variable();
			auxVar.setTipo(tipo.toString());
			auxVar.setId(id.toString());
			
			listaVariables.add(auxVar);
			listaNombreId.add(id.toString());
			
					
	}
	
	public void addConst(AST tipo,AST id) {
		
		//System.out.println(id.toString()+" se agrega constante");
			
		if(listaNombreId.contains(id.toString())){
			this.semanticError(id, "redefinicion de constante '" + id + "'");
			
			}
		else
			this.listaNombreId.add(id.toString());
			auxConst = new Constantes();
			auxConst.setTipo(tipo.toString());
			auxConst.setId(id.toString());
			
			
			listaConstantes.add(auxConst);
			
								
	}
	
	/**
	 * Chequea que la variable este definida 
	 * 
	 * @param var
	 */ 
	public void checkVar(AST var) {
		if(!listaNombreId.contains(var.toString()))
			this.semanticError(var, "variable '" + var + "' no esta definida");
		
	}
	
	public void checkTipos(ArrayList<AST> listVar) {

		ArrayList<Variable> listaEncon = new ArrayList<Variable>();
		int i=0;
		int j=0;
		
		
		while (j<listVar.size()){
			
			while (i < listaVariables.size()) {

				if (listaVariables.get(i).getId().equals(listVar.get(j).toString())) {
					listaEncon.add(listaVariables.get(i));
					
				}
			
			i++;
			
			}
			
			
			i=0;
			j++;
		}
		
		i=listaEncon.size()-1;
		
		while (i>=1){
			
			if (!(listaEncon.get(i).getTipo().equals(listaEncon.get(i-1).getTipo()))) {
				System.err.println("Incompatibilidad de tipos en las variables "
						+ listaEncon.get(i).getId() + " y " + listaEncon.get(i-1).getId());
				i=1;//para salir del bucle
			}
			
			i--;
		}
	listaEncon.clear();	
	}

	public void checkLitReal(ArrayList<AST> listVar){
		int i=0;
		int j=0;
		//System.out.println(listVar.toString());
		
	while (j<listVar.size()){
			
			while (i < listaVariables.size()) {

				if (listaVariables.get(i).getId().equals(listVar.get(j).toString()) && !listaVariables.get(i).getTipo().equals("real")) {
					System.err.println("Incompatibilidad de tipos en la variable "
							+ listaVariables.get(i).getId()+" y TIPO REAL");
					
					i=listaVariables.size();
				}
			
				i++;
			
			}
			
			
			i=0;
			j++;
		}
	
			
	}

	public void checkLitEntero(ArrayList<AST> listVar){
		
		int i=0;
		int j=0;
		//System.out.println(listVar.toString());
		
	while (j<listVar.size()){
			
			while (i < listaVariables.size()) {

				if (listaVariables.get(i).getId().equals(listVar.get(j).toString()) && !listaVariables.get(i).getTipo().equals("entero")) {
					System.err.println("Incompatibilidad de tipos en la variable "
							+ listaVariables.get(i).getId()+" y TIPO ENTERO");
					
					i=listaVariables.size();
				}
			
				i++;
			
			}
			
			
			i=0;
			j++;
		}
	
	
	}
		
	public void checkLitCaracter(ArrayList<AST> listVar){
		
		int i=0;
		int j=0;
		//System.out.println(listVar.toString());
		
	while (j<listVar.size()){
			
			while (i < listaVariables.size()) {

				if (listaVariables.get(i).getId().equals(listVar.get(j).toString()) && !listaVariables.get(i).getTipo().equals("carac")) {
					System.err.println("Incompatibilidad de tipos en la variable "
							+ listaVariables.get(i).getId()+" y TIPO CARACTER");
					
					i=listaVariables.size();
				}
			
				i++;
			
			}
						
			i=0;
			j++;
		}
		
	} 
	
	public void checkLitBooleano(ArrayList<AST> listVar){
		
		int i=0;
		int j=0;
		//System.out.println(listVar.toString());
		
	while (j<listVar.size()){
			
			while (i < listaVariables.size()) {

				if (listaVariables.get(i).getId().equals(listVar.get(j).toString()) && !listaVariables.get(i).getTipo().equals("booleano")) {
					System.err.println("Incompatibilidad de tipos en la variable "
							+ listaVariables.get(i).getId()+" y TIPO BOOLEANO");
					
					i=listaVariables.size();
				}
			
				i++;
			
			}
						
			i=0;
			j++;
		}
		
	} 
	
	public void clearOperatorTable(){
		listaOperadores.clear();
	}
	
	public void addOperator(AST op) {
				
		if(listaOperadores.contains(op.toString()))
			this.semanticError(op, "simbolo repetido '" + op + "'");
		else
			listaOperadores.add(op.toString());
	}
	
	public void semanticError(AST ast, String message)  {
		System.err.println(ast.getFilename() + ":" + 
				ast.getLine() + ":" + 
				ast.getColumn() + ": " + 
				message);
	} 

    
}


 