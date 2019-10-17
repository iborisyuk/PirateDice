package com.playtika.piratedice.core.database;

import com.playtika.piratedice.core.database.model.Players;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends CrudRepository<Players, Integer> {
}
