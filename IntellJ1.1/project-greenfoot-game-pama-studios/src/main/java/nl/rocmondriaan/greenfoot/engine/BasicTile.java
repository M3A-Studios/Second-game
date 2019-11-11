package nl.rocmondriaan.greenfoot.engine;

import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class BasicTile extends Actor {

    public boolean isSolid = false;
    public int _id;

    protected static int id;

    public TileType type = TileType.DEFAULT;
    protected int mapIcon = -1;
    protected int mapID = -1;
    protected int colom = -1;
    protected int row = -1;
    protected boolean slope = false;
    protected int slopeX = 1;
    protected int slopeY = -1;

    /**
     * Constructor of the tile. Creates a tile based on image, width and height
     *
     * @param image Path to the image file
     * @param width Width of the tile
     * @param heigth Height of the tile
     */
    public BasicTile(String image, int width, int heigth) {
        super();
        setImage(image);
        getImage().scale(width, heigth);
        if (CollisionEngine.DEBUG) {
            getImage().drawString("ID: " + id, 10, 10);
        }
        _id = id;
        id++;
    }

    /**
     * Constructor for the use of a type
     *
     * @param image Path to the image file
     * @param width Width of the tile
     * @param heigth Height of the tile
     * @param type The type of the tile
     */
    public BasicTile(String image, int width, int heigth, TileType type) {
        this(image, width, heigth);
        this.type = type;
    }

    @Override
    public String toString() {
        return "id: " + _id + "\n"
                + "X: " + getX() + "\n"
                + "Y: " + getY() + "\n"
                + "Type: " + getType();
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public int getMapIcon() {
        return mapIcon;
    }

    public void setMapIcon(int mapIcon) {
        this.mapIcon = mapIcon;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public int getColom() {
        return colom;
    }

    public void setColom(int colom) {
        this.colom = colom;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isSlope() {
        return slope;
    }

    public void setSlope(boolean slope) {
        this.slope = slope;
    }

    public int getSlopeX() {
        return slopeX;
    }

    public void setSlopeX(int slopeX) {
        this.slopeX = slopeX;
    }

    public int getSlopeY() {
        return slopeY;
    }

    public void setSlopeY(int slopeY) {
        this.slopeY = slopeY;
    }
}
