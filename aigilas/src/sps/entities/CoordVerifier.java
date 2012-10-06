package sps.entities;

import sps.bridge.EntityType;
import sps.core.Point2;
import sps.core.SpxManager;

public class CoordVerifier {
    public static boolean isValid(Point2 position) {
        return (position.PosX >= 0 && position.PosY >= 0 && position.PosX < SpxManager.VirtualWidth && position.PosY < SpxManager.VirtualHeight);
    }

    public static boolean isBlocked(Point2 target)

    {
        return EntityManager.get().isLocationBlocked(target);
    }

    public static boolean contains(Point2 target, EntityType type)

    {
        return EntityManager.get().anyContains(target, type);
    }
}
