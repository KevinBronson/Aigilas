package aigilas.entities;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.entities.Entity;
import sps.particles.Emitter;
import sps.particles.ParticleEngine;

public class ReactionMarker extends Entity {
    private final BaseCreature _parent;
    private final Emitter emitter;

    public ReactionMarker(BaseCreature source, Elements elementId) {
        initialize(source.getLocation(), null, EntityTypes.get(Aigilas.Entities.Combo_Marker), DrawDepths.get(Aigilas.Entities.Combo_Marker));
        _graphic.setAlpha(0);
        _parent = source;
        emitter = ParticleEngine.emit(sps.particles.behaviors.RotateBehavior.getInstance(), _parent, elementId.Tint);
        emitter.setSprite(SpriteTypes.get(Aigilas.Entities.Combo_Marker));
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
    }

    @Override
    public void setInactive() {
        super.setInactive();
        emitter.clear();
    }
}
