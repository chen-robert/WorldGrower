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
package org.worldgrower.goal;

import org.worldgrower.Constants;
import org.worldgrower.World;
import org.worldgrower.WorldObject;
import org.worldgrower.WorldObjectImpl;
import org.worldgrower.attribute.SkillUtils;
import org.worldgrower.attribute.WorldObjectContainer;
import org.worldgrower.generator.IllusionOnTurn;

public class IllusionPropertyUtils {

	public static int createIllusion(WorldObject performer, int sourceId, World world, int x, int y) {
		WorldObject sourceWorldObject = world.findWorldObject(Constants.ID, sourceId);
		int id = world.generateUniqueId();
		
		WorldObjectImpl illusionWorldObject = (WorldObjectImpl) sourceWorldObject.deepCopy(new IllusionOnTurn());
		illusionWorldObject.setProperty(Constants.ID, id);
		illusionWorldObject.setProperty(Constants.ILLUSION_CREATOR_ID, performer.getProperty(Constants.ID));
		illusionWorldObject.setProperty(Constants.X, x);
		illusionWorldObject.setProperty(Constants.Y, y);
		illusionWorldObject.setProperty(Constants.TURNS_TO_LIVE, (int)(10 * SkillUtils.getSkillBonus(performer, Constants.ILLUSION_SKILL)));
		
		if (sourceWorldObject.hasProperty(Constants.INVENTORY)) {
			illusionWorldObject.setProperty(Constants.INVENTORY, new WorldObjectContainer());
		}
		
		world.addWorldObject(illusionWorldObject);
		
		KnowledgeMapPropertyUtils.everyoneInVicinityKnowsOfProperty(performer, illusionWorldObject, Constants.ILLUSION_CREATOR_ID, performer.getProperty(Constants.ID), world);
		
		return id;
	}
}
