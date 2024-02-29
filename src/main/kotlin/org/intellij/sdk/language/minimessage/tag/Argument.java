package org.intellij.sdk.language.minimessage.tag;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.annotation.AnnotationHolder;
import org.codehaus.plexus.util.cli.Arg;
import org.intellij.sdk.language.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Argument {

    protected String name;
    protected List<Argument> children;
    protected List<LookupElement> completions;
    protected boolean optional = false;

    public Argument(String name) {
        this(name, new ArrayList<>());
    }

    public Argument(String name, List<Argument> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public List<Argument> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Arg(" + name + ")";
    }

    public abstract boolean check(String arg);

    public Argument argument(Argument argument) {
        children.add(argument);
        return this;
    }

    public Argument optional() {
        this.optional = true;
        return this;
    }

    public boolean isOptional() {
        return optional;
    }

    public static Argument literalArgument(String literal) {
        return new Argument(literal) {

            @Override
            public boolean check(String arg) {
                return arg.equalsIgnoreCase(literal);
            }
        };
    }

    public static Argument textArgument() {
        return new Argument("<text>") {
            @Override
            public boolean check(String arg) {
                return true;
            }
        };
    }

    public static Argument commandArgument() {
        return new Argument("<command>") {
            @Override
            public boolean check(String arg) {
                return arg.startsWith("/");
            }
        };
    }

    public static Argument fileArgument() {
        return new Argument("<file>") {
            @Override
            public boolean check(String arg) {
                return true;
            }
        };
    }

    public static Argument urlArgument() {
        return new Argument("<url>") {
            private static final Pattern URL_PATTERN = Pattern.compile("((https?://)?www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)");

            @Override
            public boolean check(String arg) {
                return URL_PATTERN.matcher(arg).matches();
            }
        };
    }

    public static Argument boolArgument() {
        return new Argument("true|false") {
            @Override
            public boolean check(String arg) {
                return arg.toLowerCase().matches("true|false");
            }
        };
    }

    public static Argument colorArgument() {
        return new Argument("<color>") {
            private static final Pattern HEX_REGEX = Pattern.compile("#[0-9a-f]{6}");
            @Override
            public boolean check(String arg) {
                return Constants.COLORS.containsKey(arg) || HEX_REGEX.matcher(arg).matches();
            }
        };
    }

    public static Argument miniMessageArgument() {
        return new Argument("<minimessage>") {
            @Override
            public boolean check(String arg) {
                return true;
            }
        };
    }

    public static Argument selectorArgument() {
        return new Argument("<selector>") {
            @Override
            public boolean check(String arg) {
                return arg.matches("@\\[([a-zA-Z0-9_-]+=.+)*]");
            }
        };
    }

    public static Argument intArgument(int from) {
        return intArgument(from, Integer.MAX_VALUE);
    }

    public static Argument intArgument() {
        return new Argument("<integer>") {
            @Override
            public boolean check(String arg) {
                try {
                    Integer.parseInt(arg);
                    return true;
                } catch (Throwable t) {
                    return false;
                }
            }
        };
    }

    public static Argument intArgument(int from, int to) {
        return new Argument("<integer: " + from + ":" + to + ">") {
            @Override
            public boolean check(String arg) {
                try {
                    var i = Integer.parseInt(arg);
                    return i >= from && i < to;
                } catch (Throwable t) {
                    return false;
                }
            }
        };
    }

    public static Argument numberArgument() {
        return new Argument("<number>") {
            @Override
            public boolean check(String arg) {
                try {
                    Double.parseDouble(arg);
                    return true;
                } catch (Throwable t) {
                    return false;
                }
            }
        };
    }

    public static Argument anyFollowingArguments(String as) {
        return new Argument(as) {
            @Override
            public boolean check(String arg) {
                return true;
            }
        };
    }

    public static Argument keyArgument() {
        return new Argument("<key>") {
            @Override
            public boolean check(String arg) {
                return arg.matches("[a-z0-9._-]");
            }
        };
    }

    public static Argument itemArgument() {
        return new Argument("<item>") {
            @Override
            public boolean check(String arg) {
                return true; //TODO _type_[:_count_[:tag]]
            }
        };
    }

    public static Argument entityArgument() {
        return new Argument("<entity>") {
            @Override
            public boolean check(String arg) {
                return true; //TODO _type_:_uuid_[:_name_]
            }
        };
    }
}
