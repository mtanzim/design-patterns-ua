public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {

    OldCoffeeMachine oldCoffeeMachine;

    public CoffeeTouchscreenAdapter(OldCoffeeMachine oldCoffeeMachine) {
        this.oldCoffeeMachine = oldCoffeeMachine;
    }

    public void chooseFirstSelection() {
        oldCoffeeMachine.selectA();
    }

    public void chooseSecondSelection() {
        oldCoffeeMachine.selectB();

    }

}
