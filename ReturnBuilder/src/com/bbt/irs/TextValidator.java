
package com.bbt.irs;

/**
 *
 * @author opeyemi
 */
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

import javafx.beans.NamedArg;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class TextValidator {

    private static final String CURRENCY_SYMBOL   = DecimalFormatSymbols.getInstance().getCurrencySymbol();
    private static final char   DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance().getDecimalSeparator();

    private final Pattern       INPUT_PATTERN;

    public TextValidator(@NamedArg("modus") ValidationModus modus, @NamedArg("countOf") int countOf) {
        this(modus.createPattern(countOf));
    }

    public TextValidator(@NamedArg("regex") String regex) {
        this(Pattern.compile(regex));
    }

    public TextValidator(Pattern inputPattern) {
        INPUT_PATTERN = inputPattern;
    }

    public static TextValidator maxFractionDigits(int countOf) {
        return new TextValidator(maxFractionPattern(countOf));
    }

    public static TextValidator maxIntegers(int countOf) {
        return new TextValidator(maxIntegerPattern(countOf));
    }

    public static TextValidator integersOnly() {
        return new TextValidator(integersOnlyPattern());
    }

    public TextFormatter<Object> getFormatter() {
        return new TextFormatter<>(this::validateChange);
    }

    private Change validateChange(Change c) {
        if (validate(c.getControlNewText())) {
            return c;
        }
        return null;
    }

    public boolean validate(String input) {
        return INPUT_PATTERN.matcher(input).matches();
    }

    private static Pattern maxFractionPattern(int countOf) {
        return Pattern.compile("\\d*(\\" + DECIMAL_SEPARATOR + "\\d{0," + countOf + "})?");
    }

    private static Pattern maxCurrencyFractionPattern(int countOf) {
        return Pattern.compile("^\\" + CURRENCY_SYMBOL + "?\\s?\\d*(\\" + DECIMAL_SEPARATOR + "\\d{0," + countOf + "})?\\s?\\" +
                CURRENCY_SYMBOL + "?");
    }

    private static Pattern maxIntegerPattern(int countOf) {
        return Pattern.compile("\\d{0," + countOf + "}");
    }

    private static Pattern integersOnlyPattern() {
        return Pattern.compile("\\d*");
    }

    public enum ValidationModus {

        MAX_CURRENCY_FRACTION_DIGITS {
            @Override
            public Pattern createPattern(int countOf) {
                return maxCurrencyFractionPattern(countOf);
            }
        },

        MAX_FRACTION_DIGITS {
            @Override
            public Pattern createPattern(int countOf) {
                return maxFractionPattern(countOf);
            }
        },
        MAX_INTEGERS {
            @Override
            public Pattern createPattern(int countOf) {
                return maxIntegerPattern(countOf);
            }
        },

        INTEGERS_ONLY {
            @Override
            public Pattern createPattern(int countOf) {
                return integersOnlyPattern();
            }
        };

        public abstract Pattern createPattern(int countOf);
    }

}

