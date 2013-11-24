/**
 * Copyright (C) 2013
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package pso;

import java.util.Random;

/**
 * An unspecific implementation of a particle swarm.
 */
abstract public class ParticleSwarm {

  private final double velocityMaximum;
  private final int generationMaximum;
  private final double localAttraction;
  private final double globalAttraction;

  private final Particle[] particles;
  private Particle bestParticle;
  protected final Random random;

  /**
   * Initializes a particle swarm with the given values.
   *
   * @param particleCount The number of particles that should be created.
   * @param generationMaximum The number of generations that should be used for solving the problem.
   * @param maximumVelocity The limit of the velocity for single particles.
   * @param localAttraction
   * @param globalAttraction
   */
  public ParticleSwarm(int particleCount, int generationMaximum, double maximumVelocity, double localAttraction, double globalAttraction) {
    this.velocityMaximum = maximumVelocity;
    this.generationMaximum = generationMaximum;
    this.particles = new BinaryParticle[particleCount];
    this.localAttraction = localAttraction;
    this.globalAttraction = globalAttraction;
    this.random = new Random();
  }

  /**
   * Generates all problem specific particles and uses the best of them as the current best.
   */
  public void initializeParticles() {
    int particleCount = this.particles.length;
    double highestEvaluation = -1.0;
    for (int i = 0; i < particleCount; i++) {
      this.particles[i] = this.createProblemSpecificParticle();
      if (this.particles[i].getCurrentEvaluation() > highestEvaluation) {
        this.bestParticle = this.particles[i];
      }
    }
  }

  /**
   * Creates an appropriate particle for the current problem implementation.
   *
   * @return
   */
  abstract protected BinaryParticle createProblemSpecificParticle();

  /**
   * Pseudocode from: http://www.cleveralgorithms.com/nature-inspired/swarm/pso.html
   */
  final public void run() {
    for (int i = 0; i < this.generationMaximum; i++) {
      for (Particle currentParticle : this.particles) {
        this.updateVelocity(currentParticle);
        this.updatePosition(currentParticle, i + 1);
        this.updateEvaluation(currentParticle);

        this.setLocalBest(currentParticle);
        this.setGlobalBest(currentParticle);
      }
      this.printCurrentBest();
    }
  }

  /**
   * Checks if the given particle is better then global best and sets it.
   */
  private void setGlobalBest(Particle particle) {
    if (this.bestParticle.getBestEvaluation() < particle.getBestEvaluation()) {
      this.bestParticle = particle;
    }
  }

  private void setLocalBest(Particle particle) {
    if (particle.getBestEvaluation() < particle.getCurrentEvaluation()) {
      particle.useCurrentAsBest();
      particle.setBestEvaluation(particle.getCurrentEvaluation());
    }
  }

  private void printCurrentBest() {
    System.out.println("" + this.bestParticle.getBestEvaluation());
  }

  abstract protected void updateEvaluation(Particle particle);

  abstract protected void updatePosition(Particle particle, int currentIteration);

  /**
   * Calculates and sets the new velocity for the given particle.
   *
   * @param particle
   */
  public void updateVelocity(Particle particle) {
    int dimensions = particle.getDimensions();
    for (int i = 0; i < dimensions; i++) {
      int localBestPosition = particle.getValueAtBest(i) ? 1 : 0;
      int currentPosition = particle.getValueAt(i) ? 1 : 0;

      double newVelocity = particle.getVelocityAt(i)
              + (this.localAttraction * random.nextDouble()
              * (localBestPosition - currentPosition))
              + (this.globalAttraction * random.nextDouble()
              * (this.bestParticle.getBestEvaluation() - currentPosition));

      //don't exceed a certain threshold
      if (newVelocity > velocityMaximum) {
        particle.setVelocityAt(i, velocityMaximum);
      } else {
        particle.setVelocityAt(i, newVelocity);
      }
    }
  }
}
