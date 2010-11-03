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
			
			comboBox.addItem(string); 
		}

		lblSelectionnerUnFichier = new JLabel("Selectionner un fichier");
		lblSelectionnerUnFichier.setBounds(70, 34, 151, 16);
		getContentPane().add(lblSelectionnerUnFichier);

		comboBox.addActionListener(this);
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnValider){
			try {
				if(!comboBox.getSelectedItem().toString().equals("")){

					String nomFichier = comboBox.getSelectedItem().toString();

					String nomFichierSortie = "c:/"+nomFichier;

					this.dispose();
					frame.setPopUp(null);
					frame.getAnnuaireCourant().setFichier(new RandomAccessFile(nomFichierSortie, "rw"));
					frame.getContentPane().remove(frame.getPanelLancement());
					frame.getContentPane().add(new AffichageAnnuaire(frame),BorderLayout.CENTER);
					frame.getContentPane().revalidate();
					frame.setEnabled(true);
					frame.toFront();


				}else{
					JOptionPane.showMessageDialog(this, "Selectionner un ficher");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				frame.setEnabled(true);
				frame.toFront();
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
