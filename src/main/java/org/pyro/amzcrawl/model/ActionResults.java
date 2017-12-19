package org.pyro.amzcrawl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alex on 19.12.2017.
 */
@Entity
@Table(name = "actionsResults")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class ActionResults {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;

    private String response;

    private Action action;

    @ManyToOne
    @JoinColumn(name = "action_id")
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
