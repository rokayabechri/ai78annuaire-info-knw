package fr.afcepf.ai78.projet1.interfaces;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import fr.afcepf.ai78.projet1.objets.Stagiaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class RechercheAvancee extends JDialog implements ActionListener,WindowListener{
	
	private static final long serialVersionUID = 1L;
	private JButton btnAnnuler		 		   = new JButton("Annuler");
	private JButton btnRechercher			   = new JButton("Rechercher");
	private JLabel lblPrenom 		 		   = new JLabel("Prénom :");
	private JLabel lblNom 			 		   = new JLabel("Nom :");
	private JLabel lblAnnee 		  		   = new JLabel("Année :");
	private JLabel lblDepartement 	  		   = new JLabel("Département :");
	private JLabel lblPromotion		  		   = new JLabel("Promotion :");
	private JLabel lblImage 		  		   = new JLabel("");
	private JTextField txtNom 		    	   = new JTextField();
	private JTextField txtPrenom 	  		   = new JTextField();
	private JFormattedTextField txtDepartement = new JFormattedTextField();
	private JFormattedTextField txtAnnee 	   = new JFormattedTextField();
	private JComboBox comboBox 		  		   = new JComboBox();
	private FenetrePrincipale frame;

	/**
	 * Create the panel.
	 */
	public RechercheAvancee(FenetrePrincipale frame) {
		this.frame = frame;
		setTitle("Recherche avancée");
		setResizable(false);
		setSize(410, 300);
		
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
		
		getContentPane().add(lblNom, "2, 2, right, center");
		getContentPane().add(lblPrenom, "2, 4, right, default");
		getContentPane().add(lblPromotion, "2, 6, right, default");
		getContentPane().add(lblAnnee, "2, 8, right, default");
		getContentPane().add(lblDepartement, "2, 10, right, default");
		
		txtNom.setToolTipText("<html>Effectuer une recherche sur le nom du stagiaire.</html>");
		txtNom.setColumns(10);
		getContentPane().add(txtNom, "4, 2, fill, default");
		
		txtPrenom.setToolTipText("<html>Effectuer une recherche sur le prénom du stagiaire.</html>");
		txtPrenom.setColumns(10);
		getContentPane().add(txtPrenom, "4, 4, fill, default");
		
		try {
			txtAnnee = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e) {
			txtAnnee.setDocument(new LimiteTexte(4));
		}
		txtAnnee.setToolTipText("<html>Effectuer une recherche sur l'année de promotion du stagiaire.</html>");
		txtAnnee.setColumns(10);
		getContentPane().add(txtAnnee, "4, 8, left, default");
		
		try {
			txtDepartement = new JFormattedTextField(new MaskFormatter("AA"));
		} catch (ParseException e) {
			txtDepartement.setDocument(new LimiteTexte(2));
		}
		txtDepartement.setToolTipText("<html>Effectuer une recherche sur le code département du stagiaire.</html>");
		txtDepartement.setColumns(10);
		getContentPane().add(txtDepartement, "4, 10, left, default");
		
		lblImage.setIcon(new ImageIcon(RechercheAvancee.class.getResource("/fr/afcepf/ai78/projet1/images/search_icon_big.png")));
		getContentPane().add(lblImage, "6, 1, 1, 7");
		
		comboBox.setToolTipText("<html>Effectuer une recherche sur l'une des promotions des stagiaires.</html>");
		comboBox.addItem("");
		for (String string : frame.getAnnuaireCourant().getPromo()) {
			comboBox.addItem(string);
		}
		getContentPane().add(comboBox, "4, 6");
		
		btnRechercher.setToolTipText("<html>Effectuer votre recherche.</html>");
		getContentPane().add(btnRechercher, "6, 8");
		
		btnAnnuler.setToolTipText("<html>Annuler votre recherche.</html>");
		getContentPane().add(btnAnnuler, "6, 10");
		
		btnRechercher.addActionListener(this);
		btnAnnuler.addActionListener(this);
		comboBox.addActionListener(this);
		this.addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnRechercher){
			
			int annee = -1;
			try {
				annee = Integer.parseInt(txtAnnee.getText());
			} catch (NumberFormatException e2) {
				//e2.printStackTrace();
			}
			String departement = txtDepartement.getText().equals("  ")?"":txtDepartement.getText();
			List<Stagiaire> liste = frame.getAnnuaireCourant().rechercheRec(0,
																txtNom.getText(),
																txtPrenom.getText(),
																comboBox.getSelectedItem().toString(),
																annee,
																departement,
																new ArrayList<Stagiaire>());
			 
			AffichageAnnuaire affichage = (AffichageAnnuaire) frame.getContentPane().getComponent(0);
			affichage.getTable().setModel(new ModeleStagiaire(liste));
			affichage.getLblStagiaire().setText("Stagiaires : "+liste.size());
			frame.disposePopUp();
		}	
		
		
		if(e.getSource()==btnAnnuler){
			frame.disposePopUp();
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
}
