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
package pso;

/**
 *
 */
public interface Particle {

  /**
   * Returns the number of dimensions of the problem space.
   */
  public int getDimensions();

  /**
   * Returns the current velocity for the asked dimension.
   */
  public double getVelocityAt(int dimension);

  /**
   * Changes the velocity for the given dimension.
   */
  public void setVelocityAt(int dimension, double newVelocity);

  /**
   * Returns the value the position vector has at the asked position.
   */
  public boolean getValueAt(int dimension);

  /**
   * Changes the value of the vector in the asked dimension.
   */
  public void setValueAt(int dimension, boolean newValue);

  /**
   * Returns the value this particle best position vector has at the asked position.
   */
  public boolean getValueAtBest(int dimension);

  /**
   * Changes the value of the best position vector to the current position.
   */
  public void useCurrentAsBest();

  /**
   * Returns the evaluation for the best position this particle had.
   */
  public double getBestEvaluation();

  /**
   * Changes the evaluation for the best position this particle was at.
   * 
   * This should correspond to the values currently set for the best position vector.
   */
  public void setBestEvaluation(double newEvaluation);

  /**
   * Returns the evaluation for the best position vector this particle was at.
   */
  public double getCurrentEvaluation();

  /**
   * Changes the evaluation for the current position this particle is at.
   * 
   * This should correspond to the values currently set for the position vector.
   */
  public void setCurrentEvaluation(double newEvaluation);
}
