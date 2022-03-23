package com.star.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.star.app.screen.ScreenManager;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private AsteroidController asteroidController;
    private Hero hero;

    public BulletController getBulletController() {
        return bulletController;
    }

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public Background getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        this.background = new Background(this);
        this.bulletController = new BulletController();
        this.asteroidController = new AsteroidController();
        this.hero = new Hero(this);
        generateAsteroid(3);
    }

    public void generateAsteroid(int count) {
        for (int i = 0; i < count; i++) {
            asteroidController.setup(MathUtils.random(128, ScreenManager.SCREEN_WIDTH),
                    MathUtils.random(128, ScreenManager.SCREEN_HEIGHT), MathUtils.random(-200, 200),
                    MathUtils.random(-200, 200));
        }
    }

    public void update(float dt) {
        background.update(dt);
        bulletController.update(dt);
        asteroidController.update(dt);
        hero.update(dt);
        checkCollisions();
    }

    public void checkCollisions() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid a = asteroidController.getActiveList().get(j);
                if (a.getPosition().dst(b.getPosition()) < 144.0f) {
                    b.deactivate();
                    a.deactivate();
                    asteroidController.setup(MathUtils.random(128, ScreenManager.SCREEN_WIDTH),
                            MathUtils.random(128, ScreenManager.SCREEN_HEIGHT), MathUtils.random(-200, 200),
                            MathUtils.random(-200, 200));
                }
            }
        }
    }
}