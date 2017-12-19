package org.pyro.amzcrawl.repository;

import org.pyro.amzcrawl.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository  extends JpaRepository<Action, Long> {
}
