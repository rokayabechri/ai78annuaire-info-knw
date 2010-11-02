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

public class Ajouter extends JDialog implements ActionListener,WindowListener{
	private JButton btnAnnuler;
	private JButton btnSauvegarder;
	private JLabel lblAnne;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAnne_1;
	private JLabel lblPromotion;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textDepartement;
	private JComboBox comboBox;
	private JTextField textAnnee;
	private FenetrePrincipale frame;
	/**
	 * Create the panel.
	 */
	public Ajouter(FenetrePrincipale frame) {
		this.frame = frame;
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("95px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(82dlu;pref)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(35dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),}));
		
		lblNom = new JLabel("Nom :");
		getContentPane().add(lblNom, "2, 2, right, center");
		
		textNom = new JTextField();
		getContentPane().add(textNom, "4, 2, fill, default");
		textNom.setColumns(10);
		
		lblAnne = new JLabel("Prénom :");
		lblAnne.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblAnne, "2, 4, right, default");
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		getContentPane().add(textPrenom, "4, 4, fill, default");
		
		lblPromotion = new JLabel("Promotion :");
		lblPromotion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPromotion, "2, 6, right, default");
		
		comboBox = new JComboBox();
		comboBox.setMinimumSize(new Dimension(12, 26));
		getContentPane().add(comboBox, "4, 6");
		
		for (String string : frame.getAnnuaireCourant().getPromo()) {
			
			comboBox.addItem(string);
		}

		lblPrnom = new JLabel("Année :");
		getContentPane().add(lblPrnom, "2, 8, right, default");
		
		textAnnee = new JTextField();
		getContentPane().add(textAnnee, "4, 8, left, default");
		textAnnee.setColumns(10);
		
		btnSauvegarder = new JButton("Sauvegarder");
		getContentPane().add(btnSauvegarder, "8, 8");
		
		lblAnne_1 = new JLabel("Département :");
		lblAnne_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblAnne_1, "2, 10, right, default");
		
		textDepartement = new JTextField();
		getContentPane().add(textDepartement, "4, 10, left, default");
		textDepartement.setColumns(10);
		
		btnAnnuler = new JButton("Annuler");
		getContentPane().add(btnAnnuler, "8, 10");
		
		
		btnSauvegarder.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);
		
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnSauvegarder){

			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			String promotion = comboBox.getSelectedItem().toString();
			String departement = textDepartement.getText();
			String annee = textAnnee.getText();

			if(!nom.equals("")&&!prenom.equals("")&&!promotion.equals("")&&!departement.equals("")&&!annee.equals("")){

				Noeud unNoeud = new Noeud(nom,prenom,departement,promotion,Integer.parseInt(annee));
				frame.getAnnuaireCourant().ajoutElementArbreBinaire(unNoeud,-1,0,false);

				try {
					frame.getAnnuaireCourant().ecrireNoeud(frame.getAnnuaireCourant().getPositionAjout(),unNoeud);
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
