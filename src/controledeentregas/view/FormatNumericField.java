package controledeentregas.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FormatNumericField extends PlainDocument {

	// getText = TRAZ O QUE TEM NO CAMPO JTextField
	// str = � O QUE FOI DIGITADO MAS N�O INSERIDO NO CAMPO AT� CHAMAR
	// super.insertString(offs, str, a);

	private static final long serialVersionUID = 1L;
	private int size;
	private int decimals;
	private int digits;

	public FormatNumericField(int size, int decimals) {
		this.size = size;
		this.decimals = decimals;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {

		// if (getLength() == 0) {
		// str = str.replaceAll("[^0-9 || -]", "");
		// } else {
		// str = str.replaceAll("[^0-9]", "");
		// }

		str = str.replaceAll("[^0-9]", "");

		StringBuffer content = new StringBuffer(getText(0, getLength()) + str);

		if (content.length() > decimals) {

			str = content.toString().replaceAll("[, || .]", "");

			if (str.length() > size + decimals) {
				return;
			}

			content = new StringBuffer(str);

			digits = content.length() - decimals;

			if (digits >= 1 && digits <= 3) {
				content.insert(digits, ",");
				super.remove(0, getLength());
				super.insertString(0, content.toString(), a);
				return;
			}

			if (digits > 3 && digits <= 6) {
				content.insert(digits, ",");
				content.insert(digits - 3, ".");
				super.remove(0, getLength());
				super.insertString(0, content.toString(), a);
				return;
			}

			if (digits > 6 && digits <= 9) {
				content.insert(digits, ",");
				content.insert((digits - 6) + 3, ".");
				content.insert(digits - 6, ".");
				super.remove(0, getLength());
				super.insertString(0, content.toString(), a);
				return;
			}

			if (digits > 9 && digits <= 12) {
				content.insert(digits, ",");
				content.insert((digits - 9) + 6, ".");
				content.insert((digits - 9) + 3, ".");
				content.insert(digits - 9, ".");
				super.remove(0, getLength());
				super.insertString(0, content.toString(), a);
				return;
			}

			if (digits > 12 && digits <= 15) {
				content.insert(digits, ",");
				content.insert((digits - 12) + 9, ".");
				content.insert((digits - 12) + 6, ".");
				content.insert((digits - 12) + 3, ".");
				content.insert(digits - 12, ".");
				super.remove(0, getLength());
				super.insertString(0, content.toString(), a);
				return;
			}

		} else
			super.insertString(offs, str, a);
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		super.remove(offs, len);
		insertString(getLength(), "", null);
	}
}
