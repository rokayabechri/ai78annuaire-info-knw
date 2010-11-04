package fr.afcepf.ai78.projet1.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnaireExistant.class.getResource("/fr/afcepf/ai78/projet1/images/menu_ouvrir.png")));
		setTitle("Ouvrir annuaire");
		setResizable(false);
		
		this.frame = frame;
		setBounds(100, 100, 267, 174);
		getContentPane().setLayout(null);

		btnValider = new JButton("Valider");
		btnValider.setBounds(32, 103, 80, 23);
		getContentPane().add(btnValider);


		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(144, 103, 80, 23);
		getContentPane().add(btnAnnuler);


		comboBox = new JComboBox();
		comboBox.setBounds(94, 44, 151, 26);
		comboBox.removeAllItems();
		getContentPane().add(comboBox);

		for (String string : listefichiers) {
					
			comboBox.addItem(string.substring(0,string.lastIndexOf("."))); 
		}

		lblSelectionnerUnFichier = new JLabel("Selectionner un fichier :");
		lblSelectionnerUnFichier.setBounds(94, 14, 151, 16);
		getContentPane().add(lblSelectionnerUnFichier);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AnnaireExistant.class.getResource("/fr/afcepf/ai78/projet1/images/folder_down.png")));
		label.setBounds(12, 14, 70, 70);
		getContentPane().add(label);

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
			frame.setPopUp(null);
			frame.setEnabled(true);
			frame.toFront();
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
