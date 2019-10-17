package com.playtika.piratedice.core.database;

import com.playtika.piratedice.core.database.model.Games;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends CrudRepository<Games, Integer> {
}
