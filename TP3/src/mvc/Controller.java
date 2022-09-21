package mvc;

import javax.swing.JOptionPane;

public class Controller {
	private Model model;
	public View view;

	public Controller(Model m, View v) {
		model = m;
		view = v;
		initView();
	}

	public void initView() {
		view.getnomeTextfield().setText(model.getNome());
		view.getcursoTextfield().setText(model.getCurso());
		view.getmatriculaTextfield().setText(model.getMatricula());
		view.getemailTextfield().setText(model.getEmail());
	}

	public void initController() {		
		 view.getAlunoSaveButton().addActionListener(e -> sayAluno());
	}


	private void sayAluno() {
		model.setNome(view.getnomeTextfield().getText());
		model.setCurso(view.getcursoTextfield().getText());
		model.setMatricula(view.getmatriculaTextfield().getText());
		model.setEmail(view.getemailTextfield().getText());
		
		JOptionPane.showMessageDialog(null, "Nome: " + model.getNome() + "\n"
										+ "Curso: " + model.getCurso() + "\n"
										+ "Matricula: " + model.getMatricula() + "\n"
										+ "Email: " + model.getEmail(),
										"Info", JOptionPane.INFORMATION_MESSAGE);								
	}

}