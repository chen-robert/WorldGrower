/*******************************************************************************
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.worldgrower.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.worldgrower.Constants;
import org.worldgrower.World;
import org.worldgrower.WorldObject;
import org.worldgrower.WorldObjectImpl;
import org.worldgrower.attribute.IdList;
import org.worldgrower.attribute.IdToIntegerMap;
import org.worldgrower.attribute.ManagedProperty;
import org.worldgrower.attribute.WorldObjectContainer;
import org.worldgrower.condition.Conditions;
import org.worldgrower.deity.Deity;
import org.worldgrower.gui.ImageIds;

public class BuildingGenerator {

	private static final String SHACK_NAME = "shack";
	private static final String HOUSE_NAME = "house";
	private static final String WELL_NAME = "well";
	private static final String TRAINING_DUMMY_NAME = "Training dummy";
	private static final String GRAVE_NAME = "grave";
	private static final String JAIL_LEFT = "Jail left";
	private static final String JAIL_DOOR = "Jail door";
	
	public static int generateVotingBox(int x, int y, World world) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 1);
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, ImageIds.VOTING_BOX);
		properties.put(Constants.NAME, "voting box");
		properties.put(Constants.TURN_COUNTER, 0);
		properties.put(Constants.CANDIDATES, new IdList());
		properties.put(Constants.VOTES, new IdToIntegerMap());
		WorldObject votingBox = new WorldObjectImpl(properties, new VotingBoxOnTurn());
		world.addWorldObject(votingBox);
		
		return id;
	}
	
	public static int generateShack(int x, int y, World world, double skillBonus) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 2);
		properties.put(Constants.HEIGHT, 3);
		properties.put(Constants.SLEEP_COMFORT, (int)(3 * skillBonus));
		properties.put(Constants.NAME, SHACK_NAME);
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, ImageIds.SHACK);
		properties.put(Constants.HIT_POINTS, 100);
		properties.put(Constants.HIT_POINTS_MAX, 100);
		properties.put(Constants.FLAMMABLE, Boolean.TRUE);
		properties.put(Constants.CONDITIONS, new Conditions());
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		properties.put(Constants.PRICE, 10);
		properties.put(Constants.INVENTORY, new WorldObjectContainer());
		properties.put(Constants.LOCK_STRENGTH, 0);
		properties.put(Constants.LOCKED, Boolean.FALSE);
		
		WorldObject shack = new WorldObjectImpl(properties);
		world.addWorldObject(shack);
		
		return id;
	}
	
	public static int generateHouse(int x, int y, World world, double skillBonus) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		
		ImageIds houseImageId = generateHouseImageIds();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 3);
		properties.put(Constants.HEIGHT, 3);
		properties.put(Constants.SLEEP_COMFORT, (int)(5 * skillBonus));
		properties.put(Constants.NAME, HOUSE_NAME);
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, houseImageId);
		properties.put(Constants.FLAMMABLE, Boolean.TRUE);
		properties.put(Constants.CONDITIONS, new Conditions());
		properties.put(Constants.HIT_POINTS, 200);
		properties.put(Constants.HIT_POINTS_MAX, 200);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		properties.put(Constants.PRICE, 50);
		properties.put(Constants.INVENTORY, new WorldObjectContainer());
		properties.put(Constants.LOCK_STRENGTH, 2);
		properties.put(Constants.LOCKED, Boolean.TRUE);
		
		WorldObject house = new WorldObjectImpl(properties);
		world.addWorldObject(house);
		
		return id;
	}
	
	public static int buildWell(int x, int y, World world, double skillBonus) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 2);
		properties.put(Constants.HEIGHT, 2);
		properties.put(Constants.WATER_SOURCE, 2000);
		properties.put(Constants.NAME, WELL_NAME);
		
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, ImageIds.WELL);
		properties.put(Constants.HIT_POINTS, 75);
		properties.put(Constants.HIT_POINTS_MAX, 75);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject well = new WorldObjectImpl(properties, new WellOnTurn());
		world.addWorldObject(well);
		
		return id;
	}
	
	private static ImageIds generateHouseImageIds() {
		return ImageIds.HOUSE6;
	}

	public static int generateTrainingDummy(int x, int y, World world, double skillBonus) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		int hitPoints = (int)(50 * skillBonus);
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 2);
		properties.put(Constants.NAME, TRAINING_DUMMY_NAME);
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, ImageIds.TRAINING_DUMMY);
		properties.put(Constants.FLAMMABLE, Boolean.TRUE);
		properties.put(Constants.CONDITIONS, new Conditions());
		properties.put(Constants.HIT_POINTS, hitPoints);
		properties.put(Constants.HIT_POINTS_MAX, hitPoints);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject trainingDummy = new WorldObjectImpl(properties);
		world.addWorldObject(trainingDummy);
		
		return id;
	}	
	
	public static int generateGrave(int x, int y, World world, WorldObject deceasedWorldObject) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 1);
		properties.put(Constants.NAME, GRAVE_NAME);
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, ImageIds.GRAVE);
		properties.put(Constants.TEXT, "Here are the " + deceasedWorldObject.getProperty(Constants.NAME) + " buried");
		properties.put(Constants.HIT_POINTS, 50);
		properties.put(Constants.HIT_POINTS_MAX, 50);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject grave = new WorldObjectImpl(properties);
		world.addWorldObject(grave);
		
		return id;
	}
	
	public static boolean isShack(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(SHACK_NAME);
	}
	
	public static boolean isHouse(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(HOUSE_NAME);
	}
	
	public static boolean isGrave(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(GRAVE_NAME);
	}
	
	public static boolean isSellable(WorldObject worldObject) {
		return isShack(worldObject) || isHouse(worldObject);
	}
	
	public static boolean isTrainingDummy(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(TRAINING_DUMMY_NAME);
	}
	
	public static boolean isJailLeft(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(JAIL_LEFT);
	}
	
	public static boolean isJailDoor(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(JAIL_DOOR);
	}
	
	public static boolean isWell(WorldObject worldObject) {
		return worldObject.getProperty(Constants.NAME).equals(WELL_NAME);
	} 

	public static void generateJail(int x, int y, World world, double useSkill) {
		createJailLeft(x, y, world);
		createJailUp(x, y, world);
		createJailRight(x, y, world);
		createJailDoor(x, y, world);
	}

	private static void createJailLeft(int x, int y, World world) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 4);
		properties.put(Constants.NAME, JAIL_LEFT);
		properties.put(Constants.ID, world.generateUniqueId());
		properties.put(Constants.IMAGE_ID, ImageIds.JAIL_LEFT);
		properties.put(Constants.HIT_POINTS, 50);
		properties.put(Constants.HIT_POINTS_MAX, 50);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject jailLeft = new WorldObjectImpl(properties);
		world.addWorldObject(jailLeft);
	}
	
	private static void createJailUp(int x, int y, World world) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		
		properties.put(Constants.X, x+1);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 2);
		properties.put(Constants.NAME, "Jail up");
		properties.put(Constants.ID, world.generateUniqueId());
		properties.put(Constants.IMAGE_ID, ImageIds.JAIL_UP);
		properties.put(Constants.HIT_POINTS, 50);
		properties.put(Constants.HIT_POINTS_MAX, 50);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject jailLeft = new WorldObjectImpl(properties);
		world.addWorldObject(jailLeft);
	}
	
	private static void createJailRight(int x, int y, World world) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		
		properties.put(Constants.X, x+2);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 4);
		properties.put(Constants.NAME, "Jail right");
		properties.put(Constants.ID, world.generateUniqueId());
		properties.put(Constants.IMAGE_ID, ImageIds.JAIL_RIGHT);
		properties.put(Constants.HIT_POINTS, 50);
		properties.put(Constants.HIT_POINTS_MAX, 50);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject jailLeft = new WorldObjectImpl(properties);
		world.addWorldObject(jailLeft);
	}
	
	private static void createJailDoor(int x, int y, World world) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		
		properties.put(Constants.X, x+1);
		properties.put(Constants.Y, y+2);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 2);
		properties.put(Constants.NAME, JAIL_DOOR);
		properties.put(Constants.ID, world.generateUniqueId());
		properties.put(Constants.IMAGE_ID, ImageIds.JAIL_DOOR);
		properties.put(Constants.HIT_POINTS, 50);
		properties.put(Constants.HIT_POINTS_MAX, 50);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject jailLeft = new WorldObjectImpl(properties);
		world.addWorldObject(jailLeft);
	}
	
	public static void addJailDoorIfNotPresent(WorldObject jailLeft, World world) {
		int jailLeftX = jailLeft.getProperty(Constants.X);
		int jailLeftY = jailLeft.getProperty(Constants.Y);
		List<WorldObject> jailDoors = world.findWorldObjects(w -> isJailDoor(w) && w.getProperty(Constants.X) == jailLeftX+1 && w.getProperty(Constants.Y) == jailLeftY + 2);
		
		if (jailDoors.size() == 0) {
			createJailDoor(jailLeftX, jailLeftY, world);
		}
	}

	public static WorldObject findEmptyJail(World world) {
		List<WorldObject> jails = world.findWorldObjects(w -> isJailLeft(w));
		for(WorldObject jail : jails) {
			int jailX = jail.getProperty(Constants.X);
			int jailY = jail.getProperty(Constants.Y);
			List<WorldObject> prisoners = world.findWorldObjectsByProperty(Constants.STRENGTH, w -> w.getProperty(Constants.X) == jailX+1 && w.getProperty(Constants.Y) == jailY+1);
			if (prisoners.size() == 0) {
				return jail;
			}
		}
		return null;
	}

	public static int generateSacrificialAltar(int x, int y, World world, WorldObject performer, Deity deity, double useSkill) {
		Map<ManagedProperty<?>, Object> properties = new HashMap<>();
		int id = world.generateUniqueId();
		
		properties.put(Constants.X, x);
		properties.put(Constants.Y, y);
		properties.put(Constants.WIDTH, 1);
		properties.put(Constants.HEIGHT, 2);
		properties.put(Constants.NAME, "sacrificial Altar");
		properties.put(Constants.ID, id);
		properties.put(Constants.IMAGE_ID, ImageIds.SACRIFIAL_ALTAR);
		properties.put(Constants.SACRIFICIAL_ALTAR_CREATOR_ID, performer.getProperty(Constants.ID));
		properties.put(Constants.TEXT, "Sacrificial Altar to " + deity.getName());
		properties.put(Constants.HIT_POINTS, 50);
		properties.put(Constants.HIT_POINTS_MAX, 50);
		properties.put(Constants.ARMOR, 0);
		properties.put(Constants.DAMAGE_RESIST, 0);
		
		WorldObject altar = new WorldObjectImpl(properties);
		world.addWorldObject(altar);
		
		return id;
	}
}