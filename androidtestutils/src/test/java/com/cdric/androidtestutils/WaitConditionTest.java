package com.cdric.androidtestutils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WaitConditionTest {

    @Test
    public void waitConditionNominalCase() throws InterruptedException {
        assertTrue(WaitCondition.waitCondition(new ConditionFunction() {
            @Override
            public boolean checkCondition() {
                return true;
            }
        }, 1000, 100));
    }

    @Test
    public void waitConditionWithWaitNominalCase() throws InterruptedException {
        final int[] i = {0};
        assertTrue(WaitCondition.waitCondition(new ConditionFunction() {
            @Override
            public boolean checkCondition() {
                i[0]++;
                return i[0] > 5;
            }
        }, 100, 10));
    }

    @Test
    public void waitConditionWithWaitFalse() throws InterruptedException {
        final int[] i = {0};
        assertFalse(WaitCondition.waitCondition(new ConditionFunction() {
            @Override
            public boolean checkCondition() {
                i[0]++;
                return i[0] > 11;
            }
        }, 100, 10));
    }

}
