import org.junit.Before;
import org.junit.Test;

import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * This test class is used  to  test the ManualTransmission operations.
 */
public class RegularManualTransmissionTest {
  RegularManualTransmission mtr;


  /**
   * This method is used to create an Object of RegularManualTransmission.
   */
  @Before
  public void setUp() {
    mtr = new RegularManualTransmission(0, 5, 4, 10, 9,
            15, 14, 20, 19, 25);

  }

  /**
   * This test case is to test the initiated speed.
   */
  @Test
  public void checkTheInitialSpeed() {

    assertEquals(0, mtr.getSpeed());

  }

  /**
   * This test case is to test the initiated gear.
   */
  @Test
  public void checkTheInitialGear() {

    assertEquals(1, mtr.getGear());

  }

  /**
   * This test case is to increase speed by 1.
   */
  @Test
  public void checkTheIncreaseSpeedByOne() {

    mtr.increaseSpeed();
    assertEquals(1, mtr.getSpeed());

  }

  /**
   * This test case is to decrease speed by 1.
   */
  @Test
  public void checkTheDecreaseSpeedByOne() {
    mtr.increaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(0, mtr.getSpeed());

  }

  /**
   * This test case is to increase speed multiple times.
   */
  @Test
  public void CheckIncreaseSpeedMultipleTimes() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    assertEquals(5, mtr.getSpeed());

  }

  /**
   * This test case is to decrease speed multiple times.
   */
  @Test
  public void checkDecreaseSpeedMultipleTimes() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();

    assertEquals(2, mtr.getSpeed());

  }


  /**
   * This test case is to check decrease the speed at 0 and check if the speed remains 0.
   */
  @Test
  public void checkIfTheSpeedRemainsAtZero() {

    mtr.decreaseSpeed();
    assertEquals(0, mtr.getSpeed());

  }

  /**
   * This test case is to if its possible to decrease the speed when the object is instantiated.
   */
  @Test
  public void checkTheStatusWhenSpeedIsDecreasedAtZero() {

    mtr.decreaseSpeed();

    assertEquals("Cannot decrease speed. Reached minimum speed.", mtr.getStatus());
  }

  /**
   * This test case is to check when the speed is decreased multiple times.
   */
  @Test
  public void checkWhenTheSpeedIsDecreasedMultipleTimesAtZero() {

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(0, mtr.getSpeed());
    assertEquals("Cannot decrease speed. Reached minimum speed.", mtr.getStatus());

  }


  /**
   * This test case is to check if the object can be instantiated with low speed less than 0.
   */
  @Test
  public void checkTheMinSpeedWhenLessThanZero() {

    try {
      RegularManualTransmission mtr1 = new RegularManualTransmission(-1, 5, 4, 10,
              9, 15, 14, 20, 19, 25);
      fail();
    } catch (Exception e) {
      assertEquals("The low speed of gear 1 should be 0.", e.getMessage());
    }
  }

  /**
   * This test case is to check if the object can be instantiated with low speed greater than 0.
   */
  @Test
  public void checkTheMinSpeedWhenMorethanZero() {

    try {
      RegularManualTransmission mtr2 = new RegularManualTransmission(1, 5, 4, 10,
              9, 15, 14, 20, 19, 25);
      fail();
    } catch (Exception e) {
      assertEquals("The low speed of gear 1 should be 0.", e.getMessage());
    }
  }


  /**
   * This test case is to check if the object can be instantiated with low speed greater than the
   * high speed of a particular gear.
   */
  @Test
  public void checkObjectLowSpeedGreaterThanHigh() {

    try {
      RegularManualTransmission mtr3 = new RegularManualTransmission(0, 5, 4, 10,
              9, 8, 14, 20, 19, 25);
      fail();
    } catch (Exception e) {
      assertEquals("The low speed and high speed for "
              + "a particular gear is Invalid.", e.getMessage());
    }
  }

  /**
   * This test case is to check if the object can be instantiated with low speed less than the lower
   * speed of the previous gear.
   */
  @Test
  public void checkLowerSpeedLessThanPreviousGear() {

    try {
      RegularManualTransmission mtr4 = new RegularManualTransmission(0, 5, 4, 10,
              3, 8, 14, 20, 19, 25);
      fail();
    } catch (Exception e) {
      assertEquals("The low speed of the next gear cannot be less "
              + "than low speed of the previous gear.", e.getMessage());
    }
  }

  /**
   * This test case is to check if the gears are overlapping.
   */
  @Test
  public void checkGearsOverlaps() {

    try {
      RegularManualTransmission mtr4 = new RegularManualTransmission(0, 5, 4, 10,
              11, 15, 14, 20, 19, 25);
      fail();
    } catch (Exception e) {
      assertEquals("the adjacent speed for a particular "
              + "gear is non overlapping.", e.getMessage());
    }
  }


  /**
   * This test case is to check when the gear is decreased immediately after the object is
   * instantiated.
   */
  @Test
  public void checkToDecreaseGearAfterInitialization() {

    mtr.decreaseGear();
    assertEquals(1, mtr.getGear());
    assertEquals("Cannot decrease gear. Reached minimum gear.", mtr.getStatus());
  }

  /**
   * This test case is to check when the gear is decreased immediately after the object is
   * instantiated.
   */
  @Test
  public void checkToIncreaseGearAfterInitialization() {

    mtr.increaseGear();
    assertEquals(1, mtr.getGear());
    assertEquals("Cannot increase gear, increase speed first.", mtr.getStatus());

  }


  /**
   * This test case is to check the status when speed is increased multiple times.
   */
  @Test
  public void checkStatusAfterIncreasingSpeedMultipleTimes() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    assertEquals("OK: everything is OK.", mtr.getStatus());

  }

  /**
   * This test case is to test the status when the speed is the range of next Gear.
   */
  @Test
  public void checkForStatusWhenSpeedIsInNextGearRange() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    assertEquals("OK: you may increase the gear.", mtr.getStatus());

  }

  /**
   * This test case is to test the status when the speed is the range of previous Gear.
   */
  @Test
  public void checkForStatusWhenSpeedIsInPreviousGearRange() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseGear();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();


    assertEquals("OK: you may decrease the gear.", mtr.getStatus());

  }

  /**
   * This test case is check if the speed is increased more than the speed limit of a particular
   * gear.
   */
  @Test
  public void checkWhenSpeedIsIncreasedMoreThanGearLimit() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();


    assertEquals("Cannot increase speed, increase gear first.", mtr.getStatus());
    assertEquals(5, mtr.getSpeed());

  }

  /**
   * This test case is test increasing Gear.
   */
  @Test
  public void checkIncreaseGear() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    assertEquals(2, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

  }

  /**
   * This Test is to check if the speed is decreased beyond the speed limit of a particular gear.
   */
  @Test
  public void checkWhenSpeedIsDecreasedLessThanGearLimit() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseGear();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();

    assertEquals("Cannot decrease speed, decrease gear first.", mtr.getStatus());
    assertEquals(4, mtr.getSpeed());

  }

  /**
   * This test is to check if the gear can be decreased when the speed is not in the range of the
   * previous gear.
   */
  @Test
  public void checkWhenTheGearIsDecreasedBeforeThePreviousGearRange() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseGear();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.decreaseGear();


    assertEquals("Cannot decrease gear, decrease speed first.", mtr.getStatus());
    assertEquals(2, mtr.getGear());

  }

  /**
   * This test is to check if the gear can be decreased when the speed is in the range of the
   * previous gear.
   */
  @Test
  public void normalDecreaseOfGear() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseGear();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();

    mtr.decreaseGear();


    assertEquals("OK: everything is OK.", mtr.getStatus());
    assertEquals(1, mtr.getGear());
    assertEquals(5, mtr.getSpeed());

  }

  /**
   * This test is to check the speed and gear is increased beyond max seep and gear respectively.
   */
  @Test
  public void checkMaxSpeedAndGear() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();


    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();


    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    assertEquals("Cannot increase speed. Reached maximum speed.", mtr.getStatus());
    assertEquals(25, mtr.getSpeed());

    mtr.increaseGear();

    assertEquals("Cannot increase gear. Reached maximum gear.", mtr.getStatus());
    assertEquals(25, mtr.getSpeed());

  }

  /**
   * This test is to check if the gear can be increased beyond 2nd gear and also check the speed.
   */
  @Test
  public void increaseGearToThirdGear() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    assertEquals(3, mtr.getGear());
    assertEquals(10, mtr.getSpeed());
    assertEquals("OK: everything is OK.", mtr.getStatus());
  }

  /**
   * This test is to check if the gear can be increased beyond 3rd gear and also check the speed.
   */
  @Test
  public void increaseGearToFourthGear() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    assertEquals(4, mtr.getGear());
    assertEquals(15, mtr.getSpeed());
    assertEquals("OK: everything is OK.", mtr.getStatus());

  }

  /**
   * This test is to check if the gear can be increased beyond 4th gear and also check the speed.
   */
  @Test
  public void increaseGearToFifthGear() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();


    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    assertEquals(5, mtr.getGear());
    assertEquals(20, mtr.getSpeed());
    assertEquals("OK: everything is OK.", mtr.getStatus());
  }

  /**
   * This test is to check if the speed can be increased to max speed.
   */
  @Test
  public void reachingMaxSpeed() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();


    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();


    assertEquals(5, mtr.getGear());
    assertEquals(25, mtr.getSpeed());
    assertEquals("OK: everything is OK.", mtr.getStatus());
  }

  /**
   * This test is to check if the speed and gear can be decreased for all gears.
   */
  @Test
  public void checkDecreaseSpeedAndGearForAllGears() {

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();


    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.increaseGear();

    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();
    mtr.increaseSpeed();

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(22, mtr.getSpeed());
    assertEquals(5, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(20, mtr.getSpeed());
    assertEquals(5, mtr.getGear());
    assertEquals("OK: you may decrease the gear.", mtr.getStatus());
    mtr.decreaseSpeed();
    mtr.decreaseGear();
    assertEquals(19, mtr.getSpeed());
    assertEquals(4, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(16, mtr.getSpeed());
    assertEquals(4, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(14, mtr.getSpeed());
    assertEquals(4, mtr.getGear());
    assertEquals("OK: you may decrease the gear.", mtr.getStatus());
    mtr.decreaseGear();
    assertEquals(14, mtr.getSpeed());
    assertEquals(3, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(11, mtr.getSpeed());
    assertEquals(3, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(9, mtr.getSpeed());
    assertEquals(3, mtr.getGear());
    assertEquals("OK: you may decrease the gear.", mtr.getStatus());
    mtr.decreaseGear();
    assertEquals(9, mtr.getSpeed());
    assertEquals(2, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(6, mtr.getSpeed());
    assertEquals(2, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    assertEquals(4, mtr.getSpeed());
    assertEquals(2, mtr.getGear());
    assertEquals("OK: you may decrease the gear.", mtr.getStatus());
    mtr.decreaseGear();
    assertEquals(4, mtr.getSpeed());
    assertEquals(1, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();
    mtr.decreaseSpeed();

    assertEquals(1, mtr.getSpeed());
    assertEquals(1, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

    mtr.decreaseSpeed();

    assertEquals(0, mtr.getSpeed());
    assertEquals(1, mtr.getGear());
    assertEquals("OK: everything is OK.", mtr.getStatus());

    mtr.decreaseSpeed();
    mtr.decreaseSpeed();

    assertEquals(0, mtr.getSpeed());
    assertEquals(1, mtr.getGear());
    assertEquals("Cannot decrease speed. Reached minimum speed.", mtr.getStatus());


  }

  /**
   * This test case is to check if the object can be instantiated with value greater than max value
   * of integer.
   */
  @Test
  public void checkSpeedLimitOverflow() {

    try {
      RegularManualTransmission mtr4 = new RegularManualTransmission(0, 5, 4, 10,
              3, 8, 14, 20, 19, Integer.MAX_VALUE + 345);
      fail();
    } catch (Exception e) {
      assertEquals("The low speed and high speed for a"
              + " particular gear is Invalid.", e.getMessage());
    }
  }


}