package vehicle;

/**
 * This interface represents a set of operations that simulates the operations of a vehicle with
 * manual transmission.Every gear works within a range of speeds. If the speed is decreased to be
 * lower than this range without decreasing the gear, then the vehicle “knocks” at this gear. If the
 * gas pedal is pushed beyond the capacity of this gear, the engine “revvs” but the speed does not
 * increase. the Initial speed of the Vehicle should be at 0 and the gear at 1. when the vehicle is
 * at the initial state the speed of the vehicle cannot be decreased. To change the Gear, the speed
 * needs to be increased to the lower limit of the next gear.
 * <ul>
 * <li>
 * An object should start in the first gear with zero speed and a status of “OK: everything is
 * OK.”.
 * </li>
 * </ul>
 */

public interface ManualTransmission {

  /**
   * Return the status of this transmission as a formatted string. The format of the string is as
   * follows:
   * <ul>
   * <li>
   * “OK: everything is OK.”
   * </li>
   * <code>: this is the status if the speed was changed successfully without changing gears,
   * or the gear was changed successfully without changing speed.</code>
   * <li>
   * "OK: you may increase the gear."
   * </li><code>: this is the status if the speed was increased successfully,
   * but it is now within the range of the next gear.</code>
   * <li>
   * "OK: you may decrease the gear."
   * </li><code>: this is the status if the speed was decreased successfully,
   * but it is now within the range of the previous gear.</code>
   * <li>
   * “Cannot increase speed, increase gear first.”
   * </li><code>: this is the status if the speed cannot be increased
   * more unless the gear is increased first. This implies that the intended speed is too high for
   * the current gear.</code>
   * <li>
   * "Cannot decrease speed, decrease gear first."
   * </li><code>: this is the status if the speed cannot be decreased
   * more unless the gear is decreased first. This implies that the intended speed is too low for
   * the current gear.</code>
   * <li>
   * “Cannot increase gear, increase speed first.”
   * </li><code>: this is the status if the gear cannot be increased more
   * unless the speed is increased first. This implies that the current speed will be too low for
   * the next gear.</code>
   * <li>
   * “Cannot decrease gear, decrease speed first.”
   * </li><code>: this is the status if the gear cannot be decreased more
   * unless the speed is decreased first. This implies that the current speed will be too high for
   * the previous gear.</code>
   * <li>
   * "Cannot increase speed. Reached maximum speed."
   * </li><code>: this is the status if the speed cannot be increased as
   * it will go beyond the speed limit of the vehicle</code>
   * <li>
   * “Cannot decrease speed. Reached minimum speed.”
   * </li><code>: this is the status if the speed cannot be decreased as it is already 0</code>
   * <li>
   * “Cannot increase gear. Reached maximum gear.”
   * </li><code>: this is the status if the gear
   * cannot be increased as it is already in gear 5</code>
   * <li>
   * “Cannot decrease gear. Reached minimum gear.”
   * </li><code>: this is the status if the gear
   * cannot be decreased as it is already in gear 1</code>
   *
   *
   * </ul>
   *
   * @return the status of the transmission as a String.
   */
  public String getStatus();

  /**
   * Get the current speed of the vehicle as a whole number.
   *
   * @return the current speed.
   */
  public int getSpeed();

  /**
   * Get the current gear of the vehicle as a whole number.
   *
   * @return the current gear.
   */
  public int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be increased, then it should return an object with the
   * same speed as before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be decreased, then it should return an object with the
   * same speed as before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one without changing speed and return the resulting transmission object.
   * If the gear cannot be increased, then it should return an object with the same speed as
   * before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission increaseGear();

  /**
   * Decrease the gear by one without changing speed and return the resulting transmission object.
   * If the gear cannot be decreased, then it should return an object with the same speed as
   * before.
   *
   * @return Object of ManualTransmission.
   */
  public ManualTransmission decreaseGear();


}
