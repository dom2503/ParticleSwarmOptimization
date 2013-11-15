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

/**
 * Represents a package for the knapsack problem.
 */
public class Package {
  final private double weight;
  final private double value;
  
  public Package(double value, double weight){
    this.weight = weight;
    this.value = value;
  }
  
  public double getWeight(){
    return this.weight;
  }
  
  public double getValue(){
    return this.value;
  }
}
