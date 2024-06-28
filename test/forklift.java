import Forklift.AutonomousForklift;
import Storage.Trailer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class forklift {
    AutonomousForklift autonomousForklift;

    @Given("A AutonomousForklift Object")
    public void aAutonomousForkliftObject() {
        System.out.println("Instantiate AutonomousForklift");
        autonomousForklift = new AutonomousForklift(new Trailer());
        System.out.println("Start AutonomousForklift");
        autonomousForklift.start();
    }

    @And("Accelerate the Forklift")
    public void accelerateTheForklift() {
        System.out.println("Accelerate AutonomousForklift");
        autonomousForklift.accelerate();
    }

    @And("Accelerate again")
    public void accelerateAgain() {
        System.out.println("Accelerate AutonomousForklift... vrooom");
        autonomousForklift.accelerate();
    }

    @And("Accelerate once again")
    public void accelerateOnceAgain() {
        System.out.println("Accelerate AutonomousForklift... vrooooooooooom");
        autonomousForklift.accelerate();
    }

    @And("decelerate the forklift")
    public void decelerateTheForklift() {
        System.out.println("Decelerate AutonomousForklift... nyoooooon");
        autonomousForklift.slowDown();
    }

    @And("decelerate the forklift once more")
    public void decelerateTheForkliftOnceMore() {
        System.out.println("Decelerate AutonomousForklift... nyooooooooooooooooooon");
        autonomousForklift.slowDown();
    }

    @Then("Forklift should be standing still")
    public void forkliftShouldBeStandingStill() {
        System.out.println("Stoppe AutonomousForklift");
        autonomousForklift.stop();
    }
}