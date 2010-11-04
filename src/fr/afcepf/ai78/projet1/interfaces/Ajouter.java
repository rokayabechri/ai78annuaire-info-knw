package fr.afcepf.ai78.projet1.interfaces;


import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import fr.afcepf.ai78.projet1.objets.Noeud;

import javax.swing.JComboBox;
import javax.swing.text.MaskFormatter;

public class Ajouter extends JDialog implements ActionListener,WindowListener{
	
	private JButton btnAnnuler;
	private JButton btnSauvegarder;
	private JLabel lblPrenom;
	private JLabel lblNom;
	private JLabel lblAnnee;
	private JLabel lblDepartement;
	private JLabel lblPromotion;
	private JFormattedTextField textNom;
	private JFormattedTextField textPrenom;
	private JFormattedTextField textDepartement;
	private JTextField textPromotion;
	private JFormattedTextField textAnnee;
	private JComboBox comboBox;
	private FenetrePrincipale frame;
	

	/**
	 * Create the panel.
	 */
	public Ajouter(FenetrePrincipale frame) {
		setResizable(false);	
		this.frame = frame;
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
		
		lblNom = new JLabel("Nom :");
		getContentPane().add(lblNom, "2, 2, right, center");
		
		
		try {
			textNom = new JFormattedTextField(new MaskFormatter("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"));
		} catch (ParseException e) {
			textNom = new JFormattedTextField();
			e.printStackTrace();
		}
		textNom.setToolTipText("<html>Renseigner le nom du nouveau stagiaire.</html>");
		getContentPane().add(textNom, "4, 2, fill, default");
		textNom.setColumns(10);



		lblPrenom = new JLabel("Prénom :");
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPrenom, "2, 4, right, default");
		
		
		try {
			textPrenom = new JFormattedTextField(new MaskFormatter("ULLLLLLLLLLLLLLLLLLLLLLLLLLLLL"));
		} catch (ParseException e) {
			textPrenom = new JFormattedTextField();
			e.printStackTrace();
		}
		textPrenom.setToolTipText("<html>Renseigner le prénom du nouveau stagiaire.</html>");
		textPrenom.setColumns(10);
		getContentPane().add(textPrenom, "4, 4, fill, default");
		
		
		lblPromotion = new JLabel("Promotion :");
		lblPromotion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPromotion, "2, 6, right, default");
		
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("<html>Selectionner une promotion dans la liste déroulante.<br/>Choisir \"autres\" pour créer une nouvelle promotion.</html>");
		comboBox.setMinimumSize(new Dimension(12, 26));
		getContentPane().add(comboBox, "4, 6");
		comboBox.removeAllItems();
		comboBox.addItem("");
		
		AffichageAnnuaire affichage = (AffichageAnnuaire) frame.getContentPane().getComponent(0);
		for (String string : affichage.getPromo()) {
			
			comboBox.addItem(string);
		}
		comboBox.addItem("autre");
		
		
		textPromotion = new JTextField();
		textPromotion.setToolTipText("<html>Renseigner une nouvelle année de promotion.</html>");
		textPromotion.setVisible(false);
		getContentPane().add(textPromotion, "6, 6");
		textPromotion.setColumns(10);
		textPromotion.setDocument(new LimiteTexte(10));
	
		
		lblAnnee = new JLabel("Année :");
		getContentPane().add(lblAnnee, "2, 8, right, default");
		
		
		try {
			textAnnee = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e) {
			textAnnee = new JFormattedTextField();
			e.printStackTrace();
		}
		textAnnee.setToolTipText("<html>Renseigner l'année de promotion du nouveau stagiaire.</html>");
		getContentPane().add(textAnnee, "4, 8, left, default");
		textAnnee.setColumns(10);

		
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setToolTipText("<html>Sauvegarder votre nouvelle enregistrement.</html>");
		getContentPane().add(btnSauvegarder, "6, 8");
		
		
		btnSauvegarder.addActionListener(this);
		
		
		lblDepartement = new JLabel("Département :");
		lblDepartement.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblDepartement, "2, 10, right, default");
		
		
		try {
			textDepartement = new JFormattedTextField(new MaskFormatter("##"));
		} catch (ParseException e) {
			textDepartement = new JFormattedTextField();
			e.printStackTrace();
		}
		textDepartement.setToolTipText("<html>Renseigner le code département du nouveau stagiaire.</html>");
		getContentPane().add(textDepartement, "4, 10, left, default");
		textDepartement.setColumns(10);
		
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setToolTipText("<html>Annuler votre enregistrement.<br/></html>");
		getContentPane().add(btnAnnuler, "6, 10");
		btnAnnuler.addActionListener(this);
		comboBox.addActionListener(this);
		this.addWindowListener(this);
		
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnSauvegarder){
			
			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			String promotion;
			if(comboBox.getSelectedItem().toString().equals("autre")){
				promotion = textPromotion.getText();
			}else{
				textPromotion.setText(comboBox.getSelectedItem().toString());
				promotion = textPromotion.getText();
			}

			String departement = textDepartement.getText();
			String annee = textAnnee.getText();

			if((!nom.equals("")&&!prenom.equals("")&&!promotion.equals("")&&!annee.equals(""))){
				try {
					Noeud unNoeud = new Noeud(nom,prenom,departement,promotion,Integer.parseInt(annee));
					frame.getAnnuaireCourant().ajoutElementArbreBinaire(unNoeud,-1,0,false,frame.getAnnuaireCourant().getPositionAjout());
					frame.getAnnuaireCourant().ecrireNoeud(frame.getAnnuaireCourant().getPositionAjout(),unNoeud);
					AffichageAnnuaire affichage = (AffichageAnnuaire)frame.getContentPane().getComponent(0);
					affichage.getTable().setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));
					if (!frame.getAnnuaireCourant().getFantome().isEmpty()) {
						frame.getAnnuaireCourant().getFantome().remove(frame.getAnnuaireCourant().getFantome().get(frame.getAnnuaireCourant().getFantome().size()-1));
					}
					
					this.dispose();
					frame.setPopUp(null);	
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			
		}
		
		if(e.getSource()==btnAnnuler){

			this.dispose();
			frame.setPopUp(null);
		}
		
		if(e.getSource()==comboBox){

			if(comboBox.getSelectedItem().toString().equals("autre")){
				textPromotion.setText("");
				textPromotion.setVisible(true);
			}else {
				textPromotion.setVisible(false);
			}
		}

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		textPromotion.setText(comboBox.getSelectedItem().toString());
		
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

	public FenetrePrincipale getFrame() {
		return frame;
	}
}
