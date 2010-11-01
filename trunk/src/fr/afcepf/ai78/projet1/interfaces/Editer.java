package fr.afcepf.ai78.projet1.interfaces;


import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import com.swtdesigner.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
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

	/**
	 * Create the panel.
	 */
	public Editer(AffichageAnnuaire parent) {
		this.parent = parent;
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

			
}
