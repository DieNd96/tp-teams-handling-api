package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public List<TeamEntity> recoverAllTeams(){
        return teamRepository.findAll();
    }

    public Optional<TeamEntity> recoverTeamByID(long id){
        return teamRepository.findById(id);
    }

    public TeamEntity insertTeam(TeamEntity T){
        return teamRepository.save(T);
    }

    public TeamEntity modifyTeam(TeamEntity T){
        return teamRepository.save(T);
            }

    public void modifyNameTeam(long id, String name){
         teamRepository.findById(id).get().setName(name);
    }

    public void deleteTeamByID(long id){
        teamRepository.deleteById(id);
    }
}
