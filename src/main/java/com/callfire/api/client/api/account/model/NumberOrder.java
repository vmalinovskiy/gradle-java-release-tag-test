package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class NumberOrder extends CallfireModel {
    private Long id;
    private Status status;
    private Date created;
    private Double totalCost;
    private NumberOrderItem localNumbers;
    private NumberOrderItem tollFreeNumbers;
    private NumberOrderItem keywords;

    public enum Status {
        NEW, PROCESSING, FINISHED, ERRORED, VOID, WAIT_FOR_PAYMENT, ADJUSTED, APPROVE_TIER_ONE,
        APPROVE_TIER_TWO, REJECTED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public NumberOrderItem getLocalNumbers() {
        return localNumbers;
    }

    public void setLocalNumbers(NumberOrderItem localNumbers) {
        this.localNumbers = localNumbers;
    }

    public NumberOrderItem getTollFreeNumbers() {
        return tollFreeNumbers;
    }

    public void setTollFreeNumbers(NumberOrderItem tollFreeNumbers) {
        this.tollFreeNumbers = tollFreeNumbers;
    }

    public NumberOrderItem getKeywords() {
        return keywords;
    }

    public void setKeywords(NumberOrderItem keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("status", status)
            .append("created", created)
            .append("totalCost", totalCost)
            .append("localNumbers", localNumbers)
            .append("tollFreeNumbers", tollFreeNumbers)
            .append("keywords", keywords)
            .toString();
    }
}
