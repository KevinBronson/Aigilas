package aigilas.creatures.impl;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;
import sps.entities.EntityManager;
import sps.entities.IActor;

public class VaporCloud extends Minion {
    private BaseCreature _host = null;

    public VaporCloud() {
        super(ActorTypes.get(Aigilas.Actors.Minion));
        setStrategy(StrategyFactory.create(Strategy.MinionCloud, this));
        add(SkillId.Vapor_Cloud);
        _composition.add(Elements.Water);
        canMove = false;
    }

    @Override
    public void update() {
        super.update();
        if (_host == null) {
            for (IActor creature : EntityManager.get().getActorsAt(_location)) {
                if (creature != this) {
                    _host = (BaseCreature) creature;
                }
            }
            if (_host == null) {
                setInactive();
            }
        }
        if (_host != null) {
            setLocation(_host.getLocation());
        }
    }
}
