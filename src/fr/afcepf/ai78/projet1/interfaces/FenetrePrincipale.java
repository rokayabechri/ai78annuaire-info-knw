package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.PrintJob;

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
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;

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
	private JMenu menuAide;
	private JMenuItem mntmImprimer;
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setLocationRelativeTo(null);
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
		setBounds(100, 100, 550, 400);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmNouveau = new JMenuItem("Nouveau");
		mntmNouveau.setHorizontalAlignment(SwingConstants.LEFT);
		mnFichier.add(mntmNouveau);
		
		mntmOuvrir = new JMenuItem("Ouvrir");
		mnFichier.add(mntmOuvrir);
		
		mntmImprimer = new JMenuItem("Imprimer");
		mnFichier.add(mntmImprimer);
		
		mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);
		
		mntmAjout = new JMenuItem("Ajouter");
		mnEdition.add(mntmAjout);
		
		mntmSuppression = new JMenuItem("Supprimer");
		mnEdition.add(mntmSuppression);
		
		mntmEdition = new JMenuItem("Modifier");
		mnEdition.add(mntmEdition);
		
		menuAide = new JMenu("?");
		menuBar.add(menuAide);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelLancement = new JPanel();
		contentPane.add(panelLancement);
		panelLancement.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnNouvelAnnuaire = new JButton("Nouvel Annuaire");
		btnNouvelAnnuaire.setSelectedIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_nouveau_over.png")));
		btnNouvelAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_nouveau.png")));
		btnNouvelAnnuaire.setPreferredSize(new Dimension(250, 300));
		panelLancement.add(btnNouvelAnnuaire);
		
		btnOuvrirAnnuaire = new JButton("Ouvrir Annuaire");
		btnOuvrirAnnuaire.setSelectedIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_ouvrir_over.png")));
		btnOuvrirAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_ouvrir.png")));
		btnOuvrirAnnuaire.setPreferredSize(new Dimension(250, 300));
		panelLancement.add(btnOuvrirAnnuaire);
		
		btnOuvrirAnnuaire.addActionListener(this);//ok
		btnNouvelAnnuaire.addActionListener(this);
		
		mntmNouveau.addActionListener(this);
		mntmOuvrir.addActionListener(this);//ok
		
		mntmAjout.addActionListener(this);
		mntmSuppression.addActionListener(this);
		mntmEdition.addActionListener(this);
		menuAide.addActionListener(this);
		mntmImprimer.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOuvrirAnnuaire || e.getSource() == mntmOuvrir){
			JFileChooser fc = new JFileChooser("C:/");
			int returnVal = fc.showOpenDialog(this);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       String chemin = fc.getSelectedFile().toString();
		       String nomFichier = fc.getSelectedFile().getName();
		       
		       nomFichier = nomFichier.substring(0, nomFichier.indexOf("."));
		       if(annuaireCourant.creationArbreBinaire(chemin, "c:/"+nomFichier+".bin")){
		    	   JOptionPane.showMessageDialog(this, "Création OK");
		    	   contentPane.remove(panelLancement);
			       contentPane.add(new AffichageAnnuaire(this),BorderLayout.CENTER);
			       contentPane.revalidate();
		       }else{
		    	   JOptionPane.showMessageDialog(this, "Fichier incorrect");
		       }
		    }
		}
	
		if (e.getSource() == btnNouvelAnnuaire || e.getSource() == mntmNouveau) {

			String nomFichier = JOptionPane.showInputDialog(this, "saisir nom du nouvel annuaire");

			if(!nomFichier.equals("")){
				try {			
					String FichierSortie =  "c:/"+nomFichier+".bin";
					annuaireCourant.setFichier(new RandomAccessFile(FichierSortie, "rw"));
			    	contentPane.remove(panelLancement);
				    contentPane.add(new AffichageAnnuaire(this),BorderLayout.CENTER);
				    contentPane.revalidate();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}	
		}
		
		if (e.getSource() == menuAide) {
			if(this.getPopUp()==null){
				this.setPopUp(new JDialog(this,"programme realiser par W.Lepante, K.Augerau, N.Chouaib"));
				this.getPopUp().setSize(450, 350);
				this.getPopUp().setLocationRelativeTo(this);
				this.getPopUp().setVisible(true);

			}else{
				if(this.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.FenetrePrincipale")){
					this.getPopUp().toFront();
				}else{
					this.getPopUp().dispose();
					this.setPopUp(new JDialog(this));
					this.getPopUp().setSize(450, 350);
					this.getPopUp().setLocationRelativeTo(this);
					this.getPopUp().setVisible(true);

				}

			}


		}
		
		if (e.getSource() == mntmImprimer) {
			Properties props = new Properties();

			props.put("awt.print.paperSize", "a4");
			props.put("awt.print.destination", "printer");


			PrintJob pJob = getToolkit().getPrintJob(this,
					"titre de la page pdf", props);
			if (pJob != null)
			{
				Graphics pg = pJob.getGraphics();
				contentPane.getComponent(0).printAll(pg);
				pg.dispose();
				pJob.end();
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
