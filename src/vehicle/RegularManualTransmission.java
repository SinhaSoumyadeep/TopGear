package vehicle;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This Class implements the ManualTransmission Interface and defines the set of operations that
 * simulates the operations of a vehicle with manual transmission.Every gear works within a range of
 * speeds. If the speed is decreased to be lower than this range without decreasing the gear, then
 * the vehicle “knocks” at this gear. If the gas pedal is pushed beyond the capacity of this gear,
 * the engine “revvs” but the speed does not increase. the Initial speed of the Vehicle should be at
 * 0 and the gear at 1. when the vehicle is at the initial state the speed of the vehicle cannot be
 * decreased. To change the Gear, the speed needs to be increased to the lower limit of the next
 * gear.
 */
public class RegularManualTransmission implements ManualTransmission {

  LinkedHashMap<Integer, HashMap<String, Integer>> gearBox
          = new LinkedHashMap<Integer, HashMap<String, Integer>>();
  private int minSpeed;
  private int maxSpeed;
  private int minGear;
  private int maxGear;
  private int speed;
  private int gear;
  private String status = "OK: everything is OK.";


  /**
   * This is the constructor of the class that creates the object of RegularManualTransmission. The
   * constructor that takes the speed ranges for the 5 gears as two integral numbers each: low and
   * high. Thus it takes arguments as l1,h1,l2,h2,...,l5,h5. For each gear x, lx should be less than
   * or equal to hx. Furthermore the lower speed for gear 1 should be strictly lesser than that of
   * gear 2, and so on. Finally (only) adjacent-gear ranges may overlap (e.g. l2 may be between l1
   * and h1, etc.) but the ranges cannot be non-overlapping (i.e. each speed is covered by a gear
   * range).
   *
   * @param l1 the lowest speed of gear 1
   * @param h1 the highest speed of gear 1
   * @param l2 the lowest speed of gear 2
   * @param h2 the highest speed of gear 2
   * @param l3 the lowest speed of gear 3
   * @param h3 the highest speed of gear 3
   * @param l4 the lowest speed of gear 4
   * @param h4 the highest speed of gear 4
   * @param l5 the lowest speed of gear 5
   * @param h5 the highest speed of gear 5
   */

  public RegularManualTransmission(int l1, int h1, int l2, int h2, int l3, int h3, int l4, int h4,
                                   int l5, int h5) throws IllegalArgumentException {

    gearBox.put(1, new HashMap<String, Integer>() {
      {
        put("low", l1);
        put("high", h1);
      }
    });

    gearBox.put(2, new HashMap<String, Integer>() {
      {
        put("low", l2);
        put("high", h2);
      }
    });

    gearBox.put(3, new HashMap<String, Integer>() {
      {
        put("low", l3);
        put("high", h3);
      }
    });

    gearBox.put(4, new HashMap<String, Integer>() {
      {
        put("low", l4);
        put("high", h4);
      }
    });

    gearBox.put(5, new HashMap<String, Integer>() {
      {
        put("low", l5);
        put("high", h5);
      }
    });

    constructorHelper();


  }

  /**
   * This method is a constructor helper function that initializes the various instance variables as
   * well as checks for exceptions based on various conditions.
   */
  private void constructorHelper() throws IllegalArgumentException {
    int i = 1;


    this.speed = 0;
    this.gear = 1;


    this.minGear = gearBox.keySet().iterator().next();


    this.minSpeed = gearBox.get(this.minGear).get("low");
    if (this.minSpeed != 0) {
      throw new IllegalArgumentException("The low speed of gear 1 should be 0.");
    }

    for (Integer key : gearBox.keySet()) {
      this.maxGear = key;
    }

    this.maxSpeed = gearBox.get(this.maxGear).get("high");


    for (Integer gear : gearBox.keySet()) {
      if (!checklowAndHighSpeedForAGear(gearBox.get(gear).get("low"),
              gearBox.get(gear).get("high"))) {
        throw new IllegalArgumentException("The low speed and high speed "
                + "for a particular gear is Invalid.");
      }
    }


    for (Integer gear : gearBox.keySet()) {

      if (i == this.maxGear) {
        break;
      }
      i++;


      if (!checkForAdjacentLowSpeed(gearBox.get(gear).get("low"),
              gearBox.get(gear + 1).get("low"))) {
        throw new IllegalArgumentException("The low speed of the next gear "
                + "cannot be less than low speed of the previous gear.");
      }
    }


    i = 1;
    for (Integer gear : gearBox.keySet()) {
      if (i == this.maxGear) {
        break;
      }
      i++;
      if (!checkForOverlappingAdjacentGear(gearBox.get(gear).get("low"),
              gearBox.get(gear + 1).get("low"), gearBox.get(gear).get("high"))) {
        throw new IllegalArgumentException("the adjacent speed for a particular "
                + "gear is non overlapping.");
      }
    }


  }

  /**
   * Return the status of this transmission as a  string.
   *
   * @return the status of the vehicle as a String.
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Get the current speed of the vehicle as a whole number.
   *
   * @return the current speed.
   */
  public int getSpeed() {
    return this.speed;
  }

  /**
   * Get the current gear of the vehicle as a whole number.
   *
   * @return the current gear.
   */
  public int getGear() {
    return this.gear;
  }

