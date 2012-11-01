package aigilas.hud;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.core.Settings;
import sps.graphics.Renderer;

public class SkillHud extends BaseHud {
    private static final String __separator = "|";

    private Point2 _energyPosition = new Point2();

    public SkillHud(BaseCreature owner) {
        super(owner, Settings.get().spriteWidth, Renderer.VirtualHeight / 4);
        _energyPosition = new Point2(getMeterAnchor().X, getMeterAnchor().Y - Renderer.VirtualHeight / 4);
    }

    private int calculateHeight(StatType statType) {
        return (int) (((float) _parent.get(statType) / _parent.getMax(statType)) * _dimensions.Y);
    }

    private int costOfCurrentSkill() {
        return (int) (_parent.getCurrentSkillCost() / (float) _parent.getMax(StatType.Energy) * _dimensions.Y);
    }

    private String getSkillStrings() {
        return "S:" + _parent.getActiveSkillName() + __separator + "Z:" + _parent.getHotSkillName(Commands.HotSkill1) + __separator + "X:" + _parent.getHotSkillName(Commands.HotSkill2) + __separator + "C:" + _parent.getHotSkillName(Commands.HotSkill3) + __separator;
    }

    public void update() {
        if (_isVisible) {
            _textHandler.update();
            _textHandler.clear();
            _textHandler.writeDefault(getSkillStrings(), 0, 0, getSkillAnchor());
        }
    }

    @Override
    public void draw() {
        if (!_isVisible) {
            return;
        }

        Renderer.get().draw(_menuBase, getMeterAnchor(), DrawDepth.HudBG, Color.GREEN, Settings.get().spriteWidth, calculateHeight(StatType.Health));
        Renderer.get().draw(_menuBase, _energyPosition, DrawDepth.HudBG, Color.BLUE, Settings.get().spriteWidth, calculateHeight(StatType.Energy));
        Renderer.get().draw(_menuBase, _energyPosition, DrawDepth.HudBG, Color.YELLOW, Settings.get().spriteWidth / 2, costOfCurrentSkill());

        _textHandler.draw();
    }
}
