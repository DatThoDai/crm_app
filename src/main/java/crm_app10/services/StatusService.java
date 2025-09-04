package crm_app10.services;

import java.util.List;

import crm_app10.repository.StatusRepository;
import entity.Status;

public class StatusService {
	private StatusRepository statusRepository = new StatusRepository();

	public List<Status> findAllStatus() {
		return statusRepository.findAllStatus();
	}
}
