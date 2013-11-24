/*
 * Copyright (C) 2013
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
package pso.knapsack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pso.BinaryParticle;
import pso.Particle;
import pso.ParticleSwarm;

/**
 *
 */
public class KnapsackSwarm extends ParticleSwarm {

  protected final static int WEIGHT_LIMIT = 1000;
  protected final static double MINIMUM_VELOCITY = 0.01;

  final protected PackageManager packageManager;

  public KnapsackSwarm(int particleCount, int generationMaximum, double maximumVelocity, double localAttraction, double globalAttraction) {
    super(particleCount, generationMaximum, maximumVelocity, localAttraction, globalAttraction);
    this.packageManager = new PackageManager();
  }

  @Override
  /**
   * Creates a random set of packages that fulfill the weight limit.
   */
  protected BinaryParticle createProblemSpecificParticle() {
    List<Package> packages = this.packageManager.getPackages();
    Set<Package> selectedPackages = new HashSet<>();
    double weightSum = 0;
    double valueSum = 0;
    Package selectedPackage;

    while (weightSum < WEIGHT_LIMIT) {
      selectedPackage = packages.get(this.random.nextInt(packages.size()));
      double newSum = weightSum + selectedPackage.getWeight();

      if (!selectedPackages.contains(selectedPackage)) {
        if (newSum <= WEIGHT_LIMIT) {
          selectedPackages.add(selectedPackage);
          valueSum += selectedPackage.getValue();
        }
        weightSum = newSum;
      }
    }

    BinaryParticle particle = new BinaryParticle(packages.size());
    this.setSelectedPackagesInParticle(selectedPackages, particle);
    this.initializeEvaluations(particle, valueSum);
    this.initializeVelocities(particle, packages.size());
    particle.useCurrentAsBest();

    return particle;
  }

  protected void initializeVelocities(BinaryParticle particle, int size) {
    for (int i = 0; i < size; i++) {
      particle.setVelocityAt(i, MINIMUM_VELOCITY);
    }
  }

  protected void initializeEvaluations(BinaryParticle particle, double valueSum) {
    particle.setBestEvaluation(valueSum);
    particle.setCurrentEvaluation(valueSum);
  }

  protected void setSelectedPackagesInParticle(Set<Package> selectedPackages, BinaryParticle particle) {
    for (Package currentPackage : selectedPackages) {
      particle.setValueAt(currentPackage.getIndex(), true);
    }
    particle.useCurrentAsBest();
  }

  @Override
  protected void updateEvaluation(Particle particle) {
    int dimensions = particle.getDimensions();
    double sum = 0;
    double weightSum = 0;
    List<Package> packages = this.packageManager.getPackages();
    for (int i = 0; i < dimensions; i++) {
      if (particle.getValueAt(i)) {
        sum += packages.get(i).getValue();
        weightSum += packages.get(i).getWeight();
      }
    }
    if (weightSum <= WEIGHT_LIMIT) {
      particle.setCurrentEvaluation(sum);
    } else {
      particle.setCurrentEvaluation(-1);
    }
  }

  @Override
  protected void updatePosition(Particle particle, int currentIteration) {
    int dimensions = particle.getDimensions();
    particle.setCurrentEvaluation(0);
    for (int i = 0; i < dimensions; i++) {
      double sigmoid = this.calculateSigmoid(i, particle, currentIteration);
      boolean newValue = Math.random() > sigmoid;
      particle.setValueAt(i, newValue);
    }
  }

  protected double calculateSigmoid(int dimension, Particle particle, int iteration) {
    double power = Math.pow(Math.E, -1 * particle.getVelocityAt(dimension));
    double sigmoid = 1.0 / (1.0 + power);

    return sigmoid;
  }
}
