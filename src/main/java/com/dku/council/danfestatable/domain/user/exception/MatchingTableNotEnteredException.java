package com.dku.council.danfestatable.domain.user.exception;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class MatchingTableNotEnteredException extends LocalizedMessageException {

    public MatchingTableNotEnteredException() {
        super(HttpStatus.BAD_REQUEST, "invalid.matching-table");
    }
}
