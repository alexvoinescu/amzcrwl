package org.pyro.amzcrawl.controller;

import org.pyro.amzcrawl.actions.ActionsFactory;
import org.pyro.amzcrawl.actions.WebActionable;
import org.pyro.amzcrawl.model.Action;
import org.pyro.amzcrawl.model.Keyword;
import org.pyro.amzcrawl.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActionController {

    @Autowired
    ActionRepository actionRepository;

    @GetMapping("/actions")
    public List<Action> getAllKeywords() {
        return actionRepository.findAll();
    }

    @PostMapping("/actions")
    public Action createAction(@Valid @RequestBody Action action) {
        return actionRepository.save(action);
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<Action> getKeywordById(@PathVariable(value = "id") Long id) {
        Action action = actionRepository.findOne(id);
        if(action == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(action);
    }

    @PutMapping("/actions/{id}")
    public ResponseEntity<Action> updateKeyword(@PathVariable(value = "id") Long id, @Valid @RequestBody Action actionDetails) {
        Action action = actionRepository.findOne(id);
        if(action == null) {
            return ResponseEntity.notFound().build();
        }

        action.setType(actionDetails.getType());
        action.setUrl(actionDetails.getUrl());
        action.setAttributes(actionDetails.getAttributes());

        Action updatedAction = actionRepository.save(action);
        return ResponseEntity.ok(updatedAction);
    }

    @GetMapping("/actions/run/{id}")
    public ResponseEntity<Action> run(@PathVariable(value = "id") Long id) {

        Action action = actionRepository.findOne(id);

        WebActionable actionable = ActionsFactory.getActionType(action);
        actionable.run(action);

        return ResponseEntity.ok(action);
    }

}
