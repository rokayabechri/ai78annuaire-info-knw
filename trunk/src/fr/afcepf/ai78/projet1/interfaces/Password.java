package fr.afcepf.ai78.projet1.interfaces;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Password extends JDialog implements ActionListener,WindowListener,KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField = new JPasswordField();
	private JLabel lblPassword			 = new JLabel("Password :");
	private JLabel lblImage 			 = new JLabel("");
	private JButton btnValider 			 = new JButton("Valider");
	private JButton btnAnnuler 			 = new JButton("Annuler");
	private FenetrePrincipale frame;
	
	/**
	 * Create the dialog.
	 */
	public Password(FenetrePrincipale frame) {
		this.frame = frame;
		setTitle("Connection");
		setSize(240, 155);
		
		getContentPane().setLayout(null);
		
		btnValider.setBounds(10, 85, 90, 25);
		getContentPane().add(btnValider);
		
		btnAnnuler.setBounds(130, 85, 90, 25);
		getContentPane().add(btnAnnuler);
		
		passwordField.setBounds(90, 35, 125, 30);
		getContentPane().add(passwordField);
		
		lblPassword.setBounds(95, 15, 100, 15);
		getContentPane().add(lblPassword);
		
		lblImage.setIcon(new ImageIcon(Password.class.getResource("/fr/afcepf/ai78/projet1/images/password_icon.png")));
		lblImage.setBounds(10, 10, 70, 60);
		getContentPane().add(lblImage);
		
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);
		passwordField.addKeyListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnValider){
			verifPassword();
		}
		
		if(e.getSource()==btnAnnuler){
			frame.disposePopUp();
		}	
	}
	
	private void verifPassword(){
		
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
				frame.getMntmSupprimerAnnuaire().setEnabled(true);
				frame.getBtnConnexion().setVisible(false);
				frame.getBtnDeconnexion().setVisible(true);
				frame.setPopUp(null);
				frame.setTitle(frame.getTitle()+" (Connecté)");
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
	
	@Override
	public void windowActivated(WindowEvent e) {}
	
	@Override
	public void windowClosed(WindowEvent e) {}
	
	@Override
	public void windowClosing(WindowEvent e) {
		frame.disposePopUp();
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	@Override
	public void windowDeiconified(WindowEvent e) {}
	
	@Override
	public void windowIconified(WindowEvent e) {}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			verifPassword();
		}else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			frame.disposePopUp();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
