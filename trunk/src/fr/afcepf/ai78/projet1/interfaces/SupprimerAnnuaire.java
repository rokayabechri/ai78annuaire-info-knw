package fr.afcepf.ai78.projet1.interfaces;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class SupprimerAnnuaire extends JDialog implements ActionListener,WindowListener {
	
	private static final long serialVersionUID = 1L;
	private JComboBox comboBoxAnnuaire = new JComboBox();
	private JButton btnSupprimer = new JButton("Supprimer");
	private JButton btnAnnuler = new JButton("Annuler");
	private JLabel lblAnnuaire = new JLabel("Annuaires : ");
	private JLabel lblImage = new JLabel("");
	private FenetrePrincipale frame;
	private File erase;

	/**
	 * Create the dialog.
	 */
	public SupprimerAnnuaire(FenetrePrincipale frame) {
		super(frame, true);
		this.frame=frame;
		setTitle("Suppression d'annuaire");
		setResizable(false);
		setSize(250, 180);
		
		getContentPane().setLayout(null);
		
		comboBoxAnnuaire.setBounds(95, 45, 130, 25);
		getContentPane().add(comboBoxAnnuaire);
		
		btnSupprimer.setBounds(20, 105, 90, 25);
		getContentPane().add(btnSupprimer);
		
		btnAnnuler.setBounds(130, 105, 90, 25);
		getContentPane().add(btnAnnuler);
		
		lblAnnuaire.setBounds(95, 10, 145, 20);
		getContentPane().add(lblAnnuaire);
		comboBoxAnnuaire.addItem("");
		
		lblImage.setIcon(new ImageIcon(SupprimerAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/supprimer_icon.png")));
		lblImage.setBounds(10, 15, 75, 75);
		getContentPane().add(lblImage);
		
		erase = new File(AnnuaireConstante.BIN_PATH);		
		for (String string : erase.list()) {
			comboBoxAnnuaire.addItem(string.substring(0, string.indexOf(".")));
		}
		
		btnSupprimer.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnSupprimer){
			if(!comboBoxAnnuaire.getSelectedItem().toString().equals("")){
				String nomFichier = comboBoxAnnuaire.getSelectedItem().toString();
				
				if(JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer "+nomFichier+" ?","",JOptionPane.OK_CANCEL_OPTION)==0){
										
					erase = new File(AnnuaireConstante.BIN_PATH+nomFichier+".bin");
					erase.delete();
				
					if( (frame.getAnnuaireCourant() != null) && (AnnuaireConstante.BIN_PATH+nomFichier+".bin").equals(frame.getAnnuaireCourant().getFichierSortie()) ){
						frame.getContentPane().remove(frame.getContentPane().getComponent(0));
						frame.getContentPane().add(frame.getPanelLancement());
						frame.setTitle("Gestion d'annuaire");
						frame.getContentPane().revalidate();
					}
					frame.disposePopUp();
				}
			}
		}

		if(e.getSource()==btnAnnuler){
			frame.disposePopUp();
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
}
