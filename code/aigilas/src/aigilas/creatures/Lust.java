package aigilas.creatures;import spx.bridge.ActorType;import aigilas.entities.Elements;import aigilas.management.SpriteType;import aigilas.skills.SkillId;public class Lust extends AbstractCreature {	public Lust() {		super(ActorType.LUST, SpriteType.LUST);		Compose(Elements.FIRE);		Strengths(StatType.STRENGTH, StatType.STRENGTH);		Add(SkillId.BRIMSTONE);	}}