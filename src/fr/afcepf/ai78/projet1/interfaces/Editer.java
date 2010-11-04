package fr.afcepf.ai78.projet1.interfaces;


import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import fr.afcepf.ai78.projet1.objets.Noeud;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class Editer extends JDialog implements ActionListener,WindowListener{
	private JButton btnAnnuler;
	private JButton btnValider;
	private JLabel lblPrenom;
	private JLabel lblNom;
	private JLabel lblAnnee;
	private JLabel lblDepartement;
	private JLabel lblPromotion;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtDepartement;
	private JTextField txtAnnee;
	private JComboBox cBPromotion;
	private AffichageAnnuaire parent;
	private Noeud unNoeudAModifier;
	private JTextField textPromotion;
	private JLabel label;

	/**
	 * Create the panel.
	 */
	public Editer(AffichageAnnuaire parent,Noeud unNoeudAModifier) {
		setTitle("Éditer un stagiaire");
		setResizable(false);
		setSize(425, 295);
		this.parent = parent;
		this.unNoeudAModifier = unNoeudAModifier;
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("140px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("11px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("140px"),},
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
		
		

		txtNom = new JTextField();
		txtNom.setToolTipText("<html>Modifier le nom du stagiaire.</html>");
		getContentPane().add(txtNom, "4, 2, fill, default");
		txtNom.setColumns(10);
		txtNom.setDocument(new LimiteTexte(30));
		txtNom.setText(unNoeudAModifier.getNom());
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Editer.class.getResource("/fr/afcepf/ai78/projet1/images/edit_icon.png")));
		getContentPane().add(label, "8, 1, 1, 5");
		
		
		lblPrenom = new JLabel("Prénom :");
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPrenom, "2, 4, right, default");
		
		
		txtPrenom = new JTextField();
		txtPrenom.setToolTipText("<html>Modifier le prénom du stagiaire.</html>");
		txtPrenom.setColumns(10);
		txtPrenom.setDocument(new LimiteTexte(30));
		getContentPane().add(txtPrenom, "4, 4, fill, default");
		txtPrenom.setText(unNoeudAModifier.getPrenom());
		
		
		lblPromotion = new JLabel("Promotion :");
		lblPromotion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPromotion, "2, 6, right, default");
		
		
		cBPromotion = new JComboBox();
		cBPromotion.setToolTipText("<html>Selectionner une promotion dans la liste déroulante.<br/>Choisir \"autres\" pour créer une nouvelle promotion.</html>");
		cBPromotion.setMinimumSize(new Dimension(12, 26));
		getContentPane().add(cBPromotion, "4, 6");
		cBPromotion.addItem("");
		for (String string : parent.getFrame().getAnnuaireCourant().getPromo()) {
			
			cBPromotion.addItem(string);
		}
		
		cBPromotion.addItem("autre");
		cBPromotion.setSelectedItem(unNoeudAModifier.getPromotion());

			
		textPromotion = new JTextField();
		textPromotion.setToolTipText("<html>Renseigner une nouvelle année de promotion.</html>");
		textPromotion.setVisible(false);
		getContentPane().add(textPromotion, "8, 6");
		textPromotion.setColumns(10);
		textPromotion.setDocument(new LimiteTexte(10));
		textPromotion.setText(unNoeudAModifier.getPromotion());
		
		
		lblAnnee = new JLabel("Année :");
		getContentPane().add(lblAnnee, "2, 8, right, default");
		
		
		txtAnnee = new JTextField();;

		txtAnnee.setToolTipText("<html>Modifier l'année de promotion du stagiaire.</html>");
		getContentPane().add(txtAnnee, "4, 8, left, default");
		txtAnnee.setColumns(4);
		txtAnnee.setDocument(new LimiteTexte(30));
		txtAnnee.setText(unNoeudAModifier.getAnnee()+"");
	
		
		
		btnValider = new JButton("Valider");
		btnValider.setToolTipText("<html>Valider votre édition.</html>");
		getContentPane().add(btnValider, "8, 8");
		
		
		btnValider.addActionListener(this);
		
		
		lblDepartement = new JLabel("Département :");
		lblDepartement.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblDepartement, "2, 10, right, default");
		
		

		txtDepartement = new JTextField();;
		txtDepartement.setToolTipText("<html>Modifier le code département du stagiaire.</html>");
		getContentPane().add(txtDepartement, "4, 10, left, default");
		txtDepartement.setColumns(10);
		txtDepartement.setDocument(new LimiteTexte(2));
		txtDepartement.setText(unNoeudAModifier.getDepartement());
		
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setToolTipText("<html>Annuler votre édition.</html>");
		getContentPane().add(btnAnnuler, "8, 10");
		btnAnnuler.addActionListener(this);
		cBPromotion.addActionListener(this);
		addWindowListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnValider){

			String nom = txtNom.getText().toUpperCase();
			String chaine = txtPrenom.getText();
			String p = chaine.substring(0,1).toUpperCase();
			String renom = chaine.substring(1).toLowerCase();
			String  prenom = p+renom;
			String promotion;
			if(cBPromotion.getSelectedItem().toString().equals("autre")){
				promotion = textPromotion.getText();
			}else{
				textPromotion.setText(cBPromotion.getSelectedItem().toString());
				promotion = textPromotion.getText();
			}
			String departement = txtDepartement.getText();
			String annee = txtAnnee.getText();

			if((!nom.equals("")&&!prenom.equals("")&&!promotion.equals("")&&!annee.equals(""))){
				Noeud unNoeud = new Noeud(nom,prenom,departement,promotion,Integer.parseInt(annee));
				parent.getFrame().getAnnuaireCourant().supprimer(unNoeudAModifier, 0);
				parent.getFrame().getAnnuaireCourant().ajoutElementArbreBinaire(unNoeud,-1,0,false,parent.getFrame().getAnnuaireCourant().getPositionAjout());
				parent.getFrame().getAnnuaireCourant().ecrireNoeud(parent.getFrame().getAnnuaireCourant().getPositionAjout(),unNoeud);
				parent.getTable().setModel(new ModeleStagiaire(parent.getFrame().getAnnuaireCourant().afficherTout()));
				parent.getFrame().getAnnuaireCourant().getFantome().remove(parent.getFrame().getAnnuaireCourant().getFantome().size()-1);

				this.dispose();
				parent.getFrame().setPopUp(null);
			}
		}

		if(e.getSource()==btnAnnuler){
			this.dispose();
			parent.getFrame().setPopUp(null);
		}
		
		if(e.getSource()==cBPromotion){

			if(cBPromotion.getSelectedItem().toString().equals("autre")){
				textPromotion.setText("");
				textPromotion.setVisible(true);
			}else {
				textPromotion.setVisible(false);
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
		parent.getFrame().setPopUp(null);
		
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

	
	
	public Noeud getUnNoeudAModifier() {
		return unNoeudAModifier;
	}

			
}
