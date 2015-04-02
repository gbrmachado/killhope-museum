package uk.ac.dur.group1.killhope_museum.dto;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.dto.json.JsonMap;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonPoint;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonRoom;

/**
 * Created by David on 02/04/2015.
 */
public class MapDTO
{
    private final Bitmap image;
    private final JsonMap map;

    public String getName()
    {
        return map.getTitle();
    }

    public Iterable<RoomDTO> getRooms()
    {
        List<JsonRoom> rooms = map.getRooms();
        ArrayList<RoomDTO> ret = new ArrayList<>();

        if(rooms == null)
            return ret;

        for(JsonRoom room : rooms)
        {
            try {
                ret.add(new RoomDTO(room));
            } catch (IllegalArgumentException e)
            {
                Log.e("Maps",String.format("Invalid room loaded: %s", e.getMessage()));
            }
        }
        return ret;
    }

    public int getRoomCount()
    {
        return map.getRooms().size();
    }

    public MapDTO(JsonMap map, Bitmap image)
    {
        this.image = image;
        this.map = map;
    }


    public Bitmap getImage()
    {
        return image;
    }

    /**
     * A scale factor is required for the points because the height of the image with points set if fixed,
     * but the image is scaled when passed to this.
     * @return
     */
    public double getScaleFactor()
    {
        double originalHeight = map.getHeight();
        double currentHeight = image.getHeight();
        return originalHeight / currentHeight;
    }

    public RoomDTO getRoom(String id)
    {
        for(RoomDTO room : this.getRooms())
        {
            if(room.getId().equals(id))
                return room;
        }
        throw new IllegalArgumentException(String.format("room: %s not found.",id));
    }


    public class RoomDTO
    {
        private final String id;
        private final List<String> rocks;
        private final List<Point> coordinates;

        public RoomDTO(String id, List<String> rocks, List<Point> coordinates) {
            if (id == null)
                throw new IllegalArgumentException("id is null");

            if(rocks == null)
                throw new IllegalArgumentException("rocks is null");

            if(coordinates == null || coordinates.size() == 0)
                throw new IllegalArgumentException("coordinates is " + coordinates == null ? "null" : "empty");

            this.id = id;
            this.rocks = rocks;
            this.coordinates = coordinates;
        }

        public RoomDTO(JsonRoom room)
        {
            this(room.getId(), room.getRocks(), JsonPoint.getPoints(room.getPoints()));
        }

        public String getId()
        {
            return id;
        }

        public Iterable<String> getRocks()
        {
            return rocks;
        }

        public boolean hasRockFilter()
        {
            return rocks.size() != 0;
        }

        public Iterable<Point> getCoordinates()
        {
            return coordinates;
        }
    }
}
