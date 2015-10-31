package irl.fw.physics.world;

import irl.fw.physics.bodies.Body;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 10/29/15
 */
class BodyInstance {

    private final Body body;
    private PhysicalState state;

    BodyInstance(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setState(PhysicalState state) {
        this.state = state;
    }

    public PhysicalState getState() {
        return state;
    }

}
