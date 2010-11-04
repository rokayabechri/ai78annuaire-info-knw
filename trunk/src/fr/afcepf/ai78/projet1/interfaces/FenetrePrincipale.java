package fr.afcepf.ai78.projet1.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
import java.io.File;
import javax.swing.ImageIcon;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import java.awt.Toolkit;


public class FenetrePrincipale extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPanel panelLancement;
	private JMenu mnFichier;
	private JMenu menuAide;
	private JMenuItem mntmNouveau;
	private JMenuItem mntmOuvrir;
	private JMenuItem mntmImprimer;
	private JMenuBar menuBar;
	private JButton btnOuvrirAnnuaire;
	private JButton btnNouvelAnnuaire;
	private JDialog popUp;
	private JProgressBar progressBar;
	private GestionBinaire annuaireCourant;
	private JMenuItem mntmQuitter;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					//UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
					//UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/frame_icon.png")));
		
		setTitle("Gestion d'annuaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmNouveau = new JMenuItem("Nouveau");
		mntmNouveau.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_nouveau.png")));
		mntmNouveau.setHorizontalAlignment(SwingConstants.LEFT);
		mnFichier.add(mntmNouveau);
		
		mntmOuvrir = new JMenuItem("Ouvrir");
		mntmOuvrir.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_ouvrir.png")));
		mnFichier.add(mntmOuvrir);
		
		mntmImprimer = new JMenuItem("Imprimer");
		mntmImprimer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_impression.png")));
		mnFichier.add(mntmImprimer);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_quitter.png")));
		mnFichier.add(mntmQuitter);
		
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
		//btnNouvelAnnuaire.setSelectedIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_nouveau_over.png")));
		btnNouvelAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_nouveau_over.png")));
		btnNouvelAnnuaire.setPreferredSize(new Dimension(250, 300));
		panelLancement.add(btnNouvelAnnuaire);
		
		btnOuvrirAnnuaire = new JButton("Ouvrir Annuaire");
		//btnOuvrirAnnuaire.setSelectedIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_ouvrir_over.png")));
		btnOuvrirAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_ouvrir_over.png")));
		btnOuvrirAnnuaire.setPreferredSize(new Dimension(250, 300));
		panelLancement.add(btnOuvrirAnnuaire);
		
		progressBar = new JProgressBar();
		contentPane.add(progressBar, BorderLayout.SOUTH);
		
		btnOuvrirAnnuaire.addActionListener(this);
		btnNouvelAnnuaire.addActionListener(this);
		mntmNouveau.addActionListener(this);
		mntmOuvrir.addActionListener(this);
		mntmQuitter.addActionListener(this);
		menuAide.addActionListener(this);
		mntmImprimer.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == btnOuvrirAnnuaire || e.getSource() == mntmOuvrir){

			this.setEnabled(false);

			File repertoire = new File("c:/binaries");
			String [] listefichiers;  
			listefichiers  = repertoire.list();


			if(this.getPopUp()==null){
				this.setPopUp(new AnnaireExistant(this,listefichiers));
				this.getPopUp().setSize(450, 350);
				this.getPopUp().setLocationRelativeTo(this);
				this.getPopUp().setVisible(true);

			}else{
				if(this.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.FenetrePrincipale")){
					this.getPopUp().toFront();
				}else{
					this.getPopUp().dispose();
					this.setPopUp(new AnnaireExistant(this,listefichiers));
					this.getPopUp().setSize(450, 350);
					this.getPopUp().setLocationRelativeTo(this);
					this.getPopUp().setVisible(true);

				}
			}
		}

		if (e.getSource() == btnNouvelAnnuaire || e.getSource() == mntmNouveau) {

			this.setEnabled(false);

			if(this.getPopUp()==null){
				this.setPopUp(new NouvelAnnuaire(this));
				this.getPopUp().setLocationRelativeTo(this);
				this.getPopUp().setVisible(true);

			}else{
				if(this.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.FenetrePrincipale")){
					this.getPopUp().toFront();
				}else{
					this.setPopUp(new NouvelAnnuaire(this));
					this.getPopUp().setLocationRelativeTo(this);
					this.getPopUp().setVisible(true);
					this.getPopUp().dispose();
				}
			}
			
			

		}
		if(e.getSource() == mntmQuitter){
			
			System.exit(0);	
		}

		//		if (e.getSource() == menuAide) {
		//			if(this.getPopUp()==null){
		//				this.setPopUp(new JDialog(this,"programme realiser par W.Lepante, K.Augerau, N.Chouaib"));
		//				this.getPopUp().setSize(450, 350);
		//				this.getPopUp().setLocationRelativeTo(this);
		//				this.getPopUp().setVisible(true);
		//
		//			}else{
		//				if(this.getPopUp().getClass().equals("fr.afcepf.ai78.projet1.interfaces.FenetrePrincipale")){
		//					this.getPopUp().toFront();
		//				}else{
		//					this.getPopUp().dispose();
		//					this.setPopUp(new NouvelAnnuaire(this));
		//					this.getPopUp().setSize(450, 350);
		//					this.getPopUp().setLocationRelativeTo(this);
		//					this.getPopUp().setVisible(true);
		//
		//				}
		//
		//			}
		//
		//
		//		}
		//
		//		if (e.getSource() == mntmImprimer) {
		//			Properties props = new Properties();
		//
		//			props.put("awt.print.paperSize", "a4");
		//			props.put("awt.print.destination", "printer");
		//
		//
		//			PrintJob pJob = getToolkit().getPrintJob(this,
		//					"titre de la page pdf", props);
		//			if (pJob != null)
		//			{
		//				Graphics pg = pJob.getGraphics();
		//				contentPane.getComponent(0).printAll(pg);
		//				pg.dispose();
		//				pJob.end();
		//			}
		//
		//		}
	}
	
	public void appelAffichage(boolean liste){
		if(liste){
			contentPane.remove(progressBar);
			contentPane.remove(contentPane.getComponent(0));
			contentPane.add(new AffichageAnnuaire(this),BorderLayout.CENTER);
			setEnabled(true);
			contentPane.revalidate();
		}else{
			JOptionPane.showMessageDialog(this, "Fichier incorrect");
			setEnabled(true);
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

	public JPanel getPanelLancement() {
		return panelLancement;
	}

	public void setAnnuaireCourant(GestionBinaire annuaireCourant) {
		this.annuaireCourant = annuaireCourant;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
