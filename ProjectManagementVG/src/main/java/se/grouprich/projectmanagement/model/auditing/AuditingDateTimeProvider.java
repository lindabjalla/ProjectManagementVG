package se.grouprich.projectmanagement.model.auditing;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.data.auditing.DateTimeProvider;

public class AuditingDateTimeProvider implements DateTimeProvider
{
	@Override
	public Calendar getNow()
	{
		return new GregorianCalendar();
	}	
}
