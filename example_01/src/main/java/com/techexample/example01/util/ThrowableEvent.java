package com.techexample.example01.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Throwable event class
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ThrowableEvent extends ApplicationEvent {
    private final Throwable throwable;
}