package controle_de_entrega.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageResources {

	public static String getResource(String key) {
		Locale currentLocale = new Locale("pt", "BR");
		ResourceBundle Resources = ResourceBundle.getBundle(
				"files/properties/LanguageResource_pt_BR",
				currentLocale);
		return Resources.getString(key);
	}

}
