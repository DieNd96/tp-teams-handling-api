package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.controller.TeamController;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
public class TeamControllerTest {
    @Mock
    private TeamService teamService ;
    @Autowired
    private TeamMapper teamMapper;

    private Optional<TeamEntity> teamEntity ;

    MockMvc mockMvc ;

    List<TeamEntity> listTeam = new ArrayList<>();

    TeamEntity  PSG = new TeamEntity(1, "Paris","Dream Bigger");
    TeamEntity  LFC = new TeamEntity(2,"Liverpool","You never walks alone");
    TeamEntity  BFC = new TeamEntity(3,"Barcelona","Mes qu'un  club");

    public List<TeamEntity> virtualDB(){
        listTeam.add(PSG);
        listTeam.add(LFC);
        listTeam.add(BFC);
        return listTeam;
    }

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(new TeamController(teamService,teamMapper));
    }

    @Test
    public void givenUrlWhenRequestGetThenOK(){
        Mockito.when(teamService.recoverAllTeams()).thenReturn(virtualDB());

        given().when()
                .get("/teams")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void shouldReturnStatus200WhenIDGivenExist(){
        Mockito.when(teamService.recoverTeamByID(1L)).thenReturn(teamEntity);

        given().when()
                .get("/teamByID/{id}",1L)
                .then()
                .assertThat()
                .statusCode(200);

    }


}
