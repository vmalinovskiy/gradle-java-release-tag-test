package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * Request object for GET /calls or /texts
 */
public abstract class FindCallsTextsRequest extends FindRequest {
    protected Long campaignId;
    protected Long batchId;
    protected String fromNumber;
    protected String toNumber;
    protected String label;
    protected Boolean inbound;

    protected Date intervalBegin;
    protected Date intervalEnd;
    protected List<Long> id;

    /**
     * Get id of broadcast
     *
     * @return id of broadcast
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * Get id of contact batch
     *
     * @return id of contact batch
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * Get phone number call/text was sent from
     *
     * @return phone number call/text was sent from
     */
    public String getFromNumber() {
        return fromNumber;
    }

    /**
     * Get phone number call/text was sent to
     *
     * @return phone number call/text was sent to
     */
    public String getToNumber() {
        return toNumber;
    }

    /**
     * Get label assigned with call/text
     *
     * @return label assigned with call/text
     */
    public String getLabel() {
        return label;
    }

    /**
     * Is inbound call/text
     *
     * @return true if call/text inbound, otherwise false
     */
    public Boolean getInbound() {
        return inbound;
    }

    /**
     * Get beginning of time interval
     *
     * @return beginning of time interval
     */
    public Date getIntervalBegin() {
        return intervalBegin;
    }

    /**
     * Get end of time interval
     *
     * @return end of time interval
     */
    public Date getIntervalEnd() {
        return intervalEnd;
    }

    /**
     * Get particular text ids
     *
     * @return list of text ids
     */
    public List<Long> getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("batchId", batchId)
            .append("fromNumber", fromNumber)
            .append("toNumber", toNumber)
            .append("label", label)
            .append("inbound", inbound)
            .append("intervalBegin", intervalBegin)
            .append("intervalEnd", intervalEnd)
            .append("id", id)
            .toString();
    }

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class CallsTextsBuilder<B extends CallsTextsBuilder, R extends FindCallsTextsRequest>
        extends FindRequestBuilder<B, R> {

        public CallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Set E.164 number that calls/texts are from
         *
         * @param fromNumber phone number text was sent from
         * @return builder self reference
         */
        public B fromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return (B) this;
        }

        /**
         * Set label assigned with call/text
         *
         * @param label label assigned with call/text
         * @return builder self reference
         */
        public B label(String label) {
            request.label = label;
            return (B) this;
        }

        /**
         * Query for calls/texts inside of a particular campaign.
         *
         * @param campaignId id of campaign
         * @return builder self reference
         */
        public B campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return (B) this;
        }

        /**
         * Query for calls/texts which were created with particular contact batch.
         *
         * @param batchId id of contact batch
         * @return builder self reference
         */
        public B batchId(Long batchId) {
            request.batchId = batchId;
            return (B) this;
        }

        /**
         * Set E.164 number that calls/texts are to
         *
         * @param toNumber phone number text was sent to
         * @return builder self reference
         */
        public B toNumber(String toNumber) {
            request.toNumber = toNumber;
            return (B) this;
        }

        /**
         * Set inbound call/text
         *
         * @param inbound true if call/text inbound
         * @return builder self reference
         */
        public B inbound(Boolean inbound) {
            request.inbound = inbound;
            return (B) this;
        }

        /**
         * Set beginning of time interval
         *
         * @param intervalBegin beginning of time interval
         * @return builder self reference
         */
        public B intervalBegin(Date intervalBegin) {
            request.intervalBegin = intervalBegin;
            return (B) this;
        }

        /**
         * Set end of time interval
         *
         * @param intervalEnd end of time interval
         * @return builder self reference
         */
        public B intervalEnd(Date intervalEnd) {
            request.intervalEnd = intervalEnd;
            return (B) this;
        }

        /**
         * Set particular call/text ids to filter
         *
         * @param id text ids to filter
         * @return builder self reference
         */
        public B id(List<Long> id) {
            request.id = id;
            return (B) this;
        }
    }
}
