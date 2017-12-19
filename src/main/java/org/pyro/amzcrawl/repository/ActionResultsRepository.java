package org.pyro.amzcrawl.repository;

import org.pyro.amzcrawl.model.ActionResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alex on 19.12.2017.
 */
@Repository
public interface ActionResultsRepository extends JpaRepository<ActionResults, Long>{
}
