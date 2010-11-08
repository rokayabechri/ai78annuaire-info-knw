package fr.afcepf.ai78.projet1.interfaces;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JRadioButtonMenuItem;

public class FenetrePrincipale extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane				   = new JPanel();
	private JPanel panelLancement			   = new JPanel();
	private JMenuBar menuBar				   = new JMenuBar();
	private JMenu mnFichier					   = new JMenu("Fichier");
	private JMenu mnInfo					   = new JMenu("?");
	private JMenu mnApparence				   = new JMenu("Apparence");
	private JMenuItem mntmNouveau			   = new JMenuItem("Nouveau");
	private JMenuItem mntmOuvrir			   = new JMenuItem("Ouvrir");
	private JMenuItem mntmImprimer			   = new JMenuItem("Imprimer");
	private JMenuItem mntmSupprimerAnnuaire	   = new JMenuItem("Supprimer annuaire");
	private JMenuItem mntmQuitter			   = new JMenuItem("Quitter");
	private JMenuItem mntmAide				   = new JMenuItem("Aide");
	private JMenuItem menuItem 				   = new JMenuItem("");
	private JMenuItem mntmAPropos 			   = new JMenuItem("À propos");
	private JButton btnOuvrirAnnuaire		   = new JButton("Ouvrir Annuaire");
	private JButton btnNouvelAnnuaire		   = new JButton("Nouvel Annuaire");
	private JButton btnDeconnexion			   = new JButton("Deconnexion");
	private JButton btnConnexion			   = new JButton("Connexion");
	private JProgressBar progressBar		   = new JProgressBar();
	private boolean isConnected				   = false;
	private JDialog popUp;
	private GestionBinaire annuaireCourant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setJMenuBar(menuBar);
		setContentPane(contentPane);

		mntmNouveau.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_nouveau.png")));
		mnFichier.add(mntmNouveau);
		
		mntmOuvrir.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_ouvrir.png")));
		mnFichier.add(mntmOuvrir);
		
		mntmSupprimerAnnuaire.setEnabled(isConnected);
		mntmSupprimerAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_supprimer.png")));
		mnFichier.add(mntmSupprimerAnnuaire);
		
		mntmImprimer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_impression.png")));
		mnFichier.add(mntmImprimer);
		
		mntmQuitter.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/menu_quitter.png")));
		mnFichier.add(mntmQuitter);
		
		menuBar.add(mnFichier);
		
		ButtonGroup skin = new ButtonGroup();	
		/**************************************************/
		for(String[] laf : getLookAndFeelsMap()){
			final String classe = laf[1];
			
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(laf[0], laf[0].equals("Default"));
			item.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent ae){ 
					try{
						UIManager.setLookAndFeel(classe);
						SwingUtilities.updateComponentTreeUI(FenetrePrincipale.this);
					}catch(Exception e){
						e.printStackTrace();
					}
				} 
			});
			
			skin.add(item); 
			mnApparence.add(item);  	
		}
		/**************************************************/
		menuBar.add(mnApparence);
		mnInfo.add(mntmAide);
		menuBar.add(mnInfo);
		
		mnInfo.add(mntmAPropos);
		
		menuItem.setMaximumSize(new Dimension(32767, 0));
		menuItem.setEnabled(false);
		
		menuBar.add(menuItem);
		
		btnConnexion.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/connexion_icon.png")));
		btnConnexion.setFocusable(false);
		btnConnexion.setContentAreaFilled(false);
		btnConnexion.setPreferredSize(new Dimension(106, 24));
		btnConnexion.setBorderPainted(false);
		menuBar.add(btnConnexion);
		
		btnDeconnexion.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/deconnexion_icon.png")));
		btnDeconnexion.setFocusable(false);
		btnDeconnexion.setContentAreaFilled(false);
		btnDeconnexion.setPreferredSize(new Dimension(106, 24));
		btnDeconnexion.setBorderPainted(false);
		btnDeconnexion.setVisible(isConnected);
		menuBar.add(btnDeconnexion);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		
		panelLancement.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(panelLancement);
		
		//btnNouvelAnnuaire.setSelectedIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_nouveau_over.png")));
		btnNouvelAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_nouveau_over.png")));
		btnNouvelAnnuaire.setPreferredSize(new Dimension(250, 300));
		panelLancement.add(btnNouvelAnnuaire);
		
		//btnOuvrirAnnuaire.setSelectedIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_ouvrir_over.png")));
		btnOuvrirAnnuaire.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/afcepf/ai78/projet1/images/btn_ouvrir_over.png")));
		btnOuvrirAnnuaire.setPreferredSize(new Dimension(250, 300));
		panelLancement.add(btnOuvrirAnnuaire);

		btnOuvrirAnnuaire.addActionListener(this);
		btnNouvelAnnuaire.addActionListener(this);
		btnDeconnexion.addActionListener(this);
		btnConnexion.addActionListener(this);
		mntmNouveau.addActionListener(this);
		mntmOuvrir.addActionListener(this);
		mntmQuitter.addActionListener(this);
		mntmImprimer.addActionListener(this);
		mntmSupprimerAnnuaire.addActionListener(this);
		mntmAPropos.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnOuvrirAnnuaire || e.getSource() == mntmOuvrir){
			
			File repertoire = new File(AnnuaireConstante.BIN_PATH);
			if(!repertoire.exists()){
				repertoire.mkdirs();
			}
			String [] listefichiers = repertoire.list();

			if(this.getPopUp()!=null){
				this.disposePopUp();
			}
			this.setPopUp(new OuvrirAnnuaire(this,listefichiers));
			this.getPopUp().setLocationRelativeTo(this);
			this.getPopUp().setVisible(true);
		}

		if (e.getSource() == btnNouvelAnnuaire || e.getSource() == mntmNouveau) {
			
			File repertoire = new File(AnnuaireConstante.BIN_PATH);
			if(!repertoire.exists()){
				repertoire.mkdirs();
			}

			if(this.getPopUp()!=null){
				this.disposePopUp();
			}
			this.setPopUp(new NouvelAnnuaire(this));
			this.getPopUp().setLocationRelativeTo(this);
			this.getPopUp().setVisible(true);
		}
		
		if(e.getSource() == mntmQuitter){
			System.exit(0);	
		}

		if (e.getSource() == mntmImprimer) {

			if(getContentPane().getComponent(0) != getPanelLancement()){
				try {
					AffichageAnnuaire affichage = (AffichageAnnuaire) getContentPane().getComponent(0);
					affichage.getTable().print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		}

		if ( (e.getSource()==mntmSupprimerAnnuaire) && isConnected) {

			if(this.getPopUp()!=null){
				this.disposePopUp();
			}
			this.setPopUp(new SupprimerAnnuaire(this));
			this.getPopUp().setLocationRelativeTo(this);
			this.getPopUp().setVisible(true);
		}


		if (e.getSource() == btnConnexion) {
			
			if(this.getPopUp()!=null){
				this.disposePopUp();
			}
			this.setPopUp(new Password(this));
			this.getPopUp().setLocationRelativeTo(this);
			this.getPopUp().setVisible(true);
		}

		if (e.getSource() == btnDeconnexion) {

			this.setConnected(false);
			if(popUp != null){
				this.disposePopUp();
			}
			
			if(getContentPane().getComponent(0) != getPanelLancement()){
				AffichageAnnuaire affichage = (AffichageAnnuaire) getContentPane().getComponent(0);
				affichage.getPanelOption().setVisible(false);
			}
			this.setTitle((getTitle().subSequence(0, getTitle().indexOf(" (Connecté)")).toString()));
			
			btnDeconnexion.setVisible(false);
			btnConnexion.setVisible(true);
		}
		
		
		if (e.getSource() == mntmAPropos) {

			if(this.getPopUp()!=null){
				this.disposePopUp();
			}
			this.setPopUp(new Apropos(this));
			this.getPopUp().setLocationRelativeTo(this);
			this.getPopUp().setVisible(true);
		}
	}

	public void appelAffichage(boolean liste){
		if(liste){
			contentPane.remove(progressBar);
			contentPane.remove(contentPane.getComponent(0));
			contentPane.add(new AffichageAnnuaire(this),BorderLayout.CENTER);
			this.setEnabled(true);
			contentPane.revalidate();
		}else{
			JOptionPane.showMessageDialog(this, "Fichier incorrect");
			this.setEnabled(true);
		}
	}

	public void disposePopUp() {
		popUp.dispose();
		this.setPopUp(null);
		this.setEnabled(true);
		this.toFront();
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

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public JButton getBtnDeconnexion() {
		return btnDeconnexion;
	}

	public void setBtnDeconnexion(JButton btnDeconnexion) {
		this.btnDeconnexion = btnDeconnexion;
	}

	public JButton getBtnConnexion() {
		return btnConnexion;
	}

	public void setBtnConnexion(JButton btnConnexion) {
		this.btnConnexion = btnConnexion;
	}

	public JMenuItem getMntmSupprimerAnnuaire() {
		return mntmSupprimerAnnuaire;
	}
	
	public String[][] getLookAndFeelsMap(){
		String [][] info = {{"Default", "com.jtattoo.plaf.graphite.GraphiteLookAndFeel"},
							//{"Aero", "com.jtattoo.plaf.aero.AeroLookAndFeel"},
							//{"Acryl", "com.jtattoo.plaf.acryl.AcrylLookAndFeel"},
							//{"Aluminium","com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"},
							//{"Noire","com.jtattoo.plaf.noire.NoireLookAndFeel"},
							//{"Windows","com.sun.java.swing.plaf.windows.WindowsLookAndFeel"},
							//{"Nimbus","com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"},
							{"Black","org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel"},
							{"Blue","org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel"},
							{"Classic","org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel"},
							{"Coffee","org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel"},
							{"Ice","org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel"},
							//{"Nimrod","com.nilo.plaf.nimrod.NimRODLookAndFeel"},
							{"Sand","org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel"},
							{"SubGraphite","org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel"},
		};
		return info;	
	}
}
