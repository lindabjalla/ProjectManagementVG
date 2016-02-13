package se.grouprich.projectmanagement.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.grouprich.projectmanagement.model.Team;
import se.grouprich.projectmanagement.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
	User findByControlNumber(Long controlNumber);

	User findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);

	List<User> findAllByFirstNameOrLastNameOrUsername(String firstName, String lastName, String username);
	
	List<User> findByTeam(Team team);
}
