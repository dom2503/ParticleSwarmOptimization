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

/**
 * Represents a solution to a PSO solvable problem.
 */
abstract public class Particle {
  private double velocity;
  private double currentPosition;
  private double bestPosition;
  
  public Particle(double velocity){
    this.velocity = velocity;
  }
  
  public double getVelocity(){
    return this.velocity;
  }

  public void setVelocity(double newVelocity){
    this.velocity = newVelocity;
  }
  
  public double getCurrentPosition(){
    return this.currentPosition;
  }
  
  public double getBestPosition(){
    return this.bestPosition;
  }
  
  abstract public void updatePosition();
}
