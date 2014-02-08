package controle_de_entrega.view;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class FormatMaskField {
	
	// "#" = QUALQUER NÚMERO VÁLIDO (0-9)
	// "U" = QUALQUER LETRA (a-z), TODAS AS LETRAS INSERIDAS SERÃO CONVERTIDAS
	// PARA MAIÚSCULAS
	// "L" = QUALQUER LETRA (a-z), TODAS AS LETRAS INSERIDAS SERÃO CONVERTIDAS
	// PARA MINÚSCULAS
	// "A" =  QUALQUER LETRA (a-z) OU NÚMERO (0-9)
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
