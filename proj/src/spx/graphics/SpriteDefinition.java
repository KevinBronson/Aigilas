package spx.graphics;

import aigilas.management.SpriteType;

public class SpriteDefinition {
	public SpriteType Type;
	public SpriteInfo Info;

	public SpriteDefinition(SpriteType type, int index, int frames) {
		Type = type;
		Info = new SpriteInfo(index, frames);
	}
}