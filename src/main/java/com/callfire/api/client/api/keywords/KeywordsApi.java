package com.callfire.api.client.api.keywords;

import com.callfire.api.client.*;
import com.callfire.api.client.api.keywords.model.Keyword;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ModelType.listHolderOf;
import static com.callfire.api.client.ModelType.of;

/**
 * Represents rest endpoint /keywords
 *
 * @since 1.0
 */
public class KeywordsApi {
    private static final String KEYWORDS_PATH = "/keywords";
    private static final String KEYWORD_AVAILABLE_PATH = "/keywords/{}/available";

    private RestApiClient client;

    public KeywordsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find keywords for purchase on the CallFire platform. If a keyword appears in the response,
     * it is available for purchase.
     *
     * @param keywords keywords to find
     * @return available keywords
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Keyword> find(List<String> keywords) {
        List<NameValuePair> queryParams = new ArrayList<>(keywords.size());
        for (String keyword : keywords) {
            queryParams.add(new BasicNameValuePair("keywords", keyword));
        }
        return client.get(KEYWORDS_PATH, listHolderOf(Keyword.class), queryParams).getItems();
    }

    /**
     * Find an individual keyword for purchase on the CallFire platform.
     *
     * @param keyword keyword to check status of
     * @return true if keyword is available, otherwise false
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Boolean isAvailable(String keyword) {
        Validate.notBlank(keyword, "keyword cannot be blank");
        return client.get(KEYWORD_AVAILABLE_PATH.replaceFirst(PLACEHOLDER, keyword), of(Boolean.class));
    }
}
