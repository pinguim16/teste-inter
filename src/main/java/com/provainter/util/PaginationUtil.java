package com.provainter.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * Classe para lidar com paginação
 * A paginação usa os mesmos princípios que <a href="https://developer.github.com/v3/#pagination">
 *
 * @author Cesar
 * @see com.provainter.util
 * @since 06/06/2020
 */
public class PaginationUtil {

    private PaginationUtil() {
    }

    public static HttpHeaders generacaoPaginacaoHttpHeaders(Page<?> page, String baseUrl) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + geracaoUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"Proximo\",";
        }
        // anterior link
        if ((page.getNumber()) > 0) {
            link += "<" + geracaoUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"Anterior\",";
        }
        // ultimo and primeiro link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + geracaoUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"Ultimo\",";
        link += "<" + geracaoUri(baseUrl, 0, page.getSize()) + ">; rel=\"Primeiro\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    private static String geracaoUri(String baseUrl, int page, int size) {
        return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
    }
}
