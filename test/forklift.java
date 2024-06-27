import Forklift.AutonomousForklift;
import Storage.Trailer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class forklift {
    AutonomousForklift autonomousForklift;

    @Given("A AutonomousForklift Object")
    public void aAutonomousForkliftObject() {
        autonomousForklift = new AutonomousForklift(new Trailer());
        autonomousForklift.start();
    }

    @And("Accelerate the Forklift")
    public void accelerateTheForklift() {
        autonomousForklift.accelerate();
    }

    @And("Accelerate again")
    public void accelerateAgain() {
        autonomousForklift.accelerate();
    }

    @And("Accelerate once again")
    public void accelerateOnceAgain() {
        autonomousForklift.accelerate();
    }

    @And("decelerate the forklift")
    public void decelerateTheForklift() {
        autonomousForklift.slowDown();
    }

    @And("decelerate the forklift once more")
    public void decelerateTheForkliftOnceMore() {
        autonomousForklift.slowDown();
    }

    @Then("Forklift should be standing still")
    public void forkliftShouldBeStandingStill() {
        autonomousForklift.stop();
    }
}