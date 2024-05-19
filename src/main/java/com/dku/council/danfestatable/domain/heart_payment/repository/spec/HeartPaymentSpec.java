package com.dku.council.danfestatable.domain.heart_payment.repository.spec;

import com.dku.council.danfestatable.domain.heart_payment.model.PaymentStatus;
import com.dku.council.danfestatable.domain.heart_payment.model.entity.HeartPayment;
import org.springframework.data.jpa.domain.Specification;

public class HeartPaymentSpec {

    public static Specification<HeartPayment> withStatus(PaymentStatus status) {
        if (status == null) {
            return Specification.where(null);
        }

        return (root, query, builder) ->
                builder.equal(root.get("paymentStatus"), status);
    }
}
