package se.grouprich.projectmanagement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.grouprich.projectmanagement.model.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long>{}
