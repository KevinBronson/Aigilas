package xna.wrapper;import org.newdawn.slick.Image;public class SpriteBatch {	public SpriteBatch(GraphicsDevice graphicsDevice) {		// TODO Auto-generated constructor stub	}	public void Draw(Image image, Rectangle subImage, Rectangle _currentCell,			Object multiply, float f, Vector2 zero, SpriteEffects none,			float depth) {		image.draw()	}	public void Draw(Image image, Vector2 position, Rectangle rectangle,			Color color, float f, Vector2 zero2, int i, SpriteEffects none,			float depth) {		// TODO Auto-generated method stub	}	public void Draw(Image image, Vector2 position, Rectangle scale,			Color black, float f, Vector2 zero2, Vector2 center,			SpriteEffects none, float depth) {		// TODO Auto-generated method stub	}	public void Draw(Image image, Vector2 vector, Color color) {		// TODO Auto-generated method stub	}	public void DrawString(SpriteFont font, String contents, Vector2 _position,			Color black, int i, float fontCenter, float f, SpriteEffects none,			float actiontextbg) {		// TODO Auto-generated method stub	}	public void DrawString(SpriteFont font, String contents, Vector2 position,			Color color, int i, Vector2 j, float alpha, SpriteEffects effects,			float depth) {		// TODO Auto-generated method stub	}}