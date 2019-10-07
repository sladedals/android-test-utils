package com.flowbird.testutils;

public class WaitCondition {

    public static final int DEFAULT_GRANULARITY = 100;
    public static final int DEFAULT_DURATION = 1000;

    /**
     * waitCondition is a method defined to regularly check the value of a condition during a defined @durationg -T
     *
     * @param conditionFunction : this is the callback used to check the result of the condition, should return True when condition is verified
     * @param duration : global duration in milliseconds
     * @param granularity : time to wait between to successive check of the condition
     * @return the (last) result of the condition
     * @throws InterruptedException
     */
    public static boolean waitCondition(ConditionFunction conditionFunction, long duration, int granularity) throws InterruptedException {
        boolean conditionResult = conditionFunction.checkCondition();
        long startWait = System.currentTimeMillis();
        while((conditionResult != true) && ((startWait + duration) > System.currentTimeMillis())){
            Thread.sleep(granularity);
            conditionResult = conditionFunction.checkCondition();
        }
        return conditionResult;
    }

    public static boolean waitCondition(ConditionFunction conditionFunction, long duration) throws InterruptedException {
        return waitCondition(conditionFunction, duration, DEFAULT_GRANULARITY);
    }

    public static boolean waitCondition(ConditionFunction conditionFunction) throws InterruptedException {
        return waitCondition(conditionFunction, DEFAULT_DURATION, DEFAULT_GRANULARITY);
    }
}
