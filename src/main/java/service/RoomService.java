package service;


import com.google.inject.Inject;
import com.google.inject.Provides;
import model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;


public class RoomService {
    private final PlayerService playerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    @Inject
    public RoomService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Provides
    public void CreateRoom(HashMap<String, Object> body) {
        System.out.println("entered create room");
        Room room = new Room();
        room.setId();
        room.setName((String) body.get("name"));
        room.setAdmin(playerService.findPlayer((body.get("admin")).toString().substring(4, 12)));
        room.setGames(new ArrayList<>());
        room.setPlayers(new ArrayList<>());
        room.setPublic((Boolean) body.get("isPublic"));
        room.setPlayerCount((Integer) body.get("playerCount"));
        GameService.openRooms.put(room.getId(), room);
    }
}
