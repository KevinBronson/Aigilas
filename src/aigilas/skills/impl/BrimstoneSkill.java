package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillFactory;
import aigilas.skills.SkillId;
import sps.core.Point2;

public class BrimstoneSkill extends BaseSkill {
    private final Point2 _direction = new Point2(0, 0);

    public BrimstoneSkill()

    {
        super(SkillId.Brimstone, AnimationType.STATIONARY);


    }

    @Override
    public void activate(BaseCreature source) {
        for (int ii = -1; ii < 2; ii++) {
            for (int jj = -1; jj < 2; jj++) {
                if (ii != 0 || jj != 0) {
                    _direction.reset(ii, jj);
                    source.setSkillVector(_direction);
                    SkillFactory.activate(SkillId.Fireball, source);

                }
            }
        }
    }
}
