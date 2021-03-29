package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;


    @Autowired
    public TeamController(TeamService teamService,TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/hello")
    public String getTeams() {
        return "Hello World";
    }

    /*@GetMapping(path="/team")
    public List<TeamEntity> getAllTeams(){
        return teamService.recoverAllTeams();
    }*/

    @GetMapping("/teams")
    public List<TeamDto> recoverAllTeams() {
        return teamService.recoverAllTeams()
                .stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }


   /* @RequestMapping(path = "/teamByID/{id}")
    public Optional<TeamEntity> getTeamByID(@PathVariable("id") long id){
        return teamService.recoverTeamByID(id);
    }*/

    @RequestMapping(path = "/teamByID/{id}")
    public TeamDto recoverTeamByID(@PathVariable long id) {

        return teamService.recoverTeamByID(id)
                .map(teamMapper::toDto)
                .orElse(null);
    }

   /* @PostMapping("/saveTeam/{id}/{name}/{slogan}")
    public TeamEntity addTeam(@PathVariable( value = "id") long id,
                              @PathVariable(value = "name") String name,
                              @PathVariable(value = "slogan") String slogan){
        TeamEntity teamEntity = new TeamEntity(id,name,slogan);
       return teamService.insertTeam(teamEntity);
    }*/

    @PostMapping("/teamToAdd")
    public TeamDto insertTeam(@RequestBody TeamDto teamDto) {
        TeamEntity teamEntityToSave = teamMapper.toEntity(teamDto);
        teamService.insertTeam(teamEntityToSave);
        return teamMapper.toDto(teamEntityToSave);

    }

    /* @PutMapping("/updateTeam/{id}/{name}")
    public Optional<TeamEntity> modifyNameTeam(@PathVariable( value = "id") long id,
                                               @PathVariable(value = "name") String name){
         teamService.modifyNameTeam(id,name);
         return teamService.recoverTeamByID(id);
    }*/

    @PutMapping("/updateTeam")
    public TeamDto modifyTeam(@RequestBody TeamDto teamDto) {
        TeamEntity teamEntityToUpdate = teamMapper.toEntity(teamDto);
        teamService.modifyTeam(teamEntityToUpdate);
        return teamMapper.toDto(teamEntityToUpdate);
    }



    @RequestMapping("/deleteTeamByID/{id}")
    public void deleteTeamByID(@PathVariable("id") long id){
        teamService.deleteTeamByID(id);
    }
    
}
