/*
 *     Copyright 2015-2018 Austin Keener & Michael Ritter & Florian Spieß
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.core.utils;

import edu.umd.cs.findbugs.annotations.DefaultAnnotationForParameters;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.Collection;

@ParametersAreNullableByDefault
@DefaultAnnotationForParameters(Nullable.class)
public class Checks
{

    public static void check(final boolean expression, final String message)
    {
        if (!expression)
            throw new IllegalArgumentException(message);
    }

    public static void check(final boolean expression, @NonNull final String message, final Object... args)
    {
        if (!expression)
            throw new IllegalArgumentException(String.format(message, args));
    }

    public static void check(final boolean expression, @NonNull final String message, final Object arg)
    {
        if (!expression)
            throw new IllegalArgumentException(String.format(message, arg));
    }

    public static void notNull(final Object argument, @NonNull final String name)
    {
        if (argument == null)
            throw new IllegalArgumentException(name + " may not be null");
    }

    public static void notEmpty(final CharSequence argument, @NonNull final String name)
    {
        notNull(argument, name);
        if (Helpers.isEmpty(argument))
            throw new IllegalArgumentException(name + " may not be empty");
    }

    public static void notBlank(final CharSequence argument, @NonNull final String name)
    {
        notNull(argument, name);
        if (Helpers.isBlank(argument))
            throw new IllegalArgumentException(name + " may not be blank");
    }

    public static void noWhitespace(final CharSequence argument, @NonNull final String name)
    {
        notNull(argument, name);
        if (Helpers.containsWhitespace(argument))
            throw new IllegalArgumentException(name + " may not contain blanks");
    }

    public static void notEmpty(final Collection<?> argument, @NonNull final String name)
    {
        notNull(argument, name);
        if (argument.isEmpty())
            throw new IllegalArgumentException(name + " may not be empty");
    }

    public static void notEmpty(final Object[] argument, @NonNull final String name)
    {
        notNull(argument, name);
        if (argument.length == 0)
            throw new IllegalArgumentException(name + " may not be empty");
    }

    public static void noneNull(final Collection<?> argument, @NonNull final String name)
    {
        notNull(argument, name);
        argument.forEach(it -> notNull(it, name));
    }

    public static void noneNull(final Object[] argument, @NonNull final String name)
    {
        notNull(argument, name);
        for (Object it : argument) {
            notNull(it, name);
        }
    }

    public static <T extends CharSequence> void noneEmpty(final Collection<T> argument, @NonNull final String name)
    {
        notNull(argument, name);
        argument.forEach(it -> notEmpty(it, name));
    }

    public static <T extends CharSequence> void noneBlank(final Collection<T> argument, @NonNull final String name)
    {
        notNull(argument, name);
        argument.forEach(it -> notBlank(it, name));
    }

    public static <T extends CharSequence> void noneContainBlanks(final Collection<T> argument, @NonNull final String name)
    {
        notNull(argument, name);
        argument.forEach(it -> noWhitespace(it, name));
    }

    public static void positive(final int n, @NonNull final String name)
    {
        if (n <= 0)
            throw new IllegalArgumentException(name + " may not be negative or zero");
    }

    public static void positive(final long n, @NonNull final String name)
    {
        if (n <= 0)
            throw new IllegalArgumentException(name + " may not be negative or zero");
    }

    public static void notNegative(final int n, @NonNull final String name)
    {
        if (n < 0)
            throw new IllegalArgumentException(name + " may not be negative");
    }

    public static void notNegative(final long n, @NonNull final String name)
    {
        if (n < 0)
            throw new IllegalArgumentException(name + " may not be negative");
    }

}
