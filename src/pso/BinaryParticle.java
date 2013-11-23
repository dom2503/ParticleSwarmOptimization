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
abstract public class BinaryParticle {
  final private double[] velocity;
  final private boolean[] currentPosition;
  
  final private boolean[] bestPosition;
  private double bestEvaluation;
  private double currentEvaluation;
  
  public BinaryParticle(int size){
    this.velocity = new double[size];
    this.currentPosition = new boolean[size];
    this.bestPosition = new boolean[size];
    this.bestEvaluation = 0.0;
  }
  
  public int getDimensions(){
    return this.currentPosition.length;
  }
  
  public double getVelocityAt(int dimension){
    return this.velocity[dimension];
  }

  public void setVelocityAt(int dimension, double newVelocity){
    this.velocity[dimension] = newVelocity;
  }
  
  public boolean getValueAt(int dimension){
    return this.currentPosition[dimension];
  }
  
  public void setValueAt(int dimension, boolean newValue){
    this.currentPosition[dimension] = newValue;
  }
  
  public boolean getValueAtBest(int dimension){
    return this.bestPosition[dimension];
  }
  
  public void setValueAtBest(int dimension, boolean newValue){
    this.bestPosition[dimension] = newValue;
  }
  
  public double getBestEvaluation(){
    return this.bestEvaluation;
  }
  
  public void setBestEvaluation(double newEvaluation){
    this.bestEvaluation = newEvaluation;
  }
  
  public double getCurrentEvaluation(){
    return this.currentEvaluation;
  }
  
  public void setCurrentEvaluation(double newEvaluation){
    this.currentEvaluation = newEvaluation;
  }
  
}
