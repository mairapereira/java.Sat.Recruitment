package sat.recruitment.api.services.domain;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PremiumUser extends User {

    @Override
    public void chargeGift() {
        var gift = 0d;
        if (this.getMoney() > 100) {
            gift = this.getMoney() * 2;
        }
        this.setMoney(this.getMoney() + gift);
    }
}
