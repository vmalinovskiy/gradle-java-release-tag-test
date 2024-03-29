package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.keywords.model.LeaseStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class NumberLease extends Number {
    private Date leaseBegin;
    private Date leaseEnd;
    private Boolean autoRenew;
    private LeaseStatus status;
    private FeatureStatus callFeatureStatus;
    private List<String> labels;

    private FeatureStatus textFeatureStatus;

    public enum FeatureStatus {
        UNSUPPORTED, PENDING, DISABLED, ENABLED
    }

    public Date getLeaseBegin() {
        return leaseBegin;
    }

    public void setLeaseBegin(Date leaseBegin) {
        this.leaseBegin = leaseBegin;
    }

    public Date getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(Date leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public LeaseStatus getStatus() {
        return status;
    }

    public FeatureStatus getCallFeatureStatus() {
        return callFeatureStatus;
    }

    public void setCallFeatureStatus(FeatureStatus callFeatureStatus) {
        this.callFeatureStatus = callFeatureStatus;
    }

    public FeatureStatus getTextFeatureStatus() {
        return textFeatureStatus;
    }

    public void setTextFeatureStatus(FeatureStatus textFeatureStatus) {
        this.textFeatureStatus = textFeatureStatus;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("leaseBegin", leaseBegin)
            .append("leaseEnd", leaseEnd)
            .append("autoRenew", autoRenew)
            .append("status", status)
            .append("callFeatureStatus", callFeatureStatus)
            .append("textFeatureStatus", textFeatureStatus)
            .append("labels", labels)
            .toString();
    }
}
