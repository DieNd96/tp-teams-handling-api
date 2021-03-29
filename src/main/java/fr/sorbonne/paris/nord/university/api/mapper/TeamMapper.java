package fr.sorbonne.paris.nord.university.api.mapper;

import fr.sorbonne.paris.nord.university.api.controller.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamEntity toEntity(TeamDto teamDto) {
        return new TeamEntity(teamDto.getName(), teamDto.getSlogan());

    }

    public TeamDto toDto(TeamEntity teamEntity) {
        return new TeamDto(teamEntity.getId(), teamEntity.getName(), teamEntity.getSlogan());
    }
}
