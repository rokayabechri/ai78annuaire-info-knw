package fr.afcepf.ai78.projet1.interfaces;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;


public class AffichageAnnuaire extends JPanel implements ActionListener,MouseListener,KeyListener, FocusListener{

	private JTable table;
	private JScrollPane scrollPane = new JScrollPane();
	private JTextField txtEntree = new JTextField();
	private JLabel lblEntree= new JLabel("");
	private JPanel panelOption = new JPanel();
	private JPanel panelRecherche = new JPanel();
	private JButton btnAfficherTout = new JButton("Afficher Tout");
	private JButton btnRechercher = new JButton("Recherche avancée");
	private JButton btnAjouter = new JButton("Ajouter");
	private JButton btnSupprimer = new JButton("Supprimer");
	private JButton btnEditer = new JButton("Editer");
	private FenetrePrincipale frame;

	/**
	 * Create the panel.
	 */
	public AffichageAnnuaire(FenetrePrincipale frame) {

		this.frame = frame;
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		panelOption.setPreferredSize(new Dimension(100, 10));
		add(panelOption, BorderLayout.WEST);
		panelOption.setVisible(frame.isConnected());

		btnAjouter.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnAjouter);

		btnSupprimer.setPreferredSize(new Dimension(95, 30));
		btnSupprimer.setEnabled(false);
		panelOption.add(btnSupprimer);

		btnEditer.setPreferredSize(new Dimension(95, 30));
		btnEditer.setEnabled(false);
		panelOption.add(btnEditer);

		FlowLayout fl_panelRecherche = (FlowLayout) panelRecherche.getLayout();
		fl_panelRecherche.setAlignment(FlowLayout.RIGHT);
		panelRecherche.setPreferredSize(new Dimension(10, 35));
		add(panelRecherche, BorderLayout.NORTH);

		panelRecherche.add(btnAfficherTout);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 30));
		separator.setOrientation(SwingConstants.VERTICAL);
		panelRecherche.add(separator);

		panelRecherche.add(btnRechercher);

		lblEntree.setIcon(new ImageIcon(AffichageAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/search_icon.png")));
		panelRecherche.add(lblEntree);

		txtEntree.setName("Recherche");
		txtEntree.setPreferredSize(new Dimension(150, 25));
		panelRecherche.add(txtEntree);
		txtEntree.setColumns(10);

		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));
		scrollPane.setViewportView(table);
		table.addMouseListener(this);

		btnRechercher.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnAfficherTout.addActionListener(this);
		btnEditer.addActionListener(this);
		btnSupprimer.addActionListener(this);
		txtEntree.addKeyListener(this);
		table.addFocusListener(this);
		table.addKeyListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnRechercher) {
			if(frame.getPopUp()==null){
				frame.setPopUp(new RechercheAvancee(this));

				frame.getPopUp().setLocationRelativeTo(frame);
				frame.getPopUp().setVisible(true);

			}else{
				if(frame.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.RechercheAvancee")){
					frame.getPopUp().toFront();
				}else{
					frame.getPopUp().dispose();
					frame.setPopUp(new RechercheAvancee(this));
					frame.getPopUp().setLocationRelativeTo(frame);
					frame.getPopUp().setVisible(true);
				}
			}
		}

		if (e.getSource() == btnAfficherTout) {
			table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));
			btnSupprimer.setEnabled(false);
			btnEditer.setEnabled(false);
		}

		if (e.getSource() == btnAjouter) {

			if(frame.getPopUp()==null){
				frame.setPopUp(new Ajouter(frame));
				frame.getPopUp().setLocationRelativeTo(frame);
				frame.getPopUp().setVisible(true);
			}else{
				if(frame.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.Ajouter")){
					frame.getPopUp().toFront();
				}else{
					frame.getPopUp().dispose();
					frame.setPopUp(new Ajouter(frame));
					frame.getPopUp().setLocationRelativeTo(frame);
					frame.getPopUp().setVisible(true);
				}
			}
		}

		if (e.getSource() == btnSupprimer) {


			int indice = table.getSelectedRow();
			int val = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer "+table.getValueAt(indice, 0), "Confirmation",JOptionPane.OK_CANCEL_OPTION);
			if(val==0){

				Noeud  unNoeud = new Noeud(table.getValueAt(indice, 0).toString(),table.getValueAt(indice, 1).toString(),table.getValueAt(indice, 4).toString(),table.getValueAt(indice, 2).toString(),Integer.parseInt(table.getValueAt(indice, 3).toString()));
				frame.getAnnuaireCourant().supprimer(unNoeud, 0);

			}
			e.setSource(btnAfficherTout);
			actionPerformed(e);
		}

		if (e.getSource() == btnEditer) {
			int indice = table.getSelectedRow();
			Noeud  unNoeud = new Noeud(table.getValueAt(indice, 0).toString(),table.getValueAt(indice, 1).toString(),table.getValueAt(indice, 4).toString(),table.getValueAt(indice, 2).toString(),Integer.parseInt(table.getValueAt(indice, 3).toString()));

			if(frame.getPopUp()==null){

				frame.setPopUp(new Editer(this,unNoeud));
				frame.getPopUp().setLocationRelativeTo(frame);
				frame.getPopUp().setVisible(true);

			}else{
				frame.getPopUp().dispose();
				frame.setPopUp(new Editer(this,unNoeud));
				frame.getPopUp().setLocationRelativeTo(frame);
				frame.getPopUp().setVisible(true);
			}
		}			
	}

	public FenetrePrincipale getFrame() {
		return frame;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		btnSupprimer.setEnabled(true);
		btnEditer.setEnabled(true);	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	public JTable getTable() {
		return table;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==127 && frame.isConnected()){
			
			int indice = table.getSelectedRow();
			int val = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer "+table.getValueAt(indice, 0), "Confirmation",JOptionPane.OK_CANCEL_OPTION);
			if(val==0){

				Noeud  unNoeud = new Noeud(table.getValueAt(indice, 0).toString(),table.getValueAt(indice, 1).toString(),table.getValueAt(indice, 4).toString(),table.getValueAt(indice, 2).toString(),Integer.parseInt(table.getValueAt(indice, 3).toString()));
				frame.getAnnuaireCourant().supprimer(unNoeud, 0);

			}
			AffichageAnnuaire affichage = (AffichageAnnuaire)frame.getContentPane().getComponent(0);
			affichage.getTable().setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));

		}		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==10){
			String recherche = txtEntree.getText();

			if (!recherche.equals("")) {
				table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().rechercher(recherche, 0, new ArrayList<Stagiaire>())));
				btnSupprimer.setEnabled(false);
				btnEditer.setEnabled(false);
			}else{
				table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));
				btnSupprimer.setEnabled(false);
				btnEditer.setEnabled(false);
			}
		}else{
			String recherche = txtEntree.getText();
			if (!recherche.equals("")) {
				table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().rechercherDynamique(recherche, 0, new ArrayList<Stagiaire>())));
				btnSupprimer.setEnabled(false);
				btnEditer.setEnabled(false);
			}else{
				if(e.getKeyCode()==8){	
					table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout()));
					btnSupprimer.setEnabled(false);
					btnEditer.setEnabled(false);
				}
				
			}
		}
		
	}
		
	@Override	
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {}

	public JButton getBtnAjouter() {
		return btnAjouter;
	}

	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	public JButton getBtnEditer() {
		return btnEditer;
	}

	public JPanel getPanelOption() {
		return panelOption;
	}
	
}


