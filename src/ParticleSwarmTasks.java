
import java.util.Scanner;
import pso.ParticleSwarm;
import pso.knapsack.KnapsackSwarm;
import pso.knapsack.VolumeKnapsack;

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

  private final Scanner scanner;

  public static void main(String[] args) {
    ParticleSwarmTasks psoTasks = new ParticleSwarmTasks();
    psoTasks.run();
  }

  public ParticleSwarmTasks() {
    this.scanner = new Scanner(System.in);
  }

  private void run() {
    System.out.println("Welcome to the PSO");
    System.out.println("Which local attraction would you like to use?");
    double localAttraction = scanner.nextDouble();
    System.out.println("Which global attraction would you like to use?");
    double globalAttraction = scanner.nextDouble();
    System.out.println("How many generations should be generated?");
    int generations = scanner.nextInt();
    System.out.println("How many particles should be used?");
    int particles = scanner.nextInt();
    System.out.println("Which type would you like to run?");
    System.out.println("5. Simple Kanpsack problem");
    System.out.println("6. Simple Kanpsack with inertia");
    System.out.println("7. Kanpsack problem with volume");
    int problemId = scanner.nextInt();

    ParticleSwarm particleSwarm = null;

    switch (problemId) {
      case 5:
        particleSwarm = new KnapsackSwarm(particles, generations, 4.25, localAttraction, globalAttraction);
        break;

      case 7:
        particleSwarm = new VolumeKnapsack(particles, generations, 4.25, localAttraction, globalAttraction);
        break;
    }

    if (particleSwarm != null) {
      particleSwarm.initializeParticles();
      particleSwarm.run();
    }
  }
}
