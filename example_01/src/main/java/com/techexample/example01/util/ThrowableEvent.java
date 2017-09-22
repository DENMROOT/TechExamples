package com.techexample.example01.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Denys_Makarov on 9/22/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ThrowableEvent extends ApplicationEvent {
    private final Throwable throwable;
}