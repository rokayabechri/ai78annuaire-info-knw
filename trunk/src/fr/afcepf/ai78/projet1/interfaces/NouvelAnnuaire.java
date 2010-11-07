package fr.afcepf.ai78.projet1.interfaces;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NouvelAnnuaire extends JDialog implements ActionListener,WindowListener,KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtAnnuaire 			 = new JTextField();
	private JRadioButton rdbtnNouveauFichier = new JRadioButton("Nouveau fichier");
	private JRadioButton rdbtnAPartirDun 	 = new JRadioButton("Importer fichier");
	private JButton btnCreer 				 = new JButton("Créer");
	private JButton btnAnnuler 				 = new JButton("Annuler");
	private JLabel lblImage 				 = new JLabel("");
	private JLabel lblSaisirNomDu 			 = new JLabel("Saisir le nom du nouvel annuaire : ");
	private FenetrePrincipale frame;

	/**
	 * Create the dialog.
	 */
	public  NouvelAnnuaire(FenetrePrincipale frame) {
		super(frame, true);
		this.frame = frame;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NouvelAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/menu_nouveau.png")));
		setResizable(false);
		setSize(300, 230);
		setTitle("Créer annuaire");
		getContentPane().setLayout(null);

		rdbtnNouveauFichier.setSelected(true);
		rdbtnNouveauFichier.setBounds(95, 95, 140, 25);
		getContentPane().add(rdbtnNouveauFichier);
		
		rdbtnAPartirDun.setBounds(95, 125, 140, 25);
		getContentPane().add(rdbtnAPartirDun);

		txtAnnuaire.setBounds(94, 49, 152, 30);
		getContentPane().add(txtAnnuaire);
		txtAnnuaire.setColumns(10);

		lblSaisirNomDu.setBounds(55, 10, 215, 25);
		getContentPane().add(lblSaisirNomDu);

		btnCreer.setBounds(45, 155, 80, 25);
		getContentPane().add(btnCreer);

		btnAnnuler.setBounds(165, 155, 80, 25);
		getContentPane().add(btnAnnuler);

		lblImage.setIcon(new ImageIcon(NouvelAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/folder_create.png")));
		lblImage.setBounds(10, 55, 70, 70);
		getContentPane().add(lblImage);

		btnAnnuler.addActionListener(this);
		btnCreer.addActionListener(this);
		rdbtnAPartirDun.addActionListener(this);
		rdbtnNouveauFichier.addActionListener(this);
		this.addWindowListener(this);
		txtAnnuaire.addKeyListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnCreer){
			creerAnnuaire();
		}

		if(e.getSource()==btnAnnuler){
			frame.disposePopUp();
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

	private void creerAnnuaire() {
		File f = new File(AnnuaireConstante.BIN_PATH);

		if(rdbtnAPartirDun.isSelected()){
			String nomFichier = txtAnnuaire.getText().toString();

			if(!nomFichier.equals("")){
				if(!hasAnnuaire(nomFichier, f)){
					aPartirDunFichier(nomFichier,false);
				}
				else if(JOptionPane.showConfirmDialog(this, "Cet annuaire existe déjà, Voulez-vous le supprimer ?","",JOptionPane.OK_CANCEL_OPTION)==0){
					aPartirDunFichier(nomFichier,true);
				}
			}else{
				JOptionPane.showMessageDialog(this, "Saisir un nom");
			}
			
		}else if(rdbtnNouveauFichier.isSelected()){
			String nomFichier = txtAnnuaire.getText().toString();

			if(!nomFichier.equals("")){
				if(!hasAnnuaire(nomFichier, f)){
					nouveauFichier(nomFichier, false);
				}else if(JOptionPane.showConfirmDialog(this, "Cet annuaire existe déjà, Voulez-vous le supprimer ?","",JOptionPane.OK_CANCEL_OPTION)==0){
					nouveauFichier(nomFichier, true);
				}
			}else{
				JOptionPane.showMessageDialog(this, "Saisir un nom");
			}
		}
	}

	private boolean hasAnnuaire(String nomFichier,File f){
		boolean trouve = false;
		
		for (String chemin : f.list()) {
			if(chemin.equals(nomFichier+".bin")) {
				trouve = true;
			}
		}
		return trouve;
	}
	
	private void aPartirDunFichier(String nomFichier,boolean supprimer){
		
		JFileChooser fc = new JFileChooser("C:/");
		
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			
			if(supprimer){
				File erase = new File(AnnuaireConstante.BIN_PATH+nomFichier+".bin");
				erase.delete();
			}
			
			GestionBinaire gb = new GestionBinaire(frame, fc.getSelectedFile().toString(), AnnuaireConstante.BIN_PATH+nomFichier+".bin");
			gb.addPropertyChangeListener(new PropertyChangeListener() {

				public  void propertyChange(PropertyChangeEvent evt) {
					if ("progress".equals(evt.getPropertyName())) {
						frame.getProgressBar().setValue((Integer)evt.getNewValue());
					}
				}
			});
			
			gb.execute();
			frame.setAnnuaireCourant(gb);
			
			if(frame.isConnected()){
				frame.setTitle("Gestion d'annuaire : "+nomFichier+" (Connecté)");
			}else{
				frame.setTitle("Gestion d'annuaire : "+nomFichier);
			}
			frame.disposePopUp();
		}
	}	
	
	private void nouveauFichier(String nomFichier,boolean supprimer){
		
		if(supprimer){
			File erase = new File(AnnuaireConstante.BIN_PATH+nomFichier+".bin");
			erase.delete();
		}
		
		frame.setAnnuaireCourant(new GestionBinaire(frame,"", AnnuaireConstante.BIN_PATH+nomFichier+".bin"));						
		frame.appelAffichage(true);
		
		if(frame.isConnected()){
			frame.setTitle("Gestion d'annuaire : "+nomFichier+" (Connecté)");
		}else{
			frame.setTitle("Gestion d'annuaire : "+nomFichier);
		}
		frame.disposePopUp();
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			creerAnnuaire();
		}else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			frame.disposePopUp();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
