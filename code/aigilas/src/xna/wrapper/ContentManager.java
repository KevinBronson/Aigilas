package xna.wrapper;import java.util.HashMap;import org.newdawn.slick.Image;import org.newdawn.slick.SpriteSheet;public class ContentManager {	static public void Load() {	}	public String RootDirectory;	private final HashMap<String, Integer> spriteIndices = new HashMap<>();	private SpriteSheet _spriteSheet;	public Image LoadTexture(String resourceName) {		return AssetManager.Get().GetImage(resourceName);	}	public Image LoadSprite(int verticalIndex) {		if (_spriteSheet == null) {			_spriteSheet = AssetManager.Get().GetSpriteSheet();		}		return _spriteSheet.getSprite(0, verticalIndex);	}	public SpriteFont LoadSpriteFont(String resourceName) {		// TODO Auto-generated method stub		return null;	}}