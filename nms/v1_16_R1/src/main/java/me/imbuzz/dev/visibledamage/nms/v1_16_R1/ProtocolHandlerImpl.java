package me.imbuzz.dev.visibledamage.nms.v1_16_R1;

import me.imbuzz.dev.visibledamage.nms.ProtocolHandler;
import me.imbuzz.dev.visibledamage.objects.HoloOBJ;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;

import java.util.List;
import java.util.stream.Collectors;

public class ProtocolHandlerImpl implements ProtocolHandler {

    @Override
    public HoloOBJ getHologram(Location location, String text) {
        return new HologramImpl(location, text);
    }

    public static class HologramImpl implements HoloOBJ {

        private final List<EntityPlayer> players;
        private final EntityArmorStand armorStand;

        public HologramImpl(Location location, String text) {
            players = location.getWorld().getPlayers().stream().map(p -> ((CraftPlayer) p).getHandle()).collect(Collectors.toList());

            EntityArmorStand armorStand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            armorStand.setInvisible(true);
            armorStand.setMarker(true); //setMarker()
            armorStand.setSmall(true);
            armorStand.setNoGravity(true);
            armorStand.setCustomName(new ChatComponentText(text));
            armorStand.setCustomNameVisible(true);
            this.armorStand = armorStand;

        }

        @Override
        public void spawn(double offset) {
            PacketPlayOutSpawnEntityLiving create = new PacketPlayOutSpawnEntityLiving(armorStand);
            PacketPlayOutEntityMetadata meta = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
            sendPacket(create);
            sendPacket(meta);
        }

        @Override
        public void remove() {
            PacketPlayOutEntityDestroy remove = new PacketPlayOutEntityDestroy(armorStand.getId());
            sendPacket(remove);
        }

        @Override
        public void sendPacket(Object packet) {
            for (EntityPlayer player : players) {
                player.playerConnection.sendPacket((Packet<?>) packet);
            }
        }
    }
}
