package nl.rocmondriaan.greenfoot.demo;

import nl.rocmondriaan.greenfoot.engine.Mover;

/**
 *
 * @author R. Springer
 */
public class DemoEnemy extends Mover {

    private final double gravity;
    private final double acc;
    private int walkRange;
    private int xMin;
    private int xMax;
    private boolean firstAct;
    private int speed;

    DemoEnemy() {
        super();
        setImage("pokerMad.png");
        getImage().mirrorHorizontally();
        walkRange = 140;
        firstAct = true;
        speed = 1;
        gravity = 9.8;
        acc = 0.6;
    }

    @Override
    public void act() {
        int x = getX();
        int y = getY();

        if (firstAct) {
            firstAct = false;
            xMin = x - walkRange / 2;
            xMax = x + walkRange / 2;
        }

        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        velocityX = speed;
        applyVelocity();
        if (getX() >= xMax) {
            speed *= -1;
            x = xMax;
            getImage().mirrorHorizontally();
        } else if (getX() <= xMin) {
            speed *= -1;
            x = xMin;
            getImage().mirrorHorizontally();
        }
    }
}
