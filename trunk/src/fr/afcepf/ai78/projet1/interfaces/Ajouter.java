package fr.afcepf.ai78.projet1.interfaces;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import fr.afcepf.ai78.projet1.objets.Noeud;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Ajouter extends JDialog implements ActionListener,WindowListener{

	private static final long serialVersionUID = 1L;
	private JButton btnAnnuler 		   		   = new JButton("Annuler");
	private JButton btnSauvegarder 	   		   = new JButton("Sauvegarder");
	private JLabel lblPrenom 		   		   = new JLabel("Prénom :");
	private JLabel lblNom 			   		   = new JLabel("Nom :");
	private JLabel lblAnnee 		   		   = new JLabel("Année :");
	private JLabel lblDepartement 	   		   = new JLabel("Département :");
	private JLabel lblPromotion		   		   = new JLabel("Promotion :");
	private JLabel lblImage 		   		   = new JLabel("");
	private JTextField txtNom 		   		   = new JTextField();
	private JTextField txtPrenom 	   		   = new JTextField();
	private JTextField txtPromotion    		   = new JTextField();
	private JFormattedTextField txtDepartement = new JFormattedTextField();
	private JFormattedTextField txtAnnee 	   = new JFormattedTextField();
	private JComboBox comboBox 		   		   = new JComboBox();
	private FenetrePrincipale frame;

	/**
	 * Create the panel.
	 */
	public Ajouter(FenetrePrincipale frame) {
		
		this.frame = frame;
		setTitle("Ajouter un stagiaire");
		setResizable(false);
		setSize(405, 300);
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("140px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("140px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("90px"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("45px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("45px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("45px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("45px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("45px"),}));

		getContentPane().add(lblNom, "2, 2, right, default");
		getContentPane().add(lblPrenom, "2, 4, right, default");
		getContentPane().add(lblPromotion, "2, 6, right, default");
		getContentPane().add(lblAnnee, "2, 8, right, default");
		getContentPane().add(lblDepartement, "2, 10, right, default");
		
		txtNom.setToolTipText("<html>Renseigner le nom du nouveau stagiaire.</html>");
		txtNom.setColumns(10);
		txtNom.setDocument(new LimiteTexte(30));
		getContentPane().add(txtNom, "4, 2, fill, default");

		txtPrenom.setToolTipText("<html>Renseigner le prénom du nouveau stagiaire.</html>");
		txtPrenom.setColumns(10);
		txtPrenom.setDocument(new LimiteTexte(30));
		getContentPane().add(txtPrenom, "4, 4, fill, default");

		try {
			txtAnnee = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e) {
			txtAnnee.setDocument(new LimiteTexte(4));
		}
		txtAnnee.setToolTipText("<html>Renseigner l'année de promotion du nouveau stagiaire.</html>");
		txtAnnee.setColumns(10);
		getContentPane().add(txtAnnee, "4, 8, left, default");
		
		try {
			txtDepartement = new JFormattedTextField(new MaskFormatter("AA"));
		} catch (ParseException e) {
			txtDepartement.setDocument(new LimiteTexte(2));
		}
		txtDepartement.setToolTipText("<html>Renseigner le code département du nouveau stagiaire.</html>");
		txtDepartement.setColumns(10);
		getContentPane().add(txtDepartement, "4, 10, left, default");
		
		txtPromotion.setToolTipText("<html>Renseigner une nouvelle année de promotion.</html>");
		txtPromotion.setVisible(false);
		txtPromotion.setColumns(10);
		txtPromotion.setDocument(new LimiteTexte(10));
		getContentPane().add(txtPromotion, "6, 6");
		
		comboBox.setToolTipText("<html>Sélectionner une promotion dans la liste déroulante.<br/>Choisir \"Autre\" pour créer une nouvelle promotion.</html>");
		comboBox.setMinimumSize(new Dimension(12, 26));
		comboBox.addItem("");

		for (String string : frame.getAnnuaireCourant().getPromo()) {
			comboBox.addItem(string);
		}
		comboBox.addItem("Autre");
		getContentPane().add(comboBox, "4, 6");
		
		lblImage.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/ajout_icon.png")));
		getContentPane().add(lblImage, "6, 1, 2, 5, default, top");
		
		btnSauvegarder.setToolTipText("<html>Sauvegarder votre nouvel enregistrement.</html>");
		getContentPane().add(btnSauvegarder, "6, 8");
		
		btnAnnuler.setToolTipText("<html>Annuler votre enregistrement.</html>");
		getContentPane().add(btnAnnuler, "6, 10");

		btnSauvegarder.addActionListener(this);
		btnAnnuler.addActionListener(this);
		comboBox.addActionListener(this);
		this.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnSauvegarder){

			if( !txtNom.getText().equals("") && !txtPrenom.getText().equals("") && !txtPromotion.getText().equals("") && !txtAnnee.getText().equals("") ){
				
				String prenom = txtPrenom.getText().substring(0,1).toUpperCase()+txtPrenom.getText().substring(1).toLowerCase();
				String departement = txtDepartement.getText().equals("  ")?"":txtDepartement.getText();
				Noeud nouveauNoeud = new Noeud(txtNom.getText().toUpperCase(),
												prenom,
												departement,
												txtPromotion.getText(),
												Integer.parseInt(txtAnnee.getText()));
				
				if(!frame.getAnnuaireCourant().ajoutElementArbreBinaire(nouveauNoeud,-1,0,false,frame.getAnnuaireCourant().getPositionAjout())){
					frame.getAnnuaireCourant().ecrireNoeud(frame.getAnnuaireCourant().getPositionAjout(),nouveauNoeud);
					
					AffichageAnnuaire affichage = (AffichageAnnuaire)frame.getContentPane().getComponent(0);
					affichage.getTable().setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));
					
					if (!frame.getAnnuaireCourant().getFantome().isEmpty()) {
						frame.getAnnuaireCourant().getFantome().remove(frame.getAnnuaireCourant().getFantome().get(frame.getAnnuaireCourant().getFantome().size()-1));
					}
				}else{
					JOptionPane.showMessageDialog(this, "Ce stagiaire existe deja");
				}
				frame.disposePopUp();
			}else{
				JOptionPane.showMessageDialog(this, "Nom, Prénom, Promotion et Année nécessaires");
			}
		}

		if(e.getSource()==btnAnnuler){
			frame.disposePopUp();
		}

		if(e.getSource()==comboBox){

			if(comboBox.getSelectedItem().toString().equals("Autre")){
				txtPromotion.setText("");
				txtPromotion.setVisible(true);
			}else {
				txtPromotion.setText(comboBox.getSelectedItem().toString());
				txtPromotion.setVisible(false);
			}
		}

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

	public FenetrePrincipale getFrame() {
		return frame;
	}
}
