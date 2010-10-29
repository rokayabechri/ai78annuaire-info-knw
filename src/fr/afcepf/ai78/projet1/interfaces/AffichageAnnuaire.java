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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class AffichageAnnuaire extends JPanel implements ActionListener{
	
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
	private GestionBinaire annuaire = new GestionBinaire();
	
	
	
	/**
	 * Create the panel.
	 */
	public AffichageAnnuaire(GestionBinaire annuaire) {
		this.annuaire=annuaire;
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		

		FlowLayout flowLayout = (FlowLayout) panelOption.getLayout();
		panelOption.setPreferredSize(new Dimension(100, 10));
		add(panelOption, BorderLayout.WEST);
		

		btnAjouter.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnAjouter);
		

		btnSupprimer.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnSupprimer);
		

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
		
		table = new JTable(new ModeleStagiaire(annuaire.afficherTout(0, true, true, new ArrayList<Stagiaire>())));
		scrollPane.setViewportView(table);

		btnRechercher.addActionListener(this);
	}

	
	public AffichageAnnuaire() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		FlowLayout flowLayout = (FlowLayout) panelOption.getLayout();
		panelOption.setPreferredSize(new Dimension(100, 10));
		add(panelOption, BorderLayout.WEST);
		
		btnAjouter.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnAjouter);
		
		btnSupprimer.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnSupprimer);
		
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

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRechercher) {
			String recherche = txtEntree.getText();
			if (!recherche.equals("")) {
				table = new JTable(new ModeleStagiaire(annuaire.rechercher(recherche, 0, new ArrayList<Stagiaire>())));
				scrollPane.setViewportView(table);
			} 
		}
		
		
	}


}
