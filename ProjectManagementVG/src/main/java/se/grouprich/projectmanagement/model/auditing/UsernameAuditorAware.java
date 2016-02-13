package se.grouprich.projectmanagement.model.auditing;

import org.springframework.data.domain.AuditorAware;

public class UsernameAuditorAware implements AuditorAware<String>
{
	@Override
	public String getCurrentAuditor()
	{
		return "DefaultUser";
	}
}
