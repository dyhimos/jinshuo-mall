package com.jinshuo.core.model;

import com.jinshuo.core.exception.JsException;

/**
 * @Classname AssertionConcern
 * @Description TODO
 * @Date 2019/6/16 20:42
 * @Created by dyh
 */
public class AssertionConcern {

    protected AssertionConcern() {
        super();
    }

    protected void assertArgumentEquals(Object anObject1, Object anObject2, int retCode, String aMessage) {
        if (!anObject1.equals(anObject2)) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentFalse(boolean aBoolean,  int retCode,String aMessage) {
        if (aBoolean) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentLength(String aString, int aMaximum, int retCode,String aMessage) {
        int length = aString.trim().length();
        if (length > aMaximum) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentLength(String aString, int aMinimum, int aMaximum, int retCode, String aMessage) {
        int length = aString.trim().length();
        if (length < aMinimum || length > aMaximum) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentNotEmpty(String aString, int retCode, String aMessage) {
        if (aString == null || aString.trim().isEmpty()) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentNotEquals(Object anObject1, Object anObject2, int retCode, String aMessage) {
        if (anObject1.equals(anObject2)) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentNotNull(Object anObject, int retCode, String aMessage) {
        if (anObject == null) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentRange(double aValue, double aMinimum, double aMaximum,  int retCode, String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentRange(float aValue, float aMinimum, float aMaximum, int retCode,String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentRange(int aValue, int aMinimum, int aMaximum, int retCode,String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentRange(long aValue, long aMinimum, long aMaximum, int retCode,String aMessage) {
        if (aValue < aMinimum || aValue > aMaximum) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertArgumentTrue(boolean aBoolean, int retCode,String aMessage) {
        if (!aBoolean) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertStateFalse(boolean aBoolean, int retCode,String aMessage) {
        if (aBoolean) {
            throw new JsException(aMessage,retCode);
        }
    }

    protected void assertStateTrue(boolean aBoolean, int retCode,String aMessage) {
        if (!aBoolean) {
            throw new JsException(aMessage,retCode);
        }
    }
}
