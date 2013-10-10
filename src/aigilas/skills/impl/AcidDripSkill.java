package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AcidDripSkill extends BaseSkill {
    public AcidDripSkill() {
        super(SkillId.Acid_Drip, AnimationType.STATIONARY);
    }

    @Override
    public void affect(BaseCreature target) {
        target.applyDamage(_id.Info.Magnitude, _source);
    }
}
