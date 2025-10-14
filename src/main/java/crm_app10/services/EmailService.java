package crm_app10.services;

import crm_app10.repository.EmailRepository;

public class EmailService {
	private EmailRepository emailRepository = new EmailRepository();

	public boolean sendTaskNotification(String toEmail, String employeeName, String taskName, String projectName, String deadline) {
		return emailRepository.sendTaskNotification(toEmail, employeeName, taskName, projectName, deadline);
	}
}
