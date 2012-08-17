package aigilas;import org.newdawn.slick.GameContainer;import org.newdawn.slick.Graphics;import org.newdawn.slick.SlickException;import org.newdawn.slick.state.BasicGameState;import org.newdawn.slick.state.StateBasedGame;import spx.core.XnaManager;import spx.devtools.DevConsole;import spx.io.Client;import spx.io.Input;import spx.particles.ParticleEngine;import spx.sprites.SpriteSheetManager;import spx.states.StateManager;import spx.text.TextManager;import xna.wrapper.Buttons;import xna.wrapper.Color;import xna.wrapper.ContentManager;import xna.wrapper.GamePad;import xna.wrapper.GraphicsDevice;import xna.wrapper.GraphicsDeviceManager;import xna.wrapper.MediaPlayer;import xna.wrapper.PlayerIndex;import xna.wrapper.Resolution;import xna.wrapper.SpriteBatch;import aigilas.management.Commands;import aigilas.management.InputInitializer;import aigilas.management.SpriteInitializer;import aigilas.states.MainMenuState;public class Aigilas extends BasicGameState {	private final GraphicsDeviceManager graphics;	private final ContentManager Content;	private final GraphicsDevice GraphicsDevice;	private boolean IsRunning = true;	public Aigilas() {		Client.Get();		graphics = new GraphicsDeviceManager(this);		XnaManager.SetupCamera(graphics, false);		Content = new ContentManager();		GraphicsDevice = new GraphicsDevice();		Content.RootDirectory = "Content";	}	@Override	public void init(GameContainer container, StateBasedGame game)			throws SlickException {		XnaManager.SetContentManager(this.Content);		Input.Setup(new InputInitializer());		SpriteSheetManager.Setup(new SpriteInitializer());		StateManager.LoadState(new MainMenuState());		ParticleEngine.Reset();		XnaManager.Renderer = new SpriteBatch(GraphicsDevice);		StateManager.LoadContent();		TextManager.LoadContent();		// $$$MediaPlayer.Play(Content.Load<Song>("MainTheme"));		MediaPlayer.IsRepeating = true;	}	@Override	public void render(GameContainer container, StateBasedGame game, Graphics g)			throws SlickException {		GraphicsDevice.clear(Color.White);		Resolution.BeginDraw();		StateManager.Draw();		ParticleEngine.Draw();		TextManager.Draw();		DevConsole.Get().Draw();	}	@Override	public void update(GameContainer container, StateBasedGame game, int delta)			throws SlickException {		Input.Update();		if (Input.DetectState(Commands.ToggleDevConsole, Client.Get()				.GetFirstPlayerIndex())) {			DevConsole.Get().Toggle();		}		if (Client.Get().NextTurn()) {			for (int ii = 0; ii < 4; ii++) {				PlayerIndex player = PlayerIndex.values()[ii];				if (GamePad.GetState(player).IsPressed(Buttons.Back)						&& GamePad.GetState(player).IsPressed(Buttons.Start)) {					SetIsRunning(false);				}			}			ParticleEngine.Update();			StateManager.Update();			TextManager.Update();			Client.Get().PrepareForNextTurn();		} else {			Client.Get().HeartBeat();		}		if (!IsRunning) {			container.exit();		}	}	private void SetIsRunning(boolean isRunning) {		IsRunning = isRunning;	}	@Override	public int getID() {		// TODO Auto-generated method stub		return 0;	}}