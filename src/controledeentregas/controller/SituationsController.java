package controledeentregas.controller;

import java.util.ArrayList;
import java.util.List;

import controledeentregas.model.dao.SituationDAO;
import controledeentregas.model.to.SituationTO;
import controledeentregas.view.FrameListSituations;
import controledeentregas.view.FrameRegistrationSituations;
import controledeentregas.view.listener.SituationsActions;

public class SituationsController implements SituationsActions {

	private SituationDAO situationDAO;
	private List<SituationTO> listSituations;
	private FrameListSituations frameListSituations;
	private FrameRegistrationSituations frameCadastreSituations;

	public void initialize() {
		frameListSituations = new FrameListSituations(this);
		frameListSituations.setVisible(true);		
	}
	
	@Override
	public List<SituationTO> getListSituations() {
		situationDAO = new SituationDAO();
		listSituations = new ArrayList<SituationTO>();
		listSituations = situationDAO.getAllActive();
		return listSituations;
	}

	
	@Override
	public void actionButtonNewFrameListSituation() {
		frameCadastreSituations = new FrameRegistrationSituations(this, new SituationTO());
		frameCadastreSituations.setVisible(true);
	}

	@Override
	public void actionButtonEditFrameListSituation(SituationTO situationTO) {
		frameCadastreSituations = new FrameRegistrationSituations(this, situationTO);
		frameCadastreSituations.setVisible(true);
	}
	
	@Override
	public void actionButtonRemoveFrameRegistrationSituation(SituationTO situationTO) {
		situationDAO = new SituationDAO();
		situationDAO.remove(situationTO);		
	}
	
	@Override
	public void actionButtonCancelFrameRegistrationSituation() {
		frameCadastreSituations.dispose();
	}
	
	@Override
	public boolean actionButtonSaveFrameRegistrationSituation(SituationTO situationTO) {

		Integer id = situationDAO.checkRegistrationSituationNewUpdate(situationTO);
		
		if (id == null) {
			
			boolean result = situationDAO.save(situationTO);

			if (result) {
				frameCadastreSituations.dispose();
				return result;
			} else {
				return result;
			}

		} else {
			
			situationTO.setId(id);
			boolean result = situationDAO.update(situationTO);

			if (result) {
				frameCadastreSituations.dispose();
				return result;
			} else {
				return result;
			}
		}
	}
}
