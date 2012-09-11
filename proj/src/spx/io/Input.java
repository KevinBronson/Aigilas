package spx.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import spx.net.Client;
import aigilas.management.Commands;

import com.badlogic.gdx.Gdx;

public class Input {
	// $$$ FIXME (Integer -> PlayerId) Maps a playerId to a context
	private static HashMap<Integer, Contexts> __contexts;

	// Lists what commands are locked for a given player
	private static List<CommandLock> __locks = new ArrayList<CommandLock>();

	// The commands that cannot be used by simply holding down the command's
	// input depending on the given context
	private static HashMap<Commands, Contexts> __lockOnPress = new HashMap<>();
	private static HashMap<Commands, Keys> _keyboardMapping = new HashMap<>();
	private static HashMap<Commands, Buttons> _gamePadMapping = new HashMap<>();
	private static boolean __isInputActive = false;

	public static void Setup(IInputInitializer initializer) {
		__contexts = new HashMap<>();
		__contexts.put(0, Contexts.Free);
		__contexts.put(1, Contexts.Free);
		__contexts.put(2, Contexts.Free);
		__contexts.put(3, Contexts.Free);

		for (CommandDefinition command : initializer.GetCommands()) {
			_keyboardMapping.put(command.Command, command.Keyboard);
			_gamePadMapping.put(command.Command, command.Gamepad);
			if (command.LockContext != null) {
				__lockOnPress.put(command.Command, command.LockContext);
			}
		}
	}

	public static boolean DetectState(Commands command, int playerIndex) {
		/*
		 * boolean gamepadActive = GamePad.GetState(
		 * PlayerIndex.values()[playerIndex]).IsButtonDown(
		 * _gamePadMapping.get(command));
		 */
		// $$$
		boolean gamepadActive = false;
		return gamepadActive || (playerIndex == Client.Get().GetFirstPlayerIndex() && Gdx.input.isKeyPressed(_keyboardMapping.get(command).getKeyCode()));
	}

	private static boolean IsDown(Commands command, int playerIndex) {
		return Client.Get().IsActive(command, playerIndex);
	}

	public static boolean IsActive(Commands command, int playerIndex) {
		return IsActive(command, playerIndex, true);
	}

	public static boolean IsActive(Commands command, int playerIndex, boolean failIfLocked) {
		__isInputActive = IsDown(command, playerIndex);
		if (!__isInputActive && ShouldLock(command, playerIndex)) {
			Unlock(command, playerIndex);
		}

		if (IsLocked(command, playerIndex) && failIfLocked) {
			return false;
		}

		if (__isInputActive && ShouldLock(command, playerIndex)) {
			Lock(command, playerIndex);
		}

		return __isInputActive;
	}

	// If the key is marked to be locked on press and its lock context is
	// currently inactive
	private static boolean ShouldLock(Commands command, int playerIndex) {
		for (Commands key : __lockOnPress.keySet()) {
			if (key == command) {
				if (__lockOnPress.get(key) == __contexts.get(playerIndex) || (__lockOnPress.get(key) == Contexts.Nonfree && __contexts.get(playerIndex) != Contexts.Free) || __lockOnPress.get(key) == Contexts.All) {
					return true;
				}
			}
		}
		return false;
	}

	public static void SetContext(Contexts context, int playerIndex) {
		__contexts.put(playerIndex, context);
	}

	public static boolean IsContext(Contexts context, int playerIndex) {
		return __contexts.get(playerIndex) == context;
	}

	public static boolean IsLocked(Commands command, int playerIndex) {
		for (CommandLock pair : __locks) {
			if (pair.Command == command && pair.PlayerIndex == playerIndex) {
				return true;
			}
		}
		return false;
	}

	public static void Lock(Commands command, int playerIndex) {
		__locks.add(new CommandLock(command, playerIndex));
	}

	public static void Unlock(Commands command, int playerIndex) {
		for (int ii = 0; ii < __locks.size(); ii++) {
			if (__locks.get(ii).Command == command && __locks.get(ii).PlayerIndex == playerIndex) {
				__locks.remove(__locks.get(ii));
				ii--;
			}
		}
	}

	public static void Update() {
		// Remove command locks if the associated key/button isn't being pressed
		for (int ii = 0; ii < __locks.size(); ii++) {
			if (!IsDown(__locks.get(ii).Command, __locks.get(ii).PlayerIndex)) {
				__locks.remove(__locks.get(ii));
				ii--;
			}
		}

		for (Commands command : _keyboardMapping.keySet()) {
			Client.Get().SetState(command, Client.Get().GetFirstPlayerIndex(), DetectState(command, Client.Get().GetFirstPlayerIndex()));
		}
	}
}