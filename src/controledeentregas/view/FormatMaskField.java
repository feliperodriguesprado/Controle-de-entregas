package controledeentregas.view;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class FormatMaskField {
	
	// "#" = QUALQUER N�MERO V�LIDO (0-9)
	// "U" = QUALQUER LETRA (a-z), TODAS AS LETRAS INSERIDAS SER�O CONVERTIDAS
	// PARA MAI�SCULAS
	// "L" = QUALQUER LETRA (a-z), TODAS AS LETRAS INSERIDAS SER�O CONVERTIDAS
	// PARA MIN�SCULAS
	// "A" =  QUALQUER LETRA (a-z) OU N�MERO (0-9)
	// "?" = QUALQUER LETRA (a-z)
	// "H" = QUALQUER CARACTERE HEXADECIMAL (0-9 OU a-f)
	// "*" = TODOS, INCLUINDO CARACTERES ESPECIAIS

	public static MaskFormatter getMaskDateField() {

		MaskFormatter maskDateField = new MaskFormatter();

		try {
			maskDateField.setMask("##/##/####");
			maskDateField.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maskDateField;
	}

}
