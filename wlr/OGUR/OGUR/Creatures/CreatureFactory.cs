﻿using System;
using OGUR.Collision;
using OGUR.GameObjects;

namespace OGUR.Creatures
{
    public class CreatureFactory
    {
        private static int s_playerCount = 0;

        public static ICreature Create(CreatureType type, int x, int y)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return (Player) GameplayObjectManager.AddObject(new Player(x, y, s_playerCount++));
                case CreatureType.GOBLIN:
                    return (NormalCreature)GameplayObjectManager.AddObject(new NormalCreature(type, new Point(x, y), new Stats(50, 10, 1, 1, 1, 20, 1,10,1.5,Stats.DefaultMoveSpeed,Stats.DefaultCoolDown*3)));
                default:
                   throw new Exception("Attempted to create an undefined Creature type in the CreatureFactory.");
            }
        }

        public static void ResetPlayerCount()
        {
            s_playerCount = 0;
        }

        public static void IncreasePlayerCount()
        {
            s_playerCount ++;
        }
    }
}