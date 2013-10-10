package aigilas.creatures.impl;

import aigilas.AigilasConfig;
import aigilas.creatures.Stats;
import aigilas.creatures.StatsRegistry;
import aigilas.entities.Elements;
import aigilas.gods.GodId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.ActorTypes;
import sps.bridge.Sps;
import sps.core.RNG;

import java.util.Arrays;
import java.util.List;

public class Player extends BaseEnemy {
    private static final List<Color> __colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.WHITE);

    public Player(int playerIndex) {
        super(ActorTypes.get(Sps.Actors.Player));
        _playerIndex = playerIndex;
        _graphic.setColor(__colors.get(_playerIndex));
        _attackColor = __colors.get(_playerIndex);
        setStrategy(StrategyFactory.create((AigilasConfig.get().activateTestBots) ? Strategy.TestBot : Strategy.ControlledByPlayer, this));
        _baseStats = StatsRegistry.get().baseStats(_actorType);
        _maxStats = new Stats(_baseStats);
        assignGod(GodId.values()[RNG.next(0, GodId.values().length)].getInstance());
        _composition.add(Elements.Physical);
    }
}
