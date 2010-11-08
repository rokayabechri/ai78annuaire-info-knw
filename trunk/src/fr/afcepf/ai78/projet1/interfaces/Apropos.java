package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Apropos extends JDialog implements ActionListener,WindowListener{
	private JButton btnOk;
	private FenetrePrincipale frame;

	/**
	 * Create the dialog.
	 */
	public Apropos(FenetrePrincipale frame) {
		
		super(frame,true);
		this.frame = frame;		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setTitle("À propos de Gestion annuaire");
		
		JLabel lblCePrgrammeA = new JLabel("Ce programme a \u00E9t\u00E9 développer par K.Augereau, W.Lepante, N, Chouaib.");
		lblCePrgrammeA.setBounds(12, 80, 420, 23);
		getContentPane().add(lblCePrgrammeA);
		
		JLabel lblVersionGestion = new JLabel("Version : Gestion Annuaire 1.0");
		lblVersionGestion.setBounds(36, 115, 279, 23);
		getContentPane().add(lblVersionGestion);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(341, 239, 91, 23);
		getContentPane().add(btnOk);
		btnOk.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnOk){
			
			frame.disposePopUp();
			
		}
		
	}
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		frame.disposePopUp();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
