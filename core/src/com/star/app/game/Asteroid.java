package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Vector2 position;
    private Vector2 velositi;
    private boolean activeA;
    private float angleA;

    @Override
    public boolean isActive() {
        return activeA;
    }
    public Vector2 getPosition() {
        return position;
    }


    public Asteroid() {
        this.position = new Vector2();
        this.velositi = new Vector2();
        this.activeA = false;
    }
     public void deactivate() {
        activeA = false;
    }

    public void update(float dt) {
        position.mulAdd(velositi, dt);
        checkBorders();
    }
    public void checkBorders() {
        if (position.y > ScreenManager.SCREEN_HEIGHT +128) {
            position.y = -128;
            deactivate(); velositi.set(velositi.x,  velositi.y);activate(position.x, position.y, velositi.x, velositi.y);

 //           activate( MathUtils.random(128, ScreenManager.SCREEN_WIDTH),MathUtils.random(128, ScreenManager.SCREEN_HEIGHT ),MathUtils.random(-200,200),MathUtils.random(-200,200));
        }
        if (position.y < - 128) {
            position.y = ScreenManager.SCREEN_HEIGHT  ;
            deactivate(); velositi.set(velositi.x, velositi.y);activate(position.x, position.y, velositi.x, velositi.y);
        }
        if (velositi.x > 0 && position.x > ScreenManager.SCREEN_WIDTH + 128) {
            velositi.x = -velositi.x;
            deactivate(); velositi.set(velositi.x,  velositi.y); activate(position.x, position.y, velositi.x, velositi.y);
        }
        if (velositi.x < 0 && position.x < -128) {
            velositi.x = -velositi.x;
            deactivate(); velositi.set(velositi.x,  velositi.y); activate(position.x, position.y, velositi.x, velositi.y);
        }

    }
    public void activate(float x, float y, float vx, float vy) {
        position.set(x, y);
        velositi.set(vx, vy);
        activeA = true;
    }
}




