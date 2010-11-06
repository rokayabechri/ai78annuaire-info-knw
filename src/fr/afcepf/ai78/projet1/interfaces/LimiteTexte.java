package fr.afcepf.ai78.projet1.interfaces;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimiteTexte extends PlainDocument {
	
	private static final long serialVersionUID = 1L;
	private int max;
	
	public LimiteTexte(int max){
		super();
		this.max = max;
	}
	
	public void insertString(int offset, String chaine, AttributeSet attribut) throws BadLocationException {
		if (chaine != null && getLength() + chaine.length() > max) {
			Toolkit.getDefaultToolkit().beep();
		} else {
			super.insertString(offset, chaine, attribut);
		}
	}
 
	public void replace(int offset, int length, String chaine, AttributeSet attribut) throws BadLocationException {
		if (chaine != null && getLength() + chaine.length() - length > max) {
			Toolkit.getDefaultToolkit().beep();
		} else {
			super.replace(offset, length, chaine, attribut);
		}
	}
}


