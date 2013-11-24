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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is able to read a list of packages from a .txt file.
 */
public class PackageManager {

  final private ArrayList<Package> packages;
  final private Random random;

  public PackageManager() {
    this.packages = new ArrayList<>();
    this.random = new Random();
    this.readPackages("pso-packages.txt");
  }

  /**
   * Takes the file with the given name creates package objects from it's content.
   * @param filename 
   */
  private void readPackages(String filename) {
    InputStream in = getClass().getResourceAsStream(filename);
    Scanner scanner = new Scanner(in);
    String line;
    double value;
    double weight;
    
    for(int i=0; i< 2000; i++){
      line = scanner.nextLine();
      String[] parts = line.split(",");
      value = Double.parseDouble(parts[0]);
      weight = Double.parseDouble(parts[1]);
      packages.add(new Package(i, value, weight, this.random.nextInt(5)+1));
    }
  }
  
  /**
   * @return A list of all the existing packages.
   */
  public List<Package> getPackages(){
    return Collections.unmodifiableList(this.packages);
  }
  
  /**
   * @param index The index of the requested package.
   * @return The package at the given index.
   */
  public Package getPackageAt(int index){
    return this.packages.get(index);
  }
}
