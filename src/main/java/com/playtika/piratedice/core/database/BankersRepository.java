package com.playtika.piratedice.core.database;

import com.playtika.piratedice.core.database.model.Bankers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankersRepository extends CrudRepository<Bankers, Integer> {
}
