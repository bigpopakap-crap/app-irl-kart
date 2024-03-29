package irl.fw.engine.world;

import irl.fw.engine.entity.Entity;

import java.util.Collection;
import java.util.Collections;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/6/15
 */
public class SimpleWorld implements World {

    private final Collection<Entity> entities;
    private final double minX, maxX, minY, maxY;

    public SimpleWorld(Collection<Entity> entities,
                       double minX, double maxX,
                       double minY, double maxY) {
        this.entities = Collections.unmodifiableCollection(entities);
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    @Override
    public Collection<Entity> getEntities() {
        return entities;
    }

    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMaxX() {
        return maxX;
    }

    @Override
    public double getMinY() {
        return minY;
    }

    @Override
    public double getMaxY() {
        return maxY;
    }
}
