package crm_app10.services;

import java.util.List;

import crm_app10.repository.JobsRepository;
import entity.Jobs;

public class JobsService {
	private JobsRepository jobsRepository = new JobsRepository();
	
	public List<Jobs> findAllJobs() {
		return jobsRepository.findAllJobs();
	}
	
	public boolean addJob(String name, String startDate, String endDate) {
		return jobsRepository.addJob(name, startDate, endDate);
	}
}
