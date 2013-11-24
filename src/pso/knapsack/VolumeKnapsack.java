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
import static pso.knapsack.KnapsackSwarm.WEIGHT_LIMIT;

/**
 *
 */
public class VolumeKnapsack extends KnapsackSwarm {

  private final static int VOLUME_LIMIT = 250;
  
  public VolumeKnapsack(int particleCount, int generationMaximum, double maximumVelocity, double localAttraction, double globalAttraction) {
    super(particleCount, generationMaximum, maximumVelocity, localAttraction, globalAttraction);
  }

  @Override
  protected void updateEvaluation(Particle particle) {
    int dimensions = particle.getDimensions();
    double sum = 0;
    double weightSum = 0;
    double volumeSum = 0;
    List<Package> packages = this.packageManager.getPackages();
    for (int i = 0; i < dimensions; i++) {
      if (particle.getValueAt(i)) {
        sum += packages.get(i).getValue();
        volumeSum += packages.get(i).getVolume();
        weightSum += packages.get(i).getWeight();
      }
    }
    if (weightSum <= WEIGHT_LIMIT && volumeSum <= VOLUME_LIMIT) {
      particle.setCurrentEvaluation(sum);
    } else {
      particle.setCurrentEvaluation(-1);
    }
  }
  
  @Override
  protected BinaryParticle createProblemSpecificParticle() {
    List<Package> packages = this.packageManager.getPackages();
    Set<Package> selectedPackages = new HashSet<>();
    double weightSum = 0;
    double valueSum = 0;
    double volumeSum = 0;
    Package selectedPackage;

    while (weightSum < WEIGHT_LIMIT&& volumeSum <VOLUME_LIMIT) {
      selectedPackage = packages.get(this.random.nextInt(packages.size()));
      double newSum = weightSum + selectedPackage.getWeight();
      double newVolumeSum = volumeSum + selectedPackage.getVolume();
      if (!selectedPackages.contains(selectedPackage)) {
        if (newSum <= WEIGHT_LIMIT&& newVolumeSum <=VOLUME_LIMIT) {
          selectedPackages.add(selectedPackage);
          valueSum += selectedPackage.getValue();
        }
        weightSum = newSum;
        volumeSum = newVolumeSum;
      }
    }

    BinaryParticle particle = new BinaryParticle(packages.size());
    this.setSelectedPackagesInParticle(selectedPackages, particle);
    this.initializeEvaluations(particle, valueSum);
    this.initializeVelocities(particle, packages.size());
    particle.useCurrentAsBest();

    return particle;
  }
}
