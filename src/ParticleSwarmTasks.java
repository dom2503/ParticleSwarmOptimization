
import pso.knapsack.KnapsackSwarm;
import pso.knapsack.PackageManager;

/*
 * Copyright (C) 2013 Dominik
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * @author Dominik Horb
 */
public class ParticleSwarmTasks {
  
  public static void main(String[] args){
    ParticleSwarmTasks psoTasks = new ParticleSwarmTasks();
    psoTasks.runSimpleKnapsack();
  }
  
  private void runSimpleKnapsack(){
    KnapsackSwarm swarm = new KnapsackSwarm(1000, 500, 4.25, 0.0004, 0.0004);
    swarm.initializeParticles();
    swarm.run();
  }
}
