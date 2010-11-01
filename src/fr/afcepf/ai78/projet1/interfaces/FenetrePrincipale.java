package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class FenetrePrincipale extends JFrame implements ActionListener{



	private JPanel contentPane;
	private JButton btnOuvrirAnnuaire;
	private JButton btnNouvelAnnuaire;
	private JMenuItem mntmNouveau;
	private JMenuItem mntmOuvrir;
	private JPanel panelLancement;
	private JMenu mnFichier;
	private JMenuBar menuBar;
	private GestionBinaire annuaireCourant = new GestionBinaire();
	private JMenu mnEdition;
	private JMenuItem mntmSuppression;
	private JMenuItem mntmEdition;
	private JMenuItem mntmAjout;
	private JDialog popUp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 339);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmNouveau = new JMenuItem("Nouveau");
		mntmNouveau.setHorizontalAlignment(SwingConstants.LEFT);
		mnFichier.add(mntmNouveau);
		
		mntmOuvrir = new JMenuItem("Ouvrir");
		mnFichier.add(mntmOuvrir);
		
		mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);
		
		mntmAjout = new JMenuItem("Ajouter");
		mnEdition.add(mntmAjout);
		
		mntmSuppression = new JMenuItem("Supprimer");
		mnEdition.add(mntmSuppression);
		
		mntmEdition = new JMenuItem("Modifier");
		mnEdition.add(mntmEdition);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelLancement = new JPanel();
		contentPane.add(panelLancement);
		//contentPane.remove(panelLancement);
		panelLancement.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnNouvelAnnuaire = new JButton("Nouvel Annuaire");
		btnNouvelAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/nouveau_48.png")));
		btnNouvelAnnuaire.setPreferredSize(new Dimension(175, 175));
		panelLancement.add(btnNouvelAnnuaire);
		
		btnOuvrirAnnuaire = new JButton("Ouvrir Annuaire");
		btnOuvrirAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/ouvrir_48.png")));
		btnOuvrirAnnuaire.setPreferredSize(new Dimension(175, 175));
		panelLancement.add(btnOuvrirAnnuaire);
		
		btnOuvrirAnnuaire.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOuvrirAnnuaire){
			JFileChooser fc = new JFileChooser("C:/");
			int returnVal = fc.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       String chemin = fc.getSelectedFile().toString();
		       String nomFichier = fc.getSelectedFile().getName();
		       
		       nomFichier = nomFichier.substring(0, nomFichier.indexOf("."));
		       if(annuaireCourant.creationArbreBinaire(chemin, "c:/"+nomFichier+".bin")){
		    	   //contentPane.remove(panelLancement);
		    	   JOptionPane.showMessageDialog(this, "Création OK");
		    	   contentPane.remove(panelLancement);
			       contentPane.add(new AffichageAnnuaire(this),BorderLayout.CENTER);
			       contentPane.revalidate();
		       }else{
		    	   JOptionPane.showMessageDialog(null, "Fichier incorrect");
		       }
		    }

		}
		
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public GestionBinaire getAnnuaireCourant() {
		return annuaireCourant;
	}
	
	public JDialog getPopUp() {
		return popUp;
	}

	public void setPopUp(JDialog popUp) {
		this.popUp = popUp;
	}
}
