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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.Popup;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class AffichageAnnuaire extends JPanel implements ActionListener,MouseListener{
	
	private JTable table;
	private JTextField txtEntree = new JTextField();
	private JLabel lblEntree= new JLabel("");
	private JPanel panelOption = new JPanel();
	private JButton btnAjouter = new JButton("Ajouter");
	private JButton btnSupprimer = new JButton("Supprimer");
	private JButton btnEditer = new JButton("Editer");
	private JPanel panelRecherche = new JPanel();
	private JButton btnAfficherTout = new JButton("Afficher Tout");
	private JButton btnLister = new JButton("Lister");
	private JButton btnRechercher = new JButton("Rechercher");
	private JScrollPane scrollPane = new JScrollPane();
	private FenetrePrincipale frame;
	
	
	/**
	 * Create the panel.
	 */
	public AffichageAnnuaire(FenetrePrincipale frame) {
		this.frame = frame;
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		

		FlowLayout flowLayout = (FlowLayout) panelOption.getLayout();
		panelOption.setPreferredSize(new Dimension(100, 10));
		add(panelOption, BorderLayout.WEST);
		

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
		

		panelRecherche.add(btnLister);
		

		panelRecherche.add(btnRechercher);
		

		lblEntree.setIcon(new ImageIcon(AffichageAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/search_16.png")));
		panelRecherche.add(lblEntree);
		

		txtEntree.setName("Recherche");
		txtEntree.setPreferredSize(new Dimension(150, 25));
		panelRecherche.add(txtEntree);
		txtEntree.setColumns(10);
		

		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout(0, true, true, new ArrayList<Stagiaire>())));
		scrollPane.setViewportView(table);
		table.addMouseListener(this);

		btnRechercher.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnAfficherTout.addActionListener(this);
		btnEditer.addActionListener(this);
		btnSupprimer.addActionListener(this);
	}

	
	public AffichageAnnuaire() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		FlowLayout flowLayout = (FlowLayout) panelOption.getLayout();
		panelOption.setPreferredSize(new Dimension(100, 10));
		add(panelOption, BorderLayout.WEST);
		
		btnAjouter.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnAjouter);
		btnSupprimer.setEnabled(false);


		
		btnSupprimer.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnSupprimer);
		btnEditer.setEnabled(false);
		
		btnEditer.setPreferredSize(new Dimension(95, 30));
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
		
		panelRecherche.add(btnLister);
		
		panelRecherche.add(btnRechercher);
		
		lblEntree.setIcon(new ImageIcon(AffichageAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/search_16.png")));
		panelRecherche.add(lblEntree);
		
		txtEntree.setName("Recherche");
		txtEntree.setPreferredSize(new Dimension(150, 25));
		panelRecherche.add(txtEntree);
		txtEntree.setColumns(10);
		
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(new ModeleStagiaire());
		scrollPane.setViewportView(table);
		
		btnRechercher.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnAfficherTout.addActionListener(this);
		btnSupprimer.addActionListener(this);
		btnEditer.addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnRechercher) {
			String recherche = txtEntree.getText();
			if (!recherche.equals("")) {
				table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().rechercher(recherche, 0, new ArrayList<Stagiaire>())));
			} 
		}
		
		if (e.getSource() == btnAfficherTout) {
			table.setModel(new ModeleStagiaire(frame.getAnnuaireCourant().afficherTout(0, new ArrayList<Stagiaire>())));
		}
			
		if (e.getSource() == btnAjouter) {

			if(frame.getPopUp()==null){
				frame.setPopUp(new Ajouter(frame));
				frame.getPopUp().setSize(450, 350);
				frame.getPopUp().setVisible(true);
			}else{
				
				frame.getPopUp().toFront();
			}
		}
		
		if (e.getSource() == btnSupprimer) {
			
				
				int val = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer "+txtEntree.getText(), "confirmaion",JOptionPane.OK_CANCEL_OPTION);
				if(val==0){
					int indice = table.getSelectedRow();
					Noeud  unNoeud = new Noeud(table.getValueAt(indice, 0).toString(),table.getValueAt(indice, 1).toString(),table.getValueAt(indice, 4).toString(),table.getValueAt(indice, 2).toString(),Integer.parseInt(table.getValueAt(indice, 3).toString()));
					frame.getAnnuaireCourant().supprimer(unNoeud, 0);
				}
			e.setSource(btnAfficherTout);
			actionPerformed(e);
		}
		
		if (e.getSource() == btnEditer) {

			if(frame.getPopUp()==null){
				frame.setPopUp(new Editer(this));
				frame.getPopUp().setSize(450, 350);
				frame.getPopUp().setVisible(true);
			}else{

				frame.getPopUp().toFront();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		btnSupprimer.setEnabled(true);
		btnEditer.setEnabled(true);
	
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public JTable getTable() {
		return table;
	}
	public FenetrePrincipale getFrame() {
		return frame;
	}
}