  /**
   * Increase the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be increased, then it should return an object with the
   * same speed as before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission increaseSpeed() {

    int temp = this.speed;
    this.speed = this.speed + 1;
    if (!checkSpeedForGear()) {
      if (checkSpeedLimit()) {
        this.status = "Cannot increase speed, increase gear first.";
      }
      this.speed = temp;
    } else {
      if (isRangeOfUpperAdjacentGear()) {
        this.status = "OK: you may increase the gear.";
      } else {
        this.status = "OK: everything is OK.";
      }

    }


    return this;
  }

  /**
   * Decrease the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be decreased, then it should return an object with the
   * same speed as before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission decreaseSpeed() {

    int temp = this.speed;
    this.speed = this.speed - 1;
    if (!checkSpeedForGear()) {
      if (checkSpeedLimit()) {
        this.status = "Cannot decrease speed, decrease gear first.";
      }
      this.speed = temp;
    } else {
      if (isRangeOfLowerAdjacentGear()) {

        this.status = "OK: you may decrease the gear.";

      } else {
        this.status = "OK: everything is OK.";
      }

    }

    return this;
  }

  /**
   * Increase the gear by one without changing speed and return the resulting transmission object.
   * If the gear cannot be increased, then it should return an object with the same speed as
   * before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission increaseGear() {
    int tempgear = this.gear;
    this.gear = this.gear + 1;
    if (!checkGearRange()) {
      this.gear = tempgear;
    } else {
      if (!checkSpeedForGear()) {
        this.status = "Cannot increase gear, increase speed first.";
        this.gear = tempgear;
      } else {
        this.status = "OK: everything is OK.";
      }

    }

    return this;
  }

  /**
   * Decrease the gear by one without changing speed and return the resulting transmission object.
   * If the gear cannot be decreased, then it should return an object with the same speed as
   * before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission decreaseGear() {

    int tempgear = this.gear;
    this.gear = this.gear - 1;
    if (!checkGearRange()) {
      this.gear = tempgear;
    } else {
      if (!checkSpeedForGear()) {
        this.status = "Cannot decrease gear, decrease speed first.";
        this.gear = tempgear;
      } else {
        this.status = "OK: everything is OK.";
      }

    }

    return this;
  }

  /**
   * This method checks whether the speed of the vehicle is within the range of the current gear.
   *
   * @return true if the speed is between the range of the gear.
   */
  private boolean checkSpeedForGear() {

    boolean a = true;
    for (Integer key : gearBox.keySet()) {
      if (this.gear == key) {
        if (this.speed < gearBox.get(key).get("low")) {
          this.status = "Cannot decrease speed. Reached minimum speed.";
          a = false;
        }

        if (this.speed > gearBox.get(key).get("high")) {
          this.status = "Cannot increase speed. Reached maximum speed.";
          a = false;
        }
        break;
      }

    }


    return a;

  }

  /**
   * This method is used to check whether the current gear is within the limits of minimum and
   * maximum gear.
   *
   * @return true if the gear is within the set range.
   */
  private boolean checkGearRange() {
    if (this.gear < this.minGear) {
      this.status = "Cannot decrease gear. Reached minimum gear.";

      return false;
    }
    if (this.gear > this.maxGear) {
      this.status = "Cannot increase gear. Reached maximum gear.";
      return false;
    }
    return true;
  }

  /**
   * This method checks if th vehicle id within the bounds of minimum speed and the maximum speed.
   *
   * @return true if the vehicle is within speed limit.
   */
  private boolean checkSpeedLimit() {
    return (this.speed >= this.minSpeed && this.speed <= this.maxSpeed);

  }

  /**
   * This method checks if the current speed of the vehicle is in the range of the next  higher
   * adjacent gear so as to decide if the gears can be changed or not.
   *
   * @return true if the current speed is within the range of the next higher adjacent gear.
   */
  private boolean isRangeOfUpperAdjacentGear() {

    int i = 1;
    for (Integer gearKey : gearBox.keySet()) {
      if (i == this.maxGear) {
        break;
      }

      i++;
      if (this.gear == gearKey && this.speed >= gearBox.get(gearKey + 1).get("low")
              && this.speed <= gearBox.get(gearKey).get("high")) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method checks if the current speed of the vehicle is in the range of the next  lower
   * adjacent gear so as to decide if the gears can be changed or not.
   *
   * @return true if the current speed is within the range of the next lower adjacent gear.
   */
  private boolean isRangeOfLowerAdjacentGear() {

    int i = 1;
    for (Integer gearKey : gearBox.keySet()) {

      if (i == 1) {
        i++;
        continue;
      }

      if (this.gear == gearKey && this.speed >= gearBox.get(gearKey).get("low")
              && this.speed <= gearBox.get(gearKey - 1).get("high")) {
        return true;
      }
    }
    return false;
  }


  /**
   * This method checks whether the lower speed of the vehicle is less than or equal to the high
   * speed of a particular gear.
   *
   * @param lx the lower speed limit of a particular gear
   * @param hx the higher speed limit of a particular gear
   * @return true if the lower speed limit is less than or equal to the higher speed limit
   */
  private boolean checklowAndHighSpeedForAGear(Integer lx, Integer hx) {
    return (lx <= hx);
  }

  /**
   * This method checks if the lower speed limit of the previous gear is strictly less than the
   * lower limit of the next higher gear.
   *
   * @param low1 lower speed limit of a gear
   * @param low2 lower speed limit of the next higher gear
   * @return true if the lower speed limit of a gear is strictly lower than the lower speed limit
   */
  private boolean checkForAdjacentLowSpeed(Integer low1, Integer low2) {
    return (low1 < low2);
  }

  /**
   * This method checks if the adjacent-gear ranges overlap (e.g. l2 may be between l1 and h1, etc.)
   * also the ranges cannot be non-overlapping (i.e. each speed is covered by a gear range).
   *
   * @param low1  lower speed limit of a particular gear
   * @param low2  lower speed limit of the next higher gear
   * @param high1 higher speed limit of the gear
   * @return boolean value based on if the above conditions are met.
   */
  private boolean checkForOverlappingAdjacentGear(Integer low1, Integer low2, Integer high1) {
    return ((low1 < low2) && (low2 <= high1));
  }


}
