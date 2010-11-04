package fr.afcepf.ai78.projet1.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class NouvelAnnuaire extends JDialog implements ActionListener,WindowListener{
	private JTextField txtAnnuaire;
	private JRadioButton rdbtnNouveauFichier;
	private JRadioButton rdbtnAPartirDun;
	private JButton btnCreer;
	private JButton btnAnnuler;
	private FenetrePrincipale frame;
	private JLabel label;


	/**
	 * Create the dialog.
	 */
	public  NouvelAnnuaire(FenetrePrincipale frame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NouvelAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/menu_nouveau.png")));
		setResizable(false);
		
		this.frame = frame;
		setSize(300, 230);
		
		setTitle("Créer annuaire");
		getContentPane().setLayout(null);

		rdbtnNouveauFichier = new JRadioButton("Nouveau fichier");
		rdbtnNouveauFichier.setSelected(true);
		rdbtnNouveauFichier.setBounds(94, 93, 140, 23);
		getContentPane().add(rdbtnNouveauFichier);

		rdbtnAPartirDun = new JRadioButton("Importer fichier");
		rdbtnAPartirDun.setBounds(94, 123, 140, 23);
		getContentPane().add(rdbtnAPartirDun);

		txtAnnuaire = new JTextField();
		txtAnnuaire.setBounds(94, 49, 152, 30);
		getContentPane().add(txtAnnuaire);
		txtAnnuaire.setColumns(10);

		JLabel lblSaisirNomDu = new JLabel("Saisir le nom du nouvel annuaire : ");
		lblSaisirNomDu.setBounds(53, 12, 212, 23);
		getContentPane().add(lblSaisirNomDu);

		btnCreer = new JButton("Créer");
		btnCreer.setBounds(43, 153, 80, 23);
		getContentPane().add(btnCreer);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(166, 153, 80, 23);
		getContentPane().add(btnAnnuler);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(NouvelAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/folder_create.png")));
		label.setBounds(12, 55, 70, 70);
		getContentPane().add(label);

		btnAnnuler.addActionListener(this);
		btnCreer.addActionListener(this);
		rdbtnAPartirDun.addActionListener(this);
		rdbtnNouveauFichier.addActionListener(this);
		this.addWindowListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnCreer){

			File f = new File(AnnuaireConstante.BIN_PATH);
			if(!f.exists()){

				f.mkdirs();
			}
			if(rdbtnAPartirDun.isSelected()){

				String nomFichier = txtAnnuaire.getText().toString();

				if(!nomFichier.equals("")){

					boolean unBoolean = false;
					for (String chemin : f.list()) {

						if(chemin.equals(nomFichier+".bin")) {
							unBoolean = true;
						}
					}

					if(!unBoolean){

						JFileChooser fc = new JFileChooser("C:/");
						int returnVal = fc.showOpenDialog(this);
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							String chemin = fc.getSelectedFile().toString();

							GestionBinaire gb = new GestionBinaire(frame, chemin, "c:/binaries/"+nomFichier+".bin");
							gb.addPropertyChangeListener(new PropertyChangeListener() {

								public  void propertyChange(PropertyChangeEvent evt) {
									if ("progress".equals(evt.getPropertyName())) {
										frame.getProgressBar().setValue((Integer)evt.getNewValue());
									}
								}
							});

							frame.setAnnuaireCourant(gb);
							frame.getAnnuaireCourant().execute();
							frame.setPopUp(null);
							frame.toFront();
							this.dispose();
						}

					}
					else{

						int val = JOptionPane.showConfirmDialog(this, "Cet annuaire existe dèja, Voulez-vous le supprimer","",JOptionPane.OK_CANCEL_OPTION);
						if(val==0){

							JFileChooser fc = new JFileChooser("C:/");
							int returnVal = fc.showOpenDialog(this);
							if(returnVal == JFileChooser.APPROVE_OPTION) {
								String chemin = fc.getSelectedFile().toString();

								GestionBinaire gb = new GestionBinaire(frame, chemin, "c:/binaries/"+nomFichier+".bin");
								gb.addPropertyChangeListener(new PropertyChangeListener() {

									public  void propertyChange(PropertyChangeEvent evt) {
										if ("progress".equals(evt.getPropertyName())) {
											frame.getProgressBar().setValue((Integer)evt.getNewValue());
										}
									}
								});

								frame.setAnnuaireCourant(gb);
								frame.getAnnuaireCourant().execute();
								frame.setPopUp(null);
								frame.toFront();
								this.dispose();

							}
						}

					}
				}}else{

					if(rdbtnNouveauFichier.isSelected()){
						String nomFichier = txtAnnuaire.getText().toString();

						if(!nomFichier.equals("")){

							boolean unBoolean = false;
							for (String chemin : f.list()) {

								if(chemin.equals(nomFichier+".bin")) {
									unBoolean = true;
								}
							}
							if(!unBoolean){
								frame.setAnnuaireCourant(new GestionBinaire(frame,"", "c:/binaries/"+nomFichier+".bin"));						
								frame.appelAffichage(true);						
								frame.setPopUp(null);
								frame.toFront();
								this.dispose();
							}else{
								int val = JOptionPane.showConfirmDialog(this, "Cet annuaire existe dèja, Voulez-vous le supprimer","",JOptionPane.OK_CANCEL_OPTION);
								if(val==0){
									frame.setAnnuaireCourant(new GestionBinaire(frame,"", "c:/binaries/"+nomFichier+".bin"));						
									frame.appelAffichage(true);						
									frame.setPopUp(null);
									frame.toFront();
									this.dispose();
								}
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
