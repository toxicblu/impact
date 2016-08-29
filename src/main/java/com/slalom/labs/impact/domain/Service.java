package com.slalom.labs.impact.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ted on 3/10/16.
 */
@Entity
public class Service {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String presentedName;
    private String description;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToMany(mappedBy = "producer", cascade = {CascadeType.ALL})
    private List<Contract> producerContracts;
    @OneToMany(mappedBy = "consumer", cascade = {CascadeType.ALL})
    private List<Contract> consumerContracts;
    @OneToMany(mappedBy = "service", cascade = {CascadeType.ALL})
    private List<Run> runs;

    protected Service() {}

    public Service(String name, String presentedName, String description, Team team, List<Run> runs,
                   List<Contract> producerContracts, List<Contract> consumerContracts) {
        this.name = name;
        this.presentedName = presentedName;
        this.team = team;
        this.runs = runs;
        this.producerContracts = producerContracts;
        this.consumerContracts = consumerContracts;
        this.description = description;
    }

    public List<Run> getEndpoints() {
        return runs;
    }

    public void setEndpoints(List<Run> runs) {
        this.runs = runs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentedName() {
        return presentedName;
    }

    public void setPresentedName(String presentedName) {
        this.presentedName = presentedName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Contract> getProducerContracts() {
        return producerContracts;
    }

    public void setProducerContracts(List<Contract> producerContracts) {
        this.producerContracts = producerContracts;
    }

    public List<Contract> getConsumerContracts() {
        return consumerContracts;
    }

    public void setConsumerContracts(List<Contract> consumerContracts) {
        this.consumerContracts = consumerContracts;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }
}
