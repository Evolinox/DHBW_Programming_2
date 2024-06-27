Feature: forklift
  Testing some methods of the Forklift Object

  Scenario: This tests the acceleration and slowdown functionality of the Forklift Object
    Given A AutonomousForklift Object
    And Accelerate the Forklift
    And Accelerate again
    And Accelerate once again
    And decelerate the forklift
    And decelerate the forklift once more
    Then Forklift should be standing still