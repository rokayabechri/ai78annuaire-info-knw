package fr.afcepf.ai78.projet1.interfaces;


import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

import javax.swing.JComboBox;

public class RechercheAvancee extends JDialog implements ActionListener,WindowListener{
	
	private JButton btnAnnuler;
	private JButton btnRechercherAv;
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
	private JTextField textPromotion;
	private AffichageAnnuaire parent;

	/**
	 * Create the panel.
	 */
	public RechercheAvancee(AffichageAnnuaire parent) {
		setResizable(false);
//		setPreferredSize(new Dimension(480, 310));	
		setBounds(100, 100, 500, 300);
		this.parent = parent;
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("80dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("80dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("109px"),},
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
		comboBox.removeAllItems();
		comboBox.addItem("");
		for (String string : parent.getFrame().getAnnuaireCourant().getPromo()) {
			
			comboBox.addItem(string);
		}
		comboBox.addItem("autre");
		
		textPromotion = new JTextField();
		textPromotion.setVisible(false);
		getContentPane().add(textPromotion, "6, 6");
		textPromotion.setColumns(10);
	
		
		lblPrnom = new JLabel("Année :");
		getContentPane().add(lblPrnom, "2, 8, right, default");
		
		textAnnee = new JTextField();
		getContentPane().add(textAnnee, "4, 8, left, default");
		textAnnee.setColumns(10);
		
		btnRechercherAv = new JButton("Rechercher");
		getContentPane().add(btnRechercherAv, "6, 8");
		
		
		btnRechercherAv.addActionListener(this);
		
		lblAnne_1 = new JLabel("Département :");
		lblAnne_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblAnne_1, "2, 10, right, default");
		
		textDepartement = new JTextField();
		getContentPane().add(textDepartement, "4, 10, left, default");
		textDepartement.setColumns(10);
		
		btnAnnuler = new JButton("Annuler");
		getContentPane().add(btnAnnuler, "6, 10");
		btnAnnuler.addActionListener(this);
		comboBox.addActionListener(this);
		this.addWindowListener(this);
		
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnRechercherAv){
			
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
			int annee = -1;
			try {
				annee = Integer.parseInt(textAnnee.getText());
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
			}
			List<Stagiaire> liste = new ArrayList<Stagiaire>() ;
			liste = parent.getFrame().getAnnuaireCourant().rechercheRec(0, nom, prenom, promotion, annee, departement, liste);
			parent.getTable().setModel(new ModeleStagiaire(liste));
			this.dispose();
			parent.getFrame().setPopUp(null);
		}	
		
		
		if(e.getSource()==btnAnnuler){

			this.dispose();
			parent.getFrame().setPopUp(null);
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

}
