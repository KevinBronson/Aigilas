package aigilas.skills.impl;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.ActorTypes;
import sps.entities.EntityManager;

public class BreakingWheelSkill extends BaseSkill {
    public BreakingWheelSkill()

    {
        super(SkillId.Breaking_Wheel, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {
        CreatureFactory.create(ActorTypes.get(Aigilas.Actors.Breaking_Wheel), EntityManager.get().getEmptyLocation());
    }

}
