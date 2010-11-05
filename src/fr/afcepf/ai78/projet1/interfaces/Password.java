package fr.afcepf.ai78.projet1.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;

public class Password extends JDialog implements ActionListener,WindowListener {
	
	
	private FenetrePrincipale frame;
	private JPasswordField passwordField;
	private JButton btnValider;
	private JButton btnAnnuler;
	
	/**
	 * Create the dialog.
	 */
	public Password(FenetrePrincipale frame) {
		
		this.frame = frame;
		setTitle("Password");
		setBounds(100, 100, 267, 174);
		getContentPane().setLayout(null);
		
		btnValider = new JButton("Valider");
		btnValider.setBounds(28, 113, 91, 23);
		getContentPane().add(btnValider);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(144, 113, 91, 23);
		getContentPane().add(btnAnnuler);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 49, 126, 28);
		getContentPane().add(passwordField);
		
		JLabel lblEntrerPassword = new JLabel("Password :");
		lblEntrerPassword.setBounds(70, 31, 98, 16);
		getContentPane().add(lblEntrerPassword);
		
		
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnValider){

			String password="";
			for (char lettre : passwordField.getPassword()) {
				password+=lettre+"";
			}

			if(!password.equals("")){


				if(password.equals(AnnuaireConstante.PASSWORD)){
					frame.setConnected(true);
					if(frame.getContentPane().getComponent(0) != frame.getPanelLancement()){
						AffichageAnnuaire aa = (AffichageAnnuaire) frame.getContentPane().getComponent(0);
						aa.getPanelOption().setVisible(true);

					}
					
					frame.getBtnConnexion().setVisible(false);
					frame.getBtnDeconnexion().setVisible(true);
					frame.setPopUp(null);
					frame.setEnabled(true);
					frame.toFront();
					this.dispose();					
				}else{
					JOptionPane.showMessageDialog(this, "Password incorrect");					
				}
			}else{
				JOptionPane.showMessageDialog(this, "Saisir Password");
			}

		}

		if(e.getSource()==btnAnnuler){
			frame.setPopUp(null);
			frame.setEnabled(true);
			frame.toFront();
			this.dispose();
		}
		
	}
	
	
	@Override
	public void windowActivated(WindowEvent e) {}
	
	@Override
	public void windowClosed(WindowEvent e) {}
	
	@Override
	public void windowClosing(WindowEvent e) {
		frame.setPopUp(null);
		frame.setEnabled(true);
		frame.toFront();
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	@Override
	public void windowDeiconified(WindowEvent e) {}
	
	@Override
	public void windowIconified(WindowEvent e) {}
	
	@Override
	public void windowOpened(WindowEvent e) {}

}
