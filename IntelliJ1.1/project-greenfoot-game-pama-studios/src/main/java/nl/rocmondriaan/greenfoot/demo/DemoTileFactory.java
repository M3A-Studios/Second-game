package nl.rocmondriaan.greenfoot.demo;

import nl.rocmondriaan.greenfoot.engine.BasicTile;
import nl.rocmondriaan.greenfoot.engine.TileEngine;
import nl.rocmondriaan.greenfoot.engine.TileFactory;
import nl.rocmondriaan.greenfoot.engine.TileType;

/**
 *
 * @author R. Springer
 */
public class DemoTileFactory extends TileFactory {

    /**
     * Voorbeeld hoe je een TileFactory kan gebruiken. Hierin zit de toegevoegde
     * TileType zie case: 1, 2, 6, 7, 8, 9, 10
     * @param mapIcon
     * @return 
     */
    @Override
    public BasicTile createTile(int mapIcon) {
        BasicTile tile;
        switch (mapIcon) {
            case 0:
                tile = new BasicTile("box.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.BOX);
                tile.isSolid = true;
                break;
            case 1:
                tile = new BasicTile("boxAlt.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.BOX);
                tile.isSolid = true;
                break;
            case 2:
                tile = new BasicTile("boxCoin.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                tile.isSolid = true;
                break;
            case 3:
                tile = new BasicTile("boxExplosive.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                tile.isSolid = true;
                break;
            case 4:
                tile = new BasicTile("boxItem.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                tile.isSolid = true;
                break;
            case 5:
                tile = new BasicTile("bridgeLogs.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                tile.getImage().mirrorVertically();
                tile.isSolid = true;
                break;
            case 6:
                // Toevoegen van een TileType
                tile = new BasicTile("grassCenter.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
            case 7:
                tile = new BasicTile("grassLeft.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
            case 8:
                tile = new BasicTile("grassMid.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
            case 9:
                tile = new BasicTile("grassRight.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
            case 10:
                tile = new BasicTile("liquidWater.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.WATER);
                break;
            case 11:
                tile = new BasicTile("liquidWaterTop_mid.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.WATER);
                break;
            case 12:
                tile = new BasicTile("signExit.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                break;
            case 13:
                tile = new BasicTile("signLeft.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                break;
            case 14:
                tile = new BasicTile("signRight.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                break;
            case 15:
                tile = new BasicTile("grassHillLeft2.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
            case 16:
                tile = new BasicTile("grassHillRight2.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
            case 17:
                tile = new BasicTile("grassHillLeft.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                // Experimental
                tile.setSlope(true);
                break;
            case 18:
                tile = new BasicTile("grassHillRight.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                // Experimental
                tile.setSlope(true);
                tile.setSlopeY(1);
                tile.setSlopeX(-1);
                break;
            default:
                tile = new BasicTile("grassMid.png", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT, TileType.GROUND);
                tile.isSolid = true;
                break;
        }
        return tile;
    }
}
