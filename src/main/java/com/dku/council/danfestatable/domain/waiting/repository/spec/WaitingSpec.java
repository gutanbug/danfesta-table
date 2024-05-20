package com.dku.council.danfestatable.domain.waiting.repository.spec;

import com.dku.council.danfestatable.domain.waiting.model.WaitingStatus;
import com.dku.council.danfestatable.domain.waiting.model.entity.Waiting;
import org.springframework.data.jpa.domain.Specification;

public class WaitingSpec {

    public static Specification<Waiting> withStatus(WaitingStatus status) {
        if (status == null) {
            return Specification.where(null);
        }

        return (root, query, builder) ->
                builder.equal(root.get("waitingStatus"), status);
    }
}
