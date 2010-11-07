package fr.afcepf.ai78.projet1.interfaces;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OuvrirAnnuaire extends JDialog implements ActionListener,WindowListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JButton btnValider 				= new JButton("Valider");
	private JButton btnAnnuler 				= new JButton("Annuler");
	private JComboBox comboBox 				= new JComboBox();
	private JLabel lblSelectionnerUnFichier = new JLabel("Selectionner un fichier :");
	private JLabel lblImage 				= new JLabel("");
	private FenetrePrincipale frame;

	/**
	 * Create the dialog.
	 */
	public OuvrirAnnuaire(FenetrePrincipale frame, String [] listefichiers) {
		super(frame, true);
		this.frame = frame;
		setIconImage(Toolkit.getDefaultToolkit().getImage(OuvrirAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/menu_ouvrir.png")));
		setTitle("Ouvrir annuaire");
		setResizable(false);
		setSize(265, 175);
		
		getContentPane().setLayout(null);

		btnValider.setBounds(30, 105, 80, 25);
		getContentPane().add(btnValider);
		
		btnAnnuler.setBounds(145, 105, 80, 25);
		getContentPane().add(btnAnnuler);
		
		comboBox.setBounds(95, 45, 150, 25);
		getContentPane().add(comboBox);

		for (String string : listefichiers) {
			comboBox.addItem(string.substring(0,string.lastIndexOf("."))); 
		}

		lblSelectionnerUnFichier.setBounds(95, 15, 150, 15);
		getContentPane().add(lblSelectionnerUnFichier);
		
		lblImage.setIcon(new ImageIcon(OuvrirAnnuaire.class.getResource("/fr/afcepf/ai78/projet1/images/folder_down.png")));
		lblImage.setBounds(10, 15, 70, 70);
		getContentPane().add(lblImage);

		comboBox.addActionListener(this);
		btnValider.addActionListener(this);
		btnAnnuler.addActionListener(this);
		this.addWindowListener(this);
		comboBox.addKeyListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnValider){
			ouvrirAnnuaire();
		}

		if(e.getSource()==btnAnnuler){
			frame.disposePopUp();
		}
	}


	private void ouvrirAnnuaire() {
		if(!comboBox.getSelectedItem().toString().equals("")){

			String nomFichier = comboBox.getSelectedItem().toString();				
			frame.setAnnuaireCourant(new GestionBinaire(frame,"", AnnuaireConstante.BIN_PATH+nomFichier+".bin"));
			
			if(frame.isConnected()){
				frame.setTitle("Gestion d'annuaire : "+nomFichier+" (Connecté)");
			}else{
				frame.setTitle("Gestion d'annuaire : "+nomFichier);
			}
			
			frame.appelAffichage(true);
			frame.disposePopUp();
				
		}else{
			JOptionPane.showMessageDialog(this, "Sélectionner un ficher");
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		frame.disposePopUp();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			ouvrirAnnuaire();
		}else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			frame.disposePopUp();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
