package aigilas.entities;

import aigilas.creatures.BaseCreature;
import sps.bridge.EntityTypes;
import sps.bridge.Sps;
import sps.entities.Entity;

public class Extensions {
    public static BaseCreature isCreature(Entity entity) {
        if (entity.getEntityType() == EntityTypes.get(Sps.Entities.Actor)) {
            return (BaseCreature) entity;
        }
        return null;
    }
}
