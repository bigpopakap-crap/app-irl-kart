package irl.fw.engine.collisions.impl;

import irl.fw.engine.collisions.CollisionResolver;
import irl.fw.engine.events.EntityCollision;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/1/15
 */
public class NoopCollisionResolver implements CollisionResolver {

    @Override
    public boolean onCollision(EntityCollision collision) {
        //do nothing. let all collisions go through
        return true;
    }

}
