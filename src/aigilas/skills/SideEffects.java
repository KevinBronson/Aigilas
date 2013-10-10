package aigilas.skills;

import aigilas.creatures.BaseCreature;
import aigilas.entities.SkillEffect;
import sps.bridge.SpriteType;
import sps.core.Point2;
import sps.entities.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class SideEffects {
    protected final BaseSkill _parent;

    protected final AnimationType _animation;
    protected final List<SkillEffect> _effectGraphics = new ArrayList<SkillEffect>();
    protected SpriteType _effectSprite;
    protected final float _effectStrength;
    protected boolean _isPersistent = false;

    public SideEffects(SpriteType effectGraphic, AnimationType animation, BaseSkill parent) {
        _parent = parent;
        _effectStrength = parent.components().getStrength();
        _effectSprite = effectGraphic;
        _animation = animation;
    }

    public void Generate(Point2 gridLocation, Point2 velocity, BaseCreature source) {
        SkillEffect effect = new SkillEffect(gridLocation, velocity, source, _parent);
        _effectGraphics.add(effect);
        EntityManager.get().addEntity(effect);
    }

    public SkillEffect getFirstGraphic() {
        if (_effectGraphics.size() > 0) {
            return _effectGraphics.get(0);
        }
        return null;
    }

    public SpriteType getSpriteType() {
        return _effectSprite;
    }

    public AnimationType getAnimationType() {
        return _animation;
    }
}
