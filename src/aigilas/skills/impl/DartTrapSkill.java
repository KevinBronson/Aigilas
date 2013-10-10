package aigilas.skills.impl;

import aigilas.Aigilas;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.entities.SkillEffect;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.EntityTypes;
import sps.entities.Entity;

public class DartTrapSkill extends BaseSkill {
    public DartTrapSkill() {
        super(SkillId.Dart_Trap, AnimationType.RANGED);
        _components.setOnlyAffects(EntityTypes.get(Aigilas.Entities.Wall));
    }

    @Override
    public void cleanup(Entity target, SkillEffect source) {
        CreatureFactory.createMinion(_id, _source, source, target.getLocation());
    }
}
