package se.grouprich.projectmanagement;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import se.grouprich.projectmanagement.exception.RepositoryException;
import se.grouprich.projectmanagement.exception.TeamException;
import se.grouprich.projectmanagement.exception.WorkItemException;
import se.grouprich.projectmanagement.model.Issue;
import se.grouprich.projectmanagement.model.Team;
import se.grouprich.projectmanagement.model.User;
import se.grouprich.projectmanagement.model.WorkItem;
import se.grouprich.projectmanagement.service.IssueService;
import se.grouprich.projectmanagement.service.TeamService;
import se.grouprich.projectmanagement.service.UserService;
import se.grouprich.projectmanagement.service.WorkItemService;
import se.grouprich.projectmanagement.status.WorkItemStatus;

public final class Main
{
	public static void main(String[] args) throws TeamException, WorkItemException, RepositoryException
	{
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext())
		{
			context.scan("se.grouprich.projectmanagement");
			context.refresh();
			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			IssueService issueService = context.getBean(IssueService.class);

			Team teamA = teamService.createOrUpdate(new Team("teamA"));
			User user = userService.createOrUpdate(new User("CocoaKakao-chan", "1111", "kakao", "smör"));
			teamService.addUserToTeam(teamA, user);
			User user1 = userService.createOrUpdate(new User("InuDesuWanWanWanko", "4444", "Wanko", "Kokohore"));
			teamService.addUserToTeam(teamA, user1);
			
			Team teamB = teamService.createOrUpdate(new Team("teamB"));
			User user2 = userService.createOrUpdate(new User("HohokekyoSpring", "2222", "Tori", "Uguisu").setTeam(teamB));
			
//			teamService.addUserToTeam(teamA, user2);
			
			teamB.setName("SUPER teamB");
			teamService.createOrUpdate(teamB);
			teamService.inactivateTeam(teamB);
			Iterable<Team> allTeams = teamService.findAll();
			System.out.println("All teams: " + allTeams);
			System.out.println();
			
			List<User> usersInTeamA = userService.findByTeam(teamA);
			System.out.println("---users in teamA---");
			usersInTeamA.forEach(System.out::println);
			System.out.println();
			
			List<User> usersInTeamB = userService.findByTeam(teamB);
			System.out.println("---users in teamB---");
			usersInTeamB.forEach(System.out::println);
			System.out.println();
			
			Page<Issue> allIssues = issueService.findAll(new PageRequest(0, 2));
			System.out.println("---All issues---");
			allIssues.forEach(System.out::println);
			System.out.println();
			
			WorkItem sy = workItemService.createOrUpdate(new WorkItem("Sy klänning"));
			workItemService.changeWorkItemStatus(sy, WorkItemStatus.DONE);
			
			List<WorkItem> finishedWorkItems = workItemService.findFinishedWorkItemsBetweenStartDateAndEndDate("2016-02-13", "2016-02-15");
			System.out.println("---finishedWorkItems---");
			finishedWorkItems.forEach(System.out::println);
			//update
		}
	}
}