package com.dku.council.danfestatable.domain.matchtable.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class AlreadyMatchingTableException extends LocalizedMessageException {

    public AlreadyMatchingTableException() {
        super(HttpStatus.BAD_REQUEST, "already.matching-table");
    }
}
