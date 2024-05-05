package com.dku.council.danfestatable.domain.matchtable.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class MatchingTableNotFoundException extends LocalizedMessageException {

    public MatchingTableNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.matching-table");
    }
}
