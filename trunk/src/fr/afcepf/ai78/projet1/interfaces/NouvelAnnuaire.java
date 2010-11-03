package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class NouvelAnnuaire extends JDialog implements ActionListener,WindowListener{
	private JTextField txtAnnuaire;
	private JRadioButton rdbtnNouveauFichier;
	private JRadioButton rdbtnAPartirDun;
	private JButton btnSauvegarder;
	private JButton btnAnnuler;
	private FenetrePrincipale frame;

	
	/**
	 * Create the dialog.
	 */
	public  NouvelAnnuaire(FenetrePrincipale frame) {
		
		this.frame = frame;
		setBounds(100, 100, 300, 230);
		getContentPane().setLayout(null);
		
		rdbtnNouveauFichier = new JRadioButton("Nouveau fichier");
		rdbtnNouveauFichier.setBounds(41, 93, 109, 23);
		getContentPane().add(rdbtnNouveauFichier);
		
		rdbtnAPartirDun = new JRadioButton("A partir d'un ficheir de données");
		rdbtnAPartirDun.setBounds(41, 123, 175, 23);
		getContentPane().add(rdbtnAPartirDun);
		
		txtAnnuaire = new JTextField();
		txtAnnuaire.setBounds(41, 46, 186, 30);
		getContentPane().add(txtAnnuaire);
		txtAnnuaire.setColumns(10);
		
		JLabel lblSaisirNomDu = new JLabel("Saisir nom du nouvel annuaire: ");
		lblSaisirNomDu.setBounds(41, 21, 186, 14);
		getContentPane().add(lblSaisirNomDu);
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(25, 169, 102, 23);
		getContentPane().add(btnSauvegarder);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(167, 169, 102, 23);
		getContentPane().add(btnAnnuler);
		
		btnAnnuler.addActionListener(this);
		btnSauvegarder.addActionListener(this);
		rdbtnAPartirDun.addActionListener(this);
		rdbtnNouveauFichier.addActionListener(this);
		this.addWindowListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==btnSauvegarder){

			if(rdbtnAPartirDun.isSelected()){

				String nomFichier = txtAnnuaire.getText().toString();

				if(!nomFichier.equals("")){
					JFileChooser fc = new JFileChooser("C:/");
					int returnVal = fc.showOpenDialog(this);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						String chemin = fc.getSelectedFile().toString();

						if(frame.getAnnuaireCourant().creationArbreBinaire(chemin, "c:/binaries/"+nomFichier+".bin")){
							JOptionPane.showMessageDialog(this, "Création OK");
							frame.getContentPane().remove(frame.getPanelLancement());
							frame.getContentPane().add(new AffichageAnnuaire(frame),BorderLayout.CENTER);
							frame.setEnabled(true);
							frame.toFront();
							frame.getContentPane().revalidate();
							frame.setPopUp(null);
							this.dispose();
						}else{
							JOptionPane.showMessageDialog(this, "Fichier incorrect");
						}
					}
				}else{
					JOptionPane.showMessageDialog(this, "Saisir un nom");
				}
			}else{

				if(rdbtnNouveauFichier.isSelected()){


					String nomFichier = txtAnnuaire.getText().toString();

					if(!nomFichier.equals("")){

						try {
							
							this.dispose();
							frame.setPopUp(null);
							String FichierSortie =  "c:/"+nomFichier+".bin";
							frame.getAnnuaireCourant().setFichier(new RandomAccessFile(FichierSortie, "rw"));
							frame.getContentPane().remove(frame.getPanelLancement());
							frame.getContentPane().add(new AffichageAnnuaire(frame),BorderLayout.CENTER);
							frame.getContentPane().revalidate();
							frame.setEnabled(true);
							frame.toFront();		
						} catch (FileNotFoundException e1) {
							frame.setEnabled(true);
							frame.toFront();
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(this, "Saisir un nom");
					}

				}
			}
		}
		
		if(e.getSource()==btnAnnuler){
			frame.toFront();
			frame.setEnabled(true);
			this.dispose();
		}
		

		
		if(e.getSource()==rdbtnAPartirDun){
			
			if(rdbtnNouveauFichier.isSelected()){
				rdbtnNouveauFichier.setSelected(false);
			}
		}
		if(e.getSource()==rdbtnNouveauFichier){
			
			if(rdbtnAPartirDun.isSelected()){
				rdbtnAPartirDun.setSelected(false);
			}
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
