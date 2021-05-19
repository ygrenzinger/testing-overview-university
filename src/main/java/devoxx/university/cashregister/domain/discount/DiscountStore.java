package devoxx.university.cashregister.domain.discount;

import java.util.List;
import java.util.Optional;

public interface DiscountStore {
    Optional<VolumeDiscount> getVolumeDiscount(String fruit);

    List<ApplicableBasketDiscount> getBasketDiscount();
}
