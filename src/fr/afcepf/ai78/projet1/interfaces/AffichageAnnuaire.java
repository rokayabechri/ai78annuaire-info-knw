package fr.afcepf.ai78.projet1.interfaces;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AffichageAnnuaire extends JPanel implements ActionListener,MouseListener,KeyListener{

	private static final long serialVersionUID	= 1L;
	private JScrollPane scrollPane 				= new JScrollPane();
	private JTextField txtEntree 				= new JTextField();
	private JLabel lblEntree					= new JLabel("");
	private JPanel panelOption 					= new JPanel();
	private JPanel panelRecherche 				= new JPanel();
	private JLabel lblStagiaire 				= new JLabel("Stagiaires : 0");
	private JButton btnAfficherTout 			= new JButton("Afficher Tout");
	private JButton btnRechercher 				= new JButton("Recherche avancée");
	private JButton btnAjouter 					= new JButton("Ajouter");
	private JButton btnSupprimer 				= new JButton("Supprimer");
	private JButton btnEditer 					= new JButton("Editer");
	private JTable table;
	private FenetrePrincipale frame;
	
	/**
	 * Create the panel.
	 */
	public AffichageAnnuaire(FenetrePrincipale frame) {
		this.frame = frame;
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		panelOption.setPreferredSize(new Dimension(100, 10));
		panelOption.setVisible(frame.isConnected());
		add(panelOption, BorderLayout.WEST);

		btnAjouter.setPreferredSize(new Dimension(95, 30));
		panelOption.add(btnAjouter);

		btnSupprimer.setPreferredSize(new Dimension(95, 30));
		btnSupprimer.setEnabled(false);
		panelOption.add(btnSupprimer);

		btnEditer.setPreferredSize(new Dimension(95, 30));
		btnEditer.setEnabled(false);
		panelOption.add(btnEditer);
		FlowLayout flowLayout = (FlowLayout) panelRecherche.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);

		panelRecherche.setPreferredSize(new Dimension(10, 35));
		add(panelRecherche, BorderLayout.NORTH);

		panelRecherche.add(btnAfficherTout);
		panelRecherche.add(btnRechercher);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 30));
		separator.setOrientation(SwingConstants.VERTICAL);
		panelRecherche.add(separator);

		lblEntree.setIcon(new ImageIcon(AffichageAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/search_icon.png")));
		panelRecherche.add(lblEntree);

		txtEntree.setName("Recherche");
		txtEntree.setPreferredSize(new Dimension(150, 25));
		txtEntree.setColumns(10);
		panelRecherche.add(txtEntree);

		add(scrollPane, BorderLayout.CENTER);

		List<Stagiaire> liste = frame.getAnnuaireCourant().afficherTout();
		table = new JTable(new ModeleStagiaire(liste));
		getLblStagiaire().setText("Stagiaire(s) : "+liste.size());
		table.addMouseListener(this);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		btnRechercher.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnAfficherTout.addActionListener(this);
		btnEditer.addActionListener(this);
		btnSupprimer.addActionListener(this);
		txtEntree.addKeyListener(this);
		table.addKeyListener(this);
		lblStagiaire.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStagiaire.setFont(new Font("Dialog", Font.BOLD, 13));
		
		add(lblStagiaire, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnRechercher) {
			if(frame.getPopUp()==null){
				frame.setPopUp(new RechercheAvancee(frame));
				frame.getPopUp().setLocationRelativeTo(frame);
				frame.getPopUp().setVisible(true);
			}else{
				if(frame.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.RechercheAvancee")){
					frame.getPopUp().toFront();
				}else{
					frame.disposePopUp();
					frame.setPopUp(new RechercheAvancee(frame));
					frame.getPopUp().setLocationRelativeTo(frame);
					frame.getPopUp().setVisible(true);
				}
			}
		}

		if (e.getSource() == btnAfficherTout) {
			afficherTout();
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
					frame.disposePopUp();
					frame.setPopUp(new Ajouter(frame));
					frame.getPopUp().setLocationRelativeTo(frame);
					frame.getPopUp().setVisible(true);
				}
			}
		}

		if (e.getSource() == btnSupprimer) {
			suppression();
		}

		if (e.getSource() == btnEditer) {
			int indice = table.getSelectedRow();
			Noeud  unNoeud = new Noeud(table.getValueAt(indice, 0).toString(),table.getValueAt(indice, 1).toString(),table.getValueAt(indice, 4).toString(),table.getValueAt(indice, 2).toString(),Integer.parseInt(table.getValueAt(indice, 3).toString()));

			if(frame.getPopUp()!=null){
				frame.disposePopUp();
			}
			frame.setPopUp(new Editer(frame,unNoeud));
			frame.getPopUp().setLocationRelativeTo(frame);
			frame.getPopUp().setVisible(true);
		}			
	}

	private void suppression() {
		int [] tabIndice =  table.getSelectedRows();
		File fichierASupprimer = new File(frame.getAnnuaireCourant().getFichierSortie());
		if(tabIndice.length==1)	{

			if(JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer "+table.getValueAt(tabIndice[0], 0)+" ?", "Confirmation",JOptionPane.OK_CANCEL_OPTION)==0){

				if((tabIndice.length==table.getRowCount())&& (((fichierASupprimer.length())/AnnuaireConstante.TAILLE_NOEUD)==(tabIndice.length+frame.getAnnuaireCourant().getFantome().size()))){
					frame.getAnnuaireCourant().getFantome().clear();
					fichierASupprimer.delete();

				}else{
					Noeud  unNoeud = new Noeud(table.getValueAt(tabIndice[0], 0).toString(),table.getValueAt(tabIndice[0], 1).toString(),table.getValueAt(tabIndice[0], 4).toString(),table.getValueAt(tabIndice[0], 2).toString(),Integer.parseInt(table.getValueAt(tabIndice[0], 3).toString()));
					frame.getAnnuaireCourant().supprimer(unNoeud, 0);
				}
			}

		}else{

			if(JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer la selection ?", "Confirmation",JOptionPane.OK_CANCEL_OPTION)==0){
				if((tabIndice.length==table.getRowCount())&&(((fichierASupprimer.length())/AnnuaireConstante.TAILLE_NOEUD)==(tabIndice.length+frame.getAnnuaireCourant().getFantome().size()))){				
					frame.getAnnuaireCourant().getFantome().clear();
					fichierASupprimer.delete();
				}else{
					for (int i : tabIndice) {
						Noeud  unNoeud = new Noeud(table.getValueAt(i, 0).toString(),table.getValueAt(i, 1).toString(),table.getValueAt(i, 4).toString(),table.getValueAt(i, 2).toString(),Integer.parseInt(table.getValueAt(i, 3).toString()));
						frame.getAnnuaireCourant().supprimer(unNoeud, 0);
					}

				}

			}
		}
		this.afficherTout();
	}

	private void afficherTout() {
		List<Stagiaire> liste = frame.getAnnuaireCourant().afficherTout();
		table.setModel(new ModeleStagiaire(liste));
		getLblStagiaire().setText("Stagiaire(s) : "+liste.size());
		btnSupprimer.setEnabled(false);
		btnEditer.setEnabled(false);
	}

	public FenetrePrincipale getFrame() {
		return frame;
	}

	public JTable getTable() {
		return table;
	}

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

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		btnSupprimer.setEnabled(true);
		btnEditer.setEnabled(true);	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DELETE && frame.isConnected()){
			suppression();
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		String recherche = txtEntree.getText();
		if (!recherche.equals("")) {
			List<Stagiaire> liste = frame.getAnnuaireCourant().rechercherDynamique(recherche, 0, new ArrayList<Stagiaire>());
			table.setModel(new ModeleStagiaire(liste));
			getLblStagiaire().setText("Stagiaire(s) : "+liste.size());
			btnSupprimer.setEnabled(false);
			btnEditer.setEnabled(false);
		}else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
			afficherTout();
		}
	}

	@Override	
	public void keyTyped(KeyEvent e) {}

	public JLabel getLblStagiaire() {
		return lblStagiaire;
	}



}