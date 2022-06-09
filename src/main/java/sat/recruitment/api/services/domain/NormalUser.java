package sat.recruitment.api.services.domain;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class NormalUser extends User {

    @Override
    public void chargeGift() {
        var percentage = 1d;
        var gift = 0d;

        if (this.getMoney() > 100) {
            percentage = Double.valueOf("0.12");
        }
        if (this.getMoney() > 10 && this.getMoney() < 100) {
            percentage = Double.valueOf("0.8");
        }
        gift = this.getMoney() * percentage;
        this.setMoney(this.getMoney() + gift);
    }
}
