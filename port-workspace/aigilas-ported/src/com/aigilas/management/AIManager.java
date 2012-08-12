package com.aigilas.management;
    public class AIManager
    {
        public static boolean IsClosestPlayerNorth(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetLocation().PosY < source.GetLocation().PosY)
            {
                return true;
            }
            return false;
        }

        public static boolean IsClosestPlayerSouth(Player source)
        {
            IEntity target = GetClosest(source);
            if (target.GetLocation().PosY > source.GetLocation().PosY)
            {
                return true;
            }
            return false;
        }

        public static boolean IsClosestPlayerEast(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetLocation().PosX > source.GetLocation().PosX)
            {
                return true;
            }
            return false;
        }

        public static boolean IsClosestPlayerWest(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetLocation().PosX < source.GetLocation().PosX)
            {
                return true;
            }
            return false;
        }

        private static Player GetClosest(Player source)
        {
            float minDist = Float.MAX_VALUE;
            Player result = null;
            for(IActor player:EntityManager.GetActors(AigilasActorType.PLAYER))
            {
                double dist = Math.abs(player.GetLocation().PosX - player.GetLocation().PosX) + Math.abs(player.GetLocation().PosY - player.GetLocation().PosY);
                if (dist < minDist)
                {
                    result = (Player)player;
                }
            }
            return result;
        }
    }