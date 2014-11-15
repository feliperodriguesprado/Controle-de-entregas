package controledeentregas.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FormatTextField extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private int size;
	private String type;
	
	public FormatTextField(int size, String type) {
		this.size = size;
		this.type = type;
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		
		if (type.equals("U")) {
			// PERMITE LETRAS, N�MEROS, E ESPA�O. N�O PERMITE CARACTERES ESPECIAIS.
			// TODAS AS LETRAS INSERIDAS SER�O CONVERTIDAS PARA MA�USCULAS.
			str = str.replaceAll("[^0-9A-Za-z ]", "");
			str = str.toUpperCase();
		} else if (type.equals("L")) {
			// TODAS AS LETRAS INSERIDAS SER�O CONVERTIDAS PARA M�NUSCULAS.
			str = str.replaceAll("[^0-9A-Za-z ]", "");
			str = str.toLowerCase();
		}
		
		
		StringBuffer content = new StringBuffer(getText(0, getLength()) + str);
		
		if (content.length() > size) {
			return;
		}
		
		super.insertString(offs, str, a);
	}
	
}
