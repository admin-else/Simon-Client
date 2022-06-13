package de.sqrt.simon.hacks;

import de.sqrt.simon.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;
import net.minecraft.util.math.Vec3d;

public class Flying extends Hack {

	public Flying(String name) {
		super("fly","Makes you go weeeeeeeee!");
	}

	MinecraftClient client = MinecraftClient.getInstance();
	static double acceleration = 0.1;

	public void accelerates() {
		if (client.player != null && isEnabled()) {

			int toggle = 0;
			int MAX_SPEED = 3;
			double FALL_SPEED = -0.04;

			boolean jumpPressed = client.options.jumpKey.isPressed();
			boolean forwardPressed = client.options.forwardKey.isPressed();
			boolean leftPressed = client.options.leftKey.isPressed();
			boolean rightPressed = client.options.rightKey.isPressed();
			boolean backPresed = client.options.backKey.isPressed();

			Entity object = client.player;
			if (client.player.hasVehicle())
				object = client.player.getVehicle();

			Vec3d velocity = object.getVelocity();
			Vec3d newVelocity = new Vec3d(velocity.x, velocity.y + 1, velocity.z);
			if (jumpPressed) {

				if (forwardPressed || leftPressed || rightPressed || backPresed) {

					if (forwardPressed)
						newVelocity = client.player.getRotationVector().multiply(acceleration);

					if (leftPressed && !client.player.hasVehicle()) {
						newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(3.1415927F / 2);
						newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
					}

					if (rightPressed && !client.player.hasVehicle()) {
						newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(-3.1415927F / 2);
						newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
					}

					if (backPresed)
						newVelocity = client.player.getRotationVector().negate().multiply(acceleration);

					object.setVelocity(newVelocity);

					if (acceleration < MAX_SPEED)
						acceleration += 0.05;
					else
						acceleration = 0;
				} else
					newVelocity = newVelocity.add(0, 5, 0);

				newVelocity = new Vec3d(newVelocity.x,
						(toggle == 0 && newVelocity.y > FALL_SPEED) ? FALL_SPEED : newVelocity.y, newVelocity.z);
				if (toggle == 0 || newVelocity.y <= -0.04)
					toggle = 40;
				toggle--;

			}
			if (client.player.fallDistance >= 2)
				client.getNetworkHandler().sendPacket(new OnGroundOnly(true));

		}

	}
}