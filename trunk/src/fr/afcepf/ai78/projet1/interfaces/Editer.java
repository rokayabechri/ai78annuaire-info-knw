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

public class Editer extends JDialog implements ActionListener,WindowListener{
	private JButton btnAnnuler;
	private JLabel lblAnne;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAnne_1;
	private JLabel lblPromotion;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtDepartement;
	private JComboBox cBPromotion;
	private JTextField txtAnnee;
	private JButton btnValider;
	private AffichageAnnuaire parent;
	private Noeud unNoeudAModifier;

	/**
	 * Create the panel.
	 */
	public Editer(AffichageAnnuaire parent,Noeud unNoeudAModifier) {
		this.parent = parent;
		this.unNoeudAModifier = unNoeudAModifier;
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
		
		txtNom = new JTextField(parent.getTable().getValueAt(parent.getTable().getSelectedRow(), 0).toString());
		getContentPane().add(txtNom, "4, 2, fill, default");
		txtNom.setColumns(10);
		
		lblAnne = new JLabel("Prénom :");
		lblAnne.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblAnne, "2, 4, right, default");
		
		txtPrenom = new JTextField(parent.getTable().getValueAt(parent.getTable().getSelectedRow(), 1).toString());
		txtPrenom.setColumns(10);
		getContentPane().add(txtPrenom, "4, 4, fill, default");
		
		lblPromotion = new JLabel("Promotion :");
		lblPromotion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPromotion, "2, 6, right, default");
		
		cBPromotion = new JComboBox();
		cBPromotion.setMinimumSize(new Dimension(12, 26));
		getContentPane().add(cBPromotion, "4, 6");
		
		for (String string : parent.getFrame().getAnnuaireCourant().getPromo()) {	
			cBPromotion.addItem(string);
		}
		cBPromotion.setSelectedItem(parent.getTable().getValueAt(parent.getTable().getSelectedRow(),2).toString());
		
		lblPrnom = new JLabel("Année :");
		getContentPane().add(lblPrnom, "2, 8, right, default");
		
		txtAnnee = new JTextField(parent.getTable().getValueAt(parent.getTable().getSelectedRow(), 3).toString());
		getContentPane().add(txtAnnee, "4, 8, left, default");
		txtAnnee.setColumns(10);
		
		btnValider = new JButton("Valider");
		getContentPane().add(btnValider, "8, 8");
		
		lblAnne_1 = new JLabel("Département :");
		lblAnne_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblAnne_1, "2, 10, right, default");
		
		txtDepartement = new JTextField(parent.getTable().getValueAt(parent.getTable().getSelectedRow(), 4).toString());
		getContentPane().add(txtDepartement, "4, 10, left, default");
		txtDepartement.setColumns(10);
		
		btnAnnuler = new JButton("Annuler");
		getContentPane().add(btnAnnuler, "8, 10");
		
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		addWindowListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnValider){

			String nom = txtNom.getText();
			String prenom = txtPrenom.getText();
			String promotion = cBPromotion.getSelectedItem().toString();
			String departement = txtDepartement.getText();
			String annee = txtAnnee.getText();

			if(!nom.equals("")&&!prenom.equals("")&&!promotion.equals("")&&!departement.equals("")&&!annee.equals("")){
				try {	
					Noeud unNoeud = new Noeud(nom,prenom,departement,promotion,Integer.parseInt(annee));
					parent.getFrame().getAnnuaireCourant().supprimer(unNoeudAModifier, 0);
					parent.getFrame().getAnnuaireCourant().ajoutElementArbreBinaire(unNoeud,-1,0,false,parent.getFrame().getAnnuaireCourant().getPositionAjout());



					parent.getFrame().getAnnuaireCourant().ecrireNoeud(parent.getFrame().getAnnuaireCourant().getPositionAjout(),unNoeud);
					this.dispose();
					parent.getFrame().setPopUp(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		if(e.getSource()==btnAnnuler){
			this.dispose();
			parent.getFrame().setPopUp(null);
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

			
}
