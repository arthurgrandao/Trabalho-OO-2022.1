package mvc;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View {

	private JFrame frame;
	private JLabel nomeLabel;
	private JLabel cursoLabel;
	private JLabel matriculaLabel;
	private JLabel emailLabel;

	private JTextField nomeTextfield;
	private JTextField cursoTextfield;
	private JTextField matriculaTextfield;
	private JTextField emailTextfield;

	private JButton AlunoSaveButton;


	public View(String title) {
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		nomeLabel = new JLabel("Nome :");
		cursoLabel = new JLabel("Curso :");
		matriculaLabel = new JLabel("Matricula :");
		emailLabel = new JLabel("Email :");

		nomeTextfield = new JTextField();
		cursoTextfield = new JTextField();
		matriculaTextfield = new JTextField();
		emailTextfield = new JTextField();

		AlunoSaveButton = new JButton("Salvar Aluno");


		GroupLayout layout = new GroupLayout(frame.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(nomeLabel)
						.addComponent(cursoLabel)
						.addComponent(matriculaLabel)
						.addComponent(emailLabel))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(nomeTextfield)
						.addComponent(cursoTextfield)
						.addComponent(matriculaTextfield)
						.addComponent(emailTextfield))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(AlunoSaveButton)));

		layout.setVerticalGroup(layout.createSequentialGroup()

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(nomeLabel)
						.addComponent(nomeTextfield))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(cursoLabel)
						.addComponent(cursoTextfield))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(matriculaLabel)
						.addComponent(matriculaTextfield))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(emailLabel)
						.addComponent(emailTextfield))
				
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(AlunoSaveButton)));

		layout.linkSize(SwingConstants.HORIZONTAL, AlunoSaveButton);
		frame.getContentPane().setLayout(layout);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JLabel getnomeLabel() {
		return nomeLabel;
	}

	public void setnomeLabel(JLabel nomeLabel) {
		this.nomeLabel = nomeLabel;
	}

	public JLabel getcursoLabel() {
		return cursoLabel;
	}

	public void setcursoLabel(JLabel cursoLabel) {
		this.cursoLabel = cursoLabel;
	}
	public JLabel getmatriculaLabel() {
		return matriculaLabel;
	}

	public void setmatriculaLabel(JLabel matriculaLabel) {
	  this.matriculaLabel = matriculaLabel;
	}
	
	public JLabel getemailLabel() {
		return emailLabel;
	}

	public void setemailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;

	}
	
	public JTextField getnomeTextfield() {
		return nomeTextfield;
	}

	public void setnomeTextfield(JTextField nomeTextfield) {
		this.nomeTextfield = nomeTextfield;
	}

	public JTextField getcursoTextfield() {
		return cursoTextfield;
	}

	public void setcursoTextfield(JTextField cursoTextfield) {
		this.cursoTextfield = cursoTextfield;
	}
	public JTextField getmatriculaTextfield() {
		return matriculaTextfield;
	}
	
	public void setmatriculaTextfield(JTextField matriculaTextfield) {
		this.matriculaTextfield = matriculaTextfield;
	}
	public JTextField getemailTextfield() {
		return emailTextfield;
	}
	
	public void setemailTextfield(JTextField emailTextfield) {
		this.emailTextfield = emailTextfield;
	}

	public JButton getAlunoSaveButton() {
		return AlunoSaveButton;
	}

	public void setFirstnameSaveButton(JButton AlunoSaveButton) {
		this.AlunoSaveButton = AlunoSaveButton;
	}
 
}