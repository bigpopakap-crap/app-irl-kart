package irl.kart.entities;

import irl.fw.engine.entity.Entity;
import irl.fw.engine.entity.VirtualEntity;
import irl.fw.engine.entity.actions.remove.RemovableEntityAdaptor;
import irl.fw.engine.entity.actions.remove.RemovableEntity;
import irl.fw.engine.entity.factory.EntityConfig;
import irl.fw.engine.entity.factory.EntityDisplayConfig;
import irl.fw.engine.entity.state.EntityState;
import irl.fw.engine.events.EngineEvent;
import irl.fw.engine.geometry.Angle;
import irl.fw.engine.geometry.ImmutableShape;
import irl.kart.entities.items.BananaItem;
import irl.kart.entities.items.Item;
import irl.kart.entities.items.ShellItem;
import irl.kart.entities.items.actions.itemuser.ItemUser;
import irl.util.ColorUtils;
import irl.util.callbacks.Callback;
import irl.util.reactiveio.EventQueue;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/11/15
 */
public class ItemBox extends VirtualEntity implements RemovableEntity {

    public static final Angle INIT_ROT = Angle.deg(45);
    public static final Angle ROTATION_SPEED = Angle.rad(-Math.PI);
    public static final ImmutableShape SHAPE = new ImmutableShape(
        ImmutableShape.Type.RECTANGLE,
        new Rectangle2D.Double(0, 0, 10, 10)
    );

    private final ArrayList<Item> availableItems;
    private final EventQueue<EngineEvent> eventQueue;
    private final RemovableEntityAdaptor remover;

    public ItemBox(EntityConfig entityConfig, EntityState initState,
                   EventQueue<EngineEvent> eventQueue) {
        this(entityConfig, initState, eventQueue, null);
    }

    public ItemBox(EntityConfig entityConfig, EntityState initState,
                   EventQueue<EngineEvent> eventQueue, Callback onRemove) {
        super(
            entityConfig
                .display(
                    new EntityDisplayConfig()
                            .outlineColor(ColorUtils.random())
                            .fillColor(ColorUtils.random(0.4))
                ),
            initState
        );

        availableItems = new ArrayList<>();
        this.eventQueue = eventQueue;
        availableItems.add(new ShellItem(this.eventQueue));
        availableItems.add(new BananaItem(this.eventQueue));

        this.remover = new RemovableEntityAdaptor(this, this.eventQueue);
        addRemoveHandler(onRemove);
    }

    @Override
    public String getEntityDisplayType() {
        return "ItemBox";
    }

    @Override
    public boolean collide(Entity other) {
        if (other instanceof ItemUser) {
            ItemUser itemUser = (ItemUser) other;
            itemUser.takeItem(getRandomItem());
            remove();
        }

        //item boxes don't interact with anything
        return false;
    }

    @Override
    public void remove() {
        remover.remove();
    }

    @Override
    public String addRemoveHandler(Callback callback) {
        return remover.addRemoveHandler(callback);
    }

    @Override
    public void removeRemoveHandler(String callbackId) {
        remover.removeRemoveHandler(callbackId);
    }

    private Item getRandomItem() {
        return availableItems.get(
            new Random(System.currentTimeMillis())
                    .nextInt(availableItems.size())
        );
    }

}
