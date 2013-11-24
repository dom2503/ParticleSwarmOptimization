/*
 * Copyright (C) 2013 dominik
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

package pso.circle;

import pso.BinaryParticle;
import pso.Particle;
import pso.ParticleSwarm;

/**
 *
 */
public class CircleSwarm extends ParticleSwarm{

  public CircleSwarm(int particleCount, int generationMaximum, double maximumVelocity, double localAttraction, double globalAttraction) {
    super(particleCount, generationMaximum, maximumVelocity, localAttraction, globalAttraction);
  }

  @Override
  protected BinaryParticle createProblemSpecificParticle() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected void updateEvaluation(Particle particle) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected void updatePosition(Particle particle, int currentIteration) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected void printState() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
