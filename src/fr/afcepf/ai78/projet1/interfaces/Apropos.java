package fr.afcepf.ai78.projet1.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Apropos extends JDialog implements ActionListener,WindowListener{

	private static final long serialVersionUID = 1L;
	private JButton btnOk = new JButton("Ok");
	private JLabel lblCeProgramme;
	private JLabel lblVersionGestion;
	private FenetrePrincipale frame;

	/**
	 * Create the dialog.
	 */
	public Apropos(FenetrePrincipale frame) {
		super(frame,true);
		setResizable(false);
		this.frame = frame;		
		
		setSize(350, 155);
		setTitle("À propos de Gestion annuaire");
		getContentPane().setLayout(null);
		
		lblCeProgramme = new JLabel("<html>Ce programme a \u00E9t\u00E9 d\u00E9velopp\u00E9 par<br/><b>K.Augereau</b>, <b>W.Lepante</b>, <b>N. Chouaib</b>.</html>");
		lblCeProgramme.setBounds(84, 12, 260, 60);
		getContentPane().add(lblCeProgramme);
		
		lblVersionGestion = new JLabel("Version : Gestion Annuaire 1.0");
		lblVersionGestion.setBounds(12, 84, 279, 23);
		getContentPane().add(lblVersionGestion);
		
		btnOk.setBounds(275, 83, 49, 23);
		getContentPane().add(btnOk);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Apropos.class.getResource("/fr/afcepf/ai78/projet1/images/book_48.png")));
		label.setBounds(12, 12, 60, 60);
		getContentPane().add(label);
		
		btnOk.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOk){
			frame.disposePopUp();
		}
	}
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		frame.disposePopUp();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}