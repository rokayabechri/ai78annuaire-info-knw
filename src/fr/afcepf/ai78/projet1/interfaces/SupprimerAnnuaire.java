package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;

public class SupprimerAnnuaire extends JDialog implements ActionListener,WindowListener {
	private JComboBox comboBoxAnnuaire;
	private JButton btnSupprimer;
	private JButton btnAnnuler;
	private FenetrePrincipale frame;
	private File erase;



	/**
	 * Create the dialog.
	 */
	public SupprimerAnnuaire(FenetrePrincipale frame) {
		
		this.frame=frame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 209);
		getContentPane().setLayout(null);
		
		comboBoxAnnuaire = new JComboBox();
		comboBoxAnnuaire.setBounds(54, 61, 175, 26);
		getContentPane().add(comboBoxAnnuaire);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(35, 134, 91, 23);
		getContentPane().add(btnSupprimer);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(150, 134, 91, 23);
		getContentPane().add(btnAnnuler);
		
		JLabel lblAnnuaire = new JLabel("Annuaires : ");
		lblAnnuaire.setBounds(54, 36, 69, 14);
		getContentPane().add(lblAnnuaire);
		comboBoxAnnuaire.addItem("");
		
		erase = new File(AnnuaireConstante.BIN_PATH);
		
		String [] tabAnnuaire = erase.list();
		
		for (String string : tabAnnuaire) {
			
			comboBoxAnnuaire.addItem(string.substring(0, string.indexOf(".")));
		}
		btnSupprimer.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==btnSupprimer){
			if(!comboBoxAnnuaire.getSelectedItem().toString().equals("")){
				String nomFichier = comboBoxAnnuaire.getSelectedItem().toString();
				int val = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer "+nomFichier+" ?","",JOptionPane.OK_CANCEL_OPTION);
				if(val==0){
										
					erase = new File(AnnuaireConstante.BIN_PATH+nomFichier+".bin");
					erase.delete();
				
					frame.setPopUp(null);
					if((AnnuaireConstante.BIN_PATH+nomFichier+".bin").equals(frame.getAnnuaireCourant().getFichierSortie())){
						AffichageAnnuaire affichageCourant = (AffichageAnnuaire) frame.getContentPane().getComponent(0);
						frame.getContentPane().remove(affichageCourant);
						frame.getContentPane().add(frame.getPanelLancement());
						frame.setTitle("Gestion d'annuaire");
						frame.getContentPane().revalidate();
					}
					frame.setEnabled(true);
					frame.toFront();
					this.dispose();
				}
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
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		frame.setPopUp(null);
		frame.setEnabled(true);
		frame.toFront();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}
