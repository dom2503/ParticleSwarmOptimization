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

  private final BinaryParticle[] particles;
  private BinaryParticle bestParticle;
  protected final Random random;

  /**
   * Initializes a particle swarm with default values.
   */
  public ParticleSwarm() {
    this(20, 1000, 4.25, 2.0, 2.0);
  }

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
   *
   */
  public void initializeParticles() {
    int particleCount = this.particles.length;
    double highestEvaluation = -1.0;
    for (int i = 0; i < particleCount; i++) {
      this.particles[i] = this.createProblemSpecificParticle();
      if(this.particles[i].getCurrentEvaluation() > highestEvaluation){
        this.bestParticle = this.particles[i];
      }
    }
  }

  abstract protected BinaryParticle createProblemSpecificParticle();

  /**
   * Pseudocode from: http://www.cleveralgorithms.com/nature-inspired/swarm/pso.html
   */
  final public void run() {
    for (int i = 0; i < this.generationMaximum; i++) {
      for (BinaryParticle currentParticle : this.particles) {
        this.updateVelocity(currentParticle);
        this.updatePosition(currentParticle, i + 1);
        this.updateEvaluation(currentParticle);

        if (currentParticle.getCurrentEvaluation() <= this.bestParticle.getBestEvaluation()) {
          this.bestParticle = currentParticle;
        }
      }
      this.printCurrentBest();
    }
  }
  
  private void printCurrentBest(){
    System.out.println("Best result: " + this.bestParticle.getBestEvaluation());
  }

  abstract protected void updateEvaluation(BinaryParticle particle);

  abstract protected void updatePosition(BinaryParticle particle, int currentIteration) ;

  protected double calculateSigmoid(int dimension, BinaryParticle particle, int iteration) {
    double sigmoid = 1 / 1 + Math.pow(Math.E, -(Math.pow(particle.getVelocityAt(dimension), iteration)));

    return sigmoid;
  }

  /**
   * Calculates and sets the new velocity for the given particle.
   *
   * @param particle
   */
  public void updateVelocity(BinaryParticle particle) {
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
      particle.setVelocityAt(i, Math.max(velocityMaximum, newVelocity));
    }
  }
}
