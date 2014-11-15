package controledeentregas.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Constraints extends GridBagConstraints {

	private static final long serialVersionUID = 1L;

	
	// INSETS = new Insets(0, 0, 0, 0)
	// ANCHOR = GridBagConstraints.CENTER
	// FILL = GridBagConstraints.NONE
	// GRIDY = GridBagConstraints.RELATIVE
	// GRIDX = GridBagConstraints.RELATIVE
	// GRIDWIDTH = 1
	// GRIDHEIGTH = 1
	// WEIGHTY = 0
	// WEIGHTX = 0
	// IPADX = 0
	// IPADY = 0
	
	
	public void setInsets(Insets insets) {
		this.insets = insets;
	}
	
	public void setAnchor(int anchor) {
		this.anchor = anchor;
	}
	
	public void setFill (int fill){
		this.fill = fill;
	}
	
	public void setGridyGridx(int gridy, int gridx) {
		this.gridy = gridy;
		this.gridx = gridx;
	}
	
	public void setGridwidthGridheight(int gridwidth, int gridheight) {
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	public void setWeightyWeightx(double weighty, double weightx) {
		this.weighty = weighty;
		this.weightx = weightx;
	}
	
	public void setIpadxIpadY(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
	}
	
	public void setConstraints(Insets insets, int anchor, int fill, int gridy,
			int gridx, int gridwidth, int gridheight, double weightx,
			double weighty, int ipadx, int ipady) {

		this.insets = insets;									// INSETS = new Insets(0, 0, 0, 0)
		this.anchor = anchor;									// ANCHOR = GridBagConstraints.CENTER
		this.fill = fill;										// FILL = GridBagConstraints.NONE
		this.gridy = gridy;										// GRIDY = GridBagConstraints.RELATIVE
		this.gridx = gridx;										// GRIDX = GridBagConstraints.RELATIVE
		this.gridwidth = gridwidth;								// GRIDWIDTH = 1
		this.gridheight = gridheight;							// GRIDHEIGTH = 1
		this.weightx = weightx;  								// WEIGHTX = 0
		this.weighty = weighty;									// WEIGHTY = 0
		this.ipadx = ipadx;										// IPADX = 0
		this.ipady = ipady;										// IPADY = 0
	}
}
