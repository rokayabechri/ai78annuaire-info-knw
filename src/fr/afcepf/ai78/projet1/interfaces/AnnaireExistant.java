package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;

public class AnnaireExistant extends JDialog implements ActionListener,WindowListener{
	private JComboBox comboBox;
	private JLabel lblSelectionnerUnFichier;
	private JButton btnValider;
	private JButton btnAnnuler;
	private FenetrePrincipale frame;


	/**
	 * Create the dialog.
	 */
	public AnnaireExistant(FenetrePrincipale frame, String [] listefichiers) {
		
		this.frame = frame;
		setBounds(100, 100, 320, 230);
		getContentPane().setLayout(null);

		btnValider = new JButton("Valider");
		btnValider.setBounds(25, 169, 102, 23);
		getContentPane().add(btnValider);


		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(167, 169, 120, 23);
		getContentPane().add(btnAnnuler);


		comboBox = new JComboBox();
		comboBox.setBounds(70, 71, 151, 26);
		comboBox.removeAllItems();
		getContentPane().add(comboBox);

		for (String string : listefichiers) {
					
			comboBox.addItem(string.substring(0,string.lastIndexOf("."))); 
		}

		lblSelectionnerUnFichier = new JLabel("Selectionner un fichier");
		lblSelectionnerUnFichier.setBounds(70, 34, 151, 16);
		getContentPane().add(lblSelectionnerUnFichier);

		comboBox.addActionListener(this);
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);
		

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnValider){
			if(!comboBox.getSelectedItem().toString().equals("")){

				String nomFichier = comboBox.getSelectedItem().toString();			
				
				frame.setAnnuaireCourant(new GestionBinaire(frame,"", "c:/binaries/"+nomFichier+".bin"));
				frame.appelAffichage(true);
				frame.setPopUp(null);
				this.dispose();


			}else{
				JOptionPane.showMessageDialog(this, "Selectionner un ficher");
			}

		}

		if(e.getSource()==btnAnnuler){
			frame.toFront();
			frame.setEnabled(true);
			this.dispose();

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
		frame.setPopUp(null);
		frame.setEnabled(true);
		frame.toFront();
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
