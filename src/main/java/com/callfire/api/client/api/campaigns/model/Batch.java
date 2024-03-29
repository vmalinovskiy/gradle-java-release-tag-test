package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Batch extends CallfireModel {
    private Long id;
    private String name;
    private Status status;
    private Long broadcastId;
    private Date created;
    private Integer size;
    private Integer remaining;
    private Boolean enabled;

    public enum Status {
        NEW, VALIDATING, ERRORS, SOURCE_ERROR, ACTIVE,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public Date getCreated() {
        return created;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("name", name)
            .append("status", status)
            .append("broadcastId", broadcastId)
            .append("created", created)
            .append("size", size)
            .append("remaining", remaining)
            .append("enabled", enabled)
            .toString();
    }
}
