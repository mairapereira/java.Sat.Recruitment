package sat.recruitment.api.services.domain;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SuperUser extends User {

    @Override
    public void chargeGift() {
        var gift = 0d;
        if (this.getMoney() > 100) {
            Double percentage = Double.valueOf("0.20");
            gift = this.getMoney() * percentage;
        }
        this.setMoney(this.getMoney() + gift);
    }
}
