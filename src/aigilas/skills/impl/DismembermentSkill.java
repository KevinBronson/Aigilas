package aigilas.skills.impl;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.ActorTypes;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.SpsConfig;

public class DismembermentSkill extends BaseSkill {
    public DismembermentSkill()

    {
        super(SkillId.Dismemberment, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature target)

    {
        super.activate(target);
        int openCell = RNG.next(1, SpsConfig.get().tileMapWidth - 1);
        for (int ii = 1; ii < SpsConfig.get().tileMapWidth - 1; ii++) {
            if (ii != openCell) {
                CreatureFactory.create(ActorTypes.get(Aigilas.Actors.Hand), new Point2(ii, 1));

            }

        }

    }

}
