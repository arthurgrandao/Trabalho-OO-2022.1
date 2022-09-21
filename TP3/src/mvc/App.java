package mvc;

public class App {
	
	
	
	 public static void main(String[] args) {
		
		 
	  Model m = new Model(null,null,null,null);
	  View v = new View("Cadastrar Aluno");
	  Controller c = new Controller(m, v);
	  c.initController();
	 }
	}