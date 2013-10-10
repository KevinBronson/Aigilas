package aigilas.dungeons;

import aigilas.Aigilas;
import aigilas.AigilasConfig;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.SpsConfig;

import java.util.ArrayList;
import java.util.List;

public class FloorPlan {
    private final int minRoomHeight = 2;
    private final int maxRoomHeight = 6;
    private final int minRoomWidth = 2;
    private final int maxRoomWidth = 6;
    private final int buffer = 2;


    private List<Room> rooms;
    private EntityType[][] tiles;

    public FloorPlan(boolean altarRoom) {
        rooms = new ArrayList<Room>();
        tiles = new EntityType[SpsConfig.get().tileMapWidth][SpsConfig.get().tileMapHeight];
        for (int ii = 0; ii < SpsConfig.get().tileMapWidth; ii++) {
            for (int jj = 0; jj < SpsConfig.get().tileMapHeight; jj++) {
                tiles[ii][jj] = EntityTypes.get(Sps.Entities.Floor);
            }
        }

        rooms.add(new Room(SpsConfig.get().tileMapHeight, SpsConfig.get().tileMapWidth, 0, 0));
        if (!altarRoom) {
            int roomsToPlace = AigilasConfig.get().minRoomCount + RNG.next(0, AigilasConfig.get().maxRoomCount);
            int attemptCount = 0;
            while (attemptCount < 100 && roomsToPlace > 0) {
                attemptCount++;
                int width = minRoomWidth + RNG.next(0, maxRoomWidth - minRoomHeight);
                int height = minRoomHeight + RNG.next(0, maxRoomHeight - minRoomHeight);
                int x = RNG.next(buffer, SpsConfig.get().tileMapWidth - width - buffer);
                int y = RNG.next(buffer, SpsConfig.get().tileMapHeight - height - buffer);

                roomsToPlace--;
                Room nextRoom = new Room(height, width, x, y);
                boolean collides = false;
                for (int ii = 1; ii < rooms.size(); ii++) {
                    Room room = rooms.get(ii);
                    if (room.collides(nextRoom)) {
                        collides = true;
                    }
                }
                if (!collides && !nextRoom.isBad()) {
                    rooms.add(nextRoom);
                }
            }
        }
    }

    public List<Tile> getTiles() {
        int roomCount = 0;
        ArrayList<OrientedPoint> entrances = new ArrayList<OrientedPoint>();
        for (Room room : rooms) {
            ArrayList<OrientedPoint> possibleEntrances = new ArrayList<OrientedPoint>();
            for (int ii = room.X; ii < room.RightSide; ii++) {
                for (int jj = room.Y; jj < room.BottomSide; jj++) {
                    if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1) {
                        if (!room.Corners.contains(new Point2(ii, jj))) {
                            if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < SpsConfig.get().tileMapWidth)) {
                                if (is(ii - 1, jj, EntityTypes.get(Sps.Entities.Floor)) && is(ii + 1, jj, EntityTypes.get(Sps.Entities.Floor))) {
                                    possibleEntrances.add(new OrientedPoint(ii, jj, true));
                                }
                            }
                            if ((jj == room.Y && jj > 0) || (jj == room.BottomSide && jj < SpsConfig.get().tileMapHeight)) {
                                if (is(ii, jj - 1, EntityTypes.get(Sps.Entities.Floor)) && is(ii, jj + 1, EntityTypes.get(Sps.Entities.Floor))) {
                                    possibleEntrances.add(new OrientedPoint(ii, jj));
                                }
                            }
                        }
                        tiles[ii][jj] = EntityTypes.get(Aigilas.Entities.Wall);
                    }
                    else {
                        tiles[ii][jj] = EntityTypes.get(Sps.Entities.Floor);
                    }
                }
            }
            if (roomCount > 0 && possibleEntrances.size() > 0) {
                int index = RNG.next(0, possibleEntrances.size() - 1);
                OrientedPoint entrance = possibleEntrances.get(index);
                if (!is(entrance.X, entrance.Y, EntityTypes.get(Sps.Entities.Floor))) {
                    entrances.add(entrance);
                }
            }
            roomCount++;
        }
        for (OrientedPoint entrance : entrances) {
            int pivot = (entrance.isHorizontal()) ? entrance.X : entrance.Y;
            int max = (entrance.isHorizontal()) ? SpsConfig.get().tileMapWidth - 1 : SpsConfig.get().tileMapHeight;
            for (int ii = pivot - 2; ii < pivot + 2; ii++) {
                if (ii < 1 || ii > max) {
                    continue;
                }
                int startX = (entrance.isHorizontal()) ? ii : entrance.X;
                int startY = (entrance.isHorizontal()) ? entrance.Y : ii;
                Point2 target = new Point2(startX, startY);
                tiles[target.GridX][target.GridY] = EntityTypes.get(Sps.Entities.Floor);
            }
        }

        List<Tile> walls = new ArrayList<Tile>();
        for (int ii = 0; ii < SpsConfig.get().tileMapWidth; ii++) {
            for (int jj = 0; jj < SpsConfig.get().tileMapHeight; jj++) {
                EntityType tile = tiles[ii][jj];
                if (ii > 0 && jj > 0 && ii < SpsConfig.get().tileMapWidth - 1 && jj < SpsConfig.get().tileMapHeight - 1) {
                    tile = RNG.percent(AigilasConfig.get().wallDecayPercent) ? EntityTypes.get(Sps.Entities.Floor) : tile;
                }
                walls.add(new Tile(tile, ii, jj));
            }
        }

        return walls;
    }

    private boolean is(int x, int y, EntityType type) {
        if (tiles[x][y] == null) {
            return false;
        }
        return tiles[x][y] == type;
    }
}
