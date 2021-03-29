package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void givenExistingID_WhenGetAllTeamByID_thenReturnTeamsInResult() {
        // Given :
        //When
        List<TeamEntity> allTeams = teamService.recoverAllTeams();
        //Then
        assertThat(allTeams).isNotNull().isNotEqualTo(allTeams.isEmpty());
    }

    @Test
    public void givenExistingID_WhenGetTeamByID_thenReturnTheTeamInResult() {
        //Given
        String given = "PSG";
        //When
        Optional<TeamEntity> teamByID = teamService.recoverTeamByID(1);

        //Then
        assertThat(teamByID.get().getName()).isEqualTo(given);
    }

    @Test
    public void givenNewTeam_WhenGetNewTeam_ThenReturnNewTeam() {
        //Given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(6);
        teamEntity.setName("Nantes");
        teamEntity.setSlogan("Respect Solidarity Combative");

        String given = "Nantes";

        //When
        TeamEntity newTeam = teamService.insertTeam(teamEntity);

        //Then
        assertThat(newTeam.getName()).isEqualTo(given);
    }

    @Test
    public void givenOneLessTeam_WhenGetTeamByID_thenReturnTrue() {
        //Given
        long given = 3;

        //When
        teamService.deleteTeamByID(given);

        //Then
        assertThat(teamService.recoverTeamByID(given)).isEmpty();
    }

    @Test
    public void givenModification_WhenGetTeamByID_ThenReturnTeam() {
        //Given
        // String given ="Bayern";
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Tours");

        //When
        TeamEntity teamModifed = teamService.modifyTeam(teamEntity);

        //Then
        //assertThat(teamModifed.getName()).isNotEqualTo(given);
        assertThat(teamModifed.getName()).isEqualTo("Tours");
    }

}
