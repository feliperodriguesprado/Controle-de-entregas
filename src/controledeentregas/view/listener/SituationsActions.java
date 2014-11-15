package controledeentregas.view.listener;

import java.util.List;

import controledeentregas.model.to.SituationTO;

public interface SituationsActions {
	
	public List<SituationTO> getListSituations();
	
	public void actionButtonNewFrameListSituation();
	
	public void actionButtonEditFrameListSituation(SituationTO situationTO);
	
	public void actionButtonCancelFrameRegistrationSituation();
	
	public boolean actionButtonSaveFrameRegistrationSituation(SituationTO situationTO);
	
	public void actionButtonRemoveFrameRegistrationSituation(SituationTO situationTO);
}
