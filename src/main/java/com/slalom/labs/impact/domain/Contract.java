package com.slalom.labs.impact.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ted on 3/10/16.
 */
@Entity
public class Contract {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Service producer;
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Service consumer;
//    @Basic(optional = false)
//    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
//    @Basic(optional = false)
//    @Column(insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastEdited;
    @OneToMany(mappedBy = "contract", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Webhook> webhooks;
    private int timeLimitSeconds;

    @PrePersist
    protected void onCreate() {
        dateCreated = dateLastEdited = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateLastEdited = new Date();
    }

    protected Contract() {}

    public Contract(Service producer, Service consumer, List<Webhook> webhooks, int time_limit_seconds) {
        this.producer = producer;
        this.consumer = consumer;
        this.webhooks = webhooks;
    }

    public Service getProducer() {
        return producer;
    }

    public void setProducer(Service producer) {
        this.producer = producer;
    }

    public Service getConsumer() {
        return consumer;
    }

    public void setConsumer(Service consumer) {
        this.consumer = consumer;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastEdited() {
        return dateLastEdited;
    }

    public void setDateLastEdited(Date dateLastEdited) {
        this.dateLastEdited = dateLastEdited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }

    public int getTimeLimitSeconds() {
        return timeLimitSeconds;
    }

    public void setTimeLimitSeconds(int timeLimitSeconds) {
        this.timeLimitSeconds = timeLimitSeconds;
    }
}
