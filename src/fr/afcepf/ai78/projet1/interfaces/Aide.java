package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import fr.afcepf.ai78.projet1.constante.AideConstante;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Aide extends JFrame implements TreeSelectionListener , WindowListener{

	private static final long serialVersionUID = 1L;
	private static boolean isInstancie = false;
	private static Aide instance;
	private JPanel contentPane 			= new JPanel();
	private JLabel txtAfficheur 		= new JLabel();
	private JScrollPane scrollPaneTree 	= new JScrollPane();

	public static Aide getInstance(){
		if(isInstancie){
			return instance;
		}else{
			return new Aide();
		}
	}

	private Aide() {
		super("Gestion d'annuaire : Aide");
		isInstancie = true;
		instance = this;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Aide.class.getResource("/fr/afcepf/ai78/projet1/images/aide_icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);
		setSize(900, 350);

		Object[] hierarchy =
		{
				"Gestion d'annuaire:Aide",
				new Object[] { 	"Gestion d'annuaire",
								"Pr�sentation",
								"Installation et d�sinstallation de 'Gestion d'annuaire'"},
				new Object[] {	"Gestion et cr�ation des 'Fichiers' annuaires",
								"'Cr�ation' d'un nouvelle annuaire",
								"'Ouvrir' un annuaire",
								"'Imprimer' un annuaire",
								"'Quitter' l'application",
								"'Connexion' � l'application"},
				new Object[] {	"Fen�tre Gestion d'annuaire",
								"Recherche par nom de stagiaire (recherche par d�faut)",
								new Object[] { 	"Les boutons d'actions",
												"Le bouton 'Recherche avanc�e'",
												"Le bouton 'Afficher Tout'", 
												"Le bouton 'Ajouter'", 
												"Le bouton 'Supprimer'", 
												"Le bouton 'Editer'"}},
				new Object[] {	"Fen�tre Ajouter un stagiaire",
								new Object[] { 	"L'Ajout",
												"Renseigner le champs 'Nom'",
												"Renseigner le champs 'Pr�nom'",
								new Object[] { 	"Renseigner le champs 'Promotion'",
												"'Choisir' une promotion",
												"'Cr�er' une nouvelle promotion" },
												"Renseigner le champs 'Ann�e'",
												"Renseigner le champs 'Departement'"},
												"'Sauvegarder' et 'Annuler' son op�ration" },
				new Object[] {	"Fen�tre Editer un stagiaire",
								new Object[] { 	"L'Edition",
												"Editer le 'Nom'",
												"Editer le 'Pr�nom'",
								new Object[] { 	"Editer le champs 'Promotion'",
												"'Choisir' une promotion",
												"'Cr�er' une nouvelle promotion" },
												"Editer le 'Ann�e'",
												"Editer le 'Departement'"},
												"'Valider' et 'Annuler' son op�ration" },
				new Object[] {	"Fen�tre Recherche avanc�e",
								new Object[] { 	"La Recherche avanc�e",
												"Recherche avanc�e sur le 'Nom'",
												"Recherche avanc�e sur le 'Pr�nom'",
												"Recherche avanc�e sur le 'Promotion'",
												"Recherche avanc�e sur le 'Ann�e'",
												"Recherche avanc�e sur le 'Departement'"},
												"'Rechercher' et 'Annuler' son op�ration" },
				new Object[] {	"Suppresion de stagiaire",
								"Suppresion de stagiaires",},};

		DefaultMutableTreeNode root = processHierarchy(hierarchy);
		JTree tree = new JTree(root);
		tree.addTreeSelectionListener(this);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(scrollPaneTree,BorderLayout.WEST);
		scrollPaneTree.setViewportView(tree);
		txtAfficheur.setBorder(new EmptyBorder(15, 15, 15, 15));
		txtAfficheur.setFont(new Font("Tahoma", Font.PLAIN, 11));

		contentPane.add(txtAfficheur, BorderLayout.CENTER);
		txtAfficheur.setVerticalAlignment(SwingConstants.TOP);
	}


	private DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);

		DefaultMutableTreeNode child;
		for(int i=1; i<hierarchy.length; i++) {
			Object nodeSpecifier = hierarchy[i];
			if (nodeSpecifier instanceof Object[])  
				child = processHierarchy((Object[])nodeSpecifier);
			else
				child = new DefaultMutableTreeNode(nodeSpecifier); 
			node.add(child);
		}
		return(node);
	}

	@Override
	public void valueChanged(TreeSelectionEvent node) {
		//System.out.println(Aide.class.getProtectionDomain().getCodeSource().getLocation());
		txtAfficheur.setText(node.getPath().getLastPathComponent().toString());
		if (node.getPath().getLastPathComponent().toString().equals("Pr�sentation")) {txtAfficheur.setText(AideConstante.Presentation);}
		if (node.getPath().getLastPathComponent().toString().equals("Installation et d�sinstallation de 'Gestion d'annuaire'")) {txtAfficheur.setText(AideConstante.InstallationEtDesinstallationDeGestionDannuaire);}
		if (node.getPath().getLastPathComponent().toString().equals("'Cr�ation' d'un nouvelle annuaire")) {txtAfficheur.setText(AideConstante.CreationDunNouvelleAnnuaire);}
		if (node.getPath().getLastPathComponent().toString().equals("'Ouvrir' un annuaire")) {txtAfficheur.setText(AideConstante.OuvrirUnAnnuaire);}
		if (node.getPath().getLastPathComponent().toString().equals("'Imprimer' un annuaire")) {txtAfficheur.setText(AideConstante.ImprimerUnAnnuaire);}
		if (node.getPath().getLastPathComponent().toString().equals("'Quitter' l'application")) {txtAfficheur.setText(AideConstante.QuitterLApplication);}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche par nom de stagiaire (recherche par d�faut)")) {txtAfficheur.setText(AideConstante.RechercheParNomDeStagiaireRechercheParDefaut);}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Recherche avanc�e'")) {txtAfficheur.setText(AideConstante.LeBoutonRechercheAvancee);}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Afficher Tout'")) {txtAfficheur.setText(AideConstante.LeBoutonAfficherTout);}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Ajouter'")) {txtAfficheur.setText(AideConstante.LeBoutonAjouter);}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Supprimer'")) {txtAfficheur.setText(AideConstante.LeBoutonSupprimer);}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Editer'")) {txtAfficheur.setText(AideConstante.LeBoutonEditer);}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Nom'")) {txtAfficheur.setText(AideConstante.RenseignerLeChampsNom);}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Pr�nom'")) {txtAfficheur.setText(AideConstante.RenseignerLeChampsPrenom);}
		if (node.getPath().getLastPathComponent().toString().equals("'Choisir' une promotion")) {txtAfficheur.setText(AideConstante.ChoisirUnePromotion);}
		if (node.getPath().getLastPathComponent().toString().equals("'Cr�er' une nouvelle promotion")) {txtAfficheur.setText(AideConstante.CreerUneNouvellePromotion);}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Ann�e'")) {txtAfficheur.setText(AideConstante.RenseignerLeChampsAnnee);}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Departement'")) {txtAfficheur.setText(AideConstante.RenseignerLeChampsDepartement);}
		if (node.getPath().getLastPathComponent().toString().equals("'Sauvegarder' et 'Annuler' son op�ration")) {txtAfficheur.setText(AideConstante.SauvegarderEtAnnulerSonOperation);}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Nom'")) {txtAfficheur.setText(AideConstante.EditerLeNom);}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Pr�nom'")) {txtAfficheur.setText(AideConstante.EditerLePrenom);}
		if (node.getPath().getLastPathComponent().toString().equals("'Choisir' une promotion")) {txtAfficheur.setText(AideConstante.ChoisirUnePromotions);}
		if (node.getPath().getLastPathComponent().toString().equals("'Cr�er' une nouvelle promotion")) {txtAfficheur.setText(AideConstante.CreerUneNouvellePromotions);}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Ann�e'")) {txtAfficheur.setText(AideConstante.EditerLeAnnee);}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Departement'")) {txtAfficheur.setText(AideConstante.EditerLeDepartement);}
		if (node.getPath().getLastPathComponent().toString().equals("'Valider' et 'Annuler' son op�ration")) {txtAfficheur.setText(AideConstante.ValiderEtAnnulerSonOperation);}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Nom'")) {txtAfficheur.setText(AideConstante.RechercheAvanceeSurLeNom);}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Pr�nom'")) {txtAfficheur.setText(AideConstante.RechercheAvanceeSurLePrenom);}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Promotion'")) {txtAfficheur.setText(AideConstante.RechercheAvanceeSurLePromotion);}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Ann�e'")) {txtAfficheur.setText(AideConstante.RechercheAvanceeSurLeAnnee);}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Departement'")) {txtAfficheur.setText(AideConstante.RechercheAvanceeSurLeDepartement);}
		if (node.getPath().getLastPathComponent().toString().equals("'Rechercher' et 'Annuler' son op�ration")) {txtAfficheur.setText(AideConstante.RechercherEtAnnulerSonOperation);}
		if (node.getPath().getLastPathComponent().toString().equals("Suppresion de stagiaires")) {txtAfficheur.setText(AideConstante.SuppresionDeStagiaires);}
		if (node.getPath().getLastPathComponent().toString().equals("'Connexion' � l'application")) {txtAfficheur.setText(AideConstante.ConnexionALAapplication);}


		if (node.getPath().getLastPathComponent().toString().equals("Suppresion de stagiaire")) {txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideSuppresionDeStagiaire.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Suppresion de stagiaires")) {txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideSuppresionDeStagiaires.png")));}
		
		if (node.getPath().getLastPathComponent().toString().equals("Editer le champs 'Promotion'")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
		
		if (node.getPath().getLastPathComponent().toString().equals("La Recherche avanc�e")) 					{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvancee.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Fen�tre Recherche avanc�e")) 				{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvancee.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Nom'")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvanceeSurLeNom.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Pr�nom'")) 		{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvanceeSurLePrenom.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Promotion'")) 	{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvanceeSurLePromotion.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Ann�e'")) 		{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvanceeSurLeAnnee.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Recherche avanc�e sur le 'Departement'")) 	{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvanceeSurLeDepartement.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Rechercher' et 'Annuler' son op�ration")) {txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AdieRechercherEtAnnulerSonOperation.png")));}
	
		
		if (node.getPath().getLastPathComponent().toString().equals("L'Edition")) 								{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideEdition.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Nom'")) 						{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideEditerNom.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Pr�nom'")) 						{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideEditerPrenom.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Choisir' une promotion")) 				{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideChoisirUnePromotions.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Cr�er' une nouvelle promotion")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideCreerUneNouvellePromotions.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Ann�e'")) 						{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideEditerAnnee.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Editer le 'Departement'")) 				{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideEditerDepartement.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Valider' et 'Annuler' son op�ration")) 	{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideValiderEtAnnulerSonOperation.png")));}
		
		
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Nom'"))				{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAjouterNom.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Pr�nom'")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAjouterPrenom.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Choisir' une promotion")) 				{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAjouterPromotion.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Cr�er' une nouvelle promotion")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideCreerUneNouvellePromotions.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Ann�e'")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAjouterAnnee.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Renseigner le champs 'Departement'")) 		{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAjouterDepartement.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Sauvegarder' et 'Annuler' son op�ration")){txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideValiderEtAnnulerSonOperation.png")));}
		

		if (node.getPath().getLastPathComponent().toString().equals("Recherche par nom de stagiaire (recherche par d�faut)")) 	{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheParNomDeStagiaireRechercheParDefaut.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Recherche avanc�e'")) 	{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideRechercheAvancee.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Afficher Tout'")) 		{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAfficherTout.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Ajouter'")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideAjouter.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Supprimer'")) 			{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideSuppresionDeStagiaire.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Le bouton 'Editer'")) 				{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AideEdition.png")));}
		
		if (node.getPath().getLastPathComponent().toString().equals("Pr�sentation")) 											{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("Installation et d�sinstallation de 'Gestion d'annuaire'")) {txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Cr�ation' d'un nouvelle annuaire")) 						{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Ouvrir' un annuaire")) 									{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Imprimer' un annuaire")) 									{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
		if (node.getPath().getLastPathComponent().toString().equals("'Quitter' l'application")) 								{txtAfficheur.setIcon(new ImageIcon(Ajouter.class.getResource("/fr/afcepf/ai78/projet1/images/aide/AidePointVide.png")));}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		Aide.isInstancie = false ;
	}

	@Override
	public void windowClosing(WindowEvent e) {	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {	
	}

	@Override
	public void windowDeiconified(WindowEvent e) {	
	}

	@Override
	public void windowIconified(WindowEvent e) {	
	}

	@Override
	public void windowOpened(WindowEvent e) {	
	}
}