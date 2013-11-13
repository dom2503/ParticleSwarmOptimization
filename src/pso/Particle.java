/**
 * Copyright (C) 2012
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
 * Represents a solution to a 
 */
abstract public class Particle {
  final private double velocity;
  final private double position;
  
  public Particle(double velocity, double position){
    this.velocity = velocity;
    this.position = position;
  }
  
  public double getVelocity(){
    return this.velocity;
  }
  
  abstract public double getPosition();
  
  abstract public void updatePosition();
}
